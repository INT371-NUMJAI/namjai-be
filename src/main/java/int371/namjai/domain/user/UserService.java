package int371.namjai.domain.user;

import int371.namjai.domain.user.dto.UserSuggestionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserService {
    @Autowired
    private UserRepository userRepo;

    public void saveUserSuggestion(UserSuggestionDTO userSuggestionDTO) {
        User user = getUserByEmail(userSuggestionDTO.getUserEmail());
        user.setTargetCategoriesSuggestion(userSuggestionDTO.getTargetCategoriesSuggestion());
        userRepo.save(user);
    }

    public boolean getNumberOfExistsUserInUserSuggestion(String userEmail) {
        boolean isExits = userRepo.getNumberOfUserSuggestion(userEmail) > 0 ? true : false;
        return isExits;
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
