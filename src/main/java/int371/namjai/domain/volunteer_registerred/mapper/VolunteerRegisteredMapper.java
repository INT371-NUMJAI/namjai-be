package int371.namjai.domain.volunteer_registerred.mapper;

import int371.namjai.domain.user.User;
import int371.namjai.domain.user.UserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface VolunteerRegisteredMapper {
    VolunteerRegisteredMapper INSTANCE = Mappers.getMapper(VolunteerRegisteredMapper.class);

    @Mappings({
            @Mapping(target = "userUUID", source = "userUUid"),
            @Mapping(target = "firstName", source = "firstName"),
            @Mapping(target = "lastName", source = "lastName"),
            @Mapping(target = "contactNumber", source = "phoneNumber"),
            @Mapping(target = "email", source = "email")
    })
    UserDTO toUserDto(User user);
}
