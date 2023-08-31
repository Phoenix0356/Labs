package Program.Lab_1.Lab_4;

import org.xml.sax.SAXException;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;

import static Program.Lab_1.Lab_4.Gui.DataArea.setList;
import static Program.Lab_1.Lab_4.Gui.DetailArea.getText;


public class Gui extends JPanel {
    static int index=-1;
    static int indexForFilterCatalog=-1;
    static boolean isFiltering=false;
    public static ArrayList<Book> catalog=new ArrayList<>();
    static boolean[] ind;
    static JList<String> list=new JList<>();
    static JList<String> listOfF = new JList<>();
    static DefaultListModel<String> model=new DefaultListModel<>();
    static DefaultListModel<String> modelOfF = new DefaultListModel<>();


    public static class DataArea extends JPanel {
        public DataArea() {
            setList();
            this.add(list);
        }

        public static void setList() {
            list.setModel(model);
            model.clear();
            list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            for (int i = 0; i < catalog.size(); i++) {
                model.addElement(catalog.get(i).title);
            }
        }

        //set listOfF
        public static void setList(JList<String> list, DefaultListModel<String> model) {
            list.setModel(model);
            model.clear();
            list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            for (int i = 0; i < catalog.size(); i++) {
                if (ind[i]) {
                    model.addElement(catalog.get(i).title);
                }
            }
        }
    }

