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

    Long lat;

    Long lon;

    @ManyToMany(mappedBy = "warehouses")
    List<Item> items;

    public Warehouse() {
    }

    public Warehouse(Long id, String name, String location, Long lat, Long lon, List<Item> items) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.lat = lat;
        this.lon = lon;
        this.items = items;
    }
}
