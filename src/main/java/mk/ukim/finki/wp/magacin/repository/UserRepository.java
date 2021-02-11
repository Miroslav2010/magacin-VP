package mk.ukim.finki.wp.magacin.repository;

import mk.ukim.finki.wp.magacin.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,String> {
    Optional<User> findByUsername(String username);
    Optional<User> findByUsernameAndPassword(String username, String password);
}