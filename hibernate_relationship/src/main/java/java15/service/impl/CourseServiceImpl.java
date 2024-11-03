package java15.service.impl;

import java15.dao.CourseDao;
import java15.dao.impl.CourseDaoImpl;
import java15.entities.Course;
import java15.service.CourseService;

import java.util.List;
import java.util.Optional;

public class CourseServiceImpl implements CourseService {
    private final CourseDao courseDao = new CourseDaoImpl();

    @Override
    public String createCourse(Course course) {
        return courseDao.save(course);
    }

    @Override
    public List<Course> getAllCourses() {
        return courseDao.findAll();
    }

    @Override
    public Optional<Course> getCourseById(Long id) {
        return courseDao.findById(id);
    }

    @Override
    public String updateCourse(Long id, Course newCourse) {
        return "";
    }

    @Override
    public String deleteCourse(Long id) {
        return courseDao.delete(id);
    }

    @Override
    public int countStudentsByCourseId() {
        return courseDao.countStudentsByCourseId();
    }

    @Override
    public void deleteAllStudentsByCourseId() {

    }
}
