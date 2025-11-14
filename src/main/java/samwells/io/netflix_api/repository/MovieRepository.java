package samwells.io.netflix_api.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import samwells.io.netflix_api.entity.Media;
import samwells.io.netflix_api.model.Genre;

public interface MovieRepository extends JpaRepository<Media, Long> {
    @Query("""
            SELECT m
            FROM Media m
            WHERE m.mediaType.name = 'MOVIE'
            AND (:genre IS NULL OR m.genre.name = :genre)
            """)
    Page<Media> getMovies(
            @Param("genre") Genre genre,
            Pageable pageable
    );

    @Query("""
            SELECT
                media
            FROM
                MediaRating rating
            JOIN
                rating.media media
            WHERE
                media.mediaType.name = 'MOVIE' AND
                (:genre IS NULL OR media.genre.name = :genre)
            GROUP BY
                media
            ORDER BY
                AVG(rating.score) DESC
            """)
    Page<Media> getTopRatedMovies(
            @Param("genre") Genre genre,
            Pageable pageable
    );
}
