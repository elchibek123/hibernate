package java15;

import java15.entities.Book;
import java15.enums.BookType;
import java15.services.BookService;
import java15.services.impl.BookServiceImpl;

import java.math.BigDecimal;
import java.util.List;

public class App 
{
    public static void main( String[] args ) {
        BookService bookService = new BookServiceImpl();

        System.out.println(bookService.createBook(
                new Book(
                        "Toolor Kulaganda",
                        "Chyngyz Aytmatov",
                        BigDecimal.valueOf(1000),
                        BookType.PAPER
                )
        ));
        System.out.println(bookService.createBook(
                new Book(
                        "Syngan Kylych",
                        "Tologon Kasymbekov",
                        BigDecimal.valueOf(800),
                        BookType.AUDIOBOOK
                )
        ));

        System.out.println(bookService.createBook(
                new Book(
                        "Voyna i mir",
                        "Lev Tolstoy",
                        BigDecimal.valueOf(1200),
                        BookType.ELECTRONIC
                )
        ));

        List<Book> bookList = bookService.getAllBooks();
        bookList.forEach(System.out::println);
//        for (Book book : bookList) {
//            System.out.println(book);
//        }

        System.out.println("Book by id: " + bookService.getBookById(1L));

        System.out.println(bookService.updateBook(1L, new Book("Erte kelgen turnalar", "Chyngyz Aytmatov", BigDecimal.valueOf(1300), BookType.AUDIOBOOK)));

        System.out.println(bookService.deleteBook(2L));

        List<Book> books = bookService.sortByPrice("desc");
        books.forEach(System.out::println);
    }
}
