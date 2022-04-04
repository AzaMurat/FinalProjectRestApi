package peaksoft.finalprojectrestapi.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import peaksoft.finalprojectrestapi.dto.CourseDto;
import peaksoft.finalprojectrestapi.maper.CourseMapper;
import peaksoft.finalprojectrestapi.exception.BadRequestException;
import peaksoft.finalprojectrestapi.exception.NotFountException;
import peaksoft.finalprojectrestapi.model.Course;
import peaksoft.finalprojectrestapi.model.Response;
import peaksoft.finalprojectrestapi.repository.CourseRepository;
import peaksoft.finalprojectrestapi.service.CompanyService;
import peaksoft.finalprojectrestapi.service.CourseService;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static org.springframework.http.HttpStatus.*;

@Service
@AllArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final CourseMapper courseMapper;
    private final CompanyService companyService;

    @Override
    public Response saveCourse(CourseDto course,Long id) {
//        String courseName = course.getCourseName();
//        Optional<Course> byCourseName = courseRepository.findByCourseName(courseName);
//
//        if (byCourseName.isPresent()) {
//            throw new BadRequestException("Course with course name=" + courseName + "already exists");
//
//        }

        Course course1 = courseMapper.create(course);
        course1.setCompany(companyService.findByCompanyId(id));
        Course saveCourse = courseRepository.save(course1);

        return Response.builder().httpStatus(CREATED).
                message(String.format("Course with email = %s successfully registered",
                        saveCourse.getCourseName())).build();
    }

    @Override
    public List<Course> getAllCourse() {
        List<Course> courses = courseRepository.findAll().stream().toList();
        return courses;
    }

    @Override
    public Course findByCourseId(Long id) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> {
                            throw new NotFountException("Course with id not found from data base");
                        }
                );

        return course;
    }

    @Override
    public Response deleteCourseId(Long id) {
        courseRepository.deleteById(id);
        return Response.builder().httpStatus(OK).build();
    }

    @Transactional
    @Override
    public Response updateCourseById(Long id, CourseDto newCourse) {
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
