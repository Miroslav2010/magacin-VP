package mk.ukim.finki.wp.magacin.repository;

import mk.ukim.finki.wp.magacin.models.ItemInWarehouse;
import mk.ukim.finki.wp.magacin.models.Item;
import mk.ukim.finki.wp.magacin.models.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EachItemRepository extends JpaRepository<ItemInWarehouse, Long> {
    Optional<ItemInWarehouse> findByItemAndWarehouse(Item item, Warehouse warehouse);
}
