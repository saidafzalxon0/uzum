package uz.java.uzum.service.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import uz.java.uzum.dto.UserDto;
import uz.java.uzum.entity.User;


@Mapper(componentModel = "spring")
public abstract class UsersMapper implements CommonMapper<UserDto, User> {
    @Mapping(target = "birthDate", dateFormat = "dd.MM.yyyy")
    public abstract User toEntity(UserDto dto);

    @Mapping(target = "birthDate", dateFormat = "dd.MM.yyyy")
    public abstract UserDto toDto(User users);

}
