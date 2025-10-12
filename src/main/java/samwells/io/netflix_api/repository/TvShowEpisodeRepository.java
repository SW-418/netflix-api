package samwells.io.netflix_api.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import samwells.io.netflix_api.entity.TvShowEpisode;

public interface TvShowEpisodeRepository extends JpaRepository<TvShowEpisode, Long> {
    @Query("""
            SELECT new samwells.io.netflix_api.model.tvshow.TvShowEpisode(
                e.id,
                m.name,
                m.description,
                e.episodeNumber,
                m.releaseDate
            )
            FROM TvShowEpisode e
            JOIN e.media m
            JOIN e.season s
            JOIN s.tvShow tv
            WHERE
                tv.id = :showId AND
                s.id = :seasonId
            """)
    Page<samwells.io.netflix_api.model.tvshow.TvShowEpisode> getTvShowEpisodes(
            @Param("showId") Long showId,
            @Param("seasonId") Long seasonId,
            Pageable pageable
    );
}