    public static class ButtonArea extends Box{
        public ButtonArea(){
            super(BoxLayout.Y_AXIS);
            this.setPanel();
        }
        public void setPanel(){
            JLabel label = new JLabel("Editing options");;
            this.add(label);
            this.add(Box.createVerticalGlue());

            JButton addBook=new JButton("add");
            this.add(addBook);
            this.add(Box.createVerticalGlue());
            addBook.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    MyDialog dialogForAdd = new MyDialog();
                    dialogForAdd.setTitle("Add book");
                    dialogForAdd.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                    dialogForAdd.addBook();
                    dialogForAdd.setVisible(true);
                }
            });

            JButton deleteBook=new JButton("delete");
            this.add(deleteBook);
            this.add(Box.createVerticalGlue());
            deleteBook.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    if (isABookSelected()) {
                        MyDialog dialogForDelete = new MyDialog();
                        dialogForDelete.setTitle("Delete book");
                        dialogForDelete.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                        dialogForDelete.deleteBook();
                        dialogForDelete.setVisible(true);
                    }
                }
            });

            JButton editBook=new JButton("edit");
            this.add(editBook);
            this.add(Box.createVerticalGlue());
            editBook.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    if (isABookSelected()) {
                        MyDialog dialogForEdit = new MyDialog();
                        dialogForEdit.setTitle("Edit book");
                        dialogForEdit.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                        dialogForEdit.editBook();
                        dialogForEdit.setVisible(true);
                    }
                }
            });

            JButton filter=new JButton("filter");
            this.add(filter);
            this.add(Box.createVerticalGlue());
            filter.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    isFiltering = true;
                    MyDialog dialogForFilter = new MyDialog();
                    dialogForFilter.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                    dialogForFilter.setTitle("Filter");
                    dialogForFilter.filterBook();
                    dialogForFilter.setVisible(true);
                    dialogForFilter.addWindowListener(new WindowAdapter() {
                        @Override
                        public void windowClosed(WindowEvent e) {
                            isFiltering = false;
                            Gui.index = list.getSelectedIndex();
                            getText();
                            indexForFilterCatalog=-1;
                            modelOfF.clear();
                        }
                    });
                }
            });


            JButton searchBook=new JButton("search");
            this.add(searchBook);
            this.add(Box.createVerticalGlue());
            searchBook.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    MyDialog dialogForSearch = new MyDialog();
                    dialogForSearch.setTitle("Search book");
                    dialogForSearch.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                    dialogForSearch.searchBook();
                    dialogForSearch.setVisible(true);
                }
            });


            JButton searchByIsbn=new JButton("search by ISBN");
            this.add(searchByIsbn);
            this.add(Box.createVerticalGlue());
            searchByIsbn.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    MyDialog dialogForSearchByIsbn = new MyDialog();
                    dialogForSearchByIsbn.setTitle("Search by ISBN");
                    dialogForSearchByIsbn.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                    dialogForSearchByIsbn.getBookByISBN();
                    dialogForSearchByIsbn.setVisible(true);
                }
            });

            JButton load=new JButton("load");
            this.add(load);
            this.add(Box.createVerticalGlue());
            load.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    JFileChooser chooser = new JFileChooser(new File("D:\\"));
                    FileNameExtensionFilter filter = new FileNameExtensionFilter(
                            "xml files", "xml");
                    chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
                    chooser.setFileFilter(filter);
                    int returnVal = chooser.showOpenDialog(null);
                    if(returnVal == JFileChooser.APPROVE_OPTION) {
                        try {
                            MyFormatData.loadFrom(chooser.getSelectedFile().getAbsolutePath());
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        } catch (ParserConfigurationException ex) {
                            ex.printStackTrace();
                        } catch (ParseException ex) {
                            ex.printStackTrace();
                        } catch (SAXException ex) {
                            ex.printStackTrace();
                        }
                    }
                }
            });

            JButton save=new JButton("save");
            this.add(save);
            this.add(Box.createVerticalGlue());
            save.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    JFileChooser chooser = new JFileChooser(new File("D:\\"));
                    FileNameExtensionFilter filter = new FileNameExtensionFilter(
                            "xml files", "xml");
                    chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
                    chooser.setFileFilter(filter);
                    int returnVal = chooser.showSaveDialog(null);
                    if(returnVal == JFileChooser.APPROVE_OPTION) {
                        if (isContainsABook()) {
                            try {
                                MyFormatData myFormatData = new MyFormatData();
                                myFormatData.saveTo(chooser.getSelectedFile().getAbsolutePath());
                            } catch (ParserConfigurationException ex) {
                                    ex.printStackTrace();
                            } catch (TransformerException ex) {
                                    ex.printStackTrace();
                            } catch (IOException ex) {
                                ex.printStackTrace();
                            }
                        }
                    }
                }
            });
        }
        public static boolean isABookSelected() {
            if (index == -1) {
                JOptionPane.showMessageDialog(null, "Please select a book");
                return false;
            }
            return true;
        }
        public static boolean isContainsABook(){
            if (catalog.size() == 0) {
                JOptionPane.showMessageDialog(null, "No book to save");
                return false;
            }
            return true;
        }
    }

    public static class DetailArea extends JPanel {
        static JTextField title;
        static JLabel authorName;
        static JTextArea annotation;
        static JLabel isbn;
        static JMenu publishDate;

        public DetailArea() {
            super();
            this.setLayout(new GridLayout(5, 2));
            this.setPanel();
            annotation.setEditable(false);
            title.setEditable(false);
            Gui.DetailArea.setData();
            Gui.DetailArea.setDataForFilter();
        }

        public void setPanel() {
            title = new JTextField(50);
            this.add(title);
            authorName = new JLabel("");
            this.add(authorName);
            annotation = new JTextArea(1, 10);
            JScrollPane scrollPane = new JScrollPane(annotation);
            this.add(scrollPane);
            isbn = new JLabel();
            this.add(isbn);
            publishDate = new JMenu();
            this.add(publishDate);
        }

        //show details of the selected book
        public static void setData() {
            list.addListSelectionListener(new ListSelectionListener() {
                @Override
                public void valueChanged(ListSelectionEvent e) {
                    Gui.index= list.getSelectedIndex();
                    getText();
                }
            });
        }
        public static void setDataForFilter() {
            listOfF.addListSelectionListener(e -> {
                if (isFiltering) {
                    indexForFilterCatalog = listOfF.getSelectedIndex();
                    if (indexForFilterCatalog != -1) {
                        Gui.index = findInd(indexForFilterCatalog);
                        getText();
                    }
                }
            });
        }

        //called in setData(),setDataForFilter()
        public static void getText() {
            if (Gui.index != -1) {
                title.setText("Title: " + catalog.get(Gui.index).getTitle());
                authorName.setText("Author Name: " + catalog.get(Gui.index).getAuthorName());
                annotation.setText("Annotation: " + catalog.get(Gui.index).getAnnotation());
                isbn.setText("ISBN: " + catalog.get(Gui.index).getISBN());
                publishDate.setText("Publish Date: " + Book.dateToString(catalog.get(Gui.index).getPublicationDate()));
            } else {
                title.setText("");
                authorName.setText("");
                annotation.setText("");
                isbn.setText("");
                publishDate.setText("");
            }
        }

        //called in the setDataForFilter()
        //To map the index of the listOfF to the index of the list
        public static int findInd(int ind){
            int i=0;
            for (int j = 0; j < Gui.ind.length; j++) {
                if (Gui.ind[j]==true&&--ind==-1) {
                    i=j;
                }
            }
            return i;
        }
    }

    public static class MyDialog extends JDialog {
        Container container = getContentPane();
        public MyDialog() {
            super();
            this.setLayout(null);
            this.setSize(300, 300);
            this.setLocation(400, 400);
        }

        //called in the ButtonArea.setPanel()
        public void addBook() {
            JLabel title = new JLabel("Title: ");
            title.setBounds(10, 10, 120, 30);
            container.add(title);
            JTextField titleField = new JTextField();
            titleField.setBounds(120, 10, 100, 30);
            container.add(titleField);

            JLabel authorName = new JLabel("AuthorName: ");
            authorName.setBounds(10, 50, 120, 30);
            container.add(authorName);
            JTextField authorNameField = new JTextField();
            authorNameField.setBounds(120, 50, 100, 30);
            container.add(authorNameField);

            JLabel annotation = new JLabel("Annotation: ");
            annotation.setBounds(10, 90, 120, 30);
            container.add(annotation);
            JTextField annotationField = new JTextField();
            annotationField.setBounds(120, 90, 100, 30);
            container.add(annotationField);

            JLabel ISBN = new JLabel("ISBN: ");
            ISBN.setBounds(10, 130, 120, 30);
            container.add(ISBN);
            JTextField ISBNField = new JTextField();
            ISBNField.setBounds(120, 130, 100, 30);
            container.add(ISBNField);

            JLabel publicationDate = new JLabel("Publication Date: ");
            publicationDate.setBounds(10, 170, 120, 30);
            container.add(publicationDate);
            JTextField publicationDateField = new JTextField();
            publicationDateField.setBounds(120, 170, 100, 30);
            container.add(publicationDateField);

            JButton button = new JButton("Add");
            button.setBounds(120, 200, 100, 30);
            container.add(button);
            button.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    Book newBook = new Book();

                    //make sure that the fields are not empty
                    if (!isEmpty(titleField.getText(),"Title"))newBook.title = titleField.getText();
                    else return;

                    if (!isEmpty(authorNameField.getText(),"Author Name"))newBook.authorName = authorNameField.getText();
                    else return;

                    if (!isEmpty(annotationField.getText(),"Annotation"))newBook.annotation = annotationField.getText();
                    else return;

                    if (!isEmpty(ISBNField.getText(),"ISBN"))newBook.ISBN = ISBNField.getText();
                    else return;

                    if (!publicationDateField.getText().equals("")) {
                        try {
                            newBook.publicationDate = newBook.getDate(publicationDateField.getText());
                            catalog.add(newBook);
                            setList();
                            dispose();
                        } catch (Exception ex) {
                            wrongDateFormatHandler();
                        }
                    } else {
                        isEmpty("","Publication Date");
                    }
                }
            });
        }
        public void deleteBook() {
            JLabel title = new JLabel();
            title.setBounds(25, 100, 250, 30);
            title.setFont(new Font("Arial", Font.CENTER_BASELINE, 15));
            title.setText("Are you sure to delete this book?");
            container.add(title);

            JButton yesButton = new JButton("Yes");
            yesButton.setBounds(40, 200, 100, 30);
            container.add(yesButton);
            yesButton.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    catalog.remove(index);
                    //set model
                    setList();
                    dispose();
                }
            });
            JButton noButton = new JButton("No");
            noButton.setBounds(150, 200, 100, 30);
            container.add(noButton);
            noButton.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    dispose();
                }
            });
        }
        public void editBook() {
            JLabel title = new JLabel("Title: ");
            title.setBounds(10, 10, 120, 30);
            container.add(title);

            JTextField titleField = new JTextField();
            titleField.setBounds(120, 10, 100, 30);
            titleField.setText(catalog.get(index).title);
            container.add(titleField);

            JLabel authorName = new JLabel("AuthorName: ");
            authorName.setBounds(10, 50, 120, 30);
            container.add(authorName);

            JTextField authorNameField = new JTextField();
            authorNameField.setBounds(120, 50, 100, 30);
            authorNameField.setText(catalog.get(index).authorName);
            container.add(authorNameField);

            JLabel annotation = new JLabel("Annotation: ");
            annotation.setBounds(10, 90, 120, 30);
            container.add(annotation);

            JTextField annotationField = new JTextField();
            annotationField.setBounds(120, 90, 100, 30);
            annotationField.setText(catalog.get(index).annotation);
            container.add(annotationField);

            JLabel ISBN = new JLabel("ISBN: ");
            ISBN.setBounds(10, 130, 120, 30);
            container.add(ISBN);

            JTextField ISBNField = new JTextField();
            ISBNField.setBounds(120, 130, 100, 30);
            ISBNField.setText(catalog.get(index).ISBN);
            container.add(ISBNField);

            JLabel publicationDate = new JLabel("Publication Date: ");
            publicationDate.setBounds(10, 170, 120, 30);
            container.add(publicationDate);

            JTextField publicationDateField = new JTextField();
            publicationDateField.setBounds(120, 170, 100, 30);
            publicationDateField.setText(Book.dateToString(catalog.get(index).publicationDate));
            container.add(publicationDateField);

            JButton button = new JButton("Edit");
            button.setBounds(120, 200, 100, 30);
            container.add(button);
            button.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (!isEmpty(titleField.getText(),"title") &&
                            !isEmpty(authorNameField.getText(),"Author name")&&
                            !isEmpty(annotationField.getText(),"Annotation") &&
                            !isEmpty(ISBNField.getText(),"ISBN") &&
                            !isEmpty(publicationDateField.getText(),"Publication Date")) {
                        editContent(index,titleField, authorNameField, annotationField, ISBNField, publicationDateField);
                        //set model
                        setList();
                        if (isFiltering){
                            //set model for JList in filter GUI
                            setList(listOfF,modelOfF);
                        }
                        dispose();
                    }
                }
            });
        }
        public void filterBook() {
            JLabel keyWord = new JLabel("Filter By Keyword: ");
            keyWord.setBounds(10, 10, 120, 30);
            container.add(keyWord);

            JTextField keyWordField = new JTextField();
            keyWordField.setBounds(120, 10, 100, 30);
            container.add(keyWordField);

            JScrollPane scrollPane = new JScrollPane(listOfF);
            scrollPane.setBounds(10, 80, 200, 100);
            container.add(scrollPane);


            JButton button = new JButton("Filter");
            button.setBounds(120, 200, 100, 30);
            container.add(button);
            button.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    isFiltering=true;
                    ind=new boolean[catalog.size()];
                    Arrays.fill(ind,false);
                    if (!isEmpty(keyWordField.getText(),"Keyword")) {
                        for (Book book : catalog) {
                            if (book.title.contains(keyWordField.getText())
                                    || book.authorName.contains(keyWordField.getText())
                                    || book.annotation.contains(keyWordField.getText())
                                    || book.ISBN.contains(keyWordField.getText())
                                    || book.dateToString(book.publicationDate).contains(keyWordField.getText())) {
                                ind[catalog.indexOf(book)] = true;
                            }
                        }
                    }
                    //set model
                    setList(listOfF,modelOfF);
                    //set Data in the DetailArea panel
                    DetailArea.setDataForFilter();
                }
            });
        }
        public void searchBook() {
            JLabel keyWord = new JLabel("Keyword ");
            keyWord.setBounds(10, 10, 120, 30);
            container.add(keyWord);

            JTextField keyWordField = new JTextField();
            keyWordField.setBounds(120, 10, 100, 30);
            container.add(keyWordField);


            JButton button = new JButton("Search");
            button.setBounds(120, 150, 100, 30);
            container.add(button);
            button.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    String keyword = keyWordField.getText();
                    if (!isEmpty(keyword,"Keyword")) {
                        sortBooks(keyword);
                        dispose();
                    }
                }
            });
        }
        public void getBookByISBN() {
            JLabel isbn = new JLabel("ISBN: ");
            isbn.setBounds(10, 10, 120, 30);
            container.add(isbn);

            JTextField isbnField = new JTextField();
            isbnField.setBounds(120, 10, 150, 30);
            container.add(isbnField);

            JLabel result = new JLabel("result: ");
            result.setBounds(10, 50, 120, 30);
            container.add(result);

            JTextArea resultField = new JTextArea();
            JScrollPane scroll = new JScrollPane(resultField);
            scroll.setBounds(120, 50, 150, 100);
            scroll.createVerticalScrollBar();
            container.add(scroll);

            JButton button = new JButton("Confirm");
            button.setBounds(120, 200, 100, 30);
            container.add(button);
            button.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    boolean isFind = false;
                    String isbnString=isbnField.getText();
                    if (!isEmpty(isbnString,"ISBN")) {
                        for (int i = 0; i < catalog.size(); i++) {
                            if (catalog.get(i).ISBN.equals(isbnString)) {
                                resultField.append(catalog.get(i).toString()+"\n");
                                resultField.append("\n");
                                isFind = true;
                                }
                            }
                            if (!isFind) {
                                resultField.setText("Not found");

                        }
                    }

                }
            });
        }

        //called in the searchBook()
        public void sortBooks(String s) {
            ArrayList<Book> searchCatalog = new ArrayList<>();
            ArrayList<Book> annotationCatalog = new ArrayList<>();
            boolean isFound = false,isFindInAnnotation = false;
            int length=0;
            int[] sort=new int[catalog.size()];

            //count keywords
            for (int i = 0; i < catalog.size(); i++) {
                int count=0;
                if (s.equals(catalog.get(i).getTitle())) count++;
                if (s.equals(catalog.get(i).getAuthorName())) count++;
                if (s.equals(catalog.get(i).getAnnotation())) count++;
                if (s.equals(catalog.get(i).getISBN())) count++;
                if (s.equals(Book.dateToString(catalog.get(i).getPublicationDate()))) count++;
                sort[i]=count;
                if(catalog.get(i).getAnnotation().contains(s)) {
                    isFindInAnnotation=true;
                    annotationCatalog.add(catalog.get(i));
                }
                if (count>0) isFound=true;
            }

            if (!isFound) JOptionPane.showMessageDialog(null, "Not found");

            //result Gui
            MyDialog d=new MyDialog();
            d.setSize(400,300);
            d.setTitle("Result");
            JLabel l=new JLabel();
            l.setBounds(10,50,300,30);
            l.setFont(new Font("Arial",Font.BOLD,13));
            l.setText("Search complicated and the list is updated!");
            d.container.add(l);
            JTextArea t=new JTextArea();
            t.setText("Key words found in the annotation of the book below:"+"\n");
            t.setEditable(false);
            JScrollPane scroll=new JScrollPane(t);
            scroll.setBounds(10,100,300,100);
            d.container.add(scroll);
            d.setVisible(true);
            if (isFindInAnnotation) {
                for (int i = 0; i < annotationCatalog.size(); i++) {
                    t.append(annotationCatalog.get(i).title+"\n");
                }
            }

            //sort Books by number of keywords appears
            while (length++<catalog.size()) {
                int ind=0;
                for (int j = 0; j < catalog.size(); j++) {
                    if (sort[ind]<sort[j]) {
                        ind=j;
                    }
                }
                sort[ind]=-1;
                searchCatalog.add(catalog.get(ind));
            }

            //cover catalog with the sorted ArrayList
            for (int i = 0; i < catalog.size(); i++) {
                catalog.set(i,searchCatalog.get(i));
            }

            //set model of JList
            setList();
        }

        public boolean isEmpty(String s,String condition){
            if (s.length()==0) {
                JOptionPane.showMessageDialog(null, "Please enter the "+condition+"!");
                return true;
            }
            return false;
        }
        public void wrongDateFormatHandler() {
            JOptionPane.showMessageDialog(null, "Please enter the date in the format yyyy-mm-dd");
        }

        //called in editBook()
        public void editContent(int ind,
                                JTextField titleField,JTextField authorNameField,
                                JTextField annotationField,JTextField ISBNField,
                                JTextField publicationDateField) {
            catalog.get(ind).title = titleField.getText();
            catalog.get(ind).authorName = authorNameField.getText();
            catalog.get(ind).annotation = annotationField.getText();
            catalog.get(ind).ISBN = ISBNField.getText();
            try {
                catalog.get(ind).publicationDate = Book.getDate(publicationDateField.getText());
            } catch (ParseException ex) {
                wrongDateFormatHandler();
            }
        }
    }
}

