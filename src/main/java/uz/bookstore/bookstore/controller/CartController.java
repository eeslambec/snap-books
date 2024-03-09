package uz.bookstore.bookstore.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.bookstore.bookstore.entity.Book;

import java.util.List;

@RequestMapping("/api/v1/cart")
public interface CartController {
    @PostMapping("/{id}")
    ResponseEntity<?>addBookToCart(@PathVariable Long id, @RequestBody Book book);
    @PostMapping
    ResponseEntity<?>addCart(@RequestBody List<Book>books);
    @GetMapping("/{id}")
    ResponseEntity<?>getCart(@PathVariable Long id);
    @GetMapping
    ResponseEntity<?>getAllCart();

    @PutMapping("/{id}")
    ResponseEntity<?> clearCart(@PathVariable Long id);
    @DeleteMapping("/{id}")
    ResponseEntity<?> deleteCart(@PathVariable Long id);

}
