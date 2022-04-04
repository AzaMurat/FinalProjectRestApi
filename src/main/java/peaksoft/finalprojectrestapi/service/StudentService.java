package peaksoft.finalprojectrestapi.service;
import peaksoft.finalprojectrestapi.dto.StudentDto;
import peaksoft.finalprojectrestapi.model.Response;
import peaksoft.finalprojectrestapi.model.Student;

import java.util.List;

public interface StudentService {


    Response saveStudent(StudentDto student, Long id);

    List<Student> getAllStudent();

    Student findByStudentId(Long id);

    Response deleteStudentId(Long id);

    Response updateStudentById(Long id, StudentDto student);
}
