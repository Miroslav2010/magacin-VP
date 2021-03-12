package mk.ukim.finki.wp.magacin.service;

import mk.ukim.finki.wp.magacin.models.EachItem;
import mk.ukim.finki.wp.magacin.models.Item;
import mk.ukim.finki.wp.magacin.models.Warehouse;

import java.util.Optional;

public interface EachItemService {
    Optional<EachItem> findById(Long id);
    boolean lowerQuantity(Long id,Integer q);
    void addItems(Integer quantity, Warehouse warehouse, Item item);
}
