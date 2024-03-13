package uz.bookstore.bookstore.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddressOutDTO {
    private Long id;
    private Double longitude;
    private Double latitude;
}
