package java15.dao.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import java15.config.HibernateConfig;
import java15.dao.CommentDao;
import java15.entities.BlogPost;
import java15.entities.Comment;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class CommentDaoImpl implements CommentDao {
    private final EntityManagerFactory entityManagerFactory = HibernateConfig.getEntityManagerFactory();

    @Override
    public boolean save(Long blogPostId, Comment comment) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            BlogPost blogPost = entityManager.find(BlogPost.class, blogPostId);
            if (blogPost == null) {
                System.err.println("Blog post with ID: " + blogPostId + " not found");
                return false;
            }
            comment.setBlogPost(blogPost);
            entityManager.persist(comment);
            entityManager.getTransaction().commit();
            return true;
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            System.err.println("Error saving comment: " + e.getMessage());
            return false;
        } finally {
            entityManager.close();
        }
    }

    @Override
    public List<Comment> findAll() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            return entityManager.createQuery("SELECT c FROM Comment c ORDER BY publishDate DESC", Comment.class).getResultList();
        } catch (Exception e) {
            System.err.println("Failed to fetch comments for comments: " + e.getMessage());
        } finally {
                entityManager.close();
        }
        return Collections.emptyList();
    }

    @Override
    public Optional<Comment> findById(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            return Optional.ofNullable(entityManager.find(Comment.class, id));
        } catch (Exception e) {
            System.err.println("Error finding comment by ID: " + e.getMessage());
            return Optional.empty();
        } finally {
            entityManager.close();
        }
    }

    @Override
    public boolean update(Long id, Comment newComment) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            int updatedCount = entityManager.createQuery(
                            "UPDATE Comment c SET c.commentText = :commentText WHERE c.id = :id")
                    .setParameter("commentText", newComment.getCommentText())
                    .setParameter("id", id)
                    .executeUpdate();
            entityManager.getTransaction().commit();
            return updatedCount > 0;
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            System.err.println("Error updating comment: " + e.getMessage());
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
            Comment comment = entityManager.find(Comment.class, id);
            if (comment == null) {
                return false;
            }
            BlogPost blogPost = comment.getBlogPost();
            if (blogPost != null) {
                blogPost.getComments().remove(comment);
                comment.setBlogPost(null);
            }
            entityManager.remove(comment);
            entityManager.getTransaction().commit();
            return true;
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            System.err.println("Error deleting comment: " + e.getMessage());
            return false;
        } finally {
            entityManager.close();
        }
    }

    @Override
    public List<Comment> sortPostByPublishDate() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            return entityManager.createQuery("SELECT c FROM Comment c ORDER BY c.publishDate DESC", Comment.class).getResultList();
        } catch (Exception e) {
            System.err.println("Error fetching sorted comments: " + e.getMessage());
            return Collections.emptyList();
        } finally {
            entityManager.close();
        }
    }

    @Override
    public List<Comment> groupCommentsByPost() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            return entityManager.createQuery(
                            "SELECT c FROM Comment c LEFT JOIN FETCH c.blogPost b " +
                                    "ORDER BY b.id, c.publishDate DESC", Comment.class).getResultList();
        } catch (Exception e) {
            System.err.println("Error grouping comments by post: " + e.getMessage());
            return Collections.emptyList();
        } finally {
            entityManager.close();
        }
    }
}
