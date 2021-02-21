package mk.ukim.finki.wp.magacin.repository;

import mk.ukim.finki.wp.magacin.models.Manufacturer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ManufacturerRepository extends JpaRepository<Manufacturer, Long> {
}
