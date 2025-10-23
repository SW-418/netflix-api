package samwells.io.netflix_api.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import samwells.io.netflix_api.entity.Media;

import java.util.Optional;

public interface TvShowEpisodeRepository extends JpaRepository<Media, Long> {
    @Query("""
            SELECT
                episode
            FROM
                Media episode
            JOIN
                episode.parent season
            WHERE
                season.id = :seasonId AND
                season.mediaType.name = 'TV_SHOW_SEASON' AND
                episode.mediaType.name = 'TV_SHOW_EPISODE'
            """)
    Page<Media> getTvShowEpisodes(
            @Param("seasonId") Long seasonId,
            Pageable pageable
    );

    @Query("""
            SELECT
                episode
            FROM
                Media episode
            WHERE
                episode.id = :id AND
                episode.mediaType.name = 'TV_SHOW_EPISODE'
            """)
    Optional<Media> getTvShowEpisode(@Param("id") Long id);
}
