package peaksoft.finalprojectrestapi.service;

import peaksoft.finalprojectrestapi.dto.StudentDto;
import peaksoft.finalprojectrestapi.model.Group;
import peaksoft.finalprojectrestapi.model.Response;
import peaksoft.finalprojectrestapi.model.Student;

import java.util.List;
import java.util.UUID;

public interface StudentService {


    Response saveStudent(StudentDto student);

    List<Student> getAllStudent();

    Student findByStudentId(UUID id);

    Response deleteStudentId(UUID id);

    Response updateStudentById(UUID id, StudentDto student);
}
