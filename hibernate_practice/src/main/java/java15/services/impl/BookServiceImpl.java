package java15.services.impl;

import java15.dao.BookDao;
import java15.dao.impl.BookDaoImpl;
import java15.entities.Book;
import java15.services.BookService;

import java.util.List;
import java.util.Optional;

public class BookServiceImpl implements BookService {
    private final BookDao bookDao = new BookDaoImpl();

    @Override
    public String createBook(Book book) {
        return bookDao.save(book) ? "Book added" : "Failed to add book";
    }

    @Override
    public List<Book> getAllBooks() {
        return bookDao.findAll();
    }

    @Override
    public Optional<Book> getBookById(Long id) {
        return bookDao.findById(id);
    }

    @Override
    public String updateBook(Long id, Book bookDetails) {
        return bookDao.update(id, bookDetails);
    }

    @Override
    public String deleteBook(Long id) {
        String result = "Book successfully deleted";
        try {
            bookDao.delete(id);
        } catch (Exception e) {
            result = "Failed to delete book";
        }
        return result;
    }

    @Override
    public List<Book> sortByPrice(String ascOrDesc) {
        return bookDao.sort(ascOrDesc);
    }
}
