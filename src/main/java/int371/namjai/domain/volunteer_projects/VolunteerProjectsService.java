package int371.namjai.domain.volunteer_projects;

import int371.namjai.domain.volunteer_projects.exceoptions.VolunteerProjectException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VolunteerProjectsService {
    @Autowired
    private VolunteerProjectsRepository volunteerProjectsRepo;

    public void createVolunteerProjects(VolunteerProjects newVolunteerProjects) {
        VolunteerProjects volunteerProjects = new VolunteerProjects();
        volunteerProjects.setVolunteerProjectsUUID(newVolunteerProjects.getVolunteerProjectsUUID());
        volunteerProjects.setVolunteerProjectName(newVolunteerProjects.getVolunteerProjectName());
        volunteerProjects.setPeopleNeeded(newVolunteerProjects.getPeopleNeeded());
        volunteerProjects.setPeopleRegistered(newVolunteerProjects.getPeopleRegistered());
        volunteerProjects.setDescription(newVolunteerProjects.getDescription());
        volunteerProjects.setQualify(newVolunteerProjects.getQualify());
        volunteerProjects.setActivityType(newVolunteerProjects.getActivityType()); //
        volunteerProjects.setStartDate(newVolunteerProjects.getStartDate());
        volunteerProjects.setEndDate(newVolunteerProjects.getEndDate());
        volunteerProjects.setActivityStartDate(newVolunteerProjects.getActivityStartDate());
        volunteerProjects.setActivityEndDate(newVolunteerProjects.getActivityEndDate());
        volunteerProjects.setCreateDate(newVolunteerProjects.getCreateDate());
        volunteerProjects.setLocationDetail(newVolunteerProjects.getLocationDetail());
        volunteerProjects.setLocationDistrict(newVolunteerProjects.getLocationDistrict());
        volunteerProjects.setLocationSubDistrict(newVolunteerProjects.getLocationSubDistrict());
        volunteerProjects.setLocationProvince(newVolunteerProjects.getLocationProvince());
        volunteerProjects.setLocationPostalCode(newVolunteerProjects.getLocationPostalCode());
        volunteerProjects.setPicturePath(newVolunteerProjects.getPicturePath());
        volunteerProjects.setFoundation(newVolunteerProjects.getFoundation());
        volunteerProjects.setUser(null);
        volunteerProjectsRepo.save(volunteerProjects);
    }

    public List<VolunteerProjects> getVolunteerProjectsList() {
        return volunteerProjectsRepo.findAll();
    }

    public VolunteerProjects getVolunteerProject(String volunteerProjectUUID) {
        return volunteerProjectsRepo.findById(volunteerProjectUUID).orElseThrow(VolunteerProjectException::new);
    }
}
