package mk.ukim.finki.wp.magacin.models;

import mk.ukim.finki.wp.magacin.models.enumerations.OrderStatus;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "MagacinOrder")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    User user;

    @ManyToMany
    List<ShoppingCartItem> items;

    String firstName;

    String lastName;

    String email;

    String address;

    String country;

    String city;

    String zipCode;

    OrderStatus status;

    Date date;

    Double totalPrice;

    public Order(User user, List<ShoppingCartItem> items, String firstName, String lastName, String email, String address, String country, String city, String zipCode, Double totalPrice) {
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

    public Order() {
    }

    public String exactDate(){
        SimpleDateFormat formatter= new SimpleDateFormat("dd-MM-yyyy");
        return formatter.format(this.date);

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<ShoppingCartItem> getItems() {
        return items;
    }

    public void setItems(List<ShoppingCartItem> items) {
        this.items = items;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
