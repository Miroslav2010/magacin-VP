package mk.ukim.finki.wp.magacin.models;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Manufacturer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String name;

    @OneToMany(mappedBy = "manufacturer")
    List<Item> items;

    public Manufacturer(String name) {
        this.name = name;
    }

    public Manufacturer() {
    }
}
