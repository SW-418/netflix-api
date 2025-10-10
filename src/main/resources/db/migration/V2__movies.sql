CREATE TABLE IF NOT EXISTS genre (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(100) UNIQUE NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL,
    version BIGINT NOT NULL
);

CREATE TABLE IF NOT EXISTS media_type (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(100) UNIQUE NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL,
    version BIGINT NOT NULL
);

CREATE TABLE IF NOT EXISTS media (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    genre_id BIGINT NOT NULL,
    media_type_id BIGINT NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL,
    version BIGINT NOT NULL,
    CONSTRAINT fk_media_genre FOREIGN KEY (genre_id) REFERENCES genre(id),
    CONSTRAINT fk_media_media_type FOREIGN KEY (media_type_id) REFERENCES media_type(id)
);

-- Get media for a given genre
CREATE INDEX IF NOT EXISTS idx_media_genre_id ON media(genre_id);
-- Get media for a given media type, e.g. get all movies, get all tv shows
CREATE INDEX IF NOT EXISTS idx_media_media_type_id ON media(media_type_id);

CREATE TABLE IF NOT EXISTS tv_show (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(100) UNIQUE NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL,
    version BIGINT NOT NULL
);

CREATE TABLE IF NOT EXISTS tv_show_season (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    tv_show_id BIGINT NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL,
    version BIGINT NOT NULL,
    CONSTRAINT fk_tv_show_season_tv_show FOREIGN KEY (tv_show_id) REFERENCES tv_show(id),
    CONSTRAINT uq_tv_show_id_name UNIQUE (tv_show_id, name)
);

-- Get all seasons for a given TV show
CREATE INDEX IF NOT EXISTS idx_tv_show_id ON tv_show_season(tv_show_id);

CREATE TABLE IF NOT EXISTS tv_show_episode (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    episode_number INT NOT NULL,
    tv_show_season_id BIGINT NOT NULL,
    media_id BIGINT NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL,
    version BIGINT NOT NULL,
    CONSTRAINT fk_tv_show_episode_tv_show_season FOREIGN KEY (tv_show_season_id) REFERENCES tv_show_season(id),
    CONSTRAINT fk_tv_show_episode_media FOREIGN KEY (media_id) REFERENCES media(id),
    -- Media can only be in a season once
    CONSTRAINT uq_tv_show_season_media UNIQUE (tv_show_season_id, media_id),
    -- Episode numbers for a season need to be unique
    CONSTRAINT uq_tv_show_season_episode_number UNIQUE (tv_show_season_id, episode_number)
);

-- Get all episodes for a given TV show season
CREATE INDEX IF NOT EXISTS idx_tv_show_episode_season_id ON tv_show_episode(tv_show_season_id);

CREATE TABLE IF NOT EXISTS media_rating (
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT NOT NULL,
    media_id BIGINT NOT NULL,
    score SMALLINT NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL,
    version BIGINT NOT NULL,
    CONSTRAINT chk_score CHECK (score >=1 AND score <=5),
    CONSTRAINT fk_media_rating_user FOREIGN KEY (user_id) REFERENCES app_user(id),
    CONSTRAINT fk_media_rating_media FOREIGN KEY (media_id) REFERENCES media(id),
    CONSTRAINT uq_media_user UNIQUE (media_id, user_id)
);

CREATE INDEX IF NOT EXISTS idx_media_rating_user_id ON media_rating(user_id);
CREATE INDEX IF NOT EXISTS idx_media_rating_media_id ON media_rating(media_id);
