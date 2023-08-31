package Program.Lab_1.Lab_4;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.text.ParseException;
import java.util.Date;

public class MyFormatData {
    public MyFormatData() {}
    public void saveTo(String filename) throws TransformerException, ParserConfigurationException, IOException {
        int indOfBooks=0;
        MyXmlModel myXmlModel=new MyXmlModel();
        Element root = myXmlModel.addRootElement("books");
        while (indOfBooks<Gui.catalog.size()) {
            Element book = myXmlModel.addElement("book");
            book.appendChild(myXmlModel.addTitle(Gui.catalog.get(indOfBooks).getTitle()));
            book.appendChild(myXmlModel.addAuthor(Gui.catalog.get(indOfBooks).getAuthorName()));
            book.appendChild(myXmlModel.addAnnotation(Gui.catalog.get(indOfBooks).getAnnotation()));
            book.appendChild(myXmlModel.addISBN(Gui.catalog.get(indOfBooks).getISBN()));
            book.appendChild(myXmlModel.addPublicationDate(Gui.catalog.get(indOfBooks).getPublicationDate()));
            indOfBooks++;
        }
            myXmlModel.getDocument().appendChild(root);
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            var transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.transform(new DOMSource(myXmlModel.getDocument()), new StreamResult(new File(filename)));
    }
    public static void loadFrom(String filename) throws ParserConfigurationException, SAXException, IOException, ParseException {

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(filename);

        Element root = doc.getDocumentElement();
        var books = root.getChildNodes();
        for (int i=0;i<books.getLength();i++){
            Node book = books.item(i);
            if (book.getNodeName().equals("book")) {
                Book b = new Book();
                for (int j=0;j<book.getChildNodes().getLength();j++){
                    Node node = book.getChildNodes().item(j);
                    if (node.getNodeName().equals("Title")) {
                        b.setTitle(node.getTextContent());
                    }
                    if (node.getNodeName().equals("Author")) {
                        b.setAuthorName(node.getTextContent());
                    }
                    if (node.getNodeName().equals("Annotation")) {
                        b.setAnnotation(node.getTextContent());
                    }
                    if (node.getNodeName().equals("ISBN")) {
                       b.setISBN(node.getTextContent());
                    }
                    if (node.getNodeName().equals("PublicationDate")) {
                        b.setPublicationDate(Book.getDate(node.getTextContent()));
                    }
                }
                Gui.catalog.add(b);
                Gui.DataArea.setList();
            }
        }
    }

    class MyXmlModel implements Serializable {
        Document doc;
        Element root;
        public MyXmlModel() throws ParserConfigurationException {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = dbFactory.newDocumentBuilder();
            doc = docBuilder.newDocument();
            root= doc.createElement("books");
        }
        public Node getDocument() {
            return doc;
        }
        public Element addRootElement(String name) {
            root=doc.createElement(name);
            return root;
        }
        public Element addElement(String book) {
            Element element=doc.createElement(book);
            root.appendChild(element);
            return element;
        }
        public Element addTitle(String value) {
            Element element = doc.createElement("Title");
            element.setTextContent(value);
            return element;
        }
        public Element addAuthor(String value) {
            Element element = doc.createElement("Author");
            element.setTextContent(value);
            return element;
        }
        public Element addAnnotation(String value) {
            Element element = doc.createElement("Annotation");
            element.setTextContent(value);
            return element;
        }
        public Element addISBN(String value) {
            Element element = doc.createElement("ISBN");
            element.setTextContent(value);
            return element;
        }
        public Element addPublicationDate(Date value) {
            Element element = doc.createElement("PublicationDate");
            element.setTextContent(Book.dateToString(value));
            return element;
        }
    }
}
