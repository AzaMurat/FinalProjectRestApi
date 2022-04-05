package peaksoft.finalprojectrestapi.maper;

import org.springframework.stereotype.Component;
import peaksoft.finalprojectrestapi.dto.StudentDto;
import peaksoft.finalprojectrestapi.model.Student;

@Component
public class StudentMapper {

    public Student create(StudentDto studentDto){
        if (studentDto==null){
            return null;
        }

        Student student = new Student();
        student.setFirstName(studentDto.getFirstName());
        student.setLastName(studentDto.getLastName());
        student.setEmail(studentDto.getEmail());
        student.setStudyFormat(studentDto.getStudyFormat());
        return student;
    }
}
