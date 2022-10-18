package int371.namjai.domain.volunteer_registerred;

import int371.namjai.domain.user.User;
import int371.namjai.domain.volunteer_projects.VolunteerProjects;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "volunteer_registered")
@Getter
@Setter
@NoArgsConstructor
public class VolunteerRegistered {
    @Id
    @Column(name = "volunteer_registered_uuid")
    private String volunteerRegisteredUUID;

    @ManyToOne
    @JoinColumn(name = "volunteer_projects_uuid")
    private VolunteerProjects volunteerProjects = new VolunteerProjects();

    @ManyToOne
    @JoinColumn(name = "user_uuid", nullable = true)
    private User user = new User();

    @ManyToOne
    @JoinColumn(name = "volunteer_registered_anonymous_uuid", nullable = true)
    private VolunteerRegisteredAnonymous volunteerRegisteredAnonymous = new VolunteerRegisteredAnonymous();


}
