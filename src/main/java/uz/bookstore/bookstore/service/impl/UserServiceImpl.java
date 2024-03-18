package uz.bookstore.bookstore.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.bookstore.bookstore.dto.UserLoginDto;
import uz.bookstore.bookstore.dto.UserRegisterDto;
import uz.bookstore.bookstore.dto.UserDto;
import uz.bookstore.bookstore.dto.UserUpdateDto;
import uz.bookstore.bookstore.entity.Role;
import uz.bookstore.bookstore.entity.User;
import uz.bookstore.bookstore.exception.AlreadyExistsException;
import uz.bookstore.bookstore.exception.NotFoundException;
import uz.bookstore.bookstore.exception.NullOrEmptyException;
import uz.bookstore.bookstore.repository.UserRepository;
import uz.bookstore.bookstore.service.UserService;
import uz.bookstore.bookstore.util.Validations;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    @Override
    public UserDto register(UserRegisterDto userRegisterDto) {
        if (userRegisterDto == null)
            throw new NullOrEmptyException("User authorization dto");
        if (Validations.isNullOrEmpty(userRegisterDto.getPassword()))
            throw new NullOrEmptyException("Password");
        if (Validations.isNullOrEmpty(userRegisterDto.getEmail()))
            throw new NullOrEmptyException("Email");
        if (Validations.isNullOrEmpty(userRegisterDto.getPhoneNumber()))
            throw new NullOrEmptyException("PhoneNumber");
        if (userRepository.findByPhoneNumberAndActive(userRegisterDto.getPhoneNumber(), true).isPresent())
            throw new AlreadyExistsException("User with this phoneNumber:" + userRegisterDto.getPhoneNumber());
        if (userRepository.findByEmailAndActive(userRegisterDto.getEmail(), true).isPresent())
            throw new AlreadyExistsException("User with this email:" + userRegisterDto.getEmail());
        return new UserDto(userRepository.save(User.builder()
                .email(userRegisterDto.getEmail())
                .password(passwordEncoder.encode(userRegisterDto.getPassword()))
                .phoneNumber(userRegisterDto.getPhoneNumber())
                .isActive(true)
                .build()));
    }

    @Override
    public UserDto login(UserLoginDto userLoginDto) {
        if (userLoginDto == null)
            throw new NullOrEmptyException("User login dto");
        if (Validations.isNullOrEmpty(userLoginDto.getPassword()))
            throw new NullOrEmptyException("Password");
        if (Validations.isNullOrEmpty(userLoginDto.getEmailOrPhoneNumber()))
            throw new NullOrEmptyException("email or phone number");
        if (Validations.isEmailOrPhoneNumber(userLoginDto.getEmailOrPhoneNumber())) {
            Optional<User> byPhoneNumberAndActive = userRepository.findByPhoneNumberAndActive(userLoginDto.getEmailOrPhoneNumber(), true);
            if (byPhoneNumberAndActive.isEmpty()) {
                throw new NullOrEmptyException("User with this phone number " + userLoginDto.getEmailOrPhoneNumber());
            }
            return new UserDto(byPhoneNumberAndActive.get());
        }
        else {
            Optional<User> byEmailAndActive = userRepository.findByEmailAndActive(userLoginDto.getEmailOrPhoneNumber(), true);
            if (byEmailAndActive.isEmpty()) {
                throw new NullOrEmptyException("User with this email " + userLoginDto.getEmailOrPhoneNumber());
            }
            return new UserDto(byEmailAndActive.get());
        }
    }

    @Override
    public UserDto getById(Long id) {
        if (id == null)
            throw new NullOrEmptyException("Id");
        return new UserDto(userRepository.findById(id).orElseThrow(() -> new NullOrEmptyException("User with this id " + id)));
    }

    @Override
    public UserDto getByEmail(String email) {
        if (Validations.isNullOrEmpty(email))
            throw new NullOrEmptyException("Email");
        return new UserDto(userRepository.findByEmailAndActive(email, true).orElseThrow(() -> new NullOrEmptyException("User with this email " + email)));
    }

    @Override
    public List<UserDto> getByUserRole(Role role) {
        if (role == null)
            throw new NullOrEmptyException("Role");
        List<User> users = userRepository.findAllByRoleAndActive(role, true);
        if (users.isEmpty())
            throw new NotFoundException("Users with this role " + role);
        return users.stream().map(UserDto::new).toList();
    }

    @Override
    public List<UserDto> getAll() {
        List<User> users = userRepository.findAllByActive(true);
        if (users.isEmpty())
            throw new NotFoundException("Users");
        return users.stream().map(UserDto::new).toList();
    }

    @Override
    public void deleteById(Long id) {
        if (id == null)
            throw new NullOrEmptyException("Id");
        User user = userRepository.findById(id).orElseThrow(
                () -> new NullOrEmptyException("User with this id " + id));
        user.setActive(false);
        userRepository.save(user);
    }

    @Override
    public void deleteByEmail(String email) {
        if (Validations.isNullOrEmpty(email))
            throw new NullOrEmptyException("Email");
        User user = userRepository.findByEmailAndActive(email, true).orElseThrow(
                () -> new NullOrEmptyException("User with this email " + email));
        user.setActive(false);
        userRepository.save(user);
    }

    @Override
    public UserDto update(UserUpdateDto userUpdateDto) {
        User user = userRepository.findByIdAndActive(userUpdateDto.getId(), true).orElseThrow(
                () -> new NotFoundException("User")
        );
        if (userUpdateDto.getEmail() != null && userRepository.findByEmailAndActive(userUpdateDto.getEmail(), true).isPresent())
            throw new AlreadyExistsException("Email");

        return new UserDto(userRepository.save(
                User.builder()
                        .id(user.getId())
                        .password(Validations.requireNonNullElse(passwordEncoder.encode(userUpdateDto.getPassword()), user.getPassword()))
                        .email(Validations.requireNonNullElse(userUpdateDto.getEmail(), user.getEmail()))
                        .phoneNumber(Validations.requireNonNullElse(userUpdateDto.getPhoneNumber(), user.getPhoneNumber()))
                        .build()
        ));
    }
}
