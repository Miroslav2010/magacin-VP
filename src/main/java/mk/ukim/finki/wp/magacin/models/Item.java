package mk.ukim.finki.wp.magacin.models;

import org.thymeleaf.standard.expression.Each;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String name;

    String description;

    String imageUrl;

    Boolean availability;

    Double price;

    @OneToMany(mappedBy = "item")
    List<EachItem> eachItemList;

    @ManyToOne
    Category category;

    @ManyToOne
    Manufacturer manufacturer;

    @ManyToMany(mappedBy = "items")
    List<ShoppingCart> shoppingCarts;

    @OneToMany(mappedBy = "item")
    List<ShoppingCartItem> shoppingCartItems;


    public Item() {

    }

    public Item(String name, String description, String imageUrl, Boolean availability, Double price,  Category category, Manufacturer manufacturer) {
        this.name = name;
        this.description = description;
        this.imageUrl = imageUrl;
        this.availability = availability;
        this.price = price;
        this.eachItemList = new ArrayList<>();
        this.category = category;
        this.manufacturer = manufacturer;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Boolean getAvailability() {
        return availability;
    }

    public void setAvailability(Boolean availability) {
        this.availability = availability;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public List<EachItem> getEachItemList() {
        return eachItemList;
    }

    public void setEachItemList(List<EachItem> eachItemList) {
        this.eachItemList = eachItemList;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    public List<Warehouse> getWarehouses(){
        List<Warehouse> warehouseList = new ArrayList<>();
        for (EachItem item:eachItemList
        ) {
            if(!warehouseList.contains(item.getWarehouse()))
            warehouseList.add(item.getWarehouse());
        }
        return warehouseList;
    }


    public boolean checkAvailability(){
        for (EachItem item: eachItemList){
            if(item.quantity > 0)
                return true;
        }
        return false;
    }

    public int getTotalQuantity(){
        int sum = 0;
        for (EachItem item: eachItemList){
            sum+= item.getQuantity();
        }
        return sum;
    }

}
