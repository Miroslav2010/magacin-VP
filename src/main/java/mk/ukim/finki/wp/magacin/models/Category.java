package mk.ukim.finki.wp.magacin.models;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Data
@Entity
public class Category {
    @Id
    @GeneratedValue
    String name;

    @OneToMany(mappedBy = "category")
    List<Item> items;
}
