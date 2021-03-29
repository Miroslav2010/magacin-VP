package mk.ukim.finki.wp.magacin.models;


import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;


@Entity
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    String itemName;

    Date date;

    public String exactDate(){
        SimpleDateFormat formatter= new SimpleDateFormat("dd-MM-yyyy");
        return formatter.format(this.date);

    }

    public Report(String itemName) {
        this.itemName = itemName;
        this.date = new Date(System.currentTimeMillis());
    }

    public Report() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
