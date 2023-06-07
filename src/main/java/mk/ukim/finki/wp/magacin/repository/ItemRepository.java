package mk.ukim.finki.wp.magacin.repository;

import mk.ukim.finki.wp.magacin.models.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    List<Item> findAllByNameContainsIgnoreCase(String name);
}
