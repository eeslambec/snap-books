package uz.bookstore.bookstore.service;

import uz.bookstore.bookstore.dto.CartDTO;
import uz.bookstore.bookstore.entity.Book;

import java.util.List;

public interface CartService {
    CartDTO addBookToCart(Long id, Book book);
    CartDTO getCart(Long id);
    List<CartDTO> getAllCart();
    CartDTO  clearCart(Long id);
    void   deleteCart(Long id);
}
