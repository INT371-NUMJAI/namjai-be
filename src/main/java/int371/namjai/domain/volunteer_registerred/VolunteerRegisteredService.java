package int371.namjai.domain.volunteer_registerred;

import int371.namjai.domain.user.User;
import int371.namjai.domain.user.UserService;
import int371.namjai.domain.volunteer_projects.VolunteerProjects;
import int371.namjai.domain.volunteer_projects.VolunteerProjectsRepository;
import int371.namjai.domain.volunteer_projects.VolunteerProjectsService;
import int371.namjai.domain.volunteer_registerred.dto.EnrolledListVolunteerProject;
import int371.namjai.domain.volunteer_registerred.dto.UnEnrolledVolunteerProjectDTO;
import int371.namjai.domain.volunteer_registerred.dto.VolunteerRegisteredUserDTO;
import int371.namjai.domain.volunteer_registerred.dto.VolunteerUnRegisteredUserDTO;
import int371.namjai.domain.volunteer_registerred.mapper.VolunteerRegisteredMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class VolunteerRegisteredService {
    private final VolunteerEnrolled volunteerEnrolled = new VolunteerEnrolled();
    @Autowired
    private VolunteerEnrolledRepository volunteerEnrolledRepo;

    @Autowired
    private VolunteerEnrolledCRUDRepository volunteerEnrolledCRUDRepository;

    @Autowired
    private VolunteerProjectsRepository volunteerProjectsRepo;

    @Autowired
    private VolunteerProjectsService volunteerProjectsService;

    @Autowired
    private UserService userService;

    public void recordVolunteerRegisterByRegisteredUser(VolunteerRegisteredUserDTO volunteerRegisteredUserDTO) {
        VolunteerProjects volunteerProject = volunteerProjectsService.getVolunteerProjectByUUID(volunteerRegisteredUserDTO.getVolunteerProjectUUID());
        volunteerEnrolled.setVolunteerEnrolledUUID(UUID.randomUUID().toString());
        volunteerEnrolled.setVolunteerProjects(volunteerProject);

        User user = userService.getUserByEmail(volunteerRegisteredUserDTO.getEmail());
        volunteerEnrolled.setUser(user);
        volunteerEnrolled.setIsMember(true);
        volunteerEnrolled.setEmail(volunteerRegisteredUserDTO.getEmail());
        volunteerEnrolled.setContactNumber(user.getPhoneNumber());
        volunteerEnrolled.setFirstName(user.getFirstName());
        volunteerEnrolled.setLastName(user.getLastName());

        volunteerEnrolledRepo.save(volunteerEnrolled);
    }

    public void recordVolunteerRegisterByUnregisteredUser(VolunteerUnRegisteredUserDTO volunteerUnRegisteredUserDTO) {
        volunteerEnrolled.setVolunteerEnrolledUUID(UUID.randomUUID().toString());
        volunteerEnrolled.setIsMember(false);
        volunteerEnrolled.setUser(null);

        volunteerEnrolled.setEmail(volunteerUnRegisteredUserDTO.getEmail());
        volunteerEnrolled.setContactNumber(volunteerUnRegisteredUserDTO.getContactNumber());
        volunteerEnrolled.setFirstName(volunteerUnRegisteredUserDTO.getFirstName());
        volunteerEnrolled.setLastName(volunteerUnRegisteredUserDTO.getLastName());
        VolunteerProjects volunteerProject = volunteerProjectsService.getVolunteerProjectByUUID(volunteerUnRegisteredUserDTO.getVolunteerProjectUUID());
        volunteerEnrolled.setVolunteerProjects(volunteerProject);
        volunteerEnrolledRepo.save(volunteerEnrolled);
    }

    @Transactional
    public void unRegisteredVolunteerProject(UnEnrolledVolunteerProjectDTO unEnrolledVolunteerProjectDTO) {
//        VolunteerEnrolled volunteerEnrolled = volunteerEnrolledRepo.findByVolunteerProjects_VolunteerProjectsUUIDAndAndVolunteerEnrolledUUID(unEnrolledVolunteerProjectDTO.getVolunteerProjectUUID(),unEnrolledVolunteerProjectDTO.getVolunteerEnrolledUUID());
        volunteerEnrolledCRUDRepository.removeVolunteerEnrolledByVolunteerProjects_VolunteerProjectsUUIDAndEmail(unEnrolledVolunteerProjectDTO.getVolunteerProjectUUID(), unEnrolledVolunteerProjectDTO.getEmail());
    }

    public List<EnrolledListVolunteerProject> getListOfEnRolledUserInVolunteerProject(String volunteerProjectUUID) {
        List<VolunteerEnrolled> volunteerEnrolledList = volunteerEnrolledRepo.findByVolunteerProjects_VolunteerProjectsUUID(volunteerProjectUUID);
        List<EnrolledListVolunteerProject> userDTOList = VolunteerRegisteredMapper.INSTANCE.toEnrolledListVolunteerProjects(volunteerEnrolledList);
        return userDTOList;
    }

    public Boolean checkUserIsEnrolledOrNot(String volunteerProjectUUID, String userEmail) {
        Boolean isEnrolled = volunteerEnrolledRepo.existsByVolunteerProjects_VolunteerProjectsUUIDAndEmail(volunteerProjectUUID, userEmail);
        return isEnrolled;
    }
}
