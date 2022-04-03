package peaksoft.finalprojectrestapi.service;

import peaksoft.finalprojectrestapi.model.Response;
import peaksoft.finalprojectrestapi.model.Student;
import peaksoft.finalprojectrestapi.model.Teacher;

import java.util.List;
import java.util.UUID;

public interface TeacherService {

    Response saveTeacher(Teacher teacher);

    List<Teacher> getAllTeacher();

    Teacher findByTeacherId(UUID id);

    Response deleteTeacherId(UUID id);

    Response updateTeacherById(UUID id, Teacher teacher);

}
