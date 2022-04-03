package peaksoft.finalprojectrestapi.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import peaksoft.finalprojectrestapi.exception.BadRequestException;
import peaksoft.finalprojectrestapi.exception.NotFountException;
import peaksoft.finalprojectrestapi.model.Course;
import peaksoft.finalprojectrestapi.model.Group;
import peaksoft.finalprojectrestapi.model.Response;
import peaksoft.finalprojectrestapi.repository.GroupRepository;
import peaksoft.finalprojectrestapi.service.GroupService;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

import static org.springframework.http.HttpStatus.*;

@Service
@AllArgsConstructor
public class GroupServiceImpl implements GroupService {

    private final GroupRepository groupRepository;


    @Override
    public Response saveGroup(Group group) {
        String groupName = group.getGroupName();
        Optional<Group> byGroupName = groupRepository.findByGroupName(groupName);

        if (byGroupName.isPresent()) {
            throw new BadRequestException("Group with cours name=" + groupName + "already exists");

        }

        Group saveGroup = groupRepository.save(group);

        return Response.builder().httpStatus(CREATED).
                message(String.format("Group with email = %s successfully registered",
                        saveGroup.getGroupName())).build();
    }

    @Override
    public List<Group> getAllGroup() {
        List<Group> groups = groupRepository.findAll().stream().toList();
        return groups;
    }

    @Override
    public Group findByGroupId(UUID id) {
        Group group = groupRepository.findById(id)
                .orElseThrow(() -> {
                            throw new NotFountException("Group with id not found from data base");
                        }
                );

        return group;
    }

    @Override
    public Response deleteGroupId(UUID id) {
        groupRepository.deleteById(id);
        return Response.builder().httpStatus(OK).build();
    }

    @Override
    public Response updateGroupById(UUID id, Group newGroup) {
        Group oldGroup = findByGroupId(id);
        String currentGroupName = oldGroup.getGroupName();
        String newGroupName = newGroup.getGroupName();
        if (!Objects.equals(currentGroupName, newGroupName)) {
            oldGroup.setGroupName(newGroupName);
        }
        String currentDateOfStart = oldGroup.getDateOfStart();
        String newGroupDateOfStart = newGroup.getDateOfStart();
        if (!Objects.equals(currentDateOfStart, newGroupDateOfStart)) {
            oldGroup.setDateOfStart(newGroupDateOfStart);
        }
        String message = String.format("Group with id %s successfully updated");
        return Response.builder().httpStatus(RESET_CONTENT).message(message).build();
    }
}
