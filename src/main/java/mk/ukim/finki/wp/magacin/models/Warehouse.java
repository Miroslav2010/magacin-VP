package mk.ukim.finki.wp.magacin.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Data
@NoArgsConstructor
public class Warehouse {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;

  private double lat;

  private double lon;

  @OneToMany(mappedBy = "warehouse")
  private List<WarehouseItem> warehouseItems;

  public Warehouse(String name, double lat, double lon) {
    this.name = name;
    this.lat = lat;
    this.lon = lon;
  }

  public int stockOfItem(Long itemId){
    int sum = 0;
    for (WarehouseItem warehouseItem: warehouseItems) {
      if (Objects.equals(warehouseItem.getItem().getId(), itemId))
        sum += warehouseItem.getQuantity();
    }
    return sum;
  }
}
