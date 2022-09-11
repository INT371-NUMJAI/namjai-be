package int371.namjai.domain.security_auth;


import int371.namjai.domain.foundation.Foundation;
import int371.namjai.domain.foundation.FoundationRepository;
import int371.namjai.domain.user.User;
import int371.namjai.domain.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private FoundationRepository foundationRepo;


    public String getProfileNameDisplay(String role, String email) {
        String profileName = "";
        if (role.equals("ROLE_FDN")) {
            Foundation newFoundation = foundationRepo.findByEmailIgnoreCase(email);
            profileName = newFoundation.getFdnName();
        } else if (role.equals("ROLE_USER")) {
            User user = userRepo.findByEmailIgnoreCase(email);
            profileName = user.getUserName();
        }
        return profileName;
    }

}
