package samwells.io.netflix_api.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import samwells.io.netflix_api.entity.Media;
import samwells.io.netflix_api.model.tvshow.TvShowSeasonWithMetadata;

public interface TvShowSeasonRepository extends JpaRepository<Media, Long> {
    @Query("""
            SELECT new samwells.io.netflix_api.model.tvshow.TvShowSeasonWithMetadata(
                seasons.id,
                seasons.name,
                seasons.description,
                seasons.releaseDate,
                COUNT(episodes)
            )
            FROM
                Media tv
            JOIN
                tv.children seasons
            LEFT JOIN
                seasons.children episodes
            WHERE
                tv.id = :id AND
                tv.mediaType.name = 'TV_SHOW'
            GROUP BY
                seasons.id,
                seasons.name,
                seasons.description,
                seasons.releaseDate
            """)
    Page<TvShowSeasonWithMetadata> getTvShowSeasons(
            @Param("id") Long id,
            Pageable pageable
    );

    @Query("""
            SELECT EXISTS (
                SELECT 1
                FROM Media tv
                JOIN tv.children seasons
                WHERE
                    tv.id = :showId AND
                    seasons.id = :seasonId
            )
            """)
    boolean existsByShowIdAndSeasonId(@Param("showId") Long showId, @Param("seasonId") Long seasonId);
}
