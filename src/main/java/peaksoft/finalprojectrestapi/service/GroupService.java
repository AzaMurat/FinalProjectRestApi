package peaksoft.finalprojectrestapi.service;
import peaksoft.finalprojectrestapi.dto.GroupDto;
import peaksoft.finalprojectrestapi.model.Group;
import peaksoft.finalprojectrestapi.model.Response;

import java.util.List;

public interface GroupService {

    Response saveGroup(GroupDto group,Long id);

    List<Group> getAllGroup();

    Group findByGroupId(Long id);

    Response deleteGroupId(Long id);

    Response updateGroupById(Long id, GroupDto group);
}
