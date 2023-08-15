package mk.ukim.finki.wp.magacin.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Item {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;

  private String description;

  private BigDecimal price;

  @OneToMany(mappedBy = "item")
  private List<WarehouseItem> warehouseItemList;

  @ManyToOne
  private Category category;

  @ManyToOne
  private Manufacturer manufacturer;

  @Builder
  public Item(
    String name,
    String description,
    BigDecimal price,
    Category category,
    Manufacturer manufacturer) {
    this.name = name;
    this.description = description;
    this.price = price;
    this.warehouseItemList = new ArrayList<>();
    this.category = category;
    this.manufacturer = manufacturer;
  }

  public boolean isAvailable() {
    for (WarehouseItem item : warehouseItemList) {
      if (item.getQuantity() > 0)
        return true;
    }
    return false;
  }

  public int getTotalQuantity() {
    int sum = 0;
    for (WarehouseItem item : warehouseItemList) {
      sum += item.getQuantity();
    }
    return sum;
  }

}
