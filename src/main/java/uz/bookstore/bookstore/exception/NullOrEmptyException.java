package uz.bookstore.bookstore.exception;

public class NullOrEmptyException extends RuntimeException{
    public NullOrEmptyException(String message) {
        super(message + " is empty!");
    }
}
