package page.pavel.wookieesbooks.repository;

import page.pavel.wookieesbooks.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Users repository
 * Repository can be used to delegate CRUD operations against the data source: http://goo.gl/P1J8QH
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findById(Long Id);

    User findByUsername(String username);

    List<User> findByUsernameContains(String username);
}
