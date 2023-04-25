package mk.ukim.finki.wp.magacin.service.command;

import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Builder
public class CreateWarehouseCommand {

  @NotBlank
  private final String name;

  @NotNull
  private final BigDecimal longitude;

  @NotNull
  private final BigDecimal latitude;
}
