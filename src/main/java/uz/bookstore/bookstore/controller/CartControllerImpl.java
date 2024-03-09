package uz.bookstore.bookstore.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import uz.bookstore.bookstore.dto.ResultMessage;
import uz.bookstore.bookstore.entity.Book;
import uz.bookstore.bookstore.service.CartService;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class CartControllerImpl implements CartController {
    private final CartService cartService;

    @Override
    public ResponseEntity<?> addBookToCart(Long id, Book book) {
        ResultMessage result = cartService.addBookToCart(id, book);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(result);
    }

    @Override
    public ResponseEntity<?> addCart(List<Book> books) {
        ResultMessage result = cartService.addCart(books);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @Override
    public ResponseEntity<?> getCart(Long id) {
        ResultMessage result = cartService.getCart(id);
        return ResponseEntity.ok(result.getObject());
    }

    @Override
    public ResponseEntity<?> getAllCart() {
        ResultMessage result = cartService.getAllCart();
        return ResponseEntity.ok(result.getObject());
    }

    @Override
    public ResponseEntity<?> clearCart(Long id) {
        ResultMessage result = cartService.clearCart(id);
        return ResponseEntity
                .status(result.isSuccess()
                        ? HttpStatus.ACCEPTED
                        : HttpStatus.NOT_ACCEPTABLE)
                .body(result);
    }

    @Override
    public ResponseEntity<?> deleteCart(Long id) {
        return ResponseEntity.ok(cartService.deleteCart(id));
    }
}
