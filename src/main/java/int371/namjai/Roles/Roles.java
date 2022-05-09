package int371.namjai.Roles;



import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "roles")
@Data
public class Roles {
    @Id
    @Column(name="role_uuid")
    private String roleUuid;

    @Column(name="role_name")
    private String roleName;

}
