package int371.namjai.domain.security_auth;


import int371.namjai.domain.foundation.Foundation;
import int371.namjai.domain.foundation.FoundationRepository;
import int371.namjai.domain.user.User;
import int371.namjai.domain.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AuthenticationService {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private FoundationRepository foundationRepo;


    //    public String getProfileNameDisplay(String role, String email) {
    public Map<String, String> getProfileNameDisplay(String email) {
        String uuid = "";
        Map<String, String> responseMap = new HashMap();
        responseMap.put("UUID", "someValue");
        User userFound = userRepo.findByEmailIgnoreCase(email);
        if (userFound.getRole().getRoleUUid().equals("3")) {
            Foundation newFoundation = foundationRepo.findByEmailIgnoreCase(userFound.getEmail());
            uuid = newFoundation.getFdnUUid();
        } else {
            uuid = userFound.getUserUUid();
        }
        responseMap.put("UUID", uuid);
        return responseMap;
    }

}
