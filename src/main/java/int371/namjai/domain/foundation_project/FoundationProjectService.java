package int371.namjai.domain.foundation_project;

import int371.namjai.domain.foundation.Foundation;
import int371.namjai.domain.foundation.FoundationService;
import int371.namjai.domain.foundation.mapper.FoundationContactDTO;
import int371.namjai.domain.foundation.mapper.FoundationMapper;
import int371.namjai.domain.foundation_project.exceptions.FoundationProjectsNotFoundException;
import int371.namjai.domain.foundation_project.mapper.FoundationProjectDTO;
import int371.namjai.domain.foundation_project.mapper.FoundationProjectMapper;
import int371.namjai.domain.foundation_project.mapper.FoundationProjectShortDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoundationProjectService {
    @Autowired
    private FoundationProjectRepository foundationProjectRepo;

    @Autowired
    private FoundationService foundationService;

    public List<FoundationProject> getAllFoundationProjects() {
        return foundationProjectRepo.findAll();
    }

    public List<FoundationProjectShortDTO> getAllFoundationProjectInShort() {
        List<FoundationProject> foundationProjectList = foundationProjectRepo.findAll();
        return FoundationProjectMapper.INSTANCE.toFoundationProjectShortDtoList(foundationProjectList);
    }

    public FoundationProject getFoundationById(String fdnProjectUUid) {
        return foundationProjectRepo.findById(fdnProjectUUid)
                .orElseThrow(FoundationProjectsNotFoundException::new);
    }

    public FoundationProjectDTO getFoundationProjectDTOById(String fdnProjectUUid) {
        FoundationProject foundationProject = getFoundationById(fdnProjectUUid);
        Foundation foundation = foundationService.getFoundationById(foundationProject.getFoundation().getFdnUUid());
        FoundationContactDTO foundationContactDTO = FoundationMapper.INSTANCE.toFoundationContactDto(foundation);
        FoundationProjectDTO foundationProjectDTO = FoundationProjectMapper.INSTANCE.toFoundationProjectDto(foundationProject, foundationContactDTO);
        return foundationProjectDTO;
    }
}