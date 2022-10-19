package int371.namjai.domain.volunteer_projects;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "volunteers_projects_qualifies")
@Getter
@Setter
@NoArgsConstructor
@IdClass(VolunteerProjectQualifies.class)
public class VolunteerProjectQualifies implements Serializable {
    @Id
    @Column(name = "volunteer_projects_uuid")
    private String volunteerProjectsUUID;

    @Id
    @Column(name = "qualifies_detail")
    private String qualifiesDetail;

}
