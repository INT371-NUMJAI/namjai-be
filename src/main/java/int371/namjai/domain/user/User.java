package int371.namjai.domain.user;

import int371.namjai.domain.role.Role;
import int371.namjai.domain.target_catergories.TargetCategories;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @Column(name = "user_uuid")
    private String userUUid;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "fname", nullable = false)
    private String firstName;

    @Column(name = "lname", nullable = false)
    private String lastName;

    @Column(name = "username", nullable = false)
    private String userName;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "create_date", nullable = false)
    private LocalDate createDate;

    @Column(name = "status")
    private String status;

    @Column(name = "profile_path")
    private String profilePath;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role = new Role();

    @ManyToMany
    @JoinTable(
            name = "user_suggestion",
            joinColumns = @JoinColumn(name = "user_uuid"),
            inverseJoinColumns = @JoinColumn(name = "target_category_id"))
    Set<TargetCategories> targetCategoriesSuggestion;



}
