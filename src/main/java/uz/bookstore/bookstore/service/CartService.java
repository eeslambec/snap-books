package uz.bookstore.bookstore.service;

import org.springframework.http.ResponseEntity;
import uz.bookstore.bookstore.dto.ResultMessage;
import uz.bookstore.bookstore.entity.Book;

import java.util.List;

public interface CartService {
    ResultMessage  addBookToCart(Long id, Book book);
    ResultMessage addCart(List<Book> books);
    ResultMessage getCart(Long id);
    ResultMessage getAllCart();
    ResultMessage  clearCart(Long id);
    ResultMessage  deleteCart(Long id);
}
