package samwells.io.netflix_api.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import samwells.io.netflix_api.entity.UserWatchHistory;
import samwells.io.netflix_api.model.history.WatchHistory;

import java.time.Instant;

public interface UserWatchHistoryRepository extends JpaRepository<UserWatchHistory, Long> {
    @Query("""
            SELECT DISTINCT new samwells.io.netflix_api.model.history.WatchHistory(
                wh.id,
                m.name,
                m.mediaType.name,
                wh.createdAt
            )
            FROM UserWatchHistory wh
            JOIN wh.user u
            JOIN wh.media m
            WHERE
                u.id = :userId AND
                (m.mediaType.name = 'MOVIE' OR m.mediaType.name = 'TV_SHOW_EPISODE') AND
                (:afterTimestamp IS NULL OR :afterId IS NULL) OR
                (:afterTimestamp, :afterId) > (wh.createdAt, wh.id)
            ORDER BY
                wh.createdAt DESC
            """)
    Page<WatchHistory> getWatchHistory(
            @Param("userId") Long userId,
            @Param("afterTimestamp") Instant afterTimestamp,
            @Param("afterId") Long afterId,
            Pageable pageable
    );
}
