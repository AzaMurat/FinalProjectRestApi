package peaksoft.finalprojectrestapi.service.impl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import peaksoft.finalprojectrestapi.dto.TeacherDto;
import peaksoft.finalprojectrestapi.maper.TeacherMapper;
import peaksoft.finalprojectrestapi.exception.BadRequestException;
import peaksoft.finalprojectrestapi.exception.NotFountException;
import peaksoft.finalprojectrestapi.model.Response;
import peaksoft.finalprojectrestapi.model.Teacher;
import peaksoft.finalprojectrestapi.repository.TeacherRepository;
import peaksoft.finalprojectrestapi.service.CourseService;
import peaksoft.finalprojectrestapi.service.TeacherService;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static org.springframework.http.HttpStatus.*;

@Service
@AllArgsConstructor
public class TeacherServiceImpl implements TeacherService {

    private final TeacherRepository teacherRepository;
    private final TeacherMapper teacherMapper;
    private final CourseService courseService;

    @Override
    public Response saveTeacher(TeacherDto teacher,Long id) {
        String firstName = teacher.getFirstName();
        Optional<Teacher> byFirstName = teacherRepository.findByTeacherName(firstName);

        if (byFirstName.isPresent()) {
            throw new BadRequestException("Teacher with teacher name=" + firstName + "already exists");

        }

        Teacher teacher1 = teacherMapper.create(teacher);
        teacher1.setCourse(courseService.findByCourseId(id));
        Teacher saveTeacher = teacherRepository.save(teacher1);

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
    public Teacher findByTeacherId(Long id) {
        Teacher teacher = teacherRepository.findById(id)
                .orElseThrow(() -> {
                            throw new NotFountException("Teacher with id not found from data base");
                        }
                );

        return teacher;
    }

    @Override
    public Response deleteTeacherId(Long id) {
        teacherRepository.deleteById(id);
        return Response.builder().httpStatus(OK).build();
    }

    @Transactional
    @Override
    public Response updateTeacherById(Long id, TeacherDto newTeacher) {
        Teacher oldTeacher = findByTeacherId(id);
        String currentFirstName = oldTeacher.getFirstName();
        String newFirstName = newTeacher.getFirstName();
        if (!Objects.equals(currentFirstName, newFirstName)) {
            oldTeacher.setFirstName(newFirstName);
        }
        String currentLastName = oldTeacher.getLastName();
        String newLastName = newTeacher.getLastNAme();
        if (!Objects.equals(currentLastName, newLastName)) {
            oldTeacher.setLastName(newLastName);
        }
        String message = String.format("Student with id %s successfully updated");
        return Response.builder().httpStatus(RESET_CONTENT).message(message).build();
    }
}
