package peaksoft.finalprojectrestapi.api;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import peaksoft.finalprojectrestapi.exception.BadRequestException;
import peaksoft.finalprojectrestapi.model.Course;
import peaksoft.finalprojectrestapi.model.Group;
import peaksoft.finalprojectrestapi.model.Response;
import peaksoft.finalprojectrestapi.service.impl.GroupServiceImpl;

import java.util.List;
import java.util.UUID;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@RestController
@RequestMapping("api/groups")
@AllArgsConstructor
public class GroupApi {

    private final GroupServiceImpl groupService;

    @PostMapping("/saveGroup")
    public Response saveNewGroup(@RequestBody Group group) {
        return groupService.saveGroup(group);
    }

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(BAD_REQUEST)
    public Response handlerBadRequestException(BadRequestException badRequestException) {
        return Response.builder().httpStatus(BAD_REQUEST).message(badRequestException.getMessage()).build();
    }

    @GetMapping
    public List<Group> getAllGroup() {
        return groupService.getAllGroup();
    }

    @DeleteMapping("/deleteGroup/{id}")
    public Response deleteById(@PathVariable UUID id) {
        return groupService.deleteGroupId(id);
    }

    @PutMapping("/updateGroup/{id}")
    public Response updateGroupById(@PathVariable UUID id, Group group) {
        return groupService.updateGroupById(id, group);
    }
}
