package int371.namjai.domain.foundation.mapper;

import int371.namjai.domain.foundation.Foundation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FoundationMapper {
    FoundationMapper INSTANCE = Mappers.getMapper(FoundationMapper.class);

    @Mappings({
            @Mapping(target = "fdnUUID", source = "fdnUUid"),
            @Mapping(target = "fdnName", source = "fdnName"),
            @Mapping(target = "createDate", source = "createDate", dateFormat = "dd/MM/yyyy"),
            @Mapping(target = "status", source = "status"),
            @Mapping(target = "approval", source = "approval")
    })
    APIFDNShort foundationToBrief(Foundation foundation);

    List<APIFDNShort> toAPIFDNShort(List<Foundation> foundationList);
}
