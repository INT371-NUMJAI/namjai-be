package int371.namjai.domain.security_auth;


import int371.namjai.domain.foundation.Foundation;
import int371.namjai.domain.foundation.FoundationRepository;
import int371.namjai.domain.user.User;
import int371.namjai.domain.user.UserRepository;
import int371.namjai.domain.user.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private FoundationRepository foundationRepo;


    //    public String getProfileNameDisplay(String role, String email) {
    public String getProfileNameDisplay(String userUUID) {
        String profileName = "";
        User userFound = userRepo.findById(userUUID).orElseThrow(UserNotFoundException::new);
        if (userFound.getRole().getRoleUUid().equals("3")) {
            Foundation newFoundation = foundationRepo.findByEmailIgnoreCase(userFound.getEmail());
            profileName = newFoundation.getFdnName();
        } else if (userFound.getRole().getRoleUUid().equals("2")) {
            User user = userRepo.findByEmailIgnoreCase(userFound.getEmail());
            profileName = user.getUserName();
        }
        return profileName;
    }

}
