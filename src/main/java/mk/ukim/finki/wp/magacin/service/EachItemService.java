package mk.ukim.finki.wp.magacin.service;

import mk.ukim.finki.wp.magacin.models.Item;
import mk.ukim.finki.wp.magacin.models.Warehouse;

public interface EachItemService {
    void addItems(Integer quantity, Warehouse warehouse, Item item);
}
