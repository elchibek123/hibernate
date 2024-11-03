package java15.dao.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import java15.config.HibernateConfig;
import java15.dao.SocialMediaDao;
import java15.entities.Person;
import java15.entities.SocialMedia;

import java.util.List;
import java.util.Optional;

public class SocialMediaDaoImpl implements SocialMediaDao {
    EntityManagerFactory entityManagerFactory = HibernateConfig.getEntityManger();

    @Override
    public String save(SocialMedia socialMedia) {
        EntityManager entityManager = null;
        try {
            entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            entityManager.persist(socialMedia);
            entityManager.getTransaction().commit();
            return "Social media successfully saved";
        } catch (Exception e) {
            if (entityManager != null && entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            return "Error saving social media: " + e.getMessage();
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
    }

    @Override
    public Optional<SocialMedia> findById(Long id) {
        try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
            SocialMedia socialMedia = entityManager.find(SocialMedia.class, id);
            return Optional.ofNullable(socialMedia);
        } catch (Exception e) {
            System.out.println("Error finding social media by ID: " + e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public void delete(SocialMedia socialMedia) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            SocialMedia managedEntity = entityManager.merge(socialMedia);
            entityManager.remove(managedEntity);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            throw new RuntimeException("Error deleting social media: " + e.getMessage(), e);
        } finally {
            entityManager.close();
        }
    }

    @Override
    public void assignSocialMediaToPerson(Long socialMediaId, Long personId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            Person person = entityManager.find(Person.class, personId);
            if (person == null) {
                throw new RuntimeException("Person with ID: " + personId + " not found");
            }
            SocialMedia socialMedia = entityManager.find(SocialMedia.class, socialMediaId);
            if (socialMedia == null) {
                throw new RuntimeException("Social media with ID: " + socialMediaId + " not found");
            }
            person.setSocialMedia(List.of(socialMedia));
            socialMedia.setPerson(person);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            throw new RuntimeException("Error assigning social media to person: " + e.getMessage(), e);
        } finally {
            entityManager.close();
        }
    }
}
