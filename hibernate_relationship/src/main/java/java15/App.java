package java15;

import java15.config.HibernateConfig;
import java15.entities.Course;
import java15.entities.Student;
import java15.enums.Gender;
import java15.service.CourseService;
import java15.service.StudentService;
import java15.service.impl.CourseServiceImpl;
import java15.service.impl.StudentServiceImpl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main( String[] args ) {
//        HibernateConfig.getEntityManger();
        CourseService courseService = new CourseServiceImpl();
        StudentService studentService = new StudentServiceImpl();


        Student student = Student.builder().firstName("John").lastName("Doe").gender(Gender.MALE).email("john@gmail.com").yearOfBirth(1997).phoneNumber("0997342342").build();
        Student student2 = Student.builder().firstName("David").lastName("Smith").gender(Gender.MALE).email("david@gmail.com").yearOfBirth(1997).phoneNumber("1997342342").build();

//        System.out.println(studentService.createStudent(student2));
//        System.out.println(studentService.createStudent(1L, student));
//        studentService.createStudent(1L, new ArrayList<Student>());

//        System.out.println(studentService.deleteStudent(3L));

//        System.out.println(studentService.assignStudentToCourse(4L, 2L));




        Course java = Course.builder().name("Java").price(BigDecimal.valueOf(800)).duration(9).startDate(LocalDate.of(2024, 7, 1)).endDate(LocalDate.of(2025, 3, 30)).build();
        Course python = Course.builder().name("Python").price(BigDecimal.valueOf(600)).duration(6).startDate(LocalDate.of(2024, 7, 1)).endDate(LocalDate.of(2024, 12, 30)).build();

//        System.out.println(courseService.createCourse(java));
//        System.out.println(courseService.createCourse(python));

//        List<Course> allCourses = courseService.getAllCourses();
//        allCourses.forEach(System.out::println);

//        System.out.println(courseService.getCourseById(1L));

        System.out.println(courseService.deleteCourse(2L));
    }
}
