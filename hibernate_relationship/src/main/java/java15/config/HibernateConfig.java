package java15.config;

import jakarta.persistence.EntityManagerFactory;
import java15.entities.*;
import org.hibernate.HibernateException;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import java.util.Properties;

public class HibernateConfig {
    public static EntityManagerFactory getEntityManger() {
        try {
            Properties properties = new Properties();
            properties.put(Environment.JAKARTA_JDBC_URL, "jdbc:postgresql://localhost:5432/postgres");
            properties.put(Environment.JAKARTA_JDBC_USER, "postgres");
            properties.put(Environment.JAKARTA_JDBC_PASSWORD, "postgresql");
            properties.put(Environment.DIALECT, "org.hibernate.dialect.PostgreSQLDialect");
            properties.put(Environment.HBM2DDL_AUTO, "update");
            properties.put(Environment.SHOW_SQL, "true");
            properties.put(Environment.FORMAT_SQL, "false");

            Configuration configuration = new Configuration();
            configuration.setProperties(properties);
            configuration.addAnnotatedClass(Course.class);
            configuration.addAnnotatedClass(Lesson.class);
            configuration.addAnnotatedClass(Mentor.class);
            configuration.addAnnotatedClass(Student.class);
            configuration.addAnnotatedClass(Task.class);

            return configuration.buildSessionFactory().unwrap(EntityManagerFactory.class);
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
