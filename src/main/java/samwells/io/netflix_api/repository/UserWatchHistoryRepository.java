package samwells.io.netflix_api.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import samwells.io.netflix_api.entity.UserWatchHistory;
import samwells.io.netflix_api.model.history.WatchHistory;

public interface UserWatchHistoryRepository extends JpaRepository<UserWatchHistory, Long> {
    @Query("""
            SELECT DISTINCT new samwells.io.netflix_api.model.history.WatchHistory(
                wh.id,
                COALESCE(tv.name, m.name),
                m.mediaType.name,
                wh.createdAt
            )
            FROM UserWatchHistory wh
            JOIN wh.user u
            JOIN wh.media m
            LEFT JOIN m.tvShowEpisode tve
            LEFT JOIN tve.season tvs
            LEFT JOIN tvs.tvShow tv
            WHERE
                u.id = :userId
            ORDER BY
                wh.createdAt DESC
            """)
    Page<WatchHistory> getWatchHistory(
            @Param("userId") Long userId,
            Pageable pageable
    );
}
