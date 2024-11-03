package java15;

import java15.config.HibernateConfig;
import java15.dao.PersonDao;
import java15.dao.SocialMediaDao;
import java15.dao.impl.PersonDaoImpl;
import java15.dao.impl.SocialMediaDaoImpl;
import java15.entities.Person;
import java15.entities.SocialMedia;
import java15.service.PersonService;
import java15.service.SocialMediaService;
import java15.service.impl.PersonServiceImpl;
import java15.service.impl.SocialMediaServiceImpl;

import java.util.List;

public class App
{
    public static void main( String[] args ) {
//        HibernateConfig.getEntityManger();

        PersonDao personDao = new PersonDaoImpl();
        PersonService personService = new PersonServiceImpl(personDao);

        SocialMediaDao socialMediaDao = new SocialMediaDaoImpl();
        SocialMediaService socialMediaService = new SocialMediaServiceImpl(socialMediaDao);

        Person person1 = Person.builder().name("John").age(34).email("john@gmail.com").build();
        Person person2 = Person.builder().name("David").age(58).email("david@gmail.com").build();

        SocialMedia instagram = SocialMedia.builder().name("Instagram").build();
        SocialMedia faceBook = SocialMedia.builder().name("FaceBook").build();

//        System.out.println(personService.createPerson(person1));
//        System.out.println(personService.createPerson(person2));

//        System.out.println("Person by id: " + personService.getPersonById(3L));

//        System.out.println(personService.deletePerson(1L));

//        System.out.println(socialMediaService.createSocialMedia(instagram));
//        System.out.println(socialMediaService.createSocialMedia(faceBook));

//        System.out.println("Social media with id: " + socialMediaService.getSocialMediaById(2L));

//        socialMediaService.deleteSocialMedia(instagram);

//        socialMediaService.assignSocialMediaToPerson(2L, 4L);

        instagram.setPerson(person1);
        faceBook.setPerson(person1);
        person1.setSocialMedia(List.of(instagram, faceBook));

        System.out.println(personService.createPerson(person1));
    }
}
