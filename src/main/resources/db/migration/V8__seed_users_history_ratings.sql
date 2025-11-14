SELECT H2VERSION();
-- =============================
-- Users
-- =============================
INSERT INTO app_user (username, password, created_at, updated_at, version)
VALUES
  ('alice@test.com', '{noop}password', NOW(), NOW(), 0),
  ('bob@test.com', '{noop}password', NOW(), NOW(), 0),
  ('charlie@test.com', '{noop}password', NOW(), NOW(), 0);

-- =============================
-- Watch history (movies and TV episodes)
-- =============================
-- Alice watched a mix of movies and episodes
INSERT INTO user_watch_history (user_id, media_id, created_at, updated_at, version)
SELECT u.id, m.id, NOW(), NOW(), 0
FROM app_user u, media m
WHERE u.username='alice@test.com' AND m.name='Inception';

INSERT INTO user_watch_history (user_id, media_id, created_at, updated_at, version)
SELECT u.id, m.id, NOW(), NOW(), 0
FROM app_user u, media m
WHERE u.username='alice@test.com' AND m.name='Chapter One: The Vanishing of Will Byers';

INSERT INTO user_watch_history (user_id, media_id, created_at, updated_at, version)
SELECT u.id, m.id, NOW(), NOW(), 0
FROM app_user u, media m
WHERE u.username='alice@test.com' AND m.name='Pilot' AND m.parent_id IS NOT NULL;

-- Bob watched movies
INSERT INTO user_watch_history (user_id, media_id, created_at, updated_at, version)
SELECT u.id, m.id, NOW(), NOW(), 0
FROM app_user u, media m
WHERE u.username='bob@test.com' AND m.name='Interstellar';

INSERT INTO user_watch_history (user_id, media_id, created_at, updated_at, version)
SELECT u.id, m.id, NOW(), NOW(), 0
FROM app_user u, media m
WHERE u.username='bob@test.com' AND m.name='The Hangover';

-- Charlie watched TV episodes
INSERT INTO user_watch_history (user_id, media_id, created_at, updated_at, version)
SELECT u.id, m.id, NOW(), NOW(), 0
FROM app_user u, media m
WHERE u.username='charlie@test.com' AND m.name='Diversity Day';

INSERT INTO user_watch_history (user_id, media_id, created_at, updated_at, version)
SELECT u.id, m.id, NOW(), NOW(), 0
FROM app_user u, media m
WHERE u.username='charlie@test.com' AND m.name='Steven Sees a Ghost';

-- =============================
-- Ratings (1-5) for the content in watch history
-- =============================
INSERT INTO media_rating (user_id, media_id, score, created_at, updated_at, version)
SELECT u.id, m.id, 5, NOW(), NOW(), 0
FROM app_user u, media m
WHERE u.username='alice@test.com' AND m.name='Inception';

INSERT INTO media_rating (user_id, media_id, score, created_at, updated_at, version)
SELECT u.id, m.id, 4, NOW(), NOW(), 0
FROM app_user u, media m
WHERE u.username='alice@test.com' AND m.name='Interstellar';

INSERT INTO media_rating (user_id, media_id, score, created_at, updated_at, version)
SELECT u.id, m.id, 4, NOW(), NOW(), 0
FROM app_user u, media m
WHERE u.username='alice@test.com' AND m.name='Chapter One: The Vanishing of Will Byers';

INSERT INTO media_rating (user_id, media_id, score, created_at, updated_at, version)
SELECT u.id, m.id, 3, NOW(), NOW(), 0
FROM app_user u, media m
WHERE u.username='alice@test.com' AND m.name='Pilot' AND m.parent_id IS NOT NULL;

INSERT INTO media_rating (user_id, media_id, score, created_at, updated_at, version)
SELECT u.id, m.id, 5, NOW(), NOW(), 0
FROM app_user u, media m
WHERE u.username='bob@test.com' AND m.name='Interstellar';

INSERT INTO media_rating (user_id, media_id, score, created_at, updated_at, version)
SELECT u.id, m.id, 3, NOW(), NOW(), 0
FROM app_user u, media m
WHERE u.username='bob@test.com' AND m.name='Inception';

INSERT INTO media_rating (user_id, media_id, score, created_at, updated_at, version)
SELECT u.id, m.id, 2, NOW(), NOW(), 0
FROM app_user u, media m
WHERE u.username='bob@test.com' AND m.name='The Hangover';

INSERT INTO media_rating (user_id, media_id, score, created_at, updated_at, version)
SELECT u.id, m.id, 4, NOW(), NOW(), 0
FROM app_user u, media m
WHERE u.username='charlie@test.com' AND m.name='Diversity Day';

INSERT INTO media_rating (user_id, media_id, score, created_at, updated_at, version)
SELECT u.id, m.id, 5, NOW(), NOW(), 0
FROM app_user u, media m
WHERE u.username='charlie@test.com' AND m.name='Steven Sees a Ghost';

INSERT INTO media_rating (user_id, media_id, score, created_at, updated_at, version)
SELECT u.id, m.id, 2, NOW(), NOW(), 0
FROM app_user u, media m
WHERE u.username='charlie@test.com' AND m.name='Interstellar';
