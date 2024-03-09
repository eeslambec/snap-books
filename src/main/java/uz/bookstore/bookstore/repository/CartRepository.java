package uz.bookstore.bookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.bookstore.bookstore.entity.Cart;
@Repository
public interface CartRepository extends JpaRepository<Cart,Long> {
}
