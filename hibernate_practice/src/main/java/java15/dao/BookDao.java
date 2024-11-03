package java15.dao;

import java15.entities.Book;

import java.util.List;
import java.util.Optional;

public interface BookDao {
    boolean save(Book book);

    List<Book> findAll();

    Optional<Book> findById(Long id);

    String update(Long id, Book bookDetails);

    void delete(Long id);

    List<Book> sort(String ascOrDesc);
}
