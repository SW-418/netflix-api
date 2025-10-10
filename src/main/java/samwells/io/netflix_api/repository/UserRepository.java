package samwells.io.netflix_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import samwells.io.netflix_api.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
