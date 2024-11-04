package java15.dao.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import java15.config.HibernateConfig;
import java15.dao.BlogPostDao;
import java15.entities.BlogPost;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class BlogPostDaoImpl implements BlogPostDao {
    private final EntityManagerFactory entityManagerFactory = HibernateConfig.getEntityManagerFactory();

    @Override
    public boolean save(BlogPost blogPost) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(blogPost);
            entityManager.getTransaction().commit();
            return true;
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            System.err.println("Error saving blog post: " + e.getMessage());
            return false;
        } finally {
            entityManager.close();
        }
    }

    @Override
    public List<BlogPost> findAll() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            return entityManager.createQuery("SELECT b FROM BlogPost b ORDER BY publishDate DESC", BlogPost.class).getResultList();
        } catch (Exception e) {
            System.err.println("Failed to fetch blog posts: " + e.getMessage());
        } finally {
            entityManager.close();
        }
        return Collections.emptyList();
    }

    @Override
    public Optional<BlogPost> findById(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            return Optional.ofNullable(entityManager.find(BlogPost.class, id));
        } catch (Exception e) {
            System.err.println("Error finding blog post by ID: " + e.getMessage());
            return Optional.empty();
        } finally {
            entityManager.close();
        }
    }

    @Override
    public boolean update(Long id, BlogPost newBlogPost) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            int updatedCount = entityManager.createQuery(
                            "UPDATE BlogPost b SET b.title = :title, b.content = :content WHERE b.id = :id")
                    .setParameter("title", newBlogPost.getTitle())
                    .setParameter("content", newBlogPost.getContent())
                    .setParameter("id", id)
                    .executeUpdate();
            entityManager.getTransaction().commit();
            return updatedCount > 0;
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            System.err.println("Error finding blog post by ID: " + e.getMessage());
            return false;
        } finally {
            entityManager.close();
        }
    }

    @Override
    public boolean delete(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            BlogPost blogPost = entityManager.find(BlogPost.class, id);
            entityManager.remove(blogPost);
            entityManager.getTransaction().commit();
            return true;
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            System.err.println("Error deleting blog post: " + e.getMessage());
            return false;
        } finally {
            entityManager.close();
        }
    }

    @Override
    public List<BlogPost> searchBlogPostsByKeyword(String keyword) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            return entityManager.createQuery(
                            "SELECT b FROM BlogPost b WHERE b.title ILIKE :keyword OR b.content ILIKE :keyword", BlogPost.class)
                    .setParameter("keyword", "%" + keyword + "%")
                    .getResultList();
        } catch (Exception e) {
            System.err.println("Error searching blog post: " + e.getMessage());
            return Collections.emptyList();
        } finally {
            entityManager.close();
        }
    }
}
