package mk.ukim.finki.wp.magacin.service.command;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class UpdateItemCommand extends CreateItemCommand {
  private final long id;

  @Builder(builderMethodName = "UpdateBuilder")
  public UpdateItemCommand(
    String name,
    String description,
    String imageUrl,
    BigDecimal price,
    long categoryId,
    long manufacturerId,
    long id) {
    super(name, description, imageUrl, price, categoryId, manufacturerId);
    this.id = id;
  }
}
