package uz.bookstore.bookstore.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AuthorUpdateDto {
    private Long id;
    private String name;
}
