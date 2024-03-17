package uz.bookstore.bookstore.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import uz.bookstore.bookstore.entity.Book;

import java.time.LocalDate;
import java.util.List;

@Getter
@AllArgsConstructor
public class BookDto {
    Long id;
    String name;
    AuthorDto author;
    String description;
    Double price;
    String photoUrl;
    boolean isAvailable;
    boolean isEBook;
    String pdfUrl;
    LocalDate releaseDate;
    List<GenreDto> genre;

    public BookDto(Book book) {
        this.id = book.getId();
        this.name = book.getName();
        this.author = new AuthorDto(book.getName());
        this.description = book.getDescription();
        this.price = book.getPrice();
        this.photoUrl = book.getPhotoUrl();
        this.isAvailable = book.isAvailable();
        this.isEBook = book.isEBook();
        this.pdfUrl = book.getPdfUrl();
        this.releaseDate = book.getReleaseDate();
        this.genre = book.getGenre().stream()
                .map(v -> new GenreDto(v.getValue())).toList();
    }

    @Getter
    @AllArgsConstructor
    public static class AuthorDto {
        String name;
    }

    @Getter
    @AllArgsConstructor
    public static class GenreDto {
        String value;
    }
}