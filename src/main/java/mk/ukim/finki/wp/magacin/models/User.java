package mk.ukim.finki.wp.magacin.models;

import lombok.Data;
import org.aspectj.weaver.ast.Or;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "MagacinUser")
public class User {
    @Id
    private String username;
    private String password;
    private String role;
    private String firstName;
    private String lastName;
    private String address;
    private String email;
    private String city;
    private String country;
    private String zipcode;
    private String imageUrl;

    @OneToMany(mappedBy = "user")
    List<Order> orders;

    @OneToOne
    ShoppingCart shoppingCart;

    public User(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
        orders = new ArrayList<Order>();
        shoppingCart = null;
    }

    public User() {
    }
}
