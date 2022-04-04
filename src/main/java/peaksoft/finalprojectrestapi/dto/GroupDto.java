package peaksoft.finalprojectrestapi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GroupDto {

    private String groupName;

    private String dateOfStart;

    private String dateOfFinish;
}
