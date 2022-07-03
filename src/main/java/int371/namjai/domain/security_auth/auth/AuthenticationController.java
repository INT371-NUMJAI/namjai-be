package int371.namjai.domain.security_auth.auth;

import int371.namjai.domain.foundation.Foundation;
import int371.namjai.domain.foundation.FoundationRepository;
import int371.namjai.domain.foundation.FoundationService;
import int371.namjai.domain.role.Role;
import int371.namjai.domain.role.RoleRepository;
import int371.namjai.domain.security_auth.auth.signup.APILoginRequest;
import int371.namjai.domain.security_auth.auth.signup.APITokenResponse;
import int371.namjai.domain.user.User;
import int371.namjai.domain.user.UserRepository;
import int371.namjai.domain.user.exceptions.UserDuplicateException;
import int371.namjai.utill.Constant;
import int371.namjai.utill.DateUtill;
import int371.namjai.utill.ResourceUtilService;
import int371.namjai.utill.UserRoleName;
import int371.namjai.utill.auth.CustomUserDetailsService;
import int371.namjai.utill.auth.TokenHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;


@RestController
@RequestMapping("/auth")
@CrossOrigin("*")
public class AuthenticationController {

    @Autowired
    private TokenHelper jwtToken;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private FoundationRepository fdnRepo;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private DateUtill dateUtill;

    @Autowired
    private ResourceUtilService resourceUtilService;

    @Autowired
    private FoundationService foundationService;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @PostMapping(value = "/login", consumes = "application/json")
    public ResponseEntity<APITokenResponse> createAuthenticationToken(@RequestBody APILoginRequest authenticationRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(), authenticationRequest.getPassword()));
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Invalid email or password");
        }
        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getEmail());
        String role = userRepo.selectRoleNameByEmail(authenticationRequest.getEmail());
        final String accessToken = jwtToken.generateToken(userDetails);

        return ResponseEntity.ok((new APITokenResponse(userDetails.getUsername(), role, accessToken)));
    }

    @PostMapping(value = "/signup")
    public ResponseEntity<Void> createNewUser(@RequestBody User newUser) {
        try {
            String newUserUUid = UUID.randomUUID().toString();
            Role role = new Role("2", UserRoleName.ROLE_USER);
            newUser.setUserUUid(newUserUUid);
            newUser.setPassword(encryptedPassword(newUser.getPassword()));
            newUser.setCreateDate(dateUtill.nowDateTimeFormatter());
            newUser.setRole(role);
            newUser.setStatus(Constant.USER_STATUS_ACTIVE);
            userRepo.save(newUser);
            return ResponseEntity.ok().build();
        }
        catch (RuntimeException e){
            throw new UserDuplicateException();
        }
    }



    @PostMapping(value = "/signup/fdn")
    public ResponseEntity<Foundation> createNewFoundation(@RequestParam("docFile") MultipartFile docFile,@RequestParam("apifdnToUser")String apifdnToUser,@RequestParam("password") String password) throws Exception {
        String newFDNUUid = UUID.randomUUID().toString();
        Role role = new Role("3", UserRoleName.ROLE_FDN);
        User fdnUser = new User();

        Foundation apiFDN = foundationService.convertJsonStringToMovie(apifdnToUser);
        apiFDN.setFdnUUid(newFDNUUid);
        apiFDN.setEstablishDate(dateUtill.nowDateFormatter());
        apiFDN.setStatus(Constant.FDN_STATUS_PENDING);
        apiFDN.setResource(null);

        String fdnToUserUUid = UUID.randomUUID().toString();
        fdnUser.setUserUUid(fdnToUserUUid);
        fdnUser.setEmail(apiFDN.getEmail());
        fdnUser.setFirstName("Foundation");
        fdnUser.setLastName(apiFDN.getFdnName());
        fdnUser.setPassword(encryptedPassword(password));
        fdnUser.setCreateDate(dateUtill.nowDateTimeFormatter());
        fdnUser.setRole(role);
        fdnUser.setStatus(Constant.USER_STATUS_DISABLE);

        fdnRepo.save(apiFDN);
        userRepo.save(fdnUser);
        resourceUtilService.saveFDNDocumentFile(docFile, apiFDN);

        return ResponseEntity.ok().body(apiFDN);
    }

    @PostMapping(value = "/signup/admin")
    public ResponseEntity<User> createNewAdmin(@RequestBody User newUser) {
        String newUserUUid = UUID.randomUUID().toString();
        Role role = new Role("1", UserRoleName.ROLE_ADMIN);
        newUser.setUserUUid(newUserUUid);
        newUser.setPassword(encryptedPassword(newUser.getPassword()));
        newUser.setCreateDate(dateUtill.nowDateTimeFormatter());
        newUser.setRole(role);
        newUser.setStatus(Constant.USER_STATUS_ACTIVE);
        userRepo.save(newUser);
        return ResponseEntity.ok().body(newUser);
    }

    @PostMapping("/test")
    public ResponseEntity<String> uploadFileWithBody(@RequestParam("file") MultipartFile file, @RequestParam("testString") String testString) throws IOException {
        resourceUtilService.saveFDNDocumentFile(file);
        String returnString = testString;
        return ResponseEntity.ok().body(returnString);
    }



    private String encryptedPassword(String password) {
        return passwordEncoder.encode(password);
    }
}
