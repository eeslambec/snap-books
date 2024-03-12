package uz.bookstore.bookstore.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import uz.bookstore.bookstore.entity.User;
import uz.bookstore.bookstore.util.annotations.Email;
import uz.bookstore.bookstore.util.annotations.PhoneNumber;

@Getter
@AllArgsConstructor
public class UserDto {
    private Long id;
    @Email
    private String email;
    @PhoneNumber
    private String phoneNumber;


    public UserDto (User user){
        this.id = user.getId();
        this.email = user.getEmail();
        this.phoneNumber = user.getPhoneNumber();
    }
}
