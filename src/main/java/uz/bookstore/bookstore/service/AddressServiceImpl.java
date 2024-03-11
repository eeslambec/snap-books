package uz.bookstore.bookstore.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.bookstore.bookstore.dto.AddressDTO;
import uz.bookstore.bookstore.dto.ResultMessage;
import uz.bookstore.bookstore.entity.Address;
import uz.bookstore.bookstore.exception.BadRequestException;
import uz.bookstore.bookstore.exception.ConflictException;
import uz.bookstore.bookstore.exception.NotFoundException;
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
        return new ResultMessage(true, address);
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
    public ResultMessage fullUpdateAddress(Long id, AddressDTO addressDTO) {
        Address address = address(id);
        checkAndMapping(addressDTO, address);
        return new ResultMessage(true, address);
    }

    @Override
    public ResultMessage deleteAddress(Long id) {
        Address address = address(id);
        addressRepository.delete(address);
        return new ResultMessage(true, address);
    }

    @Override
    public ResultMessage updateAddress(Long id, AddressDTO addressDTO) {
        Address address = address(id);
        Double latitude = addressDTO.getLatitude();
        Double longitude = addressDTO.getLongitude();
        if (latitude == null && longitude == null) {
            return new ResultMessage(false, "Address has not been changed");
        }
        if (latitude != null) address.setLatitude(latitude);
        if (longitude != null) address.setLongitude(longitude);
        addressRepository.save(address);
        return new ResultMessage(true, "Address changed");
    }


    private void checkAndMapping(AddressDTO addressDTO, Address address) {
        Double longitude = addressDTO.getLongitude();
        Double latitude = addressDTO.getLatitude();
        if (latitude == null || longitude == null)
            throw new BadRequestException("address fields cannot be null");
        if (addressRepository.existsByLongitudeAndLatitude(longitude, latitude)) {
            throw new ConflictException("This address already exist");
        }
        address.setLongitude(longitude);
        address.setLatitude(latitude);
        addressRepository.save(address);
    }

    private Address address(Long id) {
        return addressRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("this address not found"));
    }


}
