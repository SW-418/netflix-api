package samwells.io.netflix_api.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import samwells.io.netflix_api.entity.TvShow;
import samwells.io.netflix_api.model.Genre;

public interface TvShowRepository extends JpaRepository<TvShow, Long> {
    @Query("""
            SELECT tv
            FROM TvShow tv
            WHERE (:genre IS NULL OR tv.genre.name = :genre)
            """)
    Page<TvShow> getTvShows(
            @Param("genre") Genre genre,
            Pageable pageable
    );
}
