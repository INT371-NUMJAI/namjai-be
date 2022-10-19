package int371.namjai.domain.volunteer_projects;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "volunteers_projects_duties")
@Getter
@Setter
@NoArgsConstructor
@IdClass(VolunteerProjectDuties.class)
public class VolunteerProjectDuties implements Serializable {
    @Id
    @Column(name = "volunteer_projects_uuid")
    private String volunteerProjectsUUID;

    @Id
    @Column(name = "duty_detail")
    private String dutyDetail;
}
