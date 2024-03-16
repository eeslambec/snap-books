package uz.bookstore.bookstore.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;

@Getter
@AllArgsConstructor
@ToString
public class BookSaveDto {

    private String name;
    private String description;
    private Double price;
    private MultipartFile photo;
    private LocalDate releaseDate;
    private List<String> genres;
}
