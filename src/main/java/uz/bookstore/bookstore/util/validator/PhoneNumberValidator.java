package uz.bookstore.bookstore.util.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import uz.bookstore.bookstore.exception.InvalidArgumentException;
import uz.bookstore.bookstore.util.annotations.PhoneNumber;

public class PhoneNumberValidator implements ConstraintValidator<PhoneNumber, String> {

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if (s == null)
            return true;
        if (s.matches("^\\+998\\d{2}\\d{3}\\d{2}\\d{2}$"))
            return true;
        throw new InvalidArgumentException("Phone number");
    }
}
