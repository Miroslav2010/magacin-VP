package mk.ukim.finki.wp.magacin.service.command;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

/**
 * Create new item command.
 */
@Getter
@Builder
@RequiredArgsConstructor
public class CreateItemCommand {
  private final String name;
  private final String description;
  private final String imageUrl;
  private final BigDecimal price;
  private final long categoryId;
  private final long manufacturerId;
}
