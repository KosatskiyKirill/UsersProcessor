package user.processor.dto;

import java.time.LocalDate;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {
    private String name;
    private String lastName;
    @NotNull
    @Pattern(regexp = "^\\+?[1-9][0-9]{7,11}$", message = "Please type correct phone number")
    private String phoneNumber;
    @NotNull
    @Email(message = "Please type correct email")
    private String email;
    private String app;
    private LocalDate date;
}
