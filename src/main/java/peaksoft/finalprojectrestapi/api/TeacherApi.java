package peaksoft.finalprojectrestapi.api;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import peaksoft.finalprojectrestapi.dto.TeacherDto;
import peaksoft.finalprojectrestapi.exception.BadRequestException;
import peaksoft.finalprojectrestapi.model.Company;
import peaksoft.finalprojectrestapi.model.Response;
import peaksoft.finalprojectrestapi.model.Teacher;
import peaksoft.finalprojectrestapi.service.impl.TeacherServiceImpl;

import java.util.List;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@RestController
@RequestMapping("api/teachers")
@AllArgsConstructor
public class TeacherApi {

    private final TeacherServiceImpl teacherService;

    @PostMapping("/save/{courseId}")
    public Response saveNewTeacher(@RequestBody TeacherDto teacher,@PathVariable("courseId") Long id) {
        return teacherService.saveTeacher(teacher,id);
    }

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(BAD_REQUEST)
    public Response handlerBadRequestException(BadRequestException badRequestException) {
        return Response.builder().httpStatus(BAD_REQUEST).message(badRequestException.getMessage()).build();

    }

    @GetMapping
    public List<Teacher> getAllTeacher() {
        return teacherService.getAllTeacher();

    }

    @GetMapping("/getById/{id}")
    public Teacher getByTeacherId(@PathVariable Long id){
        return teacherService.findByTeacherId(id);

    }

    @DeleteMapping("/delete/{id}")
    public Response deleteById(@PathVariable Long id) {
        return teacherService.deleteTeacherId(id);

    }

    @PutMapping("/update/{id}")
    public Response updateTeacherById(@PathVariable Long id, TeacherDto teacher) {
        return teacherService.updateTeacherById(id, teacher);
    }
}
