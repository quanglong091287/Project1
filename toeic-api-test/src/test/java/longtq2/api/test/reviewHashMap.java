package longtq2.api.test;

import longtq2.model.Book;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class reviewHashMap {
    @Test
    public void checkHashMap(){
        Book b1= returnBookModel(101,"Let us C","Yashwant Kanetkar","BPB",8);
        Book b2= returnBookModel(102,"Data Communications & Networking","Forouzan","Mc Graw Hill",4);
        Book b3= returnBookModel(103,"Operating System","Galvin","Wiley",6);
        Book b4= returnBookModel(104,"Java Web","Galvin","Longtq2",10);
    //tao HashMap
        Map<Integer, Book> map = new HashMap<Integer, Book>();
        map.put(1,b1);
        map.put(2,b2);
        map.put(3,b3);
        map.put(3,b4);
        for(Map.Entry<Integer, Book> item: map.entrySet()){
            System.out.println(item.getKey() + " detail");
            System.out.println(item.getValue().getId() + "," + item.getValue().getName() + ","+ item.getValue().getAuthor());
        }
    }
    private Book returnBookModel(int id, String Name, String Author, String Publisher, int Quanlity){
        Book book = new Book();
        book.setId(id);
        book.setName(Name);
        book.setAuthor(Author);
        book.setPublisher(Publisher);
        book.setQuanlity(Quanlity);
        return book;
    }
}
