package mk.ukim.finki.wp.magacin.models;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Item {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Long id;

  String name;

  String description;

  String imageUrl;

  BigDecimal price;

  @OneToMany(mappedBy = "item")
  List<ItemInWarehouse> itemInWarehouseList;

  @ManyToOne
  Category category;

  @ManyToOne
  Manufacturer manufacturer;

  @Builder
  private Item(
    String name,
    String description,
    String imageUrl,
    BigDecimal price,
    Category category,
    Manufacturer manufacturer) {
    this.name = name;
    this.description = description;
    this.imageUrl = imageUrl;
    this.price = price;
    this.itemInWarehouseList = new ArrayList<>();
    this.category = category;
    this.manufacturer = manufacturer;
  }

  public int getTotalQuantity() {
    int sum = 0;
    for (ItemInWarehouse item : itemInWarehouseList) {
      sum += item.getQuantity();
    }
    return sum;
  }

  public boolean isAvailable() {
    return getTotalQuantity() > 0;
  }

}
