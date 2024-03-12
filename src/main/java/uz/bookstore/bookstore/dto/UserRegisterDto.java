package uz.bookstore.bookstore.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import uz.bookstore.bookstore.util.annotations.Email;
import uz.bookstore.bookstore.util.annotations.Password;
import uz.bookstore.bookstore.util.annotations.PhoneNumber;

@Getter
@AllArgsConstructor
public class UserRegisterDto {
    @Email
    private String email;
    @PhoneNumber
    private String phoneNumber;
    @Password
    private String password;
}
