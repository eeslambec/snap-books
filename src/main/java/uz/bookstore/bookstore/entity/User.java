package uz.bookstore.bookstore.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Table(name = "users")
public class User extends Auditing{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstname;
    private String lastname;
    private String phoneNumber;
    private String email;
    private String password;
    @OneToOne(fetch = FetchType.EAGER)
    private Address address;
    @OneToOne(fetch = FetchType.EAGER)
    private Cart cart;
    private boolean isActive;
    @OneToMany(fetch = FetchType.EAGER)
    private List<Book> boughtBooks;
    @OneToMany(fetch = FetchType.EAGER)
    private List<Book> likedBooks;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Role> role;
}