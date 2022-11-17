package int371.namjai.domain.volunteer_registerred;

import int371.namjai.domain.volunteer_projects.VolunteerProjects;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "volunteer_enrolled")
@Getter
@Setter
@NoArgsConstructor
public class VolunteerEnrolled {
    @Id
    @Column(name = "volunteer_enrolled_uuid")
    private String volunteerEnrolledUUID;

    @Column(name = "is_member")
    private Boolean isMember;

    @Column(name = "fname")
    private String firstName;

    @Column(name = "lname")
    private String lastName;

    @Column(name = "contact_number")
    private String contactNumber;

    @Column(name = "email")
    private String email;

    @ManyToOne
    @JoinColumn(name = "volunteer_projects_uuid")
    private VolunteerProjects volunteerProjects = new VolunteerProjects();




}
