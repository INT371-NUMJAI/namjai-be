package int371.namjai.domain.volunteer_registerred;

import int371.namjai.domain.user.User;
import int371.namjai.domain.user.UserService;
import int371.namjai.domain.volunteer_projects.VolunteerProjects;
import int371.namjai.domain.volunteer_projects.VolunteerProjectsRepository;
import int371.namjai.domain.volunteer_projects.VolunteerProjectsService;
import int371.namjai.domain.volunteer_registerred.dto.VolunteerRegisteredUserDTO;
import int371.namjai.domain.volunteer_registerred.dto.VolunteerUnRegisteredUserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class VolunteerRegisteredService {
    private final VolunteerRegistered volunteerRegistered = new VolunteerRegistered();
    @Autowired
    private VolunteerRegisteredRepository volunteerRegisteredRepo;
    @Autowired
    private VolunteerProjectsRepository volunteerProjectsRepo;
    @Autowired
    private VolunteerRegisteredAnonymousRepository volunteerRegisteredAnonymousRepo;
    @Autowired
    private VolunteerProjectsService volunteerProjectsService;
    @Autowired
    private UserService userService;

    public void recordVolunteerRegisterByRegisteredUser(VolunteerRegisteredUserDTO volunteerRegisteredUserDTO) {
        VolunteerProjects volunteerProject = volunteerProjectsService.getVolunteerProject(volunteerRegisteredUserDTO.getVolunteerProjectUUID());
        User user = userService.getUserByEmail(volunteerRegisteredUserDTO.getUserEmail());
        volunteerRegistered.setVolunteerRegisteredUUID(UUID.randomUUID().toString());
        volunteerRegistered.setVolunteerProjects(volunteerProject);
        volunteerRegistered.setUser(user);
        volunteerRegistered.setVolunteerRegisteredAnonymous(null);
        volunteerRegisteredRepo.save(volunteerRegistered);
    }

    public void recordVolunteerRegisterByUnregisteredUser(VolunteerUnRegisteredUserDTO volunteerUnRegisteredUserDTO) {
        VolunteerProjects volunteerProject = volunteerProjectsService.getVolunteerProject(volunteerUnRegisteredUserDTO.getVolunteerProjectUUID());

        VolunteerRegisteredAnonymous volunteerRegisteredAnonymous = new VolunteerRegisteredAnonymous();
        volunteerRegisteredAnonymous.setVolunteerRegisteredAnonymousUUID(UUID.randomUUID().toString());
        volunteerRegisteredAnonymous.setEmail(volunteerUnRegisteredUserDTO.getEmail());
        volunteerRegisteredAnonymous.setFirstName(volunteerUnRegisteredUserDTO.getFirstName());
        volunteerRegisteredAnonymous.setLastName(volunteerUnRegisteredUserDTO.getLastName());
        volunteerRegisteredAnonymous.setContactNumber(volunteerUnRegisteredUserDTO.getContactNumber());
        volunteerRegisteredAnonymousRepo.save(volunteerRegisteredAnonymous);

        volunteerRegistered.setVolunteerRegisteredAnonymous(volunteerRegisteredAnonymous);
        volunteerRegistered.setVolunteerRegisteredUUID(UUID.randomUUID().toString());
        volunteerRegistered.setVolunteerProjects(volunteerProject);
        volunteerRegistered.setUser(null);

        volunteerRegisteredRepo.save(volunteerRegistered);
    }

    public void unRegisteredVolunteerProject(VolunteerRegisteredUserDTO volunteerRegisteredUserDTO) {
        VolunteerRegistered volunteerRegistered = volunteerRegisteredRepo.findById()

    }
}
