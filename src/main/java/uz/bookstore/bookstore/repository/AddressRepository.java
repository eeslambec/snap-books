package uz.bookstore.bookstore.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.bookstore.bookstore.entity.Address;

import java.util.Optional;

@Repository
public interface AddressRepository extends JpaRepository<Address,Long> {
    boolean existsByLongitudeAndLatitude(Double longitude, Double latitude);
    Optional<Address> findByLongitudeAndLatitude(Double longitude, Double latitude);
}
