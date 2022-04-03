package peaksoft.finalprojectrestapi.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import peaksoft.finalprojectrestapi.exception.BadRequestException;
import peaksoft.finalprojectrestapi.exception.NotFountException;
import peaksoft.finalprojectrestapi.model.Group;
import peaksoft.finalprojectrestapi.model.Response;
import peaksoft.finalprojectrestapi.model.Student;
import peaksoft.finalprojectrestapi.model.enums.StudyFormat;
import peaksoft.finalprojectrestapi.repository.StudentRepository;
import peaksoft.finalprojectrestapi.service.StudentService;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

import static org.springframework.http.HttpStatus.*;

@Service
@AllArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;


    @Override
    public Response saveStudent(Student student) {
        String firstName = student.getFirstName();
        Optional<Student> byFirstName = studentRepository.findByStudentName(firstName);

        if (byFirstName.isPresent()) {
            throw new BadRequestException("Student with student name=" + firstName + "already exists");

        }

        Student saveStudent = studentRepository.save(student);

        return Response.builder().httpStatus(CREATED).
                message(String.format("Student with name = %s successfully registered",
                        saveStudent.getFirstName())).build();
    }

    @Override
    public List<Student> getAllStudent() {
        List<Student> students = studentRepository.findAll().stream().toList();
        return students;
    }

    @Override
    public Student findByStudentId(UUID id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> {
                            throw new NotFountException("Student with id not found from data base");
                        }
                );

        return student;    }

    @Override
    public Response deleteStudentId(UUID id) {
        studentRepository.deleteById(id);
        return Response.builder().httpStatus(OK).build();
    }

    @Override
    public Response updateStudentById(UUID id, Student newStudent) {
        Student oldStudent = findByStudentId(id);
        String currentStudentName = oldStudent.getFirstName();
        String newStudentName = newStudent.getFirstName();
        if (!Objects.equals(currentStudentName, newStudentName)) {
            oldStudent.setFirstName(newStudentName);
        }
        String currentLastName = oldStudent.getLastName();
        String newLastName = newStudent.getLastName();
        if (!Objects.equals(currentLastName, newLastName)) {
            oldStudent.setLastName(newLastName);
        }
        StudyFormat currentFormat = oldStudent.getStudyFormat();
        StudyFormat newFormat = newStudent.getStudyFormat();
        if (!Objects.equals(currentFormat,newFormat)){
            oldStudent.setStudyFormat(newFormat);
        }
        String message = String.format("Student with id %s successfully updated");
        return Response.builder().httpStatus(RESET_CONTENT).message(message).build();
    }
}
