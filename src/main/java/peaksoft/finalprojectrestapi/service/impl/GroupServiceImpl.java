package peaksoft.finalprojectrestapi.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import peaksoft.finalprojectrestapi.dto.GroupDto;
import peaksoft.finalprojectrestapi.maper.GroupMapper;
import peaksoft.finalprojectrestapi.exception.BadRequestException;
import peaksoft.finalprojectrestapi.exception.NotFountException;
import peaksoft.finalprojectrestapi.model.Group;
import peaksoft.finalprojectrestapi.model.Response;
import peaksoft.finalprojectrestapi.repository.GroupRepository;
import peaksoft.finalprojectrestapi.service.CourseService;
import peaksoft.finalprojectrestapi.service.GroupService;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

import static org.springframework.http.HttpStatus.*;

@Service
@AllArgsConstructor
public class GroupServiceImpl implements GroupService {

    private final GroupRepository groupRepository;
    private final GroupMapper groupMapper;
    private final CourseService courseService;


    @Override
    public Response saveGroup(GroupDto group,Long id) {
        String groupName = group.getGroupName();
        checkCourseName(groupName);
        Group groups = groupMapper.creat(group);
        groups.setCourse(courseService.findByCourseId(id));
        Group saveGroups = groupRepository.save(groups);

        return Response.builder().httpStatus(CREATED).
                message(String.format("Group with email = %s successfully registered",
                        saveGroups.getGroupName())).build();
    }
    private void checkCourseName(String groupName) {
        boolean exists = groupRepository.existsByName(groupName);
        if (exists) {
            throw new BadRequestException("Course with course name" + groupName + "already exists");
        }
    }

    @Override
    public List<Group> getAllGroup() {
        List<Group> groups = groupRepository.findAll().stream().toList();
        return groups;
    }

    @Override
    public Group findByGroupId(Long id) {
        Group group = groupRepository.findById(id)
                .orElseThrow(() -> {
                            throw new NotFountException("Group with id not found from data base");
                        }
                );

        return group;
    }

    @Override
    public Response deleteGroupId(Long id) {
        groupRepository.deleteById(id);
        return Response.builder().httpStatus(OK).build();
    }

    @Transactional
    @Override
    public Response updateGroupById(Long id, GroupDto newGroup) {
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
