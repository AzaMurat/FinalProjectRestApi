package peaksoft.finalprojectrestapi.dto.maper;

import org.springframework.stereotype.Component;
import peaksoft.finalprojectrestapi.dto.CourseDto;
import peaksoft.finalprojectrestapi.model.Course;

@Component
public class CourseMapper {

    public Course create(CourseDto courseDto){
        if (courseDto==null){
            return null;
        }
        Course course = new Course();
        course.setCourseName(courseDto.getCourseName());
        course.setDurationInMonth(courseDto.getDurationInMonth());
        return course;
    }
}
