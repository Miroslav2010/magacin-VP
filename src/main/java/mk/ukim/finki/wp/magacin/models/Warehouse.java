package mk.ukim.finki.wp.magacin.models;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
public class Warehouse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String name;

    String location;

    Double lat;

    Double lon;

    @OneToMany(mappedBy = "warehouse")
    List<EachItem> eachItems;

    public Warehouse() {
    }

    public Warehouse(String name, String location, Double lon, Double lat) {
        this.name = name;
        this.location = location;
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
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
}
