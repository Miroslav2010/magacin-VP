package mk.ukim.finki.wp.magacin.service.impl;

import mk.ukim.finki.wp.magacin.models.EachItem;
import mk.ukim.finki.wp.magacin.models.Item;
import mk.ukim.finki.wp.magacin.models.Warehouse;
import mk.ukim.finki.wp.magacin.repository.EachItemRepository;
import mk.ukim.finki.wp.magacin.service.EachItemService;
import org.springframework.stereotype.Service;

@Service
public class EachItemServiceImpl implements EachItemService {
    private final EachItemRepository eachItemRepository;

    public EachItemServiceImpl(EachItemRepository eachItemRepository) {
        this.eachItemRepository = eachItemRepository;
    }

    @Override
    public void addItems(Integer quantity, Warehouse warehouse, Item item) {
        for(int i=0;i<quantity;i++) {
            this.eachItemRepository.save(new EachItem(item,warehouse));
        }
    }
}