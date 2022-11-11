package int371.namjai.domain.foundation_project_progress;

import int371.namjai.domain.foundation_project.FoundationProjectService;
import int371.namjai.domain.foundation_project_progress.dto.FoundationProjectProgressDisplayDTO;
import int371.namjai.domain.foundation_project_progress.dto.FoundationProjectProgressFormDTO;
import int371.namjai.domain.foundation_project_progress.dto.FoundationProjectProgressMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public class FoundationProjectProgressService {
    @Autowired
    private FoundationProjectProgressRepository foundationProjectProgressRepo;

    @Autowired
    private FoundationProjectService foundationProjectService;


    public void createUpdateProgress(FoundationProjectProgressFormDTO fdnProgress) {
        FoundationProjectProgress foundationProjectProgress = new FoundationProjectProgress();
        foundationProjectProgress.setFdnProjectProgressUUID(fdnProgress.getFoundationProjectProgressUUID());
        foundationProjectProgress.setFoundationProject(foundationProjectService.getFoundationById(fdnProgress.getFdnProjectUUID()));
        foundationProjectProgress.setTitle(fdnProgress.getTitle());
        foundationProjectProgress.setDetail(fdnProgress.getDetail());
        foundationProjectProgress.setProceedDate(fdnProgress.getProceedDate());
        foundationProjectProgress.setCreateDate(new Timestamp(System.currentTimeMillis()));
        foundationProjectProgress.setPicturePath(null);
        foundationProjectProgressRepo.save(foundationProjectProgress);
    }

    public FoundationProjectProgressDisplayDTO getFoundationProjectProgressByID(String fdnProjectUUID) {
        return FoundationProjectProgressMapper.INSTANCE.toFoundationProjectProgressDisplayDto(foundationProjectProgressRepo.findFoundationProjectProgressByFoundationProject_FdnProjectUUid(fdnProjectUUID));
    }

    public void saveToTableFoundationProjectProgress(FoundationProjectProgress foundationProjectProgress) {
        foundationProjectProgressRepo.save(foundationProjectProgress);
    }


}
