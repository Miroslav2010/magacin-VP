package mk.ukim.finki.wp.magacin.models;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class ShoppingCart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @OneToOne
    User user;

    @ManyToMany
    List<Item> items;

    @OneToMany(mappedBy = "shoppingCart")
    List<ShoppingCartItem> shoppingCartItems;



    public ShoppingCart(User user) {
        this.user = user;
        items = new ArrayList<>();
    }

    public ShoppingCart() {
    }
}
