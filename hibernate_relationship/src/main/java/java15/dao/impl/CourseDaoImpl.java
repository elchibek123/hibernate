package java15.dao.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import java15.config.HibernateConfig;
import java15.dao.CourseDao;
import java15.entities.Course;

import java.util.List;
import java.util.Optional;

public class CourseDaoImpl implements CourseDao {
    EntityManagerFactory entityManagerFactory = HibernateConfig.getEntityManger();
    @Override
    public String save(Course course) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(course);
            entityManager.getTransaction().commit();
            entityManager.close();
            return "Course successfully saved";
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            return "Error saving course: " + e.getMessage();
        }
        finally {
            entityManager.close();
        }
    }

    @Override
    public List<Course> findAll() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<Course> courses = null;
        try {
            entityManager.getTransaction().begin();
            courses = entityManager.createQuery("SELECT s FROM Course s", Course.class).getResultList();
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            throw new RuntimeException("Failed to fetch courses", e);
        } finally {
            entityManager.close();
        }
        return courses;
    }

    @Override
    public Optional<Course> findById(Long id) {
        try {
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            return Optional.ofNullable(entityManager.find(Course.class, id));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public String update(Long id, Course newCourse) {
        return "";
    }

    @Override
    public String delete(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            Course course = entityManager.find(Course.class, id);
            entityManager.remove(course);
            entityManager.getTransaction().commit();
            return "Course successfully deleted";
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            return "Error deleting course: " + e.getMessage();
        }
        finally {
            entityManager.close();
        }
    }

    @Override
    public int countStudentsByCourseId() {
        return 0;
    }

    @Override
    public void deleteAllStudentsByCourseId() {

    }
}
