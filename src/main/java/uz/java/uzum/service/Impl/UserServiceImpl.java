package uz.java.uzum.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.java.uzum.dto.ResponseDto;
import uz.java.uzum.dto.UserDto;

import uz.java.uzum.entity.User;

import uz.java.uzum.repository.UserRepository;
import uz.java.uzum.service.UserService;
import uz.java.uzum.service.mapper.UsersMapper;

import java.util.Optional;

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
        if (usersDto.getId() == null){
            return ResponseDto.<UserDto>builder()
                    .message("User id null")
                    .code(-2)
                    .data(usersDto)
                    .build();
        }

        Optional<User> userOptional = usersRepository.findById(usersDto.getId());

        if (userOptional.isEmpty()){
            return ResponseDto.<UserDto>builder()
                    .message("User with ID " + usersDto.getId() + " is not found")
                    .code(-1)
                    .data(usersDto)
                    .build();
        }

        User user = userOptional.get();
        if (usersDto.getGender() != null) {
            user.setGender(usersDto.getGender());
        }
        if (usersDto.getEmail() != null) {
            user.setEmail(usersDto.getEmail());
        }
        if (usersDto.getLastName() != null) {
            user.setLastName(usersDto.getLastName());
        }
        if (usersDto.getBirthDate() != null) {
            user.setBirthDate(usersDto.getBirthDate());
        }
        if (usersDto.getMiddleName() != null) {
            user.setMiddleName(usersDto.getMiddleName());
        }
        if (usersDto.getFirstName() != null) {
            user.setFirstName(usersDto.getFirstName());
        }
        if (usersDto.getPhoneNumber() != null) {
            user.setPhoneNumber(usersDto.getPhoneNumber());
        }

        try {
            usersRepository.save(user);

            return ResponseDto.<UserDto>builder()
                    .data(usersMapper.toDto(user))
                    .success(true)
                    .message("OK")
                    .build();
        } catch (Exception e) {
            return ResponseDto.<UserDto>builder()
                    .data(usersMapper.toDto(user))
                    .code(1)
                    .message("Error while saving user: " + e.getMessage())
                    .build();
        }




    }

    @Override
    public ResponseDto<UserDto> getById(Integer id) {
        return null;
    }
}
