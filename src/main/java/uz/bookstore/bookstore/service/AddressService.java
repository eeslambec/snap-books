package uz.bookstore.bookstore.service;

import uz.bookstore.bookstore.dto.AddressInDTO;
import uz.bookstore.bookstore.dto.AddressOutDTO;

import java.util.List;

public interface AddressService {
    AddressOutDTO addAddress(AddressInDTO addressDTO);

    List<AddressOutDTO> getAddressList();

    AddressOutDTO getAddress(Long id);

    AddressOutDTO fullUpdateAddress(Long id, AddressInDTO addressDTO);

    void deleteAddress(Long id);

    AddressOutDTO updateAddress(Long id, AddressInDTO addressDTO);
}
