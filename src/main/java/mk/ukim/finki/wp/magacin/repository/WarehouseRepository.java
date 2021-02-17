package mk.ukim.finki.wp.magacin.repository;

import mk.ukim.finki.wp.magacin.models.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WarehouseRepository extends JpaRepository<Warehouse, Long> {
}
