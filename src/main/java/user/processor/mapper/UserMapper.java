package user.processor.mapper;

import java.time.LocalDate;
import user.processor.model.User;
import user.processor.dto.UserDto;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserDto toDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setName(user.getName());
        userDto.setLastName(user.getLastName());
        userDto.setPhoneNumber(user.getPhoneNumber());
        userDto.setEmail(user.getEmail());
        userDto.setApp(user.getApp());
        userDto.setDate(user.getDate());
        return userDto;
    }

    public User toModel(UserDto userDto) {
        User user = new User();
        user.setName(userDto.getName());
        user.setLastName(userDto.getLastName());
        user.setPhoneNumber(userDto.getPhoneNumber());
        user.setEmail(userDto.getEmail());
        user.setApp(userDto.getApp());

        if (userDto.getDate() == null) {
            user.setDate(LocalDate.now());
        } else {
            user.setDate(userDto.getDate());
        }
        return user;
    }
}
