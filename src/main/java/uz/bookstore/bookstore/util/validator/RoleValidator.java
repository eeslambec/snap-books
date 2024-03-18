package uz.bookstore.bookstore.util.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import uz.bookstore.bookstore.exception.InvalidArgumentException;
import uz.bookstore.bookstore.util.annotations.Role;

public class RoleValidator implements ConstraintValidator<Role, String> {

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if (s == null)
            return true;
        if (s.matches("^ROLE_[A-Z]+$"))
            return true;
        throw new InvalidArgumentException("Role");
    }
}
