package uz.bookstore.bookstore.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.bookstore.bookstore.dto.CartDTO;
import uz.bookstore.bookstore.entity.Book;
import uz.bookstore.bookstore.service.CartService;

import java.util.List;

@RequestMapping("/api/v1/cart")
@RestController
@RequiredArgsConstructor
public class CartControllerImpl {
    private final CartService cartService;

    @PostMapping("/{id}")
    public ResponseEntity<?> addBookToCart(@PathVariable Long id, @RequestBody Book book) {
        CartDTO result = cartService.addBookToCart(id, book);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCart(@PathVariable Long id) {
        CartDTO result = cartService.getCart(id);
        return ResponseEntity.ok(result);
    }

    @GetMapping
    public ResponseEntity<?> getAllCart() {
        List<CartDTO> result = cartService.getAllCart();
        return ResponseEntity.ok(result);
    }

    @PutMapping("/clear/{id}")
    public ResponseEntity<?> clearCart(@PathVariable Long id) {
        CartDTO result = cartService.clearCart(id);
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCart(@PathVariable Long id) {
        cartService.deleteCart(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                .body("Successfully deleted");
    }
}
