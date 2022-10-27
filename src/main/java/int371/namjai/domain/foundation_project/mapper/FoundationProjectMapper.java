package int371.namjai.domain.foundation_project.mapper;


import int371.namjai.domain.foundation.mapper.FoundationContactDTO;
import int371.namjai.domain.foundation_project.FoundationProject;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FoundationProjectMapper {
    FoundationProjectMapper INSTANCE = Mappers.getMapper(FoundationProjectMapper.class);

    @Mappings({
            @Mapping(target = "projectUUID", source = "fdnProjectUUid"),
            @Mapping(target = "projectName", source = "fdnProjectName"),
            @Mapping(target = "projectDetail", source = "fdnProjectDetail"),
            @Mapping(target = "goal", expression = "java((long)foundationProject.getGoal())"),
            @Mapping(target = " received", expression = "java((long)foundationProject.getReceived())"),
            @Mapping(target = "status", source = "status"),
            @Mapping(target = "percentage", expression = "java((double)((foundationProject.getReceived()/foundationProject.getGoal()))*100)"),

    })
    FoundationProjectShortDTO toFoundationProjectShortDTO(FoundationProject foundationProject);

    List<FoundationProjectShortDTO> toFoundationProjectShortDtoList(List<FoundationProject> foundationProjectsList);

    @Mappings({
            @Mapping(target = "foundationProjectUUID", source = "foundationProject.fdnProjectUUid"),
            @Mapping(target = "foundationProjectName", source = "foundationProject.fdnProjectName"),
            @Mapping(target = "foundationProjectDetail", source = "foundationProject.fdnProjectDetail"),
            @Mapping(target = "foundationProjectDetailPlace", source = "foundationProject.fdnProjectDetail"),
            @Mapping(target = "goal", expression = "java((long)foundationProject.getGoal())"),
            @Mapping(target = " received", expression = "java((long)foundationProject.getReceived())"),
            @Mapping(target = "percentage", expression = "java((double)((foundationProject.getReceived()/foundationProject.getGoal()))*100)"),
            @Mapping(target = "startDate", source = "foundationProject.startDate"),
            @Mapping(target = "endDate", source = "foundationProject.endDate"),
            @Mapping(target = "picturePath", source = "foundationProject.picturePath"),
            @Mapping(target = "status", source = "foundationProject.status"),
            @Mapping(target = "targetCategoriesSet", source = "foundationProject.targetCategoriesSet"),
            @Mapping(target = "foundationContactDTO", source = "foundationContactDTO"),
    })
    FoundationProjectDTO toFoundationProjectDto(FoundationProject foundationProject, FoundationContactDTO foundationContactDTO);


    @Mappings({
            @Mapping(target = "value", source = "fdnProjectUUid"),
            @Mapping(target = "foundationProjectName", source = "fdnProjectName"),
            @Mapping(target = "totalAmount", source = "received"),
    })
    FoundationProjectListToRequest toFoundationProjectListToRequest(FoundationProject foundationProject);

    List<FoundationProjectListToRequest> toFoundationProjectListToRequests(List<FoundationProject> foundationProjects);
}
