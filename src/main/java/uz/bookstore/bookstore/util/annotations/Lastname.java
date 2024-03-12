package uz.bookstore.bookstore.util.annotations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import uz.bookstore.bookstore.util.validator.LastnameValidator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = LastnameValidator.class)
public @interface Lastname {
    String message() default "";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
