-- =============================================================
-- TV SHOWS
-- =============================================================
INSERT INTO tv_show (name, description, genre_id, created_at, updated_at, version)
VALUES
  ('Stranger Things', 'A group of young friends uncover a series of supernatural mysteries in their small town.', 4, NOW(), NOW(), 0),
  ('Breaking Bad', 'A high school chemistry teacher turned methamphetamine producer struggles with morality and survival.', 3, NOW(), NOW(), 0),
  ('The Office', 'A mockumentary about the everyday lives of employees at a paper company in Scranton, Pennsylvania.', 5, NOW(), NOW(), 0),
  ('The Haunting of Hill House', 'A fractured family confronts haunting memories of their old home and the terrifying events that drove them from it.', 1, NOW(), NOW(), 0),
  ('Bridgerton', 'Set in Regency-era London, the Bridgerton family navigates love, scandal, and social expectations.', 2, NOW(), NOW(), 0);

-- =============================================================
-- TV SHOW SEASONS
-- =============================================================
INSERT INTO tv_show_season (name, description, tv_show_id, release_date, created_at, updated_at, version)
VALUES
  -- Stranger Things
  ('Season 1', 'The disappearance of a young boy leads to dark government experiments and a mysterious girl.', 1, '2016-07-15', NOW(), NOW(), 0),
  ('Season 2', 'The residents of Hawkins face a new monstrous threat from the Upside Down.', 1, '2017-10-27', NOW(), NOW(), 0),
  ('Season 3', 'Summer brings romance, mystery, and a new evil lurking beneath Starcourt Mall.', 1, '2019-07-04', NOW(), NOW(), 0),

  -- Breaking Bad
  ('Season 1', 'Walter White begins cooking meth to secure his family’s future.', 2, '2008-01-20', NOW(), NOW(), 0),
  ('Season 2', 'The consequences of Walt’s new life begin to spiral out of control.', 2, '2009-03-08', NOW(), NOW(), 0),

  -- The Office
  ('Season 1', 'The introduction to the quirky employees of Dunder Mifflin.', 3, '2005-03-24', NOW(), NOW(), 0),
  ('Season 2', 'Office romances and rivalries intensify under Michael Scott’s awkward leadership.', 3, '2005-09-20', NOW(), NOW(), 0),

  -- The Haunting of Hill House
  ('Season 1', 'The Crain family relives the horrors of Hill House as past and present collide.', 4, '2018-10-12', NOW(), NOW(), 0),

  -- Bridgerton
  ('Season 1', 'Daphne Bridgerton searches for true love in a world of gossip and matchmaking.', 5, '2020-12-25', NOW(), NOW(), 0),
  ('Season 2', 'Anthony Bridgerton faces his own romantic dilemmas as the social season begins.', 5, '2022-03-25', NOW(), NOW(), 0);

-- =============================================================
-- MEDIA (Each episode is a media record)
-- =============================================================
INSERT INTO media (name, description, genre_id, media_type_id, release_date, created_at, updated_at, version)
VALUES
  -- Stranger Things S1
  ('Chapter One: The Vanishing of Will Byers', 'A boy disappears, leading his friends to a girl with strange powers.', 4, 2, '2016-07-15', NOW(), NOW(), 0),
  ('Chapter Two: The Weirdo on Maple Street', 'Mike hides Eleven as the group searches for Will.', 4, 2, '2016-07-15', NOW(), NOW(), 0),
  ('Chapter Three: Holly, Jolly', 'Joyce makes contact with Will through Christmas lights.', 4, 2, '2016-07-15', NOW(), NOW(), 0),

  -- Breaking Bad S1
  ('Pilot', 'Walt begins his criminal transformation.', 3, 2, '2008-01-20', NOW(), NOW(), 0),
  ('Cat’s in the Bag...', 'Walt and Jesse deal with the aftermath of their first cook.', 3, 2, '2008-01-27', NOW(), NOW(), 0),
  ('...And the Bag’s in the River', 'Walt struggles with guilt and secrecy.', 3, 2, '2008-02-10', NOW(), NOW(), 0),

  -- The Office S1
  ('Pilot', 'A documentary crew films the employees of Dunder Mifflin.', 5, 2, '2005-03-24', NOW(), NOW(), 0),
  ('Diversity Day', 'Michael hosts a diversity seminar with chaotic results.', 5, 2, '2005-03-29', NOW(), NOW(), 0),
  ('Health Care', 'Dwight’s poor health care plan causes office-wide outrage.', 5, 2, '2005-04-05', NOW(), NOW(), 0),

  -- The Haunting of Hill House S1
  ('Steven Sees a Ghost', 'The Crain family’s tragic past resurfaces when another tragedy strikes.', 1, 2, '2018-10-12', NOW(), NOW(), 0),
  ('Open Casket', 'Shirley faces difficult memories from her family’s time at Hill House.', 1, 2, '2018-10-12', NOW(), NOW(), 0),
  ('Touch', 'Theo’s sensitivity reveals the house’s hidden horrors.', 1, 2, '2018-10-12', NOW(), NOW(), 0),

  -- Bridgerton S1
  ('Diamond of the First Water', 'Daphne’s debut in London’s social season draws attention.', 2, 2, '2020-12-25', NOW(), NOW(), 0),
  ('Shock and Delight', 'A scandal threatens Daphne’s future as Simon’s past emerges.', 2, 2, '2020-12-25', NOW(), NOW(), 0),
  ('Art of the Swoon', 'Romance and gossip spread as Daphne’s match grows complicated.', 2, 2, '2020-12-25', NOW(), NOW(), 0);

-- =============================================================
-- EPISODES (Map media to seasons)
-- =============================================================
INSERT INTO tv_show_episode (episode_number, tv_show_season_id, media_id, created_at, updated_at, version)
VALUES
  -- Stranger Things Season 1 (season_id = 1)
  (1, 1, 16, NOW(), NOW(), 0),
  (2, 1, 17, NOW(), NOW(), 0),
  (3, 1, 18, NOW(), NOW(), 0),

  -- Breaking Bad Season 1 (season_id = 4)
  (1, 4, 19, NOW(), NOW(), 0),
  (2, 4, 20, NOW(), NOW(), 0),
  (3, 4, 21, NOW(), NOW(), 0),

  -- The Office Season 1 (season_id = 6)
  (1, 6, 22, NOW(), NOW(), 0),
  (2, 6, 23, NOW(), NOW(), 0),
  (3, 6, 24, NOW(), NOW(), 0),

  -- The Haunting of Hill House Season 1 (season_id = 8)
  (1, 8, 25, NOW(), NOW(), 0),
  (2, 8, 26, NOW(), NOW(), 0),
  (3, 8, 27, NOW(), NOW(), 0),

  -- Bridgerton Season 1 (season_id = 9)
  (1, 9, 28, NOW(), NOW(), 0),
  (2, 9, 29, NOW(), NOW(), 0),
  (3, 9, 30, NOW(), NOW(), 0);
