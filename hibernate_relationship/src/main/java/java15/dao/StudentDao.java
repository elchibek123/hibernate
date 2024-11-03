package java15.dao;

import java15.entities.Student;

import java.util.ArrayList;
import java.util.List;

public interface StudentDao {
    String save(Student student);

    String save(Long courseId, Student student);

    String save(Long courseId, ArrayList<Student> students);

    List<Student> findAll();

    Student findById(Long id);

    String update(Long id, Student newStudent);

    String delete(Long id);

    String assignStudentToCourse(Long studentId, Long courseId);
}
