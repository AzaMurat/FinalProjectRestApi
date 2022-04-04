package peaksoft.finalprojectrestapi.maper;

import org.springframework.stereotype.Component;
import peaksoft.finalprojectrestapi.dto.GroupDto;
import peaksoft.finalprojectrestapi.model.Group;

@Component
public class GroupMapper {

    public Group creat(GroupDto groupDto){

        if (groupDto==null){

            return null;
        }

        Group group = new Group();
        group.setGroupName(groupDto.getGroupName());
        group.setDateOfStart(groupDto.getDateOfStart());
        group.setDateOfFinish(groupDto.getDateOfFinish());
         return group;
    }
}
