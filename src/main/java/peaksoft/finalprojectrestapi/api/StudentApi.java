package peaksoft.finalprojectrestapi.api;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import peaksoft.finalprojectrestapi.exception.BadRequestException;
import peaksoft.finalprojectrestapi.model.Group;
import peaksoft.finalprojectrestapi.model.Response;
import peaksoft.finalprojectrestapi.model.Student;
import peaksoft.finalprojectrestapi.service.impl.StudentServiceImpl;

import java.util.List;
import java.util.UUID;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@RestController
@RequestMapping("api/students")
@AllArgsConstructor
public class StudentApi {

    private final StudentServiceImpl studentService;

    @PostMapping("/saveStudent")
    public Response saveNewStudent(@RequestBody Student student) {
        return studentService.saveStudent(student);
    }

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(BAD_REQUEST)
    public Response handlerBadRequestException(BadRequestException badRequestException) {
        return Response.builder().httpStatus(BAD_REQUEST).message(badRequestException.getMessage()).build();
    }


    @GetMapping
    public List<Student> getAllStudent() {
        return studentService.getAllStudent();
    }

    @DeleteMapping("/deleteStudent/{id}")
    public Response deleteById(@PathVariable UUID id) {
        return studentService.deleteStudentId(id);
    }

    @PutMapping("/updateStudent/{id}")
    public Response updateStudentById(@PathVariable UUID id, Student student) {
        return studentService.updateStudentById(id, student);
    }
}
