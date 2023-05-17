package uz.java.uzum.service.Impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.java.uzum.dto.ResponseDto;
import uz.java.uzum.dto.UserDto;

import uz.java.uzum.entity.User;

import uz.java.uzum.repository.UserRepository;
import uz.java.uzum.service.UserService;
import uz.java.uzum.service.mapper.UsersMapper;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UsersMapper usersMapper;
    private final UserRepository usersRepository;


    @Override
    public ResponseDto<UserDto> addUser(UserDto dto) {
        User users = usersMapper.toEntity(dto);
        usersRepository.save(users);

        return ResponseDto.<UserDto>builder()
                .success(true)
                .data(usersMapper.toDto(users))
                .message("OK")
                .build();
    }

    @Override
    public ResponseDto<UserDto> updateUser(UserDto usersDto) {
        return null;
    }

    @Override
    public ResponseDto<UserDto> getById(Integer id) {
        return null;
    }
}
