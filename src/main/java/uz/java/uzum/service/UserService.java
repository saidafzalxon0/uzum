package uz.java.uzum.service;

import uz.java.uzum.dto.ResponseDto;
import uz.java.uzum.dto.UserDto;


public interface UserService {
    ResponseDto<UserDto> addUser(UserDto dto);
    ResponseDto<UserDto> updateUser(UserDto usersDto);
//    public ResponseDto<EntityModel<UsersDto>> getUserByPhoneNumber(String phoneNumber);
    ResponseDto<UserDto> getById(Integer id);
    ResponseDto<UserDto> delete(Integer id);



}
