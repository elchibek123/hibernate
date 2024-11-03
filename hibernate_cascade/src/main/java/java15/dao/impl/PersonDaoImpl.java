package java15.dao.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import java15.config.HibernateConfig;
import java15.dao.PersonDao;
import java15.entities.Person;

public class PersonDaoImpl implements PersonDao {
    EntityManagerFactory entityManagerFactory = HibernateConfig.getEntityManger();

    @Override
    public String save(Person person) {
        EntityManager entityManager = null;
        try {
            entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            entityManager.persist(person);
            entityManager.getTransaction().commit();
            return "Person successfully saved";
        } catch (Exception e) {
            if (entityManager != null && entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            return "Error saving person: " + e.getMessage();
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
    }

    @Override
    public Person findById(Long id) {
        EntityManager entityManager = null;
        try {
            entityManager = entityManagerFactory.createEntityManager();
            return entityManager.find(Person.class, id);
        } catch (Exception e) {
            System.out.println("Error finding person by ID: " + e.getMessage());
            return null;
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
    }

    @Override
    public String delete(Long id) {
        EntityManager entityManager = null;
        try {
            entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            Person byId = findById(id);
            entityManager.remove(byId);
            entityManager.getTransaction().commit();
            return "Person successfully deleted";
        } catch (Exception e) {
            if (entityManager != null && entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            return "Error saving person: " + e.getMessage();
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
    }
}
