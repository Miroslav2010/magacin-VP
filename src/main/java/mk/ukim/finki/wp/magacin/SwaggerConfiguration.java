package mk.ukim.finki.wp.magacin;

import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Swagger UI configuration.
 */
@Configuration
public class SwaggerConfiguration {

  /**
   * magacin-VP Open API group.
   * @param appControlApiCustomizer the open api customizer.
   * @return instance of {@link GroupedOpenApi}.
   */
  @Bean
  public GroupedOpenApi api(OpenApiCustomizer appControlApiCustomizer) {
    return GroupedOpenApi.builder()
      .group("magacin-VP")
      .addOpenApiCustomizer(appControlApiCustomizer)
      .build();
  }

  /**
   * @return open api customizer.
   */
  @Bean
  public OpenApiCustomizer appControlApiCustomizer() {
    return openApi -> {
      String description = openApi.getInfo().getDescription();
      openApi.getInfo().setDescription(description);
    };
  }
}
