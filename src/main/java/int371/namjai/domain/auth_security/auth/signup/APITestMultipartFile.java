package int371.namjai.domain.auth_security.auth.signup;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class APITestMultipartFile {

    private MultipartFile file;
    private String testString;
}
