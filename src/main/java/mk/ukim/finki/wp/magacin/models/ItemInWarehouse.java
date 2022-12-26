package mk.ukim.finki.wp.magacin.models;


import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@Getter
@NoArgsConstructor
public class ItemInWarehouse {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Long id;
  @ManyToOne
  Item item;
  @ManyToOne
  Warehouse warehouse;
  int quantity;

  public ItemInWarehouse(Item item, Warehouse warehouse, int quantity) {
    this.item = item;
    this.warehouse = warehouse;
    this.quantity = quantity;
  }

  public void addQuantity(int quantityToAdd) {
    quantity += quantityToAdd;
  }
}
