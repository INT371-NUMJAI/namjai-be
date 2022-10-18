package int371.namjai.domain.volunteer_projects;

import int371.namjai.domain.foundation.Foundation;
import int371.namjai.domain.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;

@Entity
@Table(name = "volunteers_projects")
@Getter
@Setter
@NoArgsConstructor
public class VolunteerProjects {
    @Id
    @Column(name = "volunteer_projects_uuid")
    private String volunteerProjectsUUID;

    @Column(name = "vp_name", unique = true)
    private String volunteerProjectName;

    @Column(name = "people_needed")
    private Integer peopleNeeded;

    @Column(name = "people_register")
    private Integer peopleRegistered;

    @Column(name = "description")
    private String description;

    @Column(name = "qualify")
    private String qualify;

    @Column(name = "activity_type")
    private String activityType;

    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "end_date")
    private Date endDate;

    @Column(name = "activity_start_date")
    private Date activityStartDate;

    @Column(name = "activity_end_date")
    private Date activityEndDate;

    @Column(name = "create_date")
    private Timestamp createDate;

    @Column(name = "location_detail")
    private String locationDetail;

    @Column(name = "location_district")
    private String locationDistrict;

    @Column(name = "location_subdistrict")
    private String locationSubDistrict;

    @Column(name = "location_province")
    private String locationProvince;

    @Column(name = "location_postalcose")
    private String locationPostalCode;


    @Column(name = "picture_path")
    private String picturePath;

    @ManyToOne
    @JoinColumn(name = "fdn_uuid", nullable = true)
    private Foundation foundation = new Foundation();

    @ManyToOne
    @JoinColumn(name = "user_uuid", nullable = true)
    private User user = new User();

}
