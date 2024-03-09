package uz.bookstore.bookstore.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import uz.bookstore.bookstore.entity.Book;

import java.util.List;
@RestController
public class CartControllerImpl implements CartController{
    @Override
    public ResponseEntity<?> addBookToCart(Long id, Book book) {
        return null;
    }

    @Override
    public ResponseEntity<?> addCart(List<Book> books) {
        return null;
    }

    @Override
    public ResponseEntity<?> getCart(Long id) {
        return null;
    }

    @Override
    public ResponseEntity<?> getAllCart() {
        return null;
    }

    @Override
    public ResponseEntity<?> clearCart(Long id) {
        return null;
    }

    @Override
    public ResponseEntity<?> deleteCart(Long id) {
        return null;
    }
}
