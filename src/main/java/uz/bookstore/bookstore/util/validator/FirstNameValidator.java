package uz.bookstore.bookstore.util.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import uz.bookstore.bookstore.util.annotations.FirstName;
import uz.bookstore.bookstore.exception.InvalidArgumentException;

public class FirstNameValidator implements ConstraintValidator<FirstName, String> {

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if (s == null)
            return true;
        if (s.matches("^[a-zA-Z]{2,50}$"))
            return true;
        throw new InvalidArgumentException("Name");
    }
}
