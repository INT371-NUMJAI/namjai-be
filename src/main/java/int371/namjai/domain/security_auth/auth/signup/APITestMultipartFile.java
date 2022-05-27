package int371.namjai.domain.security_auth.auth.signup;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class APITestMultipartFile {

    private MultipartFile file;
    private String testString;
}
