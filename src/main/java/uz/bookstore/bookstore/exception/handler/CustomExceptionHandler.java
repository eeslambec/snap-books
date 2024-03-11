package uz.bookstore.bookstore.exception.handler;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import uz.bookstore.bookstore.exception.BadRequestException;
import uz.bookstore.bookstore.exception.ConflictException;
import uz.bookstore.bookstore.exception.ContentTypeException;
import uz.bookstore.bookstore.exception.NotFoundException;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@RequiredArgsConstructor
@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(value = {BadRequestException.class})
    public ResponseEntity<Object> handlerCustomException(BadRequestException badRequestException) {
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;
        CustomException customException = new CustomException(
                badRequestException.getMessage(),
                badRequest,
                ZonedDateTime.now(ZoneId.of("Asia/Tashkent")));
        return new ResponseEntity<>(customException, badRequest);
    }


    @ExceptionHandler(value = {ConflictException.class})
    public ResponseEntity<Object> handlerCustomException(ConflictException conflictException) {
        HttpStatus conflict = HttpStatus.CONFLICT;
        CustomException customException = new CustomException(
                conflictException.getMessage(),
                conflict,
                ZonedDateTime.now(ZoneId.of("Asia/Tashkent")));
        return new ResponseEntity<>(customException, conflict);
    }

    @ExceptionHandler(value = {NotFoundException.class})
    public ResponseEntity<Object> handlerCustomException(NotFoundException notFoundException) {
        HttpStatus notFound = HttpStatus.NOT_FOUND;
        CustomException customException = new CustomException(
                notFoundException.getMessage(),
                notFound,
                ZonedDateTime.now(ZoneId.of("Asia/Tashkent")));
        return new ResponseEntity<>(customException, notFound);
    }


    @ExceptionHandler(value = {ContentTypeException.class})
    public ResponseEntity<Object> handlerCustomException(ContentTypeException contentTypeException) {
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;
        CustomException customException = new CustomException(
                contentTypeException.getMessage(),
                badRequest,
                ZonedDateTime.now(ZoneId.of("Asia/Tashkent")));
        return new ResponseEntity<>(customException, badRequest);
    }

    @ExceptionHandler(value = RuntimeException.class)
    public ResponseEntity<?> otherRunTimeErrors(Exception ignoredException) {
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;
        CustomException customException = new CustomException(
                "some error",
                badRequest,
                ZonedDateTime.now(ZoneId.of("Asia/Tashkent")));
        return new ResponseEntity<>(customException, badRequest);
    }


}
