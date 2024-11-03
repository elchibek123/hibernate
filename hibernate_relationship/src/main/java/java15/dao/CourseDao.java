package java15.dao;

import java15.entities.Course;

import java.util.List;
import java.util.Optional;

public interface CourseDao {
    String save(Course course);

    List<Course> findAll();

    Optional<Course> findById(Long id);

    String update(Long id, Course newCourse);

    String delete(Long id);

    int countStudentsByCourseId();

    void deleteAllStudentsByCourseId();
}
