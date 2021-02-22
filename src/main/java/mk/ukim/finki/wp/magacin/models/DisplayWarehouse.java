package mk.ukim.finki.wp.magacin.models;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class DisplayWarehouse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;
    String location;
    Double latitude;
    Double longitude;

    public DisplayWarehouse() {
    }

    public DisplayWarehouse(String name, String location, Double latitude, Double longitude) {
        this.name = name;
        this.location = location;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
