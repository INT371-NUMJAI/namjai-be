package int371.namjai.domain.role;

import int371.namjai.utill.UserRoleName;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RoleController {
    private RoleRepository roleRepository;
    public RoleController(RoleRepository roleRepository) {
        this.roleRepository=roleRepository;
    }

    @GetMapping("/check")
    public List<Role> getAllRoles(){
        return roleRepository.findAll();
    }

    @PostMapping("/role")
    public ResponseEntity<Void> createRole(){
//       Role newRole = new Role(roleReq.getRoleUUid(),roleReq.getRoleName());
//        Role newRole = new Role("1",UserRoleName.ROLE_ADMIN);
//        Role newRole = new Role("2",UserRoleName.ROLE_USER);
        Role newRole = new Role("3", UserRoleName.ROLE_FDN);
//       newRole.setRoleUUid("1");
//       newRole.setRoleName(UserRoleName.ROLE_ADMIN);
        roleRepository.save(newRole);
        return ResponseEntity.ok().build();

    }
}
