package uz.bookstore.bookstore.exception;

public class ConflictException extends RuntimeException {
    public ConflictException(String message) {
        super(message);
    }

}