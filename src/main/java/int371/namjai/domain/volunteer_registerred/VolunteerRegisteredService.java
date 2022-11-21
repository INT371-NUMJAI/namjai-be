package int371.namjai.domain.volunteer_registerred;

import int371.namjai.domain.user.User;
import int371.namjai.domain.user.UserService;
import int371.namjai.domain.user_favorite.mapper.UserFavoriteDTO;
import int371.namjai.domain.volunteer_projects.VolunteerProjects;
import int371.namjai.domain.volunteer_projects.VolunteerProjectsRepository;
import int371.namjai.domain.volunteer_projects.VolunteerProjectsService;
import int371.namjai.domain.volunteer_projects.exceoptions.VolunteerProjectNotFoundException;
import int371.namjai.domain.volunteer_registerred.dto.EnrolledListVolunteerProject;
import int371.namjai.domain.volunteer_registerred.dto.EnrolledListVolunteerProjectDTO;
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
        int peopleCount = volunteerProject.getPeopleRegistered() + 1;
        volunteerProject.setPeopleRegistered(peopleCount);


        User user = userService.getUserByEmail(volunteerRegisteredUserDTO.getEmail());
        volunteerEnrolled.setIsMember(true);
        volunteerEnrolled.setEmail(volunteerRegisteredUserDTO.getEmail());
        volunteerEnrolled.setContactNumber(user.getPhoneNumber());
        volunteerEnrolled.setFirstName(user.getFirstName());
        volunteerEnrolled.setLastName(user.getLastName());

        volunteerEnrolledRepo.save(volunteerEnrolled);
        volunteerProjectsRepo.save(volunteerProject);
    }

    public void recordVolunteerRegisterByUnregisteredUser(VolunteerUnRegisteredUserDTO volunteerUnRegisteredUserDTO) {
        volunteerEnrolled.setVolunteerEnrolledUUID(UUID.randomUUID().toString());
        volunteerEnrolled.setIsMember(false);

        boolean isRegistered = volunteerEnrolledRepo.existsByVolunteerProjects_VolunteerProjectsUUIDAndEmailAndContactNumber(volunteerUnRegisteredUserDTO.getVolunteerProjectUUID(), volunteerUnRegisteredUserDTO.getEmail(), volunteerUnRegisteredUserDTO.getContactNumber());
        if (isRegistered) {
            throw new DuplicateEnrolledException();
        } else {
            volunteerEnrolled.setEmail(volunteerUnRegisteredUserDTO.getEmail());
            volunteerEnrolled.setContactNumber(volunteerUnRegisteredUserDTO.getContactNumber());
            volunteerEnrolled.setFirstName(volunteerUnRegisteredUserDTO.getFirstName());
            volunteerEnrolled.setLastName(volunteerUnRegisteredUserDTO.getLastName());
            VolunteerProjects volunteerProject = volunteerProjectsService.getVolunteerProjectByUUID(volunteerUnRegisteredUserDTO.getVolunteerProjectUUID());
            int peopleCount = volunteerProject.getPeopleRegistered() + 1;
            volunteerProject.setPeopleRegistered(peopleCount);
            volunteerEnrolled.setVolunteerProjects(volunteerProject);
            volunteerEnrolledRepo.save(volunteerEnrolled);
        }
    }

    @Transactional
    public void unRegisteredVolunteerProject(String email, String volunteerProjectUUID) {
//        VolunteerEnrolled volunteerEnrolled = volunteerEnrolledRepo.findByVolunteerProjects_VolunteerProjectsUUIDAndAndVolunteerEnrolledUUID(unEnrolledVolunteerProjectDTO.getVolunteerProjectUUID(),unEnrolledVolunteerProjectDTO.getVolunteerEnrolledUUID());
        VolunteerProjects volunteerProjects = volunteerProjectsRepo.findById(volunteerProjectUUID).orElseThrow(VolunteerProjectNotFoundException::new);
        int peopleCount = volunteerProjects.getPeopleRegistered() - 1;
        volunteerProjects.setPeopleRegistered(peopleCount);
        volunteerProjectsRepo.save(volunteerProjects);
        VolunteerEnrolled volunteerEnrolled = volunteerEnrolledRepo.findVolunteerEnrolledByEmailAndVolunteerProjects_VolunteerProjectsUUID(email, volunteerProjectUUID);
        volunteerEnrolledRepo.delete(volunteerEnrolled);
//        volunteerEnrolledRepo.save();
    }

    public EnrolledListVolunteerProjectDTO getListOfEnRolledUserInVolunteerProject(String volunteerProjectUUID) {
        List<VolunteerEnrolled> volunteerEnrolledList = volunteerEnrolledRepo.findByVolunteerProjects_VolunteerProjectsUUID(volunteerProjectUUID);
        List<EnrolledListVolunteerProject> userDTOList = VolunteerRegisteredMapper.INSTANCE.toEnrolledListVolunteerProjects(volunteerEnrolledList);
        EnrolledListVolunteerProjectDTO enrolledListVolunteerProjectDTO = new EnrolledListVolunteerProjectDTO();
        enrolledListVolunteerProjectDTO.setEnrolledListVolunteerProjectList(userDTOList); //enrolledListVolunteerProjectList
        enrolledListVolunteerProjectDTO.setVolunteerProjectName(volunteerProjectsRepo.getVolunteerName(volunteerProjectUUID));
        enrolledListVolunteerProjectDTO.setVolunteerProjectUUID(volunteerProjectUUID);
        return enrolledListVolunteerProjectDTO;
    }

    public Boolean checkUserIsEnrolledOrNot(String volunteerProjectUUID, String userEmail) {
        Boolean isEnrolled = volunteerEnrolledRepo.existsByVolunteerProjects_VolunteerProjectsUUIDAndEmail(volunteerProjectUUID, userEmail);
        return isEnrolled;
    }

    public List<UserFavoriteDTO> getActivityJoinedVolunteerProjectsByEmail(String email) {
        List<VolunteerEnrolled> volunteerEnrolledList = volunteerEnrolledRepo.findVolunteerEnrolledsByEmailOrderByVolunteerProjectCreateDateDesc(email);
        return VolunteerRegisteredMapper.INSTANCE.toUserFavoriteDTO(volunteerEnrolledList);
    }


}
