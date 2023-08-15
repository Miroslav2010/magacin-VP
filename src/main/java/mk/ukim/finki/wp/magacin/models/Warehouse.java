package mk.ukim.finki.wp.magacin.models;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@Getter
@Entity
@NoArgsConstructor
public class Warehouse {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;

  private BigDecimal lat;

  private BigDecimal lon;

  @OneToMany(mappedBy = "warehouse")
  private List<WarehouseItem> warehouseItems;

  @Builder
  public Warehouse(String name, BigDecimal lat, BigDecimal lon) {
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
