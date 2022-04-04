package peaksoft.finalprojectrestapi.service;
import peaksoft.finalprojectrestapi.dto.CourseDto;
import peaksoft.finalprojectrestapi.model.Course;
import peaksoft.finalprojectrestapi.model.Response;

import java.util.List;

public interface CourseService {

    Response saveCourse(CourseDto course,Long id);

    List<Course> getAllCourse();

    Course findByCourseId(Long id);

    Response deleteCourseId(Long id);

    Response updateCourseById(Long id, CourseDto course);
}
