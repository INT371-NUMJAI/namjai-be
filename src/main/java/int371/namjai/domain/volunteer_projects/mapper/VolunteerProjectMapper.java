package int371.namjai.domain.volunteer_projects.mapper;

import int371.namjai.domain.foundation.mapper.FoundationContactDTO;
import int371.namjai.domain.volunteer_projects.VolunteerProjects;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface VolunteerProjectMapper {
    VolunteerProjectMapper INSTANCE = Mappers.getMapper(VolunteerProjectMapper.class);

    @Mappings({
            @Mapping(target = "volunteerProjectsUUID", source = " volunteerProjects.volunteerProjectsUUID"),
            @Mapping(target = "volunteerProjectName", source = " volunteerProjects.volunteerProjectName"),
            @Mapping(target = "status", source = " volunteerProjects.status"),
            @Mapping(target = "peopleNeeded", source = " volunteerProjects.peopleNeeded"),
            @Mapping(target = "peopleRegistered", source = " volunteerProjects.peopleRegistered"),
            @Mapping(target = "description", source = " volunteerProjects.description"),
            @Mapping(target = "activityType", source = " volunteerProjects.activityType"),
            @Mapping(target = "startDate", source = " volunteerProjects.startDate"),
            @Mapping(target = "endDate", source = " volunteerProjects.endDate"),
            @Mapping(target = "activityStartDate", source = "volunteerProjects.activityStartDate"),//
            @Mapping(target = "activityEndDate", source = "volunteerProjects.activityEndDate"), //
            @Mapping(target = "locationDetail", source = "volunteerProjects.locationDetail"),
            @Mapping(target = "locationDistrict", source = "volunteerProjects.locationDistrict"),
            @Mapping(target = "locationSubDistrict", source = "volunteerProjects.locationSubDistrict"),
            @Mapping(target = "locationProvince", source = "volunteerProjects.locationProvince"),
            @Mapping(target = "locationPostalCode", source = "volunteerProjects.locationPostalCode"),
            @Mapping(target = "targetCategoriesSet", source = "volunteerProjects.targetCategoriesSet"),
            @Mapping(target = "qualify", source = "volunteerProjects.qualify"),
            @Mapping(target = "duty", source = "volunteerProjects.duty"),
            @Mapping(target = "picturePath", source = "volunteerProjects.picturePath"),
            @Mapping(target = "foundationContactDTO", source = "contact"),

    })
    VolunteerProjectDetailDTO toVolunteerProjectDetailDto(VolunteerProjects volunteerProjects, FoundationContactDTO contact);


    @Mappings({
            @Mapping(target = "volunteerProjectUUID", source = " volunteerProjects.volunteerProjectsUUID"),
            @Mapping(target = "volunteerProjectName", source = " volunteerProjects.volunteerProjectName"),
            @Mapping(target = "foundationName", source = "volunteerProjects.foundation.fdnName"),
            @Mapping(target = "peopleNeeded", source = " volunteerProjects.peopleNeeded"),
            @Mapping(target = "peopleRegistered", source = " volunteerProjects.peopleRegistered"),
            @Mapping(target = "activityStartDate", source = " volunteerProjects.activityStartDate"),
            @Mapping(target = "activityEndDate", source = " volunteerProjects.activityEndDate"),
            @Mapping(target = "locationProvince", source = "volunteerProjects.locationProvince"),
            @Mapping(target = "status", source = "volunteerProjects.status"),
            @Mapping(target = "picturePath", source = "volunteerProjects.picturePath"),
    })
    VolunteerProjectShort toVolunteerProjectShort(VolunteerProjects volunteerProjects);

    List<VolunteerProjectShort> toVolunteerProjectShortList(List<VolunteerProjects> volunteerProjectsList);


}
