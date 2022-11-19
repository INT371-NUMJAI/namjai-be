package int371.namjai.domain.volunteer_projects;

import int371.namjai.domain.foundation.Foundation;
import int371.namjai.domain.foundation.FoundationService;
import int371.namjai.domain.foundation.mapper.FoundationContactDTO;
import int371.namjai.domain.foundation.mapper.FoundationMapper;
import int371.namjai.domain.user.UserService;
import int371.namjai.domain.volunteer_projects.exceoptions.VolunteerProjectNotFoundException;
import int371.namjai.domain.volunteer_projects.mapper.VolunteerProjectDetailDTO;
import int371.namjai.domain.volunteer_projects.mapper.VolunteerProjectMapper;
import int371.namjai.domain.volunteer_projects.mapper.VolunteerProjectShort;
import int371.namjai.domain.volunteer_projects.mapper.VolunteerProjectsFormDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class VolunteerProjectsService {
    @Autowired
    private VolunteerProjectsRepository volunteerProjectsRepo;

    @Autowired
    private FoundationService foundationService;

    @Autowired
    private UserService userService;


    public void createVolunteerProjects(VolunteerProjectsFormDTO newVolunteerProjects) {
        VolunteerProjects volunteerProjects = new VolunteerProjects();
        Foundation foundation = foundationService.getFoundationById(newVolunteerProjects.getFoundationUUID());
        volunteerProjects.setFoundation(foundation);
        volunteerProjects.setVolunteerProjectsUUID(newVolunteerProjects.getVolunteerProjectsUUID());
        volunteerProjects.setVolunteerProjectName(newVolunteerProjects.getVolunteerProjectName());
        volunteerProjects.setPeopleNeeded(newVolunteerProjects.getPeopleNeeded());
        volunteerProjects.setPeopleRegistered(0);
        volunteerProjects.setDescription(newVolunteerProjects.getDescription());
        volunteerProjects.setActivityType(newVolunteerProjects.getActivityType()); //
        volunteerProjects.setStartDate(newVolunteerProjects.getStartDate());
        volunteerProjects.setEndDate(newVolunteerProjects.getEndDate());
        volunteerProjects.setActivityStartDate(newVolunteerProjects.getActivityStartDate());
        volunteerProjects.setActivityEndDate(newVolunteerProjects.getActivityEndDate());
        volunteerProjects.setCreateDate(new Timestamp(System.currentTimeMillis()));
        volunteerProjects.setLocationDetail(newVolunteerProjects.getLocationDetail());
        volunteerProjects.setLocationDistrict(newVolunteerProjects.getLocationDistrict());
        volunteerProjects.setLocationSubDistrict(newVolunteerProjects.getLocationSubDistrict());
        volunteerProjects.setLocationProvince(newVolunteerProjects.getLocationProvince());
        volunteerProjects.setLocationPostalCode(newVolunteerProjects.getLocationPostalCode());
        volunteerProjects.setTargetCategoriesSet(newVolunteerProjects.getTargetCategoriesSet());
        volunteerProjects.setQualify(newVolunteerProjects.getQualify());
        volunteerProjects.setDuty(newVolunteerProjects.getDuty());
        volunteerProjects.setStatus(newVolunteerProjects.getStatus());
        volunteerProjectsRepo.save(volunteerProjects);
    }

    public List<VolunteerProjectShort> getVolunteerProjectsList() {
        List<VolunteerProjects> volunteerProjectsList = volunteerProjectsRepo.findAllByStatusIsOrderByEndDateDesc("OPEN");
        List<VolunteerProjectShort> volunteerProjectShorts = VolunteerProjectMapper.INSTANCE.toVolunteerProjectShortList(volunteerProjectsList);
        return volunteerProjectShorts;
    }

    public List<VolunteerProjectShort> getVolunteerProjectsListByFDNEmail(String fdnEmail) {
        List<VolunteerProjects> volunteerProjectsList = volunteerProjectsRepo.findVolunteerProjectsByFoundation_Email(fdnEmail);
        List<VolunteerProjectShort> volunteerProjectShorts = VolunteerProjectMapper.INSTANCE.toVolunteerProjectShortList(volunteerProjectsList);
        return volunteerProjectShorts;
    }

    public List<VolunteerProjectShort> getVolunteerProjectsListByTargetCatName(String targetCatname) {
        List<VolunteerProjects> volunteerProjectsList = null;
        if (targetCatname.equalsIgnoreCase("total")) {
            volunteerProjectsList = volunteerProjectsRepo.findAllByStatusIsOrderByEndDateDesc("OPEN");
        } else {
            volunteerProjectsList = volunteerProjectsRepo.findByTargetCategoriesSet(targetCatname);
        }
        List<VolunteerProjectShort> volunteerProjectShorts = VolunteerProjectMapper.INSTANCE.toVolunteerProjectShortList(volunteerProjectsList);
        return volunteerProjectShorts;
    }

    public List<VolunteerProjectShort> getVolunteerProjectsListByFDNEmailAndStatusOpen(String fdnEmail) {
        List<VolunteerProjects> volunteerProjectsList = volunteerProjectsRepo.findVolunteerProjectsByFoundation_EmailAndStatusOpen(fdnEmail);
        List<VolunteerProjectShort> volunteerProjectShorts = VolunteerProjectMapper.INSTANCE.toVolunteerProjectShortList(volunteerProjectsList);
        return volunteerProjectShorts;
    }

    public VolunteerProjectDetailDTO getVolunteerProjectDetailByUUID(String volunteerProjectUUID) {
        VolunteerProjects volunteerProjects = volunteerProjectsRepo.findById(volunteerProjectUUID).orElseThrow(VolunteerProjectNotFoundException::new);
        FoundationContactDTO foundationContactDTO = FoundationMapper.INSTANCE.toFoundationContactDto(volunteerProjects.getFoundation());
        return VolunteerProjectMapper.INSTANCE.toVolunteerProjectDetailDto(volunteerProjects, foundationContactDTO);
    }

    public VolunteerProjects getVolunteerProjectByUUID(String volunteerProjectUUID) {
        return volunteerProjectsRepo.findById(volunteerProjectUUID).orElseThrow(VolunteerProjectNotFoundException::new);

    }

    public List<VolunteerProjectShort> getVolunteerProjectsListByName(String volunteerName) {
        List<VolunteerProjects> volunteerProjectsList = volunteerProjectsRepo.findVolunteerProjectsByVolunteerProjectNameContainingIgnoreCase(volunteerName);
        List<VolunteerProjectShort> volunteerProjectShorts = VolunteerProjectMapper.INSTANCE.toVolunteerProjectShortList(volunteerProjectsList);
        return volunteerProjectShorts;
    }

    public List<VolunteerProjectShort> getVolunteerProjectUserSuggestionByUserEmail(String userEmail) {
        String userUUID = userService.getUserByEmail(userEmail).getUserUUid();
        return VolunteerProjectMapper.INSTANCE.toVolunteerProjectShortList(volunteerProjectsRepo.findVolunteerProjectsByUserSuggestion(userUUID));
    }

    public List<VolunteerProjectShort> getVolunteerProjectTop6Random() {
        return VolunteerProjectMapper.INSTANCE.toVolunteerProjectShortList(volunteerProjectsRepo.findTop6AndStatusOpen());
    }

    public void saveToTableVolunteer(VolunteerProjects volunteerProjects) {
        volunteerProjectsRepo.save(volunteerProjects);
    }


}
