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
            @Mapping(target = "goal", source = "goal")
    })
    FoundationProjectShortDTO toFoundationProjectShortDTO(FoundationProject foundationProject);

    List<FoundationProjectShortDTO> toFoundationProjectShortDtoList(List<FoundationProject> foundationProjectsList);

    @Mappings({
            @Mapping(target = "foundationProjectUUID", source = "foundationProject.fdnProjectUUid"),
            @Mapping(target = "foundationProjectName", source = "foundationProject.fdnProjectName"),
            @Mapping(target = "foundationProjectDetail", source = "foundationProject.fdnProjectDetail"),
            @Mapping(target = "foundationProjectDetailPlace", source = "foundationProject.fdnProjectDetail"),
            @Mapping(target = "goal", source = "foundationProject.goal"),
            @Mapping(target = "startDate", source = "foundationProject.startDate"),
            @Mapping(target = "endDate", source = "foundationProject.endDate"),
            @Mapping(target = "picturePath", source = "foundationProject.picturePath"),
            @Mapping(target = "targetCategoriesSet", source = "foundationProject.targetCategoriesSet"),
            @Mapping(target = "foundationContactDTO", source = "foundationContactDTO"),
    })
    FoundationProjectDTO toFoundationProjectDto(FoundationProject foundationProject, FoundationContactDTO foundationContactDTO);

}
