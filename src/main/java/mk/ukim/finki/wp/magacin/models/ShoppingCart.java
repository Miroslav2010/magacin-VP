package mk.ukim.finki.wp.magacin.models;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class ShoppingCart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @OneToOne
    User user;
}
