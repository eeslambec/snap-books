package uz.bookstore.bookstore.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.bookstore.bookstore.dto.ResultMessage;
import uz.bookstore.bookstore.entity.Book;
import uz.bookstore.bookstore.entity.Cart;
import uz.bookstore.bookstore.repository.CartRepository;
import java.util.List;
@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService{
    private final CartRepository cartRepository;
    @Override
    public ResultMessage addBookToCart(Long id, Book book) {
        Cart cart = cart(id);
        cart.getBooks().add(book);
        return new ResultMessage(true,cart);
    }

    @Override
    public ResultMessage addCart(List<Book> books) {
        Cart cart=new Cart();
        cart.setBooks(books);
        cartRepository.save(cart);
        return new ResultMessage(true,cart);
    }

    @Override
    public ResultMessage getCart(Long id) {
       return new ResultMessage(true, cart(id));
    }

    @Override
    public ResultMessage getAllCart() {
        List<Cart> all = cartRepository.findAll();
        return new ResultMessage(true,all);
    }

    @Override
    public ResultMessage clearCart(Long id) {
        Cart cart = cart(id);
        if (cart.getBooks().isEmpty()){
            return new ResultMessage(false,"This cart empty");
        }else return new ResultMessage(true,"This cart cleared");
    }

    @Override
    public ResultMessage deleteCart(Long id) {
        Cart cart = cart(id);
        cartRepository.delete(cart);
        return new ResultMessage(true,cart);
    }
    private Cart cart(Long id) {
        return cartRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cart id not found"));
    }
}
