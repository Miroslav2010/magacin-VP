package mk.ukim.finki.wp.magacin.service.impl;

import mk.ukim.finki.wp.magacin.models.Item;
import mk.ukim.finki.wp.magacin.repository.ItemRepository;
import mk.ukim.finki.wp.magacin.service.ItemService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {
    private final ItemRepository itemRepository;

    public ItemServiceImpl(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    public List<Item> listAll() {
        return this.itemRepository.findAll();
    }

}
