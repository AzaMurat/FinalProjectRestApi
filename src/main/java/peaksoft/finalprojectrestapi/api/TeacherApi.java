package peaksoft.finalprojectrestapi.api;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import peaksoft.finalprojectrestapi.dto.TeacherDto;
import peaksoft.finalprojectrestapi.exception.BadRequestException;
import peaksoft.finalprojectrestapi.model.Response;
import peaksoft.finalprojectrestapi.model.Teacher;
import peaksoft.finalprojectrestapi.service.impl.TeacherServiceImpl;

import java.util.List;
import java.util.UUID;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@RestController
@RequestMapping("api/teachers")
@AllArgsConstructor
public class TeacherApi {

    private final TeacherServiceImpl teacherService;

    @PostMapping("/saveTeachers")
    public Response saveNewTeacher(@RequestBody TeacherDto teacher) {
        return teacherService.saveTeacher(teacher);
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

    @DeleteMapping("/deleteTeacher/{id}")
    public Response deleteById(@PathVariable UUID id) {
        return teacherService.deleteTeacherId(id);
    }

    @PutMapping("/updateTeacher/{id}")
    public Response updateTeacherById(@PathVariable UUID id, TeacherDto teacher) {
        return teacherService.updateTeacherById(id, teacher);
    }
}
