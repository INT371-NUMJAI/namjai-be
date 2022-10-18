package int371.namjai.domain.volunteer_registerred;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "volunteer_registered_anonymous")
@Getter
@Setter
@NoArgsConstructor
public class VolunteerRegisteredAnonymous {
    @Id
    @Column(name = "volunteer_registered_anonymous_uuid")
    private String volunteerRegisteredAnonymousUUID;

    @Column(name = "fname")
    private String firstName;

    @Column(name = "lname")
    private String lastName;

    @Column(name = "contact_number")
    private String contactNumber;

    @Column(name = "email")
    private String email;


}
