package mk.ukim.finki.wp.magacin.models;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Item {
    @Id
    String name;

    String description;

    @OneToMany(mappedBy = "item")
    List<EachItem> eachItemList;

    @ManyToMany
    List<Warehouse> warehouses;

    @ManyToOne
    Category category;

    @ManyToOne
    Manufacturer manufacturer;

    String imageUrl;

    Boolean availability;

    Double price;

}
