package uz.bookstore.bookstore.dto;

import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResultMessage {
    boolean success;
    Object object;
}
