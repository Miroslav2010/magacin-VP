package mk.ukim.finki.wp.magacin.models;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Data
@Entity
public class EachItem {
    @Id
    @GeneratedValue
    Long id;

    @ManyToOne
    Item item;
}
