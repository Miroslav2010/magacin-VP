package mk.ukim.finki.wp.magacin.models;


import javax.persistence.*;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Warehouse getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
