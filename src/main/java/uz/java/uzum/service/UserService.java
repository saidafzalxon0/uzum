package uz.java.uzum.service;

import uz.java.uzum.dto.ResponseDto;
import uz.java.uzum.dto.UserDto;


public interface UserService {
    public ResponseDto<UserDto> addUser(UserDto dto);
    public ResponseDto<UserDto> updateUser(UserDto usersDto);
//    public ResponseDto<EntityModel<UsersDto>> getUserByPhoneNumber(String phoneNumber);
    public ResponseDto<UserDto> getById(Integer id);


}
