package uz.bookstore.bookstore.dto;

import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.bookstore.bookstore.entity.Book;

import java.util.List;

@Setter
@AllArgsConstructor
public class CartDTO {
    private Long id;
    private List<Book> books;
}
