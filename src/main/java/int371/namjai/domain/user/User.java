package int371.namjai.domain.user;

import int371.namjai.domain.role.Role;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
public class User {
    @Id
    @Column(name = "user_uuid")
    private String userUUid;

    @Column(name = "username", unique = true, nullable = false)
    private String userName;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "fname", nullable = false)
    private String firstName;

    @Column(name = "lname", nullable = false)
    private String lastName;

    @Column(name = "password", nullable = false)
    private String password;


    @Column(name = "create_date", nullable = false)
    private Timestamp createDate;
    //    @ManyToMany(fetch=FetchType.EAGER)
//    @JoinTable(name="users_roles",
//            joinColumns = @JoinColumn(name="user_uuid",referencedColumnName = "user_uuid"),inverseJoinColumns = @JoinColumn(name="role_uuid",referencedColumnName = "role_uuid"))
//    private Set<Role> roles = new HashSet<>();
//
    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role = new Role();

//    public void setRole(Role role){
//        this.roles.add(role);
//    }


    public User(String userUUid, String userName, String email, String firstName, String lastName, String password) {
        this.userUUid = userUUid;
        this.userName = userName;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
    }


}
