package uz.bookstore.bookstore.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.bookstore.bookstore.dto.AddressInDTO;
import uz.bookstore.bookstore.dto.AddressOutDTO;
import uz.bookstore.bookstore.entity.Address;
import uz.bookstore.bookstore.exception.BadRequestException;
import uz.bookstore.bookstore.exception.ConflictException;
import uz.bookstore.bookstore.exception.NotFoundException;
import uz.bookstore.bookstore.repository.AddressRepository;
import uz.bookstore.bookstore.util.Validations;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {
    private final AddressRepository addressRepository;

    @Override
    public AddressOutDTO addAddress(AddressInDTO addressDTO) {
        Address address = new Address();
        checkAndMapping(addressDTO, address);
        return getOutDTO(address);
    }

    @Override
    public List<AddressOutDTO> getAddressList() {
        List<Address> all = addressRepository.findAll();
        List<AddressOutDTO> result = new ArrayList<>();
        for (Address address : all) {
            result.add(getOutDTO(address));
        }
        return result;
    }

    @Override
    public AddressOutDTO getAddress(Long id) {
        return getOutDTO(address(id));
    }

    @Override
    public AddressOutDTO fullUpdateAddress(Long id, AddressInDTO addressDTO) {
        Address address = address(id);
        checkAndMapping(addressDTO, address);
        return getOutDTO(address);
    }

    @Override
    public void deleteAddress(Long id) {
        Address address = address(id);
        addressRepository.delete(address);
    }

    @Override
    public AddressOutDTO updateAddress(Long id, AddressInDTO addressDTO) {
        Address address = address(id);
        Double latitude = addressDTO.getLatitude();
        Double longitude = addressDTO.getLongitude();
        if (Validations.requireNonNullElse(longitude, -1D) == -1D
                && Validations.requireNonNullElse(latitude, -1D) == -1D) {
            throw new BadRequestException("Address has not been changed");
        }
        if (Validations.requireNonNullElse(longitude, -1D) != -1D)
            address.setLatitude(latitude);
        if (Validations.requireNonNullElse(longitude, -1D) != -1D)
            address.setLongitude(longitude);
        addressRepository.save(address);
        return getOutDTO(address);
    }


    private void checkAndMapping(AddressInDTO addressDTO, Address address) {
        Double longitude = addressDTO.getLongitude();
        Double latitude = addressDTO.getLatitude();
        if (Validations.requireNonNullElse(longitude, -1D) == -1D
                || Validations.requireNonNullElse(latitude, -1D) == -1D) {
            throw new BadRequestException("Address fields cannot be null or less than 0");
        }
        if (addressRepository.existsByLongitudeAndLatitude(longitude, latitude)) {
            throw new ConflictException("This address already exist");
        }
        address.setLongitude(longitude);
        address.setLatitude(latitude);
        addressRepository.save(address);
    }

    private Address address(Long id) {
        return addressRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("There is no address with this id " + id));
    }

    private AddressOutDTO getOutDTO(Address address) {
        return AddressOutDTO.builder().
                id(address.getId()).
                latitude(address.getLatitude()).
                longitude(address.getLongitude()).
                build();
    }


}
