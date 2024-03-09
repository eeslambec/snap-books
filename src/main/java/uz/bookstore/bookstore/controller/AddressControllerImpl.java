package uz.bookstore.bookstore.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import uz.bookstore.bookstore.dto.AddressDTO;
import uz.bookstore.bookstore.dto.ResultMessage;
import uz.bookstore.bookstore.service.AddressService;

@RestController
@RequiredArgsConstructor
public class AddressControllerImpl implements AddressController {
    private final AddressService addressService;

    @Override
    public ResponseEntity<?> addAddress(AddressDTO addressDTO) {
        ResultMessage result = addressService.addAddress(addressDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @Override
    public ResponseEntity<?> getAddressList() {
        ResultMessage result = addressService.getAddressList();
        return ResponseEntity
                .status(result.isSuccess()
                        ? HttpStatus.OK
                        : HttpStatus.NOT_FOUND)
                .body(result.getObject());
    }

    @Override
    public ResponseEntity<?> getAddress(Long id) {
        ResultMessage result = addressService.getAddress(id);
        return ResponseEntity.ok(result.getObject());
    }

    @Override
    public ResponseEntity<?> fullUpdateAddress(Long id, AddressDTO addressDTO) {
        ResultMessage result = addressService.fullUpdateAddress(id, addressDTO);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(result);
    }

    @Override
    public ResponseEntity<?> updateAddress(Long id, AddressDTO addressDTO) {
        ResultMessage result = addressService.updateAddress(id, addressDTO);
        return ResponseEntity
                .status(result.isSuccess()
                        ? HttpStatus.ACCEPTED
                        : HttpStatus.NOT_ACCEPTABLE)
                .body(result.getObject());
    }

    @Override
    public ResponseEntity<?> deleteAddress(Long id) {
        ResultMessage result = addressService.deleteAddress(id);
        return ResponseEntity.ok(result);
    }

}
