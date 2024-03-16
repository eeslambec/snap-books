package uz.bookstore.bookstore.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@AllArgsConstructor
public class BookUpdateDto {
    private Long id;
    private String name;
    private String description;
    private Double price;
    private MultipartFile photo;
    private List<String> genres;
}
