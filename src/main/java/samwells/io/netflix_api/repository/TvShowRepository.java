package samwells.io.netflix_api.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import samwells.io.netflix_api.entity.TvShow;
import samwells.io.netflix_api.model.Genre;
import samwells.io.netflix_api.model.tvshow.TvShowSeasonWithMetadata;
import samwells.io.netflix_api.model.tvshow.TvShowWithMetadata;

import java.util.Optional;

public interface TvShowRepository extends JpaRepository<TvShow, Long> {
    @Query("""
            SELECT new samwells.io.netflix_api.model.tvshow.TvShowWithMetadata(
                tv.id,
                tv.name,
                tv.description,
                tv.genre.name,
                COUNT(DISTINCT(s)),
                COUNT(DISTINCT(e))
            )
            FROM TvShow tv
            LEFT JOIN tv.seasons s
            LEFT JOIN s.episodes e
            WHERE (:genre IS NULL OR tv.genre.name = :genre)
            GROUP BY tv.id, tv.name, tv.description, tv.genre.name
            """)
    Page<TvShowWithMetadata> getTvShows(
            @Param("genre") Genre genre,
            Pageable pageable
    );

    @Query("""
            Select new samwells.io.netflix_api.model.tvshow.TvShowWithMetadata(
                tv.id,
                tv.name,
                tv.description,
                tv.genre.name,
                COUNT(DISTINCT(s)),
                COUNT(DISTINCT(e))
            )
            FROM TvShow tv
            LEFT JOIN tv.seasons s
            LEFT JOIN s.episodes e
            WHERE tv.id = :id
            GROUP BY tv.id, tv.name, tv.description, tv.genre.name
            """)
    Optional<TvShowWithMetadata> getTvShow(@Param("id") Long id);

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
}
