package mk.ukim.finki.wp.magacin.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class ShoppingCart {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @OneToOne
  private User user;

  @ManyToMany
  private List<Item> items;

  @OneToMany(mappedBy = "shoppingCart")
  private List<ShoppingCartItem> shoppingCartItems;

  public ShoppingCart(User user) {
    this.user = user;
    this.shoppingCartItems = new ArrayList<>();
  }
}
