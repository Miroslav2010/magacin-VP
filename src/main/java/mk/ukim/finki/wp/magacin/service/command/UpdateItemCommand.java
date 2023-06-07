package mk.ukim.finki.wp.magacin.service.command;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

/**
 * Update item command.
 */
@Getter
@Builder
public class UpdateItemCommand {

  private final Long id;
  private final String name;
  private final String description;
  private final BigDecimal price;
  private final Long manufacturerId;
  private final Long categoryId;
}
