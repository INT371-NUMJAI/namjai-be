package int371.namjai.domain.volunteer_registerred.mapper;

import int371.namjai.domain.volunteer_registerred.VolunteerEnrolled;
import int371.namjai.domain.volunteer_registerred.dto.EnrolledListVolunteerProject;
import int371.namjai.domain.volunteer_registerred.dto.UserEnrolledVolunteerProjectDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface VolunteerRegisteredMapper {
    VolunteerRegisteredMapper INSTANCE = Mappers.getMapper(VolunteerRegisteredMapper.class);

    //    @Mapping(target = "volunteerProjectUUID", source = "volunteerEnrolled.volunteerProjectUUID"),
    @Mappings({
            @Mapping(target = "volunteerEnrolledUUID", source = "volunteerEnrolledUUID"),
            @Mapping(target = "isMember", source = "volunteerEnrolled.isMember"),
            @Mapping(target = "firstName", source = "volunteerEnrolled.firstName"),
            @Mapping(target = "lastName", source = "volunteerEnrolled.lastName"),
            @Mapping(target = "contactNumber", source = "volunteerEnrolled.contactNumber"),
            @Mapping(target = "email", source = "volunteerEnrolled.email")
    })
    EnrolledListVolunteerProject toEnrolledVolunteerProject(VolunteerEnrolled volunteerEnrolled);

    List<EnrolledListVolunteerProject> toEnrolledListVolunteerProjects(List<VolunteerEnrolled> volunteerEnrolled);


    @Mappings({
            @Mapping(target = "userFavoriteUUID", source = "volunteerEnrolledUUID"),
            @Mapping(target = "userEmail", source = "volunteerEnrolled.email"),
            @Mapping(target = "typeOfFavorite", constant = "ENROLLED_VOLUNTEER"),
            @Mapping(target = "favoriteReferenceUUID", source = "volunteerEnrolled.volunteerProjects.volunteerProjectsUUID"),
            @Mapping(target = "favoriteReferenceTitle", source = "volunteerEnrolled.volunteerProjects.volunteerProjectName"),
            @Mapping(target = "enrolledDate", source = "volunteerEnrolled.enrolledDate", dateFormat = "dd MMM yyyy"),
    })
    UserEnrolledVolunteerProjectDTO toUserFavoriteDto(VolunteerEnrolled volunteerEnrolled);

    List<UserEnrolledVolunteerProjectDTO> toUserFavoriteDTO(List<VolunteerEnrolled> volunteerEnrolledList);

}
