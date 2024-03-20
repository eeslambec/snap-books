package uz.bookstore.bookstore.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import uz.bookstore.bookstore.entity.Book;
import java.util.List;

@Getter
@AllArgsConstructor
public class CartDTO {
    private Long id;
    private List<Book> books;
}
