package peaksoft.finalprojectrestapi.api;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import peaksoft.finalprojectrestapi.dto.GroupDto;
import peaksoft.finalprojectrestapi.exception.BadRequestException;
import peaksoft.finalprojectrestapi.model.Company;
import peaksoft.finalprojectrestapi.model.Group;
import peaksoft.finalprojectrestapi.model.Response;
import peaksoft.finalprojectrestapi.service.impl.GroupServiceImpl;

import java.util.List;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@RestController
@RequestMapping("api/groups")
@AllArgsConstructor
public class GroupApi {

    private final GroupServiceImpl groupService;

    @PostMapping("/save/{courseId}")
    public Response saveNewGroup(@RequestBody GroupDto group,@PathVariable("courseId") Long id) {
        return groupService.saveGroup(group,id);
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

    @GetMapping("/getById/{id}")
    public Group getByGroupId(@PathVariable Long id){
        return groupService.findByGroupId(id);

    }

    @DeleteMapping("/delete/{id}")
    public Response deleteById(@PathVariable Long id) {
        return groupService.deleteGroupId(id);

    }

    @PutMapping("/update/{id}")
    public Response updateGroupById(@PathVariable Long id, GroupDto group) {
        return groupService.updateGroupById(id, group);

    }
}
