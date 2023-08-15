package mk.ukim.finki.wp.magacin.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class WarehouseItem {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  private Item item;

  @ManyToOne
  private Warehouse warehouse;

  private int quantity;

  public WarehouseItem(Item item, Warehouse warehouse, int quantity) {
    this.item = item;
    this.warehouse = warehouse;
    this.quantity = quantity;
  }
}
