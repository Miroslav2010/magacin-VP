package mk.ukim.finki.wp.magacin.models;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Data
@Entity
public class Manufacturer {
    @Id
    String name;

    @OneToMany(mappedBy = "manufacturer")
    List<Item> items;
}
