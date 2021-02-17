package mk.ukim.finki.wp.magacin.repository;

import mk.ukim.finki.wp.magacin.models.EachItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EachItemRepository extends JpaRepository<EachItem, Long> {
}
