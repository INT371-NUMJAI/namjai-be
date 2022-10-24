package int371.namjai.domain.auth_security.auth;

import int371.namjai.domain.auth_security.auth.signup.APILoginRequest;
import int371.namjai.domain.auth_security.auth.signup.APITokenResponse;
import int371.namjai.domain.foundation.APIFDNRegister;
import int371.namjai.domain.foundation.Foundation;
import int371.namjai.domain.foundation.FoundationRepository;
import int371.namjai.domain.foundation.FoundationService;
import int371.namjai.domain.foundation_document.FoundationDocuments;
import int371.namjai.domain.foundation_document.FoundationDocumentsRepo;
import int371.namjai.domain.role.Role;
import int371.namjai.domain.role.RoleRepository;
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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Date;
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
    private FoundationDocumentsRepo foundationDocumentsRepo;

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
        String userName = userRepo.findByEmailIgnoreCase(authenticationRequest.getEmail()).getUserName();
        String userStatus = userRepo.findByEmailIgnoreCase(authenticationRequest.getEmail()).getStatus();
        final String accessToken = jwtToken.generateToken(userDetails);

        return ResponseEntity.ok((new APITokenResponse(userName, userDetails.getUsername(), role, userStatus, accessToken)));
    }

    @PostMapping(value = "/signup")
    public ResponseEntity<Void> createNewUser(@RequestBody User newUser) {
        try {
            String newUserUUid = UUID.randomUUID().toString();
            Role role = new Role("2", UserRoleName.ROLE_USER);
            newUser.setUserUUid(newUserUUid);
            newUser.setPassword(encryptedPassword(newUser.getPassword()));
            newUser.setCreateDate(LocalDate.now());
            newUser.setRole(role);
            newUser.setStatus(Constant.USER_STATUS_ACTIVE);
            newUser.setPhoneNumber(newUser.getPhoneNumber());
            userRepo.save(newUser);
            return ResponseEntity.ok().build();
        }
        catch (RuntimeException e){
            throw new UserDuplicateException();
        }
    }


    @PostMapping(value = "/signup/fdn")
    public ResponseEntity<Foundation> createNewFoundation(@RequestBody APIFDNRegister apifdnRegister) throws Exception {
        Role role = new Role("3", UserRoleName.ROLE_FDN);
        User fdnUser = new User();

        Foundation newFDN = new Foundation();
        newFDN.setFdnUUid(apifdnRegister.getFdnUUid());
        newFDN.setFdnName(apifdnRegister.getFdnName());
        newFDN.setNameEn(apifdnRegister.getFdnNameEn());
        newFDN.setAddressDetail(apifdnRegister.getAddressDetail());
        newFDN.setSubDistrict(apifdnRegister.getSubDistrict());
        newFDN.setDistrict(apifdnRegister.getDistrict());
        newFDN.setProvince(apifdnRegister.getProvince());
        newFDN.setPostalCode(apifdnRegister.getPostalCode());
        newFDN.setFounderName(apifdnRegister.getFounderName());
        newFDN.setFdnDetail(apifdnRegister.getFdnDetail());
        newFDN.setFdnSize(apifdnRegister.getFdnSize());
        newFDN.setEmail(apifdnRegister.getEmail());
        newFDN.setContactNumber(apifdnRegister.getContactNo());
        newFDN.setEstablishDate(apifdnRegister.getEstablishDate());
        newFDN.setStatus(Constant.FDN_STATUS_PENDING);
        newFDN.setProfilePath(null);
        newFDN.setQrCodePath(null);

        String fdnToUserUUid = UUID.randomUUID().toString();
        fdnUser.setUserUUid(fdnToUserUUid);
        fdnUser.setEmail(apifdnRegister.getEmail());
        fdnUser.setFirstName("Foundation");
        fdnUser.setLastName(newFDN.getFdnName());
        fdnUser.setUserName(apifdnRegister.getFdnName());
        fdnUser.setPassword(encryptedPassword(apifdnRegister.getPassword()));
        fdnUser.setCreateDate(LocalDate.now());
        fdnUser.setRole(role);
        fdnUser.setPhoneNumber(apifdnRegister.getContactNo());
        fdnUser.setStatus(Constant.USER_STATUS_DISABLE);

        fdnRepo.save(newFDN);
        userRepo.save(fdnUser);
        return ResponseEntity.ok().body(newFDN);
    }

    @PostMapping(value = "/signup/admin")
    public ResponseEntity<User> createNewAdmin(@RequestBody User newUser) {
        String newUserUUid = UUID.randomUUID().toString();
        Role role = new Role("1", UserRoleName.ROLE_ADMIN);
        newUser.setUserUUid(newUserUUid);
        newUser.setPassword(encryptedPassword(newUser.getPassword()));
        newUser.setCreateDate(LocalDate.now());
        newUser.setRole(role);
        newUser.setStatus(Constant.USER_STATUS_ACTIVE);
        newUser.setPhoneNumber(newUser.getPhoneNumber());
        userRepo.save(newUser);
        return ResponseEntity.ok().body(newUser);
    }

    // todo =>  check filetype (only zip)
    @PostMapping("/fdn/upload-doc")
    public ResponseEntity<String> uploadFileWithBody(@RequestParam("file") MultipartFile file, @RequestParam("fdnUuid") String fdnUuid) throws IOException {
        resourceUtilService.saveFDNDocumentFile(file, fdnUuid);
        String newFDNDocUUid = UUID.randomUUID().toString();
        String fileName = file.getOriginalFilename();
        String fileExtension = resourceUtilService.getFileExtension(fileName);
        Date date = new Date();
//        FoundationDocuments fdnDoc = new FoundationDocuments(newFDNDocUUid, fileName, Constant.FDN_DOC_PATH, fileExtension, fdnUuid,new Timestamp(date.getTime()));
        FoundationDocuments fdnDoc = new FoundationDocuments(newFDNDocUUid, fileName, Constant.FDN_DOC_PATH, fileExtension, fdnUuid, new Timestamp(date.getTime()));
        foundationDocumentsRepo.save(fdnDoc);
        return ResponseEntity.ok().body(newFDNDocUUid);
    }

    @DeleteMapping(value = "/delete/user")
    @Transactional
    public ResponseEntity<Void> deleteUser(@RequestParam("email") String userEmail) {
        userRepo.deleteByEmail(userEmail);

        return ResponseEntity.ok().build();
    }

    private String encryptedPassword(String password) {
        return passwordEncoder.encode(password);
    }


}
