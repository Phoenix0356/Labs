package Program.Lab_1.Lab_4;

import java.text.ParseException;
import java.util.Date;

public class Book {
    String title;
    String authorName;
    String annotation;
    String ISBN;
    Date publicationDate;
    public Book(){}
    public  String getTitle() {
        return title;
    }
    public  String getAuthorName() {
        return authorName;
    }
    public  String getAnnotation() {
        return annotation;
    }
    public  String getISBN() {
        return ISBN;
    }
    public Date getPublicationDate() {
        return publicationDate;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }
    public void setAnnotation(String annotation) {
        this.annotation = annotation;
    }
    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }
    public Date setPublicationDate(Date publicationDate) {
        this.publicationDate = publicationDate;
        return publicationDate;
    }
    public String toStringWithoutAnnotation() {
        return "Title: " + title + "\nAuthor: " + authorName + "\nISBN: " + ISBN + "\nPublication date: " + publicationDate;
    }
    public static Date getDate(String s) throws ParseException {
        int[] dateArr = new int[3];
        String[] dateStr = s.split("-");
        dateArr[0]=Integer.parseInt(dateStr[0])-1900;
        dateArr[1]=Integer.parseInt(dateStr[1])-1;
        dateArr[2]=Integer.parseInt(dateStr[2]);
        Date date = new Date(dateArr[0],dateArr[1],dateArr[2]);
        return date;
    }
    public static String dateToString(Date date) {
        String s=String.valueOf(date.getYear()+1900)+"-"+String.valueOf(date.getMonth()+1)+"-"+String.valueOf(date.getDate());
        return s;
    }
    public String toString() {
        return "Title: " + title +
                "\nAuthor: " + authorName
                + "\nAnnotation: " + annotation
                + "\nISBN: " + ISBN
                + "\nPublication date: " + dateToString(publicationDate);
    }
}
