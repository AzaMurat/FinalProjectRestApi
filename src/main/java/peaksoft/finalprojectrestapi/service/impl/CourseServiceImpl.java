package peaksoft.finalprojectrestapi.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import peaksoft.finalprojectrestapi.exception.BadRequestException;
import peaksoft.finalprojectrestapi.exception.NotFountException;
import peaksoft.finalprojectrestapi.model.Course;
import peaksoft.finalprojectrestapi.model.Response;
import peaksoft.finalprojectrestapi.repository.CourseRepository;
import peaksoft.finalprojectrestapi.service.CourseService;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

import static org.springframework.http.HttpStatus.*;

@Service
@AllArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;

    @Override
    public Response saveCourse(Course course) {
        String courseName = course.getCourseName();
        Optional<Course> byCourseName = courseRepository.findByCourseName(courseName);

        if (byCourseName.isPresent()) {
            throw new BadRequestException("Course with cours name=" + courseName + "already exists");

        }

        Course saveCourse = courseRepository.save(course);

        return Response.builder().httpStatus(CREATED).
                message(String.format("Company with email = %s successfully registered",
                        saveCourse.getCourseName())).build();
    }

    @Override
    public List<Course> getAllCourse() {
        List<Course> courses = courseRepository.findAll().stream().toList();
        return courses;
    }

    @Override
    public Course findByCourseId(UUID id) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> {
                            throw new NotFountException("Course with id not found from data base");
                        }
                );

        return course;
    }

    @Override
    public Response deleteCourseId(UUID id) {
        courseRepository.deleteById(id);
        return Response.builder().httpStatus(OK).build();
    }

    @Override
    public Response updateCourseById(UUID id, Course newCourse) {
        Course oldCourse = findByCourseId(id);
        String currentCourseName = oldCourse.getCourseName();
        String newCourseName = newCourse.getCourseName();
        if (!Objects.equals(currentCourseName, newCourseName)) {
            oldCourse.setCourseName(newCourseName);
        }
        String currentDurationInMonth = oldCourse.getDurationInMonth();
        String newCourseDurationInMonth = newCourse.getDurationInMonth();
        if (!Objects.equals(currentDurationInMonth, newCourseDurationInMonth)) {
            oldCourse.setDurationInMonth(newCourseDurationInMonth);
        }
        String message = String.format("Course with id %s successfully updated");
        return Response.builder().httpStatus(RESET_CONTENT).message(message).build();
    }
}
