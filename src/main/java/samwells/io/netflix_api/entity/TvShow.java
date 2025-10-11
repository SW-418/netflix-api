package samwells.io.netflix_api.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "tv_show")
public class TvShow {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "name", nullable = false, length = 100)
    String name;

    @Column(name = "description")
    String description;

    @JoinColumn(name = "genre_id")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    Genre genre;

    @OneToMany(fetch = FetchType.LAZY)
    List<TvShowSeason> seasons;

    @Transient
    Integer seasonCount;

    @Transient
    Integer episodeCount;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    Instant createdAt;

    @CreationTimestamp
    @Column(name = "updated_at", nullable = false)
    Instant updatedAt;

    @Version
    Long version;
}
