package mk.ukim.finki.wp.magacin.models;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class EachItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    Item item;

    @ManyToOne
    Warehouse warehouse;

    public EachItem() {
    }

    public EachItem(Item item, Warehouse warehouse) {
        this.item = item;
        this.warehouse = warehouse;
    }
}
