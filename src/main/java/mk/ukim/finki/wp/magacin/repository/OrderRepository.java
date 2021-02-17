package mk.ukim.finki.wp.magacin.repository;

import mk.ukim.finki.wp.magacin.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}
