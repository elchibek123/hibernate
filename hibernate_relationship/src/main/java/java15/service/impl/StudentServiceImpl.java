package java15.service.impl;

import java15.dao.CourseDao;
import java15.dao.StudentDao;
import java15.dao.impl.CourseDaoImpl;
import java15.dao.impl.StudentDaoImpl;
import java15.entities.Course;
import java15.entities.Student;
import java15.service.StudentService;

import java.util.*;

public class StudentServiceImpl implements StudentService {
    private final StudentDao studentDao = new StudentDaoImpl();
    private final CourseDao courseDao = new CourseDaoImpl();

    @Override
    public String createStudent(Student student) {
        return studentDao.save(student);
    }

    @Override
    public String createStudent(Long courseId, Student student) {
        return studentDao.save(courseId, student);
    }

    @Override
    public String createStudent(Long courseId, ArrayList<Student> students) {
        return "";
    }

    @Override
    public List<Student> getAllStudents() {
        return null;
    }

    @Override
    public Student getStudentById(Long id) {
        return null;
    }

    @Override
    public String updateStudent(Long id, Student newStudent) {
        return "";
    }

    @Override
    public String deleteStudent(Long id) {
        return studentDao.delete(id);
    }

    @Override
    public String assignStudentToCourse(Long studentId, Long courseId) {
        return studentDao.assignStudentToCourse(studentId, courseId);
    }
}
