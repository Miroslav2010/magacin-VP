package mk.ukim.finki.wp.magacin.repository;

import mk.ukim.finki.wp.magacin.models.EachItem;
import mk.ukim.finki.wp.magacin.models.Item;
import mk.ukim.finki.wp.magacin.models.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.Optional;

@Repository
public interface EachItemRepository extends JpaRepository<EachItem, Long> {
    Optional<EachItem> findByItemAndWarehouse(Item item, Warehouse warehouse);
}
