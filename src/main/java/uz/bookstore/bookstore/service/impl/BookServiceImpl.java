package uz.bookstore.bookstore.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import uz.bookstore.bookstore.dto.request.BookSaveDto;
import uz.bookstore.bookstore.dto.request.BookUpdateDto;
import uz.bookstore.bookstore.dto.response.BookDto;
import uz.bookstore.bookstore.entity.Book;
import uz.bookstore.bookstore.entity.Genre;
import uz.bookstore.bookstore.exception.NotFoundException;
import uz.bookstore.bookstore.exception.NullOrEmptyException;
import uz.bookstore.bookstore.repository.BookRepository;
import uz.bookstore.bookstore.service.BookService;
import uz.bookstore.bookstore.util.Validations;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;

    private static final String BASE_URL =  "src/main/resources/pictures";
    private static final Path UPLOAD_DIR = Path.of(System.getProperty("user.home") + File.separator);

    @SneakyThrows
    @Override
    public BookDto save(BookSaveDto bookSaveDto) {
        if (Validations.isNullOrEmpty(bookSaveDto.getName()))
            throw new NullOrEmptyException(bookSaveDto.getName());
        if (Validations.isNullOrEmpty(bookSaveDto.getDescription()))
            throw new NullOrEmptyException(bookSaveDto.getDescription());
        if (bookSaveDto.getPrice() == null)
            throw new NullOrEmptyException("Price");
        if (bookSaveDto.getPhoto() == null)
            throw new NullOrEmptyException("Photo");
        if (bookSaveDto.getReleaseDate() == null)
            throw new NullOrEmptyException("Released date");
        if (bookSaveDto.getGenres() == null) {
            throw new NullOrEmptyException("Genres");
        }
        List<Genre> genreList = new ArrayList<>();
        Files.write(UPLOAD_DIR, bookSaveDto.getPhoto().getInputStream().readAllBytes());
        for (String genre : bookSaveDto.getGenres()) {
            genreList.add(Genre.builder()
                    .value(genre)
                    .build());
        }
        Book book = Book.builder()
                .name(bookSaveDto.getName())
                .description(bookSaveDto.getDescription())
                .price(bookSaveDto.getPrice())
                .photoUrl(BASE_URL + bookSaveDto.getPhoto().getOriginalFilename())
                .releaseDate(bookSaveDto.getReleaseDate())
                .genre(genreList)
                .build();

        return new BookDto(bookRepository.save(book));
    }

    @SneakyThrows
    @Override
    public BookDto update(BookUpdateDto bookUpdateDto) {
        Book book = bookRepository.findById(bookUpdateDto.getId()).orElseThrow(
                () -> new NotFoundException("Book")
        );
        Files.write(UPLOAD_DIR,bookUpdateDto.getPhoto().getInputStream().readAllBytes());
        return new BookDto(
                bookRepository.save(Book.builder()
                        .id(book.getId())
                        .name(Validations.requireNonNullElse(bookUpdateDto.getName(), book.getName()))
                        .description(Validations.requireNonNullElse(bookUpdateDto.getDescription(), book.getDescription()))
                        .price(bookUpdateDto.getPrice())
                        .photoUrl(BASE_URL + bookUpdateDto.getPhoto().getOriginalFilename())
                        .genre(bookUpdateDto.getGenres().stream().map(v -> Genre.builder().value(v).build()).toList()).build())
        );
    }


    @Override
    public List<BookDto> getAll() {
        return bookRepository.findAll().stream().map(BookDto::new).toList();
    }

    @Override
    public BookDto getBookByAuthor(String name) {
        if (Validations.isNullOrEmpty(name)) {
            throw new NullOrEmptyException(name);
        }
        return new BookDto(bookRepository.findByAuthorName(name).orElseThrow(
                () -> new NotFoundException(name)
        ));
    }

    @Override
    public List<BookDto> getAllAvailableBooks() {
        return bookRepository.findAllAvailableBooks().stream().map(BookDto::new).toList();
    }

    @Override
    public BookDto getBookByName(String name) {
        if (Validations.isNullOrEmpty(name)) {
            throw new NullOrEmptyException(name);
        }
        return new BookDto(bookRepository.findByName(name).orElseThrow(
                () -> new NotFoundException(name)
        ));
    }
}
