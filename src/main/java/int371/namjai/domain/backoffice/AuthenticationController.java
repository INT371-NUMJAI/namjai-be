package int371.namjai.domain.backoffice;


import int371.namjai.domain.auth.auth.JwtAuthenticationRequest;
import int371.namjai.domain.auth.auth.JwtAuthenticationResponse;
import int371.namjai.domain.foundation.Foundation;
import int371.namjai.domain.foundation.FoundationRepository;
import int371.namjai.domain.resource.Resource;
import int371.namjai.domain.role.Role;
import int371.namjai.domain.role.RoleRepository;
import int371.namjai.domain.user.UserRepository;
import int371.namjai.domain.user.User;
import int371.namjai.utill.Constant;
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

//    @Autowired
//    private UserService userService;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private FoundationRepository fdnRepo;

    @Autowired
    private RoleRepository roleRepository;


    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @PostMapping(value = "/login", consumes = "application/json")
    public ResponseEntity<JwtAuthenticationResponse> createAuthenticationToken(@RequestBody JwtAuthenticationRequest authenticationRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(), authenticationRequest.getPassword()));
        } catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or password", e);
        }
//        final UserDetails userDetails = userDetailsService.loadUserByEmail(authenticationRequest.getEmail());
        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getEmail());
//        Users user = userRepo.findByEmailIgnoreCase(authenticationRequest.getEmail());
        final String accessToken = jwtToken.generateToken(userDetails);

        return ResponseEntity.ok((new JwtAuthenticationResponse(accessToken)));
    }

    @PostMapping(value = "/signup")
    public ResponseEntity<User> createNewUser(@RequestBody User newUser) {
        String encryptedPassword = passwordEncoder.encode(newUser.getPassword());
//        String encryptedPassword = passwordEncoder.encode("123");
        String newUserUUid = UUID.randomUUID().toString();
//        Role role = new Role("2", UserRoleName.ROLE_USER);
        Role role = new Role("1", UserRoleName.ROLE_ADMIN);
       newUser.setUserUUid(newUserUUid);
        newUser.setPassword(encryptedPassword);
        newUser.setRole(role);
        userRepo.save(newUser);
        return ResponseEntity.ok().body(newUser);
    }

    @PostMapping(value = "/signup/fdn")
    public ResponseEntity<Foundation> createNewFoundation() {
//        String encryptedPassword = passwordEncoder.encode(apiUserSignup.getPassword());
        String newFDNUUid = UUID.randomUUID().toString();
//        Role role = new Role("2","user");
//        User newUser = new User(newUserUUid, apiUserSignup.getUserName(),apiUserSignup.getEmail(),apiUserSignup.getFirstName(),apiUserSignup.getLastName(),encryptedPassword,role) ;
        Foundation apiFDN = new Foundation();
        Resource resource = new Resource("testResourceUUid","testFilename","testPathName");
        apiFDN.setFdnUUid(newFDNUUid);
        apiFDN.setFdnName("test1");
        apiFDN.setAddressNo("test1");
        apiFDN.setStreetNo("test1");
        apiFDN.setStreetName("testStreetname");
        apiFDN.setSubDistrict("test1");
        apiFDN.setDistrict("test1");
        apiFDN.setProvince("test1");
        apiFDN.setPostalCode("test1");
        apiFDN.setFounderName("test1");
        apiFDN.setFdnDetail("testDetail1");
        apiFDN.setFdnSize(Constant.FDN_SMALL_SIZE);
        apiFDN.setEstablishDate("today");
        apiFDN.setEmail("testEmail1");
        apiFDN.setContactNumber("09999999991");
        apiFDN.setStatus(Constant.FDN_STATUS_PENDING);
        apiFDN.setResource(resource);
//        apiFDN.setEstablishDate(System.cu);
        fdnRepo.save(apiFDN);
        return ResponseEntity.ok().body(apiFDN);
    }
//    @PutMapping("/user/edit")
//    public ResponseEntity<Users> editProfile(@RequestBody Users user, Authentication auth) {
//        Users updateUser = userService.getUserCurrent(auth);
//        updateUser.setUsername(user.getUsername());
//        updateUser.setFirstname(user.getFirstname());
//        updateUser.setLastname(user.getLastname());
//        String encryptedPassword = passwordEncoder.encode(user.getPassword());
//        updateUser.setPassword(encryptedPassword);
//        userRepo.save(updateUser);
//        return ResponseEntity.ok().body(updateUser);
//    }
}
