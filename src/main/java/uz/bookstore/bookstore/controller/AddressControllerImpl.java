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
        ResultMessage resultMessage = addressService.addAddress(addressDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(resultMessage);
    }

    @Override
    public ResponseEntity<?> getAddressList() {
        ResultMessage resultMessage = addressService.getAddressList();
        return ResponseEntity
                .status(resultMessage.isSuccess()
                        ? HttpStatus.OK
                        : HttpStatus.NOT_FOUND)
                .body(resultMessage);
    }

    @Override
    public ResponseEntity<?> getAddress(Long id) {
        ResultMessage result = addressService.getAddress(id);
        return ResponseEntity.ok(result);
    }

    @Override
    public ResponseEntity<?> updateAddress(Long id, AddressDTO addressDTO) {
        ResultMessage resultMessage = addressService.updateAddress(id, addressDTO);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(resultMessage);
    }

    @Override
    public ResponseEntity<?> deleteAddress(Long id) {
        ResultMessage resultMessage = addressService.deleteAddress(id);
        return ResponseEntity.ok(resultMessage);
    }

    @Override
    public ResponseEntity<?> getAddressByField(Double longitude, Double latitude) {
        ResultMessage resultMessage = addressService.getAddressByField(longitude, latitude);
        return ResponseEntity
                .status(resultMessage.isSuccess()
                        ? HttpStatus.OK
                        : HttpStatus.NOT_FOUND)
                .body(resultMessage);
    }
}
