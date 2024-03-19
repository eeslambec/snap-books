package uz.bookstore.bookstore.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import uz.bookstore.bookstore.entity.Author;

import java.time.LocalDate;
import java.util.List;

@Getter
@AllArgsConstructor
public class AuthorDto {
    Long id;
    String name;
    List<BookDto> books;

    public AuthorDto(Author author) {
        this.id = author.getId();
        this.name = author.getName();
        this.books = author.getBooks().stream().map(
                b -> new BookDto(b.getName(), b.getReleaseDate())
        ).toList();
    }

    @Getter
    @AllArgsConstructor
    public static class BookDto {
        String name;
        LocalDate releaseDate;
    }
}