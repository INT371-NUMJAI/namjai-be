package int371.namjai.utill;

import int371.namjai.domain.security_auth.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.rmi.RemoteException;

@RestController
@RequestMapping("/view")
@CrossOrigin("*")
public class UtilController {

    @Autowired
    private AuthenticationService authenticationService;

    @GetMapping("/img")
    public ResponseEntity<byte[]> getImage(@RequestParam String imagePath) throws IOException {
        try {
//            FileInputStream file = new FileInputStream(imagePath);
            Path path = Paths.get(imagePath);
            byte[] image = Files.readAllBytes(path);
//            file.close();
            return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(image);
        } catch (Exception e) {
            throw new RemoteException("Image not Found");
        }

    }
}
