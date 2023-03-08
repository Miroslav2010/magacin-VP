package mk.ukim.finki.wp.magacin.models;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import mk.ukim.finki.wp.magacin.models.enumerations.OrderStatus;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Table(name = "MagacinOrder")
@NoArgsConstructor
public class Order {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  private User user;

  @ManyToMany
  private List<ShoppingCartItem> items;

  private String firstName;
  private String lastName;
  private String email;
  private String address;
  private String country;
  private String city;
  private String zipCode;
  private OrderStatus status;
  private Date date;
  private Double totalPrice;

  @Builder
  public Order(
    User user,
    List<ShoppingCartItem> items,
    String firstName,
    String lastName,
    String email,
    String address,
    String country,
    String city,
    String zipCode,
    Double totalPrice) {
    this.user = user;
    this.items = items;
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.address = address;
    this.country = country;
    this.city = city;
    this.zipCode = zipCode;
    this.status = OrderStatus.PENDING;
    this.totalPrice = totalPrice;
    this.date = new Date(System.currentTimeMillis());
  }

  public String exactDate() {
    SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
    return formatter.format(this.date);
  }
}
