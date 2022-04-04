package peaksoft.finalprojectrestapi.api;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import peaksoft.finalprojectrestapi.dto.CourseDto;
import peaksoft.finalprojectrestapi.exception.BadRequestException;
import peaksoft.finalprojectrestapi.model.Company;
import peaksoft.finalprojectrestapi.model.Course;
import peaksoft.finalprojectrestapi.model.Response;
import peaksoft.finalprojectrestapi.service.CourseService;

import java.util.List;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@RestController
@RequestMapping("api/courses")
@AllArgsConstructor
public class CourseApi {

    private final CourseService courseService;

    @PostMapping("/save/{companyId}")
    public Response registerNewCourse(@RequestBody CourseDto course,@PathVariable ("companyId") Long id) {
        return courseService.saveCourse(course,id);
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
    @GetMapping("/getById/{id}")
    public Course getByCompanyId(@PathVariable Long id){
        return courseService.findByCourseId(id);
    }

    @DeleteMapping("/delete/{id}")
    public Response deleteById(@PathVariable Long id) {
        return courseService.deleteCourseId(id);
    }

    @PutMapping("/update/{id}")
    public Response updateCourseById(@PathVariable Long id, CourseDto course) {
        return courseService.updateCourseById(id, course);
    }
}