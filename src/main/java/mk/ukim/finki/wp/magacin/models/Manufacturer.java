package mk.ukim.finki.wp.magacin.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Id;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Manufacturer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "manufacturer")
    private List<Item> items;

    public Manufacturer(String name) {
        this.name = name;
    }
}
