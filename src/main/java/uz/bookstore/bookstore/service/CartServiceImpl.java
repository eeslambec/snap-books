package uz.bookstore.bookstore.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.bookstore.bookstore.dto.CartDTO;
import uz.bookstore.bookstore.entity.Book;
import uz.bookstore.bookstore.entity.Cart;
import uz.bookstore.bookstore.exception.NotFoundException;
import uz.bookstore.bookstore.repository.CartRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {
    private final CartRepository cartRepository;

    @Override
    public CartDTO addBookToCart(Long id, Book book) {
        Cart cart = cart(id);
        cart.getBooks().add(book);
        cartRepository.save(cart);
        return getCartDTO(cart);
    }

    @Override
    public CartDTO getCart(Long id) {
        return getCartDTO(cart(id));
    }

    @Override
    public List<CartDTO> getAllCart() {
        List<Cart> all = cartRepository.findAll();
        List<CartDTO> result = new ArrayList<>();
        for (Cart cart : all) {
            result.add(getCartDTO(cart));
        }
        return result;
    }

    @Override
    public CartDTO clearCart(Long id) {
        Cart cart = cart(id);
        cart.setBooks(new ArrayList<>());
        cartRepository.save(cart);
        return getCartDTO(cart);
    }

    @Override
    public void deleteCart(Long id) {
        Cart cart = cart(id);
        cartRepository.delete(cart);
    }

    private Cart cart(Long id) {
        return cartRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("There is no cart with this id " + id));
    }

    private CartDTO getCartDTO(Cart cart) {
        return new CartDTO(cart.getId(), cart.getBooks());
    }
}
