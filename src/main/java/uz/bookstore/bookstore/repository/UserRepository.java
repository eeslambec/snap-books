package uz.bookstore.bookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.bookstore.bookstore.entity.Role;
import uz.bookstore.bookstore.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByPhoneNumberAndActive(String phoneNumber, boolean isActive);
    Optional<User> findByEmailAndActive(String email, boolean isActive);
    @Query("SELECT u FROM User u WHERE u.role = ?1 AND u.isActive = ?2")
    List<User> findAllByRoleAndActive(Role role, boolean  isActive);
    List<User> findAllByActive(boolean isActive);
    Optional<User> findByIdAndActive(Long id, boolean isActive);
}