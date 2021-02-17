package mk.ukim.finki.wp.magacin.service;

import mk.ukim.finki.wp.magacin.models.Item;
import mk.ukim.finki.wp.magacin.repository.ItemRepository;

import java.util.List;

public interface ItemService {
    List<Item> listAll();
}
