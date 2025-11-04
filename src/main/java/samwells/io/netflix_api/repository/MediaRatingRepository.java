package samwells.io.netflix_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import samwells.io.netflix_api.entity.MediaRating;

import java.util.Optional;

@Repository
public interface MediaRatingRepository extends JpaRepository<MediaRating, Long> {
    @Query("""
            Select
                rating
            FROM
                MediaRating rating
            WHERE
                rating.media.id = :media_id AND
                rating.user.id = :user_id
            """)
    Optional<MediaRating> findByMediaIdAndUserId(
            @Param("media_id") Long mediaId,
            @Param("user_id") Long userId
    );
}
