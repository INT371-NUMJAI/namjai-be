package int371.namjai.domain.volunteer_registerred.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class EnrolledListVolunteerProjectDTO {
    private String volunteerProjectUUID;
    private String volunteerProjectName;
    private List<EnrolledListVolunteerProject> enrolledListVolunteerProjectList;

}
