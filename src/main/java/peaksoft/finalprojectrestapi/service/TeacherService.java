package peaksoft.finalprojectrestapi.service;
import peaksoft.finalprojectrestapi.dto.TeacherDto;
import peaksoft.finalprojectrestapi.model.Response;
import peaksoft.finalprojectrestapi.model.Teacher;
import java.util.List;

public interface TeacherService {

    Response saveTeacher(TeacherDto teacher, Long id);

    List<Teacher> getAllTeacher();

    Teacher findByTeacherId(Long id);

    Response deleteTeacherId(Long id);

    Response updateTeacherById(Long id, TeacherDto teacher);

}
