package mk.ukim.finki.wp.magacin.models;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class ShoppingCartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    Integer quantity;

    @ManyToOne
    Item item;

    @ManyToOne
    ShoppingCart shoppingCart;

    public ShoppingCartItem() {
    }

    public ShoppingCartItem(Integer quantity, Item item, ShoppingCart shoppingCart) {
        this.quantity = quantity;
        this.item = item;
        this.shoppingCart = shoppingCart;
    }
}
