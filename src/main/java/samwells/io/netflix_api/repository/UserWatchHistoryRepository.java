package samwells.io.netflix_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import samwells.io.netflix_api.entity.UserWatchHistory;

public interface UserWatchHistoryRepository extends JpaRepository<UserWatchHistory, Long> { }
