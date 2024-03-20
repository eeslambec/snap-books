package uz.bookstore.bookstore.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class AddressOutDTO {
    private Long id;
    private Double longitude;
    private Double latitude;
}
