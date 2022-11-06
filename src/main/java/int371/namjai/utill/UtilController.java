package int371.namjai.utill;

import int371.namjai.domain.auth_security.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.rmi.RemoteException;

@RestController
@RequestMapping("/util")
@CrossOrigin("*")
public class UtilController {

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private ResourceUtilService resourceUtilService;


    @GetMapping("/img")
    public ResponseEntity<byte[]> getImage(@RequestParam("path") String imagePath) throws IOException {
        try {
            String extension = resourceUtilService.getFileExtension(imagePath);
            MediaType mediaType = resourceUtilService.getMediaType(extension);
            byte[] image = resourceUtilService.getImageFile(imagePath);
            return ResponseEntity.ok().contentType(mediaType).body(image);
        } catch (Exception e) {
            throw new RemoteException("Image not Found");
        }

    }

    @PostMapping("/resource/upload-pic")
    public ResponseEntity<Void> uploadFileWithBody(@RequestParam("file") MultipartFile file, @RequestParam("type") String type, @RequestParam("userName") String userName, @RequestParam("uuid") String uuid) throws IOException {
        resourceUtilService.saveFileToFolder(file, type, userName, uuid);
        resourceUtilService.saveImagePathToTable(file.getOriginalFilename(), type, uuid, userName);
        return ResponseEntity.ok().build();
    }

}
