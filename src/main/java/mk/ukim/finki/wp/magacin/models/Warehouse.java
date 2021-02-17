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

    @ManyToMany(mappedBy = "warehouses")
    List<Item> items;
}
