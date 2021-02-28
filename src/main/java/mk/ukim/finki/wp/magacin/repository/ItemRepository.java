package mk.ukim.finki.wp.magacin.repository;

import mk.ukim.finki.wp.magacin.models.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    @Query("select distinct name from Item ")
    List<String> getItemNames();
    List<Item> findAllByNameContains(String name);
}
