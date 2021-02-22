package mk.ukim.finki.wp.magacin.repository;

import mk.ukim.finki.wp.magacin.models.DisplayWarehouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DisplayWarehouseRepository extends JpaRepository<DisplayWarehouse,Long> {
}
