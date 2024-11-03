package java15.service;

import java15.entities.Student;

import java.util.ArrayList;
import java.util.List;

public interface StudentService {
    String createStudent(Student student);

    String createStudent(Long courseId, Student student);

    String createStudent(Long courseId, ArrayList<Student> students);

    List<Student> getAllStudents();

    Student getStudentById(Long id);

    String updateStudent(Long id, Student newStudent);

    String deleteStudent(Long id);

    String assignStudentToCourse(Long studentId, Long courseId);
}
