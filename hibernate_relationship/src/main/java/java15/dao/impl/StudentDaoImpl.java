package java15.dao.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import java15.config.HibernateConfig;
import java15.dao.StudentDao;
import java15.entities.Course;
import java15.entities.Student;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class StudentDaoImpl implements StudentDao {
    EntityManagerFactory entityManagerFactory = HibernateConfig.getEntityManger();
    @Override
    public String save(Student student) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(student);
            entityManager.getTransaction().commit();
            entityManager.close();
            return "Student successfully saved";
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            return "Error saving student: " + e.getMessage();
        } finally {
            entityManager.close();
        }
    }

    @Override
    public String save(Long courseId, Student student) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            Course course = entityManager.find(Course.class, courseId);
            if (course == null) {
                throw new RuntimeException("Course with ID: " + courseId + " not found");
            }
            course.getStudents().add(student);
            if (student.getCourses() == null) {
                student.setCourses(new HashSet<>());
            }
            student.getCourses().add(course);
            entityManager.persist(student);
            entityManager.getTransaction().commit();
            return "Student successfully saved";
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            return "Error saving student: " + e.getMessage();
        } finally {
            entityManager.close();
        }
    }

    @Override
    public String save(Long courseId, ArrayList<Student> students) {
        return "";
    }

    @Override
    public List<Student> findAll() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<Student> students = null;
        try {
            entityManager.getTransaction().begin();
            students = entityManager.createQuery("SELECT s FROM Student s", Student.class).getResultList();
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            throw new RuntimeException("Failed to fetch students", e);
        } finally {
            entityManager.close();
        }
        return students;
    }

    @Override
    public Student findById(Long id) {
        return null;
    }

    @Override
    public String update(Long id, Student newStudent) {
        return "";
    }

    @Override
    public String delete(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            Student student = entityManager.find(Student.class, id);
            entityManager.remove(student);
            entityManager.getTransaction().commit();
            return "Student successfully deleted";
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            return "Error deleting student: " + e.getMessage();
        } finally {
            entityManager.close();
        }
    }

    @Override
    public String assignStudentToCourse(Long studentId, Long courseId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            Student student = entityManager.find(Student.class, studentId);
            if (student == null) {
                throw new RuntimeException("Student with id: " + studentId + " not found");
            }
            Course course = entityManager.createQuery("SELECT c FROM Course c WHERE c.id = :courseId", Course.class)
                    .setParameter("courseId", courseId)
                    .getSingleResult();
            if (course == null) {
                throw new RuntimeException("Course with id: " + courseId + " not found");
            }
            student.getCourses().add(course);
            entityManager.getTransaction().commit();
            return "Student assigned to course successfully";
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            throw e;
        } finally {
            entityManager.close();
        }
    }
}
