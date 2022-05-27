package int371.namjai.domain.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


@Component("userService")
@Scope("singleton")
@Service
public class UserService {
    @Autowired
    private UserRepository userRepo;


    public User getUserCurrent(Authentication auth){
        String userCurrent  = auth.getName();
        return    userRepo.findByEmailIgnoreCaseAndStatusActive(userCurrent);
    }

    public Boolean checkUserNameIsAlreadyExists(String email){
        return userRepo.existsByEmail(email);
    }
}
