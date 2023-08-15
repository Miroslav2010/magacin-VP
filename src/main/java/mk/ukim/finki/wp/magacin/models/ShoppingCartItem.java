package mk.ukim.finki.wp.magacin.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class ShoppingCartItem {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private int quantity;

  @ManyToOne
  private Item item;

  @ManyToOne
  private ShoppingCart shoppingCart;

  @ManyToOne
  private Warehouse warehouse;

  public ShoppingCartItem(int quantity, Item item, ShoppingCart shoppingCart, Warehouse warehouse) {
    this.quantity = quantity;
    this.item = item;
    this.shoppingCart = shoppingCart;
    this.warehouse = warehouse;
  }
}
