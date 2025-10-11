package samwells.io.netflix_api.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "media")
public class Media {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "name", nullable = false)
    String name;

    @Column(name = "description", nullable = false)
    String description;

    @JoinColumn(name = "genre_id")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    Genre genre;

    @JoinColumn(name = "media_type_id")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    MediaType mediaType;

    @Column(name = "release_date", nullable = false, updatable = false)
    Instant createdAt;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    LocalDate releaseDate;

    @CreationTimestamp
    @Column(name = "updated_at", nullable = false)
    Instant updatedAt;

    @Version
    Long version;
}
