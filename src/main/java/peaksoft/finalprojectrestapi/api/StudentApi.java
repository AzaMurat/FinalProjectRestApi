package peaksoft.finalprojectrestapi.api;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import peaksoft.finalprojectrestapi.dto.StudentDto;
import peaksoft.finalprojectrestapi.exception.BadRequestException;
import peaksoft.finalprojectrestapi.model.Company;
import peaksoft.finalprojectrestapi.model.Response;
import peaksoft.finalprojectrestapi.model.Student;
import peaksoft.finalprojectrestapi.service.impl.StudentServiceImpl;
import java.util.List;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@RestController
@RequestMapping("api/students")
@AllArgsConstructor
public class StudentApi {

    private final StudentServiceImpl studentService;

    @PostMapping("/save/{groupId}")
    public Response saveNewStudent(@RequestBody StudentDto student,@PathVariable("groupId") Long id) {
        return studentService.saveStudent(student,id);

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

    @GetMapping("/getById/{id}")
    public Student getByStudentId(@PathVariable Long id){
        return studentService.findByStudentId(id);

    }

    @DeleteMapping("/deleteStudent/{id}")
    public Response deleteById(@PathVariable Long id) {
        return studentService.deleteStudentId(id);

    }

    @PutMapping("/updateStudent/{id}")
    public Response updateStudentById(@PathVariable Long id, StudentDto student) {
        return studentService.updateStudentById(id, student);

    }
}
