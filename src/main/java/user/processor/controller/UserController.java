package user.processor.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import user.processor.model.User;
import user.processor.service.UserService;
import user.processor.dto.UserDto;
import user.processor.mapper.UserMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;

    @Autowired
    public UserController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @PostMapping
    public ResponseEntity<UserDto> saveUserData(@Valid @RequestBody UserDto userDto) {
        userService.findByPhoneNumber(userDto.getPhoneNumber())
                .ifPresent(u -> {
                    throw new RuntimeException("User with this phone number already exist");
                });

        userService.findByEmail(userDto.getEmail())
                .ifPresent(u -> {
                    throw new RuntimeException("User with this email already exist");
                });
        User created = userService.save(userMapper.toModel(userDto));
        return ResponseEntity.ok(userMapper.toDto(created));
    }

    @GetMapping("/search/phone")
    public ResponseEntity<UserDto> findByPhoneNumber(@RequestParam(name = "phoneNumber") String phoneNumber) {
        return userService.findByPhoneNumber(phoneNumber)
                .map(userMapper::toDto)
                .map(ResponseEntity::ok)
                .orElseGet(ResponseEntity.status(HttpStatus.NOT_FOUND)::build);
    }

    @GetMapping("/search/email")
    public ResponseEntity<UserDto> findByEmail(@RequestParam(name = "email") String email) {
        return userService.findByEmail(email)
                .map(userMapper::toDto)
                .map(ResponseEntity::ok)
                .orElseGet(ResponseEntity.status(HttpStatus.NOT_FOUND)::build);
    }
}
