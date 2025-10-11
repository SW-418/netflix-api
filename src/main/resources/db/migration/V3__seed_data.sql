INSERT INTO genre (name, created_at, updated_at, version)
VALUES
   ('HORROR', NOW(), NOW(), 0),
   ('ROMANCE', NOW(), NOW(), 0),
   ('ACTION', NOW(), NOW(), 0),
   ('SCI_FI', NOW(), NOW(), 0),
   ('COMEDY', NOW(), NOW(), 0);

INSERT INTO media_type (name, created_at, updated_at, version)
VALUES
   ('MOVIE', NOW(), NOW(), 0),
   ('TV_SHOW', NOW(), NOW(), 0);

INSERT INTO media(name, genre_id, media_type_id, created_at, updated_at, version)
VALUES
  -- Horror movies
  ('Sinister', 1, 1, NOW(), NOW(), 0),
  ('The Conjuring', 1, 1, NOW(), NOW(), 0),
  ('Hereditary', 1, 1, NOW(), NOW(), 0),

  -- Romance movies
  ('500 Days of Summer', 2, 1, NOW(), NOW(), 0),
  ('The Notebook', 2, 1, NOW(), NOW(), 0),
  ('La La Land', 2, 1, NOW(), NOW(), 0),

  -- Action movies
  ('Mission Impossible', 3, 1, NOW(), NOW(), 0),
  ('John Wick', 3, 1, NOW(), NOW(), 0),
  ('Mad Max: Fury Road', 3, 1, NOW(), NOW(), 0),

  -- Sci-fi movies
  ('Interstellar', 4, 1, NOW(), NOW(), 0),
  ('Inception', 4, 1, NOW(), NOW(), 0),
  ('The Matrix', 4, 1, NOW(), NOW(), 0),

  -- Comedy movies
  ('Superbad', 5, 1, NOW(), NOW(), 0),
  ('The Hangover', 5, 1, NOW(), NOW(), 0),
  ('Step Brothers', 5, 1, NOW(), NOW(), 0);
