package mk.ukim.finki.wp.magacin.web.rest;

import lombok.RequiredArgsConstructor;
import mk.ukim.finki.wp.magacin.service.ItemService;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ItemsRestController {

  private final ItemService itemService;
}
