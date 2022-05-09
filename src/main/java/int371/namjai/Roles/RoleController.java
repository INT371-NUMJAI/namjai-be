package int371.namjai.Roles;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.management.relation.Role;
import java.util.List;

@RestController
public class RoleController {
    private RoleRepository roleRepository;
    public RoleController(RoleRepository roleRepository) {
        this.roleRepository=roleRepository;
    }

    @GetMapping("/check")
    public List<Roles> getAllRoles(){
        return roleRepository.findAll();
    }
}
