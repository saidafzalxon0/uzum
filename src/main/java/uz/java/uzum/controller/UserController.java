package uz.java.uzum.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import uz.java.uzum.dto.ResponseDto;
import uz.java.uzum.dto.UserDto;
import uz.java.uzum.service.UserService;


@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping()
    public ResponseDto<UserDto> addUser(@RequestBody UserDto usersDto){
        return userService.addUser(usersDto);
    }

    @PatchMapping
    public ResponseDto<UserDto> updateUser(@RequestBody UserDto userDto){
        return userService.updateUser(userDto);
    }

    @GetMapping("/{id}")
    public ResponseDto<UserDto> getUserById(@PathVariable Integer id){
        return userService.getById(id);
    }

    @DeleteMapping("/{id}")
    public ResponseDto<UserDto> deleteUser(@PathVariable Integer id){
        return userService.delete(id);
    }


}
