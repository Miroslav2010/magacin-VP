package mk.ukim.finki.wp.magacin.models;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Data
@Entity
public class Manufacturer {
    @Id
    @GeneratedValue
    Long id;

    String name;

    @OneToMany(mappedBy = "manufacturer")
    List<Item> items;

    public Manufacturer(Long id, String name, List<Item> items) {
        this.id = id;
        this.name = name;
        this.items = items;
    }

    public Manufacturer() {
    }
}
