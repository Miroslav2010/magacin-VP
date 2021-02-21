package mk.ukim.finki.wp.magacin.models;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "MagacinOrder")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    User user;

    public Order(Long id, User user) {
        this.id = id;
        this.user = user;
    }

    public Order() {
    }
}
