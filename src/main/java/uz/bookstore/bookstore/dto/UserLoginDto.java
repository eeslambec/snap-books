package uz.bookstore.bookstore.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import uz.bookstore.bookstore.util.annotations.Password;

@Getter
@AllArgsConstructor
public class UserLoginDto {
    private String emailOrPhoneNumber;
    @Password
    private String password;
}
