package uz.bookstore.bookstore.service;

import org.springframework.stereotype.Service;
import uz.bookstore.bookstore.dto.request.AuthorCreateDto;
import uz.bookstore.bookstore.dto.request.AuthorUpdateDto;
import uz.bookstore.bookstore.dto.response.AuthorDto;

import java.util.List;


@Service
public interface AuthorService {

    AuthorDto create(AuthorCreateDto authorCreateDto);
    List<AuthorDto> getAll();
    AuthorDto getByName(String name);

    AuthorDto update(AuthorUpdateDto authorUpdateDto);

}
