package int371.namjai.domain.backoffice;

import int371.namjai.domain.foundation.Foundation;
import int371.namjai.domain.foundation.FoundationRepository;
import int371.namjai.domain.user.User;
import int371.namjai.domain.user.UserRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/backoffice")
@PreAuthorize("hasRole('ADMIN')")
@CrossOrigin("*")
public class BackOfficeController {

    private UserRepository userRepository;
    private FoundationRepository foundationRepository;

    public BackOfficeController(UserRepository userRepository, FoundationRepository foundationRepository) {
        this.userRepository = userRepository;
        this.foundationRepository = foundationRepository;
    }

    @GetMapping(value = "/users")
//    @PreAuthorize("hasRole('ADMIN')")
    private List<User> getAllUser(){
        return userRepository.findAll();
    }
//
    @GetMapping(value = "/foundation/{id}")
//    @PreAuthorize("hasRole('ADMIN')")
    private Foundation getFoundationById(@PathVariable("id") String fdnUUid){
        return foundationRepository.findById(fdnUUid).orElse(null);
    }



}
