package peaksoft.finalprojectrestapi.service;

import peaksoft.finalprojectrestapi.model.Course;
import peaksoft.finalprojectrestapi.model.Group;
import peaksoft.finalprojectrestapi.model.Response;

import java.util.List;
import java.util.UUID;

public interface GroupService {

    Response saveGroup(Group group);

    List<Group> getAllGroup();

    Group findByGroupId(UUID id);

    Response deleteGroupId(UUID id);

    Response updateGroupById(UUID id, Group group);
}
