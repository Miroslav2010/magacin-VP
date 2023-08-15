package mk.ukim.finki.wp.magacin.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.util.Date;

@Data
@Entity
@NoArgsConstructor
public class Report {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String itemName;

  private Date date;

  public Report(String itemName) {
    this.itemName = itemName;
    this.date = new Date(System.currentTimeMillis());
  }
}
