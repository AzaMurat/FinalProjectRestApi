package peaksoft.finalprojectrestapi.api;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import peaksoft.finalprojectrestapi.dto.CourseDto;
import peaksoft.finalprojectrestapi.exception.BadRequestException;
import peaksoft.finalprojectrestapi.model.Course;
import peaksoft.finalprojectrestapi.model.Response;
import peaksoft.finalprojectrestapi.service.CourseService;

import java.util.List;
import java.util.UUID;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@RestController
@RequestMapping("api/courses")
@AllArgsConstructor
public class CourseApi {

    private final CourseService courseService;

    @PostMapping("/saveCourse")
    public Response registerNewCourse(@RequestBody CourseDto course) {
        return courseService.saveCourse(course);
    }

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(BAD_REQUEST)
    public Response handlerBadRequestException(BadRequestException badRequestException) {
        return Response.builder().httpStatus(BAD_REQUEST)
                .message(badRequestException.getMessage())
                .build();
    }

    @GetMapping
    public List<Course> getAllCourse() {
        return courseService.getAllCourse();
    }

    @DeleteMapping("/deleteCourse/{id}")
    public Response deleteById(@PathVariable UUID id) {
        return courseService.deleteCourseId(id);
    }

    @PutMapping("/updateCourse/{id}")
    public Response updateCourseById(@PathVariable UUID id, CourseDto course) {
        return courseService.updateCourseById(id, course);
    }
}