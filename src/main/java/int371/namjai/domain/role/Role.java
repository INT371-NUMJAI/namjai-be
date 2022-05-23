package int371.namjai.domain.role;


import int371.namjai.utill.UserRoleName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Entity
@Table(name = "roles")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Role implements GrantedAuthority {
    @Id
    @Column(name="role_uuid")
    private String roleUUid;

    @Column(name="role_name")
    @Enumerated( EnumType.STRING)
    private UserRoleName roleName;


    @Override
    public String getAuthority() {
        return roleName.name();
    }
}
