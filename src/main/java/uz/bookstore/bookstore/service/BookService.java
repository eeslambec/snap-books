package uz.bookstore.bookstore.service;

import org.springframework.stereotype.Service;
import uz.bookstore.bookstore.dto.request.BookSaveDto;
import uz.bookstore.bookstore.dto.request.BookUpdateDto;
import uz.bookstore.bookstore.dto.response.BookDto;

import java.util.List;

@Service
public interface BookService {

    BookDto save(BookSaveDto bookSaveDto);

    BookDto update(BookUpdateDto bookUpdateDto);
    List<BookDto> getAll();
    BookDto getBookByAuthor(String name);

    List<BookDto> getAllAvailableBooks();

    BookDto getBookByName(String name);
}
