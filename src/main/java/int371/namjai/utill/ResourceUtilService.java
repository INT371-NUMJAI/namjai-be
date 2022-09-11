package int371.namjai.utill;

import int371.namjai.domain.foundation.Foundation;
import int371.namjai.domain.foundation_document.FoundationDocuments;
import int371.namjai.domain.foundation_document.FoundationDocumentsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

@Service
public class ResourceUtilService {

    @Autowired
    private FoundationDocumentsRepo foundationDocumentsRepo;


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

    public String saveFileToProjectFolder(MultipartFile file, String fdnName) throws IOException {
        String fullPath = "";
        if (!ObjectUtils.isEmpty(file)) {
            String fileName = file.getOriginalFilename();
            fullPath = Constant.FDN_PATH + fdnName;
            File createDir = new File(fullPath);
            createDir.mkdirs();
            File myFile = new File(fullPath, fileName);
            FileOutputStream fos = new FileOutputStream(myFile); //
            fos.write(file.getBytes());
            fos.close();
            return fullPath;
        }
        return fullPath;
    }

    public String getFileExtension(String fileName) {
        int index = fileName.lastIndexOf('.');
        if (index > 0) {
            String extension = fileName.substring(index + 1);
            return extension;
        }
        return "";
    }

}
