package java15.services;

import java15.entities.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {
    String createBook(Book book);

    List<Book> getAllBooks();

    Optional<Book> getBookById(Long id);

    String updateBook(Long id, Book book);

    String deleteBook(Long id);

    List<Book> sortByPrice(String ascOrDesc);
}
