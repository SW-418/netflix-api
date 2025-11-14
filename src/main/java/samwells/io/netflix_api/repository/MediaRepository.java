package samwells.io.netflix_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import samwells.io.netflix_api.entity.Media;
import samwells.io.netflix_api.model.MediaType;

@Repository
public interface MediaRepository extends JpaRepository<Media, Long> {
    @Query("""
            SELECT EXISTS (
                SELECT
                    1
                FROM
                    Media m
                WHERE
                    m.id = :id AND
                    m.mediaType.name = :media_type
            )
            """)
    boolean existsByIdAndMediaType(
            @Param("id") Long id,
            @Param("media_type") MediaType mediaType
    );
}
