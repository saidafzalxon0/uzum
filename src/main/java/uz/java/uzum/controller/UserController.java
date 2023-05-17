package uz.java.uzum.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.java.uzum.dto.ResponseDto;
import uz.java.uzum.dto.UserDto;


@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    @PostMapping()
    public ResponseDto<UserDto> addUser(@RequestBody UserDto usersDto){
        return null;
    }


}
