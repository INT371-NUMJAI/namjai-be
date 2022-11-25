package int371.namjai.domain.foundation_project;

import int371.namjai.domain.foundation.Foundation;
import int371.namjai.domain.foundation.FoundationService;
import int371.namjai.domain.foundation.mapper.FoundationContactDTO;
import int371.namjai.domain.foundation.mapper.FoundationMapper;
import int371.namjai.domain.foundation_project.exceptions.FoundationProjectsNotFoundException;
import int371.namjai.domain.foundation_project.mapper.FoundationProjectDTO;
import int371.namjai.domain.foundation_project.mapper.FoundationProjectListToRequest;
import int371.namjai.domain.foundation_project.mapper.FoundationProjectMapper;
import int371.namjai.domain.foundation_project.mapper.FoundationProjectShortDTO;
import int371.namjai.domain.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class FoundationProjectService {
    @Autowired
    private FoundationProjectRepository foundationProjectRepo;

    @Autowired
    private FoundationService foundationService;

    @Autowired
    private UserService userService;

    public List<FoundationProject> getAllFoundationProjects() {
        return foundationProjectRepo.findAll();
    }

    public List<FoundationProjectShortDTO> getAllFoundationProjectInShort() {
        List<FoundationProject> foundationProjectList = foundationProjectRepo.findAllByOrderByCreateDateAsc();
        return FoundationProjectMapper.INSTANCE.toFoundationProjectShortDtoList(foundationProjectList);
    }

    public FoundationProject getFoundationById(String fdnProjectUUid) {
        return foundationProjectRepo.findById(fdnProjectUUid)
                .orElseThrow(FoundationProjectsNotFoundException::new);
    }

    public List<FoundationProject> getFoundationProjectsUserSuggestionByUserEmail(String userEmail) {
        String userUUID = userService.getUserByEmail(userEmail).getUserUUid();
        return foundationProjectRepo.findTop6UserSuggestion(userUUID);
    }


    public FoundationProjectDTO getFoundationProjectDTOById(String fdnProjectUUid) {
        FoundationProject foundationProject = getFoundationById(fdnProjectUUid);
        Foundation foundation = foundationService.getFoundationById(foundationProject.getFoundation().getFdnUUid());
        FoundationContactDTO foundationContactDTO = FoundationMapper.INSTANCE.toFoundationContactDto(foundation);
        FoundationProjectDTO foundationProjectDTO = FoundationProjectMapper.INSTANCE.toFoundationProjectDto(foundationProject, foundationContactDTO);
        return foundationProjectDTO;
    }

    public void editFoundationProjectStatus(APIEditStatusProjectStatus apiEditStatusProjectStatus) {
        FoundationProject foundationProject = getFoundationById(apiEditStatusProjectStatus.getFdnProjectUUID());
        foundationProject.setStatus(apiEditStatusProjectStatus.getNewStatus());
        foundationProjectRepo.save(foundationProject);
    }

    public void createFoundationProject(APIFoundationProjectForm apiFoundationProjectForm) {
        FoundationProject foundationProject = new FoundationProject();
        Foundation foundation = foundationService.getFoundationById(apiFoundationProjectForm.getFdnUUID());
        foundationProject.setFoundation(foundation);
        foundationProject.setFdnProjectUUid(apiFoundationProjectForm.getFdnProjectUUID());
        foundationProject.setFdnProjectName(apiFoundationProjectForm.getFdnProjectName());
        foundationProject.setStartDate(apiFoundationProjectForm.getStartDate());
        foundationProject.setEndDate(apiFoundationProjectForm.getEndDate());
        foundationProject.setGoal(apiFoundationProjectForm.getGoal());
        foundationProject.setCreateDate(new Timestamp(System.currentTimeMillis()));
        foundationProject.setPicturePath(null);
        foundationProject.setFdnProjectDetail(apiFoundationProjectForm.getFdnProjectDetail());
        foundationProject.setFdnProjectDetailPlace(apiFoundationProjectForm.getFdnProjectDetailPlace());
        foundationProject.setTargetCategoriesSet(apiFoundationProjectForm.getTargetCategoriesSet());
        foundationProject.setResponsiblePerson(apiFoundationProjectForm.getResponsiblePerson());
        foundationProject.setReceived(0L);
        foundationProject.setStatus(apiFoundationProjectForm.getStatus());
        foundationProjectRepo.save(foundationProject);
    }

    public List<FoundationProjectShortDTO> getAllFoundationProjectInShortByTargetCatName(String targetCatName) {
        List<FoundationProject> foundationProjectList = null;
        if (targetCatName.equalsIgnoreCase("total")) {
            foundationProjectList = foundationProjectRepo.findFoundationProjectsByStatusIsOrderByCreateDateDesc("OPEN");
        } else {
            foundationProjectList = foundationProjectRepo.findByTargetCategoriesSet(targetCatName);
        }
        return FoundationProjectMapper.INSTANCE.toFoundationProjectShortDtoList(foundationProjectList);
    }

    public List<FoundationProjectShortDTO> getFoundationProjectsByFoundationUUID(String fdnUUID) {
        return FoundationProjectMapper.INSTANCE.toFoundationProjectShortDtoList(foundationProjectRepo.findByfdnUUID(fdnUUID));
    }

    public List<FoundationProjectListToRequest> getFoundationProjectToRequest(String fdnUUID) {
        List<FoundationProject> foundationProjects = foundationProjectRepo.findFoundationProjectsByFDNAndStatus(fdnUUID);
        return FoundationProjectMapper.INSTANCE.toFoundationProjectListToRequests(foundationProjects);
    }

    public List<FoundationProjectShortDTO> getFoundationProjectByName(String fdnName) {
        List<FoundationProject> foundationProjects = foundationProjectRepo.findFoundationProjectsByProjectNameAndStatus(fdnName);
        return FoundationProjectMapper.INSTANCE.toFoundationProjectShortDtoList(foundationProjects);
    }

    public void saveToTable(FoundationProject project) {
        foundationProjectRepo.save(project);
    }

}