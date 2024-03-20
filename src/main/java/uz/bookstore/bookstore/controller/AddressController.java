package uz.bookstore.bookstore.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.bookstore.bookstore.dto.AddressInDTO;
import uz.bookstore.bookstore.dto.AddressOutDTO;
import uz.bookstore.bookstore.service.AddressService;

import java.util.List;

@RequestMapping("/api/v1/address")
@RestController
@RequiredArgsConstructor
public class AddressController {
    private final AddressService addressService;

    @PostMapping
    public ResponseEntity<?> addAddress(@RequestBody AddressInDTO addressDTO) {
        AddressInDTO result = addressService.addAddress(addressDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @GetMapping
    public ResponseEntity<?> getAddressList() {
        List<AddressOutDTO> result = addressService.getAddressList();
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAddress(@PathVariable Long id) {
        AddressOutDTO result = addressService.getAddress(id);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> fullUpdateAddress(@PathVariable Long id,
                                               @RequestBody AddressInDTO addressDTO) {
        AddressOutDTO result = addressService.fullUpdateAddress(id, addressDTO);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(result);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updateAddress(@PathVariable Long id,
                                           @RequestBody AddressInDTO addressDTO) {
        AddressOutDTO result = addressService.updateAddress(id, addressDTO);
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAddress(@PathVariable Long id) {
        addressService.deleteAddress(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                .body("Successfully deleted");
    }

}
