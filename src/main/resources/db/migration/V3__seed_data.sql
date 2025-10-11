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

INSERT INTO media (name, genre_id, media_type_id, description, release_date, created_at, updated_at, version)
VALUES
  -- Horror movies
  ('Sinister', 1, 1, 'A true-crime writer finds a box of home movies that suggest the murder he’s researching is the work of a serial killer whose legacy dates back decades.', '2012-03-11', NOW(), NOW(), 0),
  ('The Conjuring', 1, 1, 'Paranormal investigators Ed and Lorraine Warren work to help a family terrorized by a dark presence in their farmhouse.', '2013-07-19', NOW(), NOW(), 0),
  ('Hereditary', 1, 1, 'A grieving family is haunted by tragic and disturbing occurrences after the death of their secretive grandmother.', '2018-06-08', NOW(), NOW(), 0),

  -- Romance movies
  ('500 Days of Summer', 2, 1, 'An offbeat romantic comedy about a woman who doesn’t believe in love, and the young man who falls for her.', '2009-08-07', NOW(), NOW(), 0),
  ('The Notebook', 2, 1, 'A young couple falls in love in the 1940s but are soon separated by social differences and war.', '2004-06-25', NOW(), NOW(), 0),
  ('La La Land', 2, 1, 'While navigating their careers in Los Angeles, a pianist and an actress fall in love but struggle to maintain their relationship.', '2016-12-09', NOW(), NOW(), 0),

  -- Action movies
  ('Mission Impossible', 3, 1, 'An American agent, under false suspicion of disloyalty, must discover and expose the real spy without the help of his organization.', '1996-05-22', NOW(), NOW(), 0),
  ('John Wick', 3, 1, 'An ex-hitman comes out of retirement to track down the gangsters that killed his dog and took everything from him.', '2014-10-24', NOW(), NOW(), 0),
  ('Mad Max: Fury Road', 3, 1, 'In a post-apocalyptic wasteland, Max teams up with a mysterious woman, Furiosa, to flee from a tyrant and his army.', '2015-05-15', NOW(), NOW(), 0),

  -- Sci-fi movies
  ('Interstellar', 4, 1, 'A team of explorers travel through a wormhole in space in an attempt to ensure humanity’s survival.', '2014-11-07', NOW(), NOW(), 0),
  ('Inception', 4, 1, 'A skilled thief who steals secrets through dream-sharing technology is given a chance to erase his past crimes.', '2010-07-16', NOW(), NOW(), 0),
  ('The Matrix', 4, 1, 'A computer hacker learns that reality as he knows it is a simulated world controlled by intelligent machines.', '1999-03-31', NOW(), NOW(), 0),

  -- Comedy movies
  ('Superbad', 5, 1, 'Two high school friends plan to party before graduation but their night spirals into chaos.', '2007-08-17', NOW(), NOW(), 0),
  ('The Hangover', 5, 1, 'Three friends wake up from a bachelor party in Las Vegas with no memory of the previous night and the groom missing.', '2009-06-05', NOW(), NOW(), 0),
  ('Step Brothers', 5, 1, 'Two middle-aged, lazy men become stepbrothers and are forced to live together.', '2008-07-25', NOW(), NOW(), 0);
