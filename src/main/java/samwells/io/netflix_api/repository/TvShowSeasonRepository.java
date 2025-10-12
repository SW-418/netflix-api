package samwells.io.netflix_api.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import samwells.io.netflix_api.entity.TvShowSeason;
import samwells.io.netflix_api.model.tvshow.TvShowSeasonWithMetadata;

public interface TvShowSeasonRepository extends JpaRepository<TvShowSeason, Long> {
    @Query("""
            SELECT new samwells.io.netflix_api.model.tvshow.TvShowSeasonWithMetadata(
                tvs.id,
                tvs.name,
                tvs.description,
                tvs.releaseDate,
                COUNT(e)
            )
            FROM TvShowSeason tvs
            JOIN tvs.tvShow tv
            LEFT JOIN tvs.episodes e
            WHERE tv.id = :id
            GROUP BY tvs.id, tvs.name, tvs.description, tvs.releaseDate
            """)
    Page<TvShowSeasonWithMetadata> getTvShowSeasons(@Param("id") Long id, Pageable pageable);

    @Query("""
            SELECT EXISTS (
                SELECT 1
                FROM TvShowSeason s
                JOIN s.tvShow tv
                WHERE
                    s.id = :seasonId AND
                    tv.id = :showId
            )
            """)
    boolean existsByShowIdAndSeasonId(@Param("showId") Long showId, @Param("seasonId") Long seasonId);
}
