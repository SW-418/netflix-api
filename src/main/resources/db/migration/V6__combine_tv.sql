INSERT INTO media_type (name, created_at, updated_at, version)
VALUES
   ('TV_SHOW_SEASON', NOW(), NOW(), 0),
   ('TV_SHOW_EPISODE', NOW(), NOW(), 0);

ALTER TABLE media
ADD COLUMN parent_id BIGINT;

ALTER TABLE media
ADD CONSTRAINT fk_media_parent_id FOREIGN KEY (parent_id) REFERENCES media(id);

DROP TABLE tv_show_episode;
DROP TABLE tv_show_season;
DROP TABLE tv_show;

-- Remove old tv show episodes
DELETE FROM media WHERE media_type_id = 2;

-- =============================================================
-- TV SHOWS
-- =============================================================
INSERT INTO media (name, description, genre_id, media_type_id, release_date, created_at, updated_at, version)
VALUES
  ('Stranger Things', 'A group of young friends uncover a series of supernatural mysteries in their small town.', 4, 2, '2016-07-15', NOW(), NOW(), 0),
  ('Breaking Bad', 'A high school chemistry teacher turned methamphetamine producer struggles with morality and survival.', 3, 2, '2008-01-20', NOW(), NOW(), 0),
  ('The Office', 'A mockumentary about the everyday lives of employees at a paper company in Scranton, Pennsylvania.', 5, 2, '2005-03-24', NOW(), NOW(), 0),
  ('The Haunting of Hill House', 'A fractured family confronts haunting memories of their old home and the terrifying events that drove them from it.', 1, 2, '2018-10-12', NOW(), NOW(), 0),
  ('Bridgerton', 'Set in Regency-era London, the Bridgerton family navigates love, scandal, and social expectations.', 2, 2, '2020-12-25', NOW(), NOW(), 0);

-- =============================================================
-- TV SHOW SEASONS
-- =============================================================
INSERT INTO media (name, description, genre_id, parent_id, media_type_id, release_date, created_at, updated_at, version)
VALUES
  -- Stranger Things
  ('Season 1', 'The disappearance of a young boy leads to dark government experiments and a mysterious girl.', 4, 31, 3, '2016-07-15', NOW(), NOW(), 0),
  ('Season 2', 'The residents of Hawkins face a new monstrous threat from the Upside Down.', 4, 31, 3, '2017-10-27', NOW(), NOW(), 0),
  ('Season 3', 'Summer brings romance, mystery, and a new evil lurking beneath Starcourt Mall.', 4, 31, 3, '2019-07-04', NOW(), NOW(), 0),

  -- Breaking Bad
  ('Season 1', 'Walter White begins cooking meth to secure his family’s future.', 3, 32, 3, '2008-01-20', NOW(), NOW(), 0),
  ('Season 2', 'The consequences of Walt’s new life begin to spiral out of control.', 3, 32, 3, '2009-03-08', NOW(), NOW(), 0),

  -- The Office
  ('Season 1', 'The introduction to the quirky employees of Dunder Mifflin.', 5, 33, 3, '2005-03-24', NOW(), NOW(), 0),
  ('Season 2', 'Office romances and rivalries intensify under Michael Scott’s awkward leadership.', 5, 33, 3, '2005-09-20', NOW(), NOW(), 0),

  -- The Haunting of Hill House
  ('Season 1', 'The Crain family relives the horrors of Hill House as past and present collide.', 1, 34, 3, '2018-10-12', NOW(), NOW(), 0),

  -- Bridgerton
  ('Season 1', 'Daphne Bridgerton searches for true love in a world of gossip and matchmaking.', 2, 35, 3, '2020-12-25', NOW(), NOW(), 0),
  ('Season 2', 'Anthony Bridgerton faces his own romantic dilemmas as the social season begins.', 2, 35, 3, '2022-03-25', NOW(), NOW(), 0);

---- =============================================================
---- TV SHOW EPISODES
---- =============================================================
INSERT INTO media (name, description, genre_id, parent_id, media_type_id, release_date, created_at, updated_at, version)
VALUES
  -- Stranger Things S1
  ('Chapter One: The Vanishing of Will Byers', 'A boy disappears, leading his friends to a girl with strange powers.', 4, 36, 4, '2016-07-15', NOW(), NOW(), 0),
  ('Chapter Two: The Weirdo on Maple Street', 'Mike hides Eleven as the group searches for Will.', 4, 36, 4, '2016-07-15', NOW(), NOW(), 0),
  ('Chapter Three: Holly, Jolly', 'Joyce makes contact with Will through Christmas lights.', 4, 36, 4, '2016-07-15', NOW(), NOW(), 0),

  -- Breaking Bad S1
  ('Pilot', 'Walt begins his criminal transformation.', 3, 39, 4, '2008-01-20', NOW(), NOW(), 0),
  ('Cat’s in the Bag...', 'Walt and Jesse deal with the aftermath of their first cook.', 3, 39, 4, '2008-01-27', NOW(), NOW(), 0),
  ('...And the Bag’s in the River', 'Walt struggles with guilt and secrecy.', 3, 39, 4, '2008-02-10', NOW(), NOW(), 0),

  -- The Office S1
  ('Pilot', 'A documentary crew films the employees of Dunder Mifflin.', 5, 41, 4, '2005-03-24', NOW(), NOW(), 0),
  ('Diversity Day', 'Michael hosts a diversity seminar with chaotic results.', 5, 41, 4, '2005-03-29', NOW(), NOW(), 0),
  ('Health Care', 'Dwight’s poor health care plan causes office-wide outrage.', 5, 41, 4, '2005-04-05', NOW(), NOW(), 0),

  -- The Haunting of Hill House S1
  ('Steven Sees a Ghost', 'The Crain family’s tragic past resurfaces when another tragedy strikes.', 1, 43, 4, '2018-10-12', NOW(), NOW(), 0),
  ('Open Casket', 'Shirley faces difficult memories from her family’s time at Hill House.', 1, 43, 4, '2018-10-12', NOW(), NOW(), 0),
  ('Touch', 'Theo’s sensitivity reveals the house’s hidden horrors.', 1, 43, 4, '2018-10-12', NOW(), NOW(), 0),

  -- Bridgerton S1
  ('Diamond of the First Water', 'Daphne’s debut in London’s social season draws attention.', 2, 44, 4, '2020-12-25', NOW(), NOW(), 0),
  ('Shock and Delight', 'A scandal threatens Daphne’s future as Simon’s past emerges.', 2, 44, 4, '2020-12-25', NOW(), NOW(), 0);
