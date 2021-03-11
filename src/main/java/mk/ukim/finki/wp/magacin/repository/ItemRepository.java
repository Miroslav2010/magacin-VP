package mk.ukim.finki.wp.magacin.repository;

import mk.ukim.finki.wp.magacin.models.Category;
import mk.ukim.finki.wp.magacin.models.Item;
import mk.ukim.finki.wp.magacin.models.Manufacturer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    @Query("select distinct name from Item ")
    List<String> getItemNames();
    List<Item> findAllByNameContains(String name);
    List<Item> findAllByCategory(Category category);
    List<Item> findAllByManufacturer(Manufacturer manufacturer);
    List<Item> findAllByCategoryAndManufacturer(Category category,Manufacturer manufacturer);
    List<Item> findAllByCategoryAndManufacturerAndAvailability(Category category,Manufacturer manufacturer,Boolean availability);
    List<Item> findAllByAvailability(Boolean availability);
    List<Item> findAllByCategoryAndAvailability(Category category,Boolean availability);
    List<Item> findAllByManufacturerAndAvailability(Manufacturer manufacturer,Boolean availability);
    List<Item> findFirst3ByOrderById();
}
