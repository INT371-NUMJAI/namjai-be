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


    @Mappings({
            @Mapping(target = "fdnUUID", source = "fdnUUid"),
            @Mapping(target = "fdnName", source = "fdnName"),
            @Mapping(target = "addressDetail", source = "addressDetail"),
            @Mapping(target = "subDistrict", source = "subDistrict"),
            @Mapping(target = "district", source = "district"),
            @Mapping(target = "province", source = "province"),
            @Mapping(target = "postalCode", source = "postalCode"),
            @Mapping(target = "email", source = "email"),
            @Mapping(target = "contactNumber", source = "contactNumber"),
    })
    FoundationContactDTO toFoundationContactDto(Foundation foundation);


    @Mappings({
            @Mapping(target = "fdnUUID", source = "foundation.fdnUUid"),
            @Mapping(target = "fdnName", source = "foundation.fdnName"),
            @Mapping(target = "fdnEmail", source = "foundation.email"),
            @Mapping(target = "profilePath", source = "foundation.profilePath"),
    })
    APIFDNList foundationListShort(Foundation foundation);

    List<APIFDNList> toFDNListShort(List<Foundation> foundationList);

}
