package mk.ukim.finki.wp.magacin.repository;

import mk.ukim.finki.wp.magacin.models.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    @Query("select distinct name from Item")
    List<String> getItemNames();
    List<Item> findAllByNameContains(String name);
    List<Item> findAllByCategoryId(long categoryId);
    List<Item> findAllByManufacturerId(long manufacturerId);
    List<Item> findAllByCategoryIdAndManufacturerId(long categoryId, long manufacturerId);
}
