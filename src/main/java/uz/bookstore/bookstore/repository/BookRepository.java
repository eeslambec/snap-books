package uz.bookstore.bookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uz.bookstore.bookstore.entity.Book;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    @Query("SELECT b FROM Book b WHERE b.isAvailable = true")
    List<Book> findAllAvailableBooks();

    Optional<Book> findByName(String name);

    Optional<Book> findByAuthorName(String name);
}