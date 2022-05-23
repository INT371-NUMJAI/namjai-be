package int371.namjai.domain.foundation;

import int371.namjai.domain.user.User;
import int371.namjai.domain.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestWrapper;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin("*")
public class FoundationController {
    @Autowired
    private UserRepository userRepo;

//    @Bean
//    public SecurityContextHolderAwareRequestWrapper securityContextHolderAwareRequestWrapper (){
//        return new SecurityContextHolderAwareRequestWrapper();
//    }

    @GetMapping(value = "/users")
    @PreAuthorize("hasRole('ADMIN')")
    List<User> getAllUser(){
            return userRepo.findAll();
    }

}
