package peaksoft.finalprojectrestapi.service;
import peaksoft.finalprojectrestapi.model.Course;
import peaksoft.finalprojectrestapi.model.Response;

import java.util.List;
import java.util.UUID;

public interface CourseService {

    Response saveCourse(Course course);

    List<Course> getAllCourse();

    Course findByCourseId(UUID id);

    Response deleteCourseId(UUID id);

    Response updateCourseById(UUID id, Course course);
}
