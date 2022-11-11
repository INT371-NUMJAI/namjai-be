package int371.namjai.domain.foundation_project_progress.dto;

import int371.namjai.domain.foundation_project_progress.FoundationProjectProgress;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FoundationProjectProgressMapper {
    FoundationProjectProgressMapper INSTANCE = Mappers.getMapper(FoundationProjectProgressMapper.class);

    @Mappings({
            @Mapping(target = "foundationProjectProgressUUID", source = "foundationProjectProgress.fdnProjectProgressUUID"),
            @Mapping(target = "fdnProjectUUID", source = "foundationProjectProgress.foundationProject.fdnProjectUUid"),
            @Mapping(target = "proceedDate", source = "foundationProjectProgress.proceedDate", dateFormat = "dd MMM yyyy"),
            @Mapping(target = "title", source = "foundationProjectProgress.title"),
            @Mapping(target = "detail", source = "foundationProjectProgress.detail"),
            @Mapping(target = "picturePath", source = "foundationProjectProgress.picturePath"),
    })
    FoundationProjectProgressDisplayDTO toFoundationProjectProgressDisplayDto(FoundationProjectProgress foundationProjectProgress);

    List<FoundationProjectProgressDisplayDTO> toFoundationProjectProgressDisplayDTOList(List<FoundationProjectProgress> foundationProjectProgressList);
}
