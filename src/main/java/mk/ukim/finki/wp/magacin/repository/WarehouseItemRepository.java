package mk.ukim.finki.wp.magacin.repository;

import mk.ukim.finki.wp.magacin.models.WarehouseItem;
import mk.ukim.finki.wp.magacin.models.Item;
import mk.ukim.finki.wp.magacin.models.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WarehouseItemRepository extends JpaRepository<WarehouseItem, Long> {
    Optional<WarehouseItem> findByItemAndWarehouse(Item item, Warehouse warehouse);
}
