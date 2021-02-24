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

    Integer quantity;

    public EachItem() {
    }

    public EachItem(Item item, Warehouse warehouse, Integer quantity) {
        this.item = item;
        this.warehouse = warehouse;
        this.quantity = quantity;
    }


}
