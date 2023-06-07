package mk.ukim.finki.wp.magacin.service.command;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

/**
 * Create new item command.
 */
@Getter
@Builder
public class CreateItemCommand {

  private final String name;
  private final String description;
  private final BigDecimal price;
  private final Long manufacturerId;
  private final Long categoryId;
}
