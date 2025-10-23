package samwells.io.netflix_api.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import samwells.io.netflix_api.entity.Media;
import samwells.io.netflix_api.model.Genre;
import samwells.io.netflix_api.model.tvshow.TvShowWithMetadata;

import java.util.Optional;

public interface TvShowRepository extends JpaRepository<Media, Long> {
    @Query("""
            Select new samwells.io.netflix_api.model.tvshow.TvShowWithMetadata(
                tv.id,
                tv.name,
                tv.description,
                tv.genre.name,
                COUNT(DISTINCT(tvs.id)),
                COUNT(DISTINCT(tve.id))
            )
            FROM Media tv
            JOIN tv.children tvs
            LEFT JOIN tvs.children tve
            WHERE
                (:genre IS NULL OR tv.genre.name = :genre) AND
                tv.mediaType.name = 'TV_SHOW'
            GROUP BY
                tv.id, tv.name, tv.description, tv.genre.name
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
                COUNT(DISTINCT(tvs.id)),
                COUNT(DISTINCT(tve.id))
            )
            FROM Media tv
            JOIN tv.children tvs
            LEFT JOIN tvs.children tve
            WHERE
                tv.id = :id AND
                tv.mediaType.name = 'TV_SHOW'
            GROUP BY
                tv.id, tv.name, tv.description, tv.genre.name
            """)
    Optional<TvShowWithMetadata> getTvShow(@Param("id") Long id);
}
