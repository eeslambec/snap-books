package uz.bookstore.bookstore.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BookSaveDto {

    private String name;
    private String description;
    private Double price;
    private MultipartFile photo;
    private LocalDate releaseDate;
    private List<String> genres;
}
