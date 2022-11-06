package int371.namjai.utill;

import int371.namjai.domain.foundation.Foundation;
import int371.namjai.domain.foundation_document.FoundationDocuments;
import int371.namjai.domain.foundation_document.FoundationDocumentsRepo;
import int371.namjai.domain.foundation_project.FoundationProject;
import int371.namjai.domain.foundation_project.FoundationProjectService;
import int371.namjai.domain.volunteer_projects.VolunteerProjects;
import int371.namjai.domain.volunteer_projects.VolunteerProjectsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class ResourceUtilService {

    @Autowired
    private FoundationDocumentsRepo foundationDocumentsRepo;

    @Autowired
    private FoundationProjectService foundationProjectService;

    @Autowired
    private VolunteerProjectsService volunteerProjectsService;

    public void saveFDNDocumentFile(MultipartFile docFile, Foundation foundation) throws IOException {
        FoundationDocuments foundationDocuments = new FoundationDocuments();
        if (!ObjectUtils.isEmpty(docFile)) {
            String fileName = docFile.getOriginalFilename();
            File myFile = new File(Constant.FDN_DOC_PATH + fileName);
            FileOutputStream fos = new FileOutputStream(myFile);
            fos.write(docFile.getBytes());
            fos.close();
            foundationDocuments.setFdnDocUUid(UUID.randomUUID().toString());
            foundationDocuments.setFileName(fileName);
            foundationDocuments.setFilePath(Constant.FDN_DOC_PATH);
            foundationDocuments.setFileType(getFileExtension(fileName));
            foundationDocuments.setFoundationUUid(foundation.getFdnUUid());
            foundationDocumentsRepo.save(foundationDocuments);
        }


    }

    public void saveFDNDocumentFile(MultipartFile docFile, String fdnName) throws IOException {
        if (!ObjectUtils.isEmpty(docFile)) {
            String fileName = docFile.getOriginalFilename();
            String fullPath = Constant.FDN_DOC_PATH + fdnName;
            File createDir = new File(fullPath);
            createDir.mkdir();
            File myFile = new File(fullPath, fileName);
            FileOutputStream fos = new FileOutputStream(myFile);
            fos.write(docFile.getBytes());
            fos.close();
        }
    }

    //    foundations/kaitomnampla/project/12Ny/รูป.jpg
    public void saveFileToFolder(MultipartFile file, String type, String userName, String uuid) throws IOException {
        String fullPath = "";
        if (!ObjectUtils.isEmpty(file)) {
            Path path = Paths.get(Constant.FDN_PATH + "/" + userName + "/" + type + "/" + "/" + uuid);
            if (!Files.exists(path)) {
                String fileName = file.getOriginalFilename();
                fullPath = Constant.FDN_PATH + "/" + userName + "/" + type + "/" + "/" + uuid;
                File createDir = new File(fullPath);
                createDir.mkdirs();
                File myFile = new File(fullPath, fileName);
                FileOutputStream fos = new FileOutputStream(myFile); //
                fos.write(file.getBytes());
                fos.close();
//                return fullPath;
            }
        }
//        return fullPath;
    }

    public void savePictureProfileToFoundation(MultipartFile file, String fdnUUID) throws IOException {
        String fileName = file.getOriginalFilename();
        if (!ObjectUtils.isEmpty(file) && !ObjectUtils.isEmpty(fileName)) {
            String fullPath = Constant.FDN_PATH + fdnUUID;
            File myFile = new File(fullPath, fileName);
            myFile.mkdir();
            FileOutputStream fos = new FileOutputStream(myFile); //
            fos.write(file.getBytes());
            fos.close();
        }
    }


    public void saveImagePathToTable(String fileName, String type, String uuid, String userName) {
        String path = userName + "/" + type + "/" + uuid + "/" + fileName;
        switch (type) {
            case "project":
                FoundationProject project = foundationProjectService.getFoundationById(uuid);
                project.setPicturePath(path);
                foundationProjectService.saveToTable(project);
                break;
            case "volunteer":
                VolunteerProjects volunteerProjects = volunteerProjectsService.getVolunteerProjectByUUID(uuid);
                volunteerProjects.setPicturePath(path);
                volunteerProjectsService.saveToTableVolunteer(volunteerProjects);
                break;

            // articles
        }
    }

    public void createDirForVerifiedFoundation(String fdnUserName) {
        String newDir = Constant.FDN_PATH + "/" + fdnUserName;
        new File(newDir).mkdirs();
    }

    public byte[] getImageFile(String fileName) throws IOException {
        Path path = Paths.get(Constant.FDN_PATH + fileName);
        byte[] data = Files.readAllBytes(path);
        return data;
    }

    public String getFileExtension(String fileName) {
        int index = fileName.lastIndexOf('.');
        if (index > 0) {
            String extension = fileName.substring(index + 1);
            return extension;
        }
        return "";
    }

    public String getFileTypeFromContentType(String contentType) {
        String fileType = "";
        switch (contentType) {
            case "image/jpeg":
                fileType = ".jpg";
                break;
            case "image/png":
                fileType = ".png";
                break;
            default:
                break;
        }
        return fileType;
    }

    public MediaType getMediaType(String contentType) {
        MediaType mediaType = null;
        switch (contentType) {
            case "jpg":
            case "jpeg":
                mediaType = MediaType.IMAGE_JPEG;
                break;
            case "png":
                mediaType = MediaType.IMAGE_PNG;
                break;
            default:
                break;
        }
        return mediaType;
    }


}
