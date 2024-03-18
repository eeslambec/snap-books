package uz.bookstore.bookstore.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.bookstore.bookstore.dto.request.AuthorCreateDto;
import uz.bookstore.bookstore.dto.request.AuthorUpdateDto;
import uz.bookstore.bookstore.dto.response.AuthorDto;
import uz.bookstore.bookstore.entity.Author;
import uz.bookstore.bookstore.exception.NotFoundException;
import uz.bookstore.bookstore.exception.NullOrEmptyException;
import uz.bookstore.bookstore.repository.AuthorRepository;
import uz.bookstore.bookstore.service.AuthorService;
import uz.bookstore.bookstore.util.Validations;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;
    @Override
    public AuthorDto create(AuthorCreateDto authorCreateDto) {
        if (Validations.isNullOrEmpty(authorCreateDto.getName())) {
            throw new NullOrEmptyException(authorCreateDto.getName());
        }
        return new AuthorDto(
                authorRepository.save(Author.builder()
                        .name(authorCreateDto.getName())
                        .build()
        ));
    }

    @Override
    public List<AuthorDto> getAll() {
        return authorRepository.findAll().stream().map(AuthorDto::new).toList();
    }

    @Override
    public AuthorDto getByName(String name) {
        if (Validations.isNullOrEmpty(name)) {
            throw new NullOrEmptyException(name);
        }
        return new AuthorDto(authorRepository.findByName(name).orElseThrow(
                () -> new NotFoundException(name)
        ));
    }

    @Override
    public AuthorDto update(AuthorUpdateDto authorUpdateDto) {
        Author author = authorRepository.findById(authorUpdateDto.getId()).orElseThrow(
                () -> new NotFoundException("Author")
        );
        if (Validations.isNullOrEmpty(authorUpdateDto.getName())) {
            throw new NullOrEmptyException("Name");
        }
        return new AuthorDto(
                authorRepository.save(
                        Author.builder()
                                .id(authorUpdateDto.getId())
                                .name(Validations.requireNonNullElse(authorUpdateDto.getName(), author.getName()))
                                .build()
                )
        );
    }
}
