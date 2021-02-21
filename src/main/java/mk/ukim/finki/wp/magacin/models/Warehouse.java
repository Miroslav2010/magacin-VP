package mk.ukim.finki.wp.magacin.models;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Warehouse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String name;

    String location;

    Double lat;

    Double lon;

    @ManyToMany(mappedBy = "warehouses")
    List<Item> items;

    @OneToMany(mappedBy = "warehouse")
    List<EachItem> eachItems;

    public Warehouse() {
    }

    public Warehouse(String name, String location, Double lon, Double lat) {
        this.name = name;
        this.location = location;
        this.lat = lat;
        this.lon = lon;
    }
}
