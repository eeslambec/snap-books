package uz.bookstore.bookstore.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.bookstore.bookstore.dto.AddressDTO;
import uz.bookstore.bookstore.dto.ResultMessage;
import uz.bookstore.bookstore.entity.Address;
import uz.bookstore.bookstore.repository.AddressRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {
    private final AddressRepository addressRepository;

    @Override
    public ResultMessage addAddress(AddressDTO addressDTO) {
        Address address = new Address();
        checkAndMapping(addressDTO, address);
        return new ResultMessage(true, addressRepository.save(address));
    }

    @Override
    public ResultMessage getAddressList() {
        List<Address> all = addressRepository.findAll();
        if (all.isEmpty()) return new ResultMessage(false, "address not exist");
        return new ResultMessage(true, all);
    }

    @Override
    public ResultMessage getAddress(Long id) {
        return new ResultMessage(true, address(id));
    }

    @Override
    public ResultMessage updateAddress(Long id, AddressDTO addressDTO) {
        Address address = address(id);
        checkAndMapping(addressDTO, address);
        return new ResultMessage(true, address);
    }

    @Override
    public ResultMessage getAddressByField(Double longitude, Double latitude) {
        if (longitude == null || latitude == null) {
            return new ResultMessage(false, "the data is incorrect");
        }
        Address address = addressRepository.findByLongitudeAndLatitude(longitude, latitude)
                .orElseThrow(() -> new RuntimeException("This address not found"));
        return new ResultMessage(true, address);
    }

    @Override
    public ResultMessage deleteAddress(Long id) {
        Address address = address(id);
        addressRepository.delete(address);
        return new ResultMessage(true, address);
    }


    private void checkAndMapping(AddressDTO addressDTO, Address address) {
        Double longitude = addressDTO.getLongitude();
        Double latitude = addressDTO.getLatitude();
        if (latitude == null || longitude == null)
            throw new NullPointerException("address fields cannot be null");
        if (addressRepository.existsByLongitudeAndLatitude(longitude, latitude)) {
            throw new RuntimeException("This address already exist");
            //create new some exception
        }
        address.setLongitude(longitude);
        address.setLatitude(latitude);
    }

    private Address address(Long id) {
        return addressRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("this address not found"));
        //create notfoundException
    }


}
