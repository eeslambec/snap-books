package uz.bookstore.bookstore.service;

import uz.bookstore.bookstore.dto.AddressDTO;
import uz.bookstore.bookstore.dto.ResultMessage;

public interface AddressService {
    ResultMessage addAddress(AddressDTO addressDTO);

    ResultMessage getAddressList();

    ResultMessage getAddress(Long id);

    ResultMessage fullUpdateAddress(Long id, AddressDTO addressDTO);

    ResultMessage deleteAddress(Long id);

    ResultMessage updateAddress(Long id, AddressDTO addressDTO);
}
