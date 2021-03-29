package mk.ukim.finki.wp.magacin.models;

import javax.persistence.*;
import java.util.List;

@Entity
public class Warehouse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String name;

    Double lat;

    Double lon;

    @OneToMany(mappedBy = "warehouse")
    List<EachItem> eachItems;

    public Warehouse() {
    }

    public Warehouse(String name, Double lat, Double lon) {
        this.name = name;
        this.lat = lat;
        this.lon = lon;
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

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }

    public List<EachItem> getEachItems() {
        return eachItems;
    }

    public void setEachItems(List<EachItem> eachItems) {
        this.eachItems = eachItems;
    }

    public Integer stockOfItem(Long itemId){
        Integer sum = 0;
        for (EachItem eachItem: eachItems) {
            if (eachItem.getItem().getId() == itemId)
                sum+= eachItem.getQuantity();
        }
        return sum;
    }
}
