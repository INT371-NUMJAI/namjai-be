package int371.namjai.domain.volunteer_projects.mapper;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VolunteerProjectShort {
    private String volunteerProjectUUID;
    private String volunteerProjectName;
    private Integer peopleNeeded;
    private Integer peopleRegistered;
    private String locationProvince;
    private String activityStartDate;
    private String activityEndDate;
//    private String picturePath;
}
