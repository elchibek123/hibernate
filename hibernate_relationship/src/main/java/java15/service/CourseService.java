package java15.service;

import java15.entities.Course;

import java.util.List;
import java.util.Optional;

public interface CourseService {
    String createCourse(Course course);

    List<Course> getAllCourses();

    Optional<Course> getCourseById(Long id);

    String updateCourse(Long id, Course newCourse);

    String deleteCourse(Long id);

    int countStudentsByCourseId();

    void deleteAllStudentsByCourseId();
}
