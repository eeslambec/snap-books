package uz.bookstore.bookstore.service;

import uz.bookstore.bookstore.dto.AddressDTO;
import uz.bookstore.bookstore.dto.ResultMessage;

public interface AddressService {
    ResultMessage addAddress(AddressDTO addressDTO);

    ResultMessage getAddressList();

    ResultMessage getAddress(Long id);

    ResultMessage updateAddress(Long id, AddressDTO addressDTO);

    ResultMessage getAddressByField(Double longitude, Double latitude);

    ResultMessage deleteAddress(Long id);
}
