package mk.ukim.finki.wp.magacin.models;

import lombok.Data;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;

@Data
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
}
