package int371.namjai.domain.auth_security.auth;

import int371.namjai.domain.auth_security.AuthenticationService;
import int371.namjai.domain.auth_security.auth.signup.APILoginRequest;
import int371.namjai.domain.auth_security.auth.signup.APITokenResponse;
import int371.namjai.domain.foundation.APIFDNRegister;
import int371.namjai.domain.user.User;
import int371.namjai.domain.user.UserService;
import int371.namjai.domain.user.dto.ProfileDTO;
import int371.namjai.domain.user.dto.UserSuggestionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@RestController
@RequestMapping("/auth")
@CrossOrigin("*")
public class AuthenticationController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping(value = "/login", consumes = "application/json")
    public ResponseEntity<APITokenResponse> login(@RequestBody APILoginRequest authenticationRequest) {
        APITokenResponse apiTokenResponse = authenticationService.createAuthenticationToken(authenticationRequest);
        return ResponseEntity.ok().body(apiTokenResponse);
    }

    @PostMapping(value = "/signup")
    public ResponseEntity<Void> signupUser(@RequestBody User newUser) {
        authenticationService.createNewUser(newUser);
        return ResponseEntity.ok().build();

    }


    @PostMapping(value = "/signup/fdn")
    public ResponseEntity<Void> signupNewFoundation(@RequestBody APIFDNRegister apifdnRegister) throws Exception {
        authenticationService.createNewFoundation(apifdnRegister);
        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "/signup/admin")
    public ResponseEntity<Void> signupNewAdmin(@RequestBody User newUser) {
        authenticationService.createNewAdmin(newUser);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/fdn/upload-doc")
    public ResponseEntity<Void> uploadFileWithBody(@RequestParam("file") MultipartFile file, @RequestParam("fdnUuid") String fdnUuid) throws IOException {
        authenticationService.uploadFoundationDoc(file, fdnUuid);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(value = "/delete/user")
    public ResponseEntity<Void> deleteUser(@RequestParam("email") String userEmail) {
        authenticationService.deleteUser(userEmail);
        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "/user-suggestion/add")
    public ResponseEntity<Void> createUserSuggestion(@RequestBody UserSuggestionDTO userSuggestionDTO) {
        userService.saveUserSuggestion(userSuggestionDTO);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/user/suggestion")
    public ResponseEntity<Boolean> getNumberOfUseruuidInUserSuggestion(@RequestParam("id") String userEmail) {
        return ResponseEntity.ok().body(userService.getNumberOfExistsUserInUserSuggestion(userEmail));
    }

    @GetMapping(value = "/user/profile")
    public ResponseEntity<ProfileDTO> getUserProfile(@RequestParam("id") String userEmail) {
        return ResponseEntity.ok().body(userService.getUserProfile(userEmail));
    }


}
