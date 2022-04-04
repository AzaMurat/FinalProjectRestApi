package peaksoft.finalprojectrestapi.maper;

import org.springframework.stereotype.Component;
import peaksoft.finalprojectrestapi.dto.TeacherDto;
import peaksoft.finalprojectrestapi.model.Teacher;

@Component
public class TeacherMapper {

    public Teacher create(TeacherDto teacherDto){
        if (teacherDto==null){
            return null;
        }
        Teacher teacher = new Teacher();
        teacher.setFirstName(teacherDto.getFirstName());
        teacher.setLastName(teacherDto.getLastNAme());
        return teacher;
    }
}
