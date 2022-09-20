package int371.namjai.utill;

import int371.namjai.domain.security_auth.AuthenticationService;
import int371.namjai.utill.web_resources.WebResourceRepository;
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
@RequestMapping("/util")
@CrossOrigin("*")
public class UtilController {

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private ResourceUtilService resourceUtilService;

    @Autowired
    private WebResourceRepository webResourceRepo;

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

//    @PostMapping("/resource/upload-pic")
//    public ResponseEntity<Void> uploadFileWithBody(@RequestParam("file") MultipartFile file) throws IOException {
//        resourceUtilService.saveFileToWebResource(file);
//
//        String fileName = file.getOriginalFilename();
//        WebResources webResources = new WebResources();
//        webResources.setResourceUUID(UUID.randomUUID().toString());
//        webResources.setFileName(fileName);
//        webResources.setCreateDate(new Timestamp(System.currentTimeMillis()));
//
//        webResourceRepo.save(webResources);
//        return ResponseEntity.ok().build();
//    }
//
//    @GetMapping("/web-resources")
//    public ResponseEntity<byte[]> getWebResourcesImage(@RequestParam String imageName) throws IOException {
////    public @ResponseBody byte[] getWebResourcesImage(@RequestParam String imageName) throws IOException {
////        InputStream in = getClass()
////                .getResourceAsStream(Constant.WEB_RESOURCE_PATH+imageName);
////                .getResourceAsStream("/com/baeldung/produceimage/image.jpg");
////        return IOUtils.toByteArray(in);
////        return ResponseEntity.ok().contentType(IOUtils.toByteArray(in));
////        IOUtils.toByteArray(in);
//        try {
//            Path path = Paths.get(Constant.WEB_RESOURCE_PATH+imageName);
//            byte[] image = Files.readAllBytes(path);
//            return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(image);
//        } catch (Exception e) {
//            throw new RemoteException("Image not Found");
//        }
//
//    }
}
