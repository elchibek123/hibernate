package java15.dao.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;
import java15.config.DatabaseConnection;
import java15.dao.BookDao;
import java15.entities.Book;
import org.hibernate.HibernateException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BookDaoImpl implements BookDao, AutoCloseable {
    private final EntityManagerFactory entityManagerFactory = DatabaseConnection.getEntityManagerFactory();

    @Override
    public boolean save(Book book) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(book);
            entityManager.getTransaction().commit();
            return true;
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Override
    public List<Book> findAll() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<Book> books = new ArrayList<>();
        try {
            entityManager.getTransaction().begin();
            TypedQuery<Book> selectBFromBookB = entityManager.createQuery("SELECT b FROM Book b", Book.class);
            books = selectBFromBookB.getResultList();
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
        }
        return books;
    }

    @Override
    public Optional<Book> findById(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            Book book = entityManager.find(Book.class, id);
            entityManager.getTransaction().commit();
            return Optional.ofNullable(book);
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public String update(Long id, Book bookDetails) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            Book book = entityManager.find(Book.class, id);
            if (book == null) {
                return "Book with id: " + id + " not found";
            }
            book.setTitle(bookDetails.getTitle());
            book.setAuthorFullName(bookDetails.getAuthorFullName());
            book.setPrice(bookDetails.getPrice());
            book.setBookType(bookDetails.getBookType());
            entityManager.getTransaction().commit();
            return "Book successfully updated";
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            System.out.println("Error updating book: " + e.getMessage());
            return "Failed to update the book";
        }
    }

    @Override
    public void delete(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
//            Book book = entityManager.find(Book.class, id);
            Book book = entityManager
                    .createQuery("SELECT b FROM Book b WHERE b.id = :id", Book.class)
                    .setParameter("id", id).getSingleResult();
            entityManager.remove(book);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw new RuntimeException();
        }
    }

    @Override
    public List<Book> sort(String ascOrDesc) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<Book> books = null;
        String direction = ascOrDesc.equalsIgnoreCase("ASC") ? "ASC" : "DESC";
        String query = "SELECT b FROM Book b ORDER BY b.price " + direction;
        try {
            entityManager.getTransaction().begin();
            books = entityManager.createQuery(query, Book.class).getResultList();
            entityManager.getTransaction().commit();
        } catch (HibernateException e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            throw new RuntimeException();
        }
        return books;
    }

    @Override
    public void close() {
        entityManagerFactory.close();
    }
}
