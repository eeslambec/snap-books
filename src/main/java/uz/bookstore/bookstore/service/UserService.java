package uz.bookstore.bookstore.service;

import org.springframework.stereotype.Service;
import uz.bookstore.bookstore.dto.UserLoginDto;
import uz.bookstore.bookstore.dto.UserRegisterDto;
import uz.bookstore.bookstore.dto.UserDto;
import uz.bookstore.bookstore.dto.UserUpdateDto;
import uz.bookstore.bookstore.entity.Role;

import java.util.List;

@Service
public interface UserService {
    UserDto register(UserRegisterDto userRegisterDto);
    UserDto login(UserLoginDto  userLoginDto);
    UserDto getById(Long id);
    UserDto getByEmail(String email);
    List<UserDto> getByUserRole(Role role);
    List<UserDto> getAll();
    void deleteById(Long id);
    void deleteByEmail(String email);
    UserDto update(UserUpdateDto userUpdateDto);
}
