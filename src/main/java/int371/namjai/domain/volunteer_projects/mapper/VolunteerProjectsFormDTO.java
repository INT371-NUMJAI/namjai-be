package int371.namjai.domain.volunteer_projects.mapper;

import int371.namjai.domain.target_catergories.TargetCategories;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.util.Set;

@Getter
@Setter
public class VolunteerProjectsFormDTO {

    private String volunteerProjectsUUID;
    private String volunteerProjectName;
    private Integer peopleNeeded;
    private String description;
    private String activityType;
    private Date startDate;
    private Date endDate;
    private Date activityStartDate;
    private Date activityEndDate;
    //    private Timestamp createDate;
    private String locationDetail;
    private String locationDistrict;
    private String locationSubDistrict;
    private String locationProvince;
    private String locationPostalCode;
    private Set<TargetCategories> targetCategoriesSet;
    private String duty;
    private String qualify;
    //    private String picturePath;
    private String foundationUUID;
    private String userUUID;

}
