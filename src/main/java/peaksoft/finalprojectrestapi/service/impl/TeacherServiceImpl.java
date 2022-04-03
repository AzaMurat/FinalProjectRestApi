package peaksoft.finalprojectrestapi.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import peaksoft.finalprojectrestapi.exception.BadRequestException;
import peaksoft.finalprojectrestapi.exception.NotFountException;
import peaksoft.finalprojectrestapi.model.Response;
import peaksoft.finalprojectrestapi.model.Teacher;
import peaksoft.finalprojectrestapi.repository.TeacherRepository;
import peaksoft.finalprojectrestapi.service.TeacherService;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

import static org.springframework.http.HttpStatus.*;

@Service
@AllArgsConstructor
public class TeacherServiceImpl implements TeacherService {

    private final TeacherRepository teacherRepository;

    @Override
    public Response saveTeacher(Teacher teacher) {
        String firstName = teacher.getFirstName();
        Optional<Teacher> byFirstName = teacherRepository.findByTeacherName(firstName);

        if (byFirstName.isPresent()) {
            throw new BadRequestException("Teacher with teacher name=" + firstName + "already exists");

        }

        Teacher saveTeacher = teacherRepository.save(teacher);

        return Response.builder().httpStatus(CREATED).
                message(String.format("Teacher with name = %s successfully registered",
                        saveTeacher.getFirstName())).build();
    }

    @Override
    public List<Teacher> getAllTeacher() {
        List<Teacher> teachers = teacherRepository.findAll().stream().toList();
        return teachers;
    }

    @Override
    public Teacher findByTeacherId(UUID id) {
        Teacher teacher = teacherRepository.findById(id)
                .orElseThrow(() -> {
                            throw new NotFountException("Teacher with id not found from data base");
                        }
                );

        return teacher;
    }

    @Override
    public Response deleteTeacherId(UUID id) {
        teacherRepository.deleteById(id);
        return Response.builder().httpStatus(OK).build();
    }

    @Override
    public Response updateTeacherById(UUID id, Teacher newTeacher) {
        Teacher oldTeacher = findByTeacherId(id);
        String currentFirstName = oldTeacher.getFirstName();
        String newFirstName = newTeacher.getFirstName();
        if (!Objects.equals(currentFirstName, newFirstName)) {
            oldTeacher.setFirstName(newFirstName);
        }
        String currentLastName = oldTeacher.getLastName();
        String newLastName = newTeacher.getLastName();
        if (!Objects.equals(currentLastName, newLastName)) {
            oldTeacher.setLastName(newLastName);
        }
        String message = String.format("Student with id %s successfully updated");
        return Response.builder().httpStatus(RESET_CONTENT).message(message).build();
    }
}
