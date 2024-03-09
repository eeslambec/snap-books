package uz.bookstore.bookstore.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.bookstore.bookstore.dto.AddressDTO;


@RequestMapping("/api/v1/address")
public interface AddressController {
    @PostMapping
    ResponseEntity<?> addAddress(@RequestBody AddressDTO addressDTO);
    @GetMapping
    ResponseEntity<?> getAddressList();
    @GetMapping("/{id}")
    ResponseEntity<?> getAddress(@PathVariable Long id);
    @PutMapping("/{id}")
    ResponseEntity<?> updateAddress(@PathVariable Long id,
                                 @RequestBody AddressDTO addressDTO);
    @DeleteMapping("/{id}")
    ResponseEntity<?> deleteAddress(@PathVariable Long id);
    @GetMapping
    ResponseEntity<?> getAddressByField(@RequestParam("longitude")Double longitude,
                                        @RequestParam("latitude")Double latitude);
}
