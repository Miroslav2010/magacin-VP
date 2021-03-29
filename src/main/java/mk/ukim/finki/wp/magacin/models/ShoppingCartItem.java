package mk.ukim.finki.wp.magacin.models;

import javax.persistence.*;

@Entity
public class ShoppingCartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    Integer quantity;

    @ManyToOne
    Item item;

    @ManyToOne
    ShoppingCart shoppingCart;

    @ManyToOne
    Warehouse warehouse;

    public ShoppingCartItem() {
    }

    public ShoppingCartItem(Integer quantity, Item item, ShoppingCart shoppingCart,Warehouse warehouse) {
        this.quantity = quantity;
        this.item = item;
        this.shoppingCart = shoppingCart;
        this.warehouse = warehouse;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

    public void setShoppingCart(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    public Warehouse getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
    }
}
