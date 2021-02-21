package mk.ukim.finki.wp.magacin.models;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String name;

    String description;

    String imageUrl;

    Boolean availability;

    Double price;

    @OneToMany(mappedBy = "item")
    List<EachItem> eachItemList;

    @ManyToMany
    List<Warehouse> warehouses;

    @ManyToOne
    Category category;

    @ManyToOne
    Manufacturer manufacturer;

    public Item() {

    }

    public Item(String name, String description, String imageUrl, Boolean availability, Double price, List<EachItem> eachItemList, List<Warehouse> warehouses, Category category, Manufacturer manufacturer) {
        this.name = name;
        this.description = description;
        this.imageUrl = imageUrl;
        this.availability = availability;
        this.price = price;
        this.eachItemList = eachItemList;
        this.warehouses = warehouses;
        this.category = category;
        this.manufacturer = manufacturer;
    }

}
