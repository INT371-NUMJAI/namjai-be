package int371.namjai.domain.user;

import int371.namjai.domain.foundation.Foundation;
import int371.namjai.domain.foundation.FoundationService;
import int371.namjai.domain.user.dto.ProfileDTO;
import int371.namjai.domain.user.dto.UserSuggestionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserService {
    @Autowired
    private UserRepository userRepo;

    @Autowired
    private FoundationService foundationService;

    public void saveUserSuggestion(UserSuggestionDTO userSuggestionDTO) {
        User user = getUserByEmail(userSuggestionDTO.getUserEmail());
        user.setTargetCategoriesSuggestion(userSuggestionDTO.getTargetCategoriesSuggestion());
        userRepo.save(user);
    }

    public boolean getNumberOfExistsUserInUserSuggestion(String userEmail) {
        boolean isExits = userRepo.getNumberOfUserSuggestion(userEmail) > 0 ? true : false;
        return isExits;
    }

    public ProfileDTO getUserProfile(String userEmail) {
        String profile = null;
        ProfileDTO profileDTO = new ProfileDTO();
        User user = getUserByEmail(userEmail);
        if (user.getRole().getRoleUUid().equalsIgnoreCase("3")) {
            Foundation fdn = foundationService.getFoundationByEmail(userEmail);
            profileDTO.setProfileDetail(fdn.getFdnDetail());
            profileDTO.setProfileAddress(fdn.getAddressDetail() + " " + fdn.getSubDistrict() + " " + fdn.getDistrict() + " " + fdn.getProvince() + " " + fdn.getPostalCode());
            profileDTO.setContactNumber(fdn.getContactNumber());
            profileDTO.setEmail(userEmail);
        } else if (user.getRole().getRoleUUid().equalsIgnoreCase("2")) {
            profileDTO.setProfileDetail(user.getFirstName() + " " + user.getLastName());
            profileDTO.setProfileAddress("");
            profileDTO.setContactNumber("");
            profileDTO.setEmail(userEmail);
        }
        return profileDTO;
    }

    public User getUserCurrent(Authentication auth) {
        String userCurrent = auth.getName();
        return userRepo.findByEmailIgnoreCaseAndStatusActive(userCurrent);
    }

    public Boolean checkUserNameIsAlreadyExists(String email) {
        return userRepo.existsByEmail(email);
    }

    public User getUserByEmail(String email) {
        return userRepo.findByEmailIgnoreCaseAndStatusActive(email);
//                .orElseThrow(UserNotFoundException::new);
    }

    public User getUserByUserName(String userName) {
        return userRepo.findByUserNameIgnoreCase(userName);
    }

    public void saveToTableUser(User user) {
        userRepo.save(user);
    }

    public List<User> getUserListByRoleIDAndStatusActive() {
        return userRepo.findUsersByRole_RoleUUidAndStatus("3", "ACTIVE");
    }


}
