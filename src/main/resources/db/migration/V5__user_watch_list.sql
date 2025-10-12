CREATE TABLE IF NOT EXISTS user_watch_history (
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT NOT NULL,
    media_id BIGINT NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL,
    version BIGINT NOT NULL,
    CONSTRAINT fk_user_watch_history_user_id FOREIGN KEY (user_id) REFERENCES app_user(id),
    CONSTRAINT fk_user_watch_history_media_id FOREIGN KEY (media_id) REFERENCES media(id),
    CONSTRAINT uq_user_watch_history_user_media UNIQUE (user_id, media_id)
);
