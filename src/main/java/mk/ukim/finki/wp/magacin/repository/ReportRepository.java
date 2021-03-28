package mk.ukim.finki.wp.magacin.repository;

import mk.ukim.finki.wp.magacin.models.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportRepository extends JpaRepository<Report,Long>{
}
