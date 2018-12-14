CREATE  TABLE FTE(
  ID BIGINT NOT NULL,
  FTE DOUBLE,
  MONTH TINYINT,
  YEAR INTEGER,
  TEAM_ID BIGINT
);
CREATE CACHED TABLE MEMBER(
  ID BIGINT NOT NULL,
  BILLING_VALUE VARCHAR(255),
  FOCUS VARCHAR(255),
  NAME VARCHAR(255),
  POSITION VARCHAR(255),
  SEARCH_STRING VARCHAR(255),
  TEAM_ID BIGINT
);

CREATE CACHED TABLE ROLE(
  ID BIGINT NOT NULL,
  NAME VARCHAR(60)
);
CREATE CACHED TABLE SPRINT(
  ID BIGINT NOT NULL,
  END TIMESTAMP,
  NAME VARCHAR(255),
  START TIMESTAMP
);
CREATE CACHED TABLE SPRINT_DATA(
  ID BIGINT NOT NULL,
  STORY_POINTS_CLOSED DOUBLE NOT NULL,
  STORY_POINTS_TAKEN DOUBLE NOT NULL,
  SPRINT_ID BIGINT,
  TEAM_ID BIGINT
);
CREATE CACHED TABLE TEAM(
  ID BIGINT NOT NULL,
  FOCUS VARCHAR(255),
  NAME VARCHAR(255)
);
CREATE CACHED TABLE USER(
  ID BIGINT NOT NULL,
  EMAIL VARCHAR(255),
  FIRST_NAME VARCHAR(255),
  LAST_NAME VARCHAR(255),
  PASSWORD VARCHAR(255),
  TEAM_ID BIGINT
);
CREATE CACHED TABLE USER_ROLES(
  USER_ID BIGINT NOT NULL,
  ROLE_ID BIGINT NOT NULL
);
CREATE CACHED TABLE VACATION(
  ID BIGINT NOT NULL,
  IS_HALF_DAY BOOLEAN NOT NULL,
  START TIMESTAMP,
  MEMBER_ID BIGINT
);
CREATE SEQUENCE SEQSPRINT START WITH 1000;
CREATE SEQUENCE HIBERNATE_SEQUENCE START WITH 1;
CREATE SEQUENCE SSEQUSER START WITH 1000;
CREATE SEQUENCE SEQMEMBER START WITH 1000;
CREATE SEQUENCE SEQROLE START WITH 1000;
CREATE SEQUENCE SEQFTE START WITH 1000;
CREATE SEQUENCE SEQTEAM START WITH 1000;
CREATE SEQUENCE SEQSPRINTDATA START WITH 1000;
INSERT INTO TEAM(ID, FOCUS, NAME) VALUES
  (1, 'string', 'Manatee'),
  (2, 'string', 'Beach Blonde'),
  (3, 'string', 'Sunny Beach'),
  (4, 'string', 'Chill Factory'),
  (5, 'string', 'Grape'),
  (6, 'string', 'N/A'),
  (7, 'string', 'Labs');
INSERT INTO MEMBER(ID, BILLING_VALUE, FOCUS, NAME, POSITION, SEARCH_STRING, TEAM_ID) VALUES
  (1, NULL, NULL, 'Marcel Bodnar', 'java', 'marcelbodnar', 1),
  (2, NULL, NULL, 'Marian Kresak', 'node js / web ui', 'mariankresak', 1),
  (3, NULL, NULL, 'Peter Jurcak', 'node js', 'peterjurcak', 1),
  (4, NULL, NULL, 'Kamil Jancar', 'web', 'kamiljancar', 1),
  (5, NULL, NULL, 'Martin Pach', 'java / js', 'martinpach', 1),
  (6, NULL, NULL, 'Matus Bernat', 'web', 'matusbernat', 1),
  (7, NULL, NULL, 'Matej Gomolcak', 'qa automation', 'matejgomolcak', 1),
  (8, NULL, NULL, 'Marek Olejnik', 'qa automation', 'marekolejnik', 1),
  (9, NULL, NULL, 'Michal Gubriansky', 'node js', 'michalgubriansky', 2),
  (10, NULL, NULL, 'Lukas Jeziorski', 'java', 'lukasjeziorski', 2),
  (11, NULL, NULL, 'Peter Pincak', 'js', 'peterpincak', 2),
  (12, NULL, NULL, 'Marcel Presalovic', 'c++', 'marcelpresalovic', 2),
  (13, NULL, NULL, 'Martin Cepela', 'qa automation', 'martincepela', 2),
  (14, NULL, NULL, 'Roman Rovensky', 'qa automation', 'romanrovensky', 2),
  (15, NULL, NULL, 'Vladislav Mino', 'java', 'vladislavmino', 3),
  (16, NULL, NULL, 'Vladimir Uram', 'java /js', 'vladimiruram', 3),
  (17, NULL, NULL, 'Jozef Zelinsky', 'java', 'jozefzelinsky', 3),
  (18, NULL, NULL, 'Martina Tutokiova', 'java', 'martinatutokiova', 3),
  (19, NULL, NULL, 'Martin Varga', 'qa automation', 'martinvarga', 3),
  (20, NULL, NULL, 'Martin Cuchran', 'java / js', 'martincuchran', 4),
  (21, NULL, NULL, 'Matus Hozza', 'java / js', 'matushozza', 4),
  (22, NULL, NULL, 'Dominika Fric', 'java / js', 'dominikafric', 4),
  (23, NULL, NULL, 'Julius Urban', 'java / js', 'juliusurban', 4),
  (24, NULL, NULL, 'Michal Timko', 'qa automation', 'michaltimko', 4),
  (25, NULL, NULL, 'Ivan Hospodar', 'qa automation', 'ivanhospodar', 4),
  (26, NULL, NULL, 'Milan Lukac', 'web ui/js', 'milanlukac', 5),
  (27, NULL, NULL, 'Jan Kassovic', 'java', 'jankassovic', 5),
  (28, NULL, NULL, 'Richard Forrai', 'java', 'richardforrai', 5),
  (29, NULL, NULL, 'Pavol Kosc', 'java', 'pavolkosc', 5),
  (30, NULL, NULL, 'Mario Kandra', 'qa automation', 'mariokandra', 5),
  (31, NULL, NULL, 'Peter Simco', 'qa automation', 'petersimco', 5),
  (32, NULL, NULL, 'Richard Chudy', 'Manager', 'richardchudy', 6),
  (33, NULL, NULL, 'Vladimir Trojanovic', 'Scrum Master', 'vladimirtrojanovic', 6),
  (34, NULL, NULL, 'Dinesh Singh', 'OnSite ', 'dineshsingh', 6),
  (35, NULL, NULL, 'Peter Ziak', 'java ', 'peterziak', 7),
  (36, NULL, NULL, 'Jaroslav Regec', 'java ', 'jaroslavregec', 7),
  (37, NULL, NULL, 'Radek Leifer', 'java', 'radekleifer', 7),
  (38, NULL, NULL, 'Tomas Oberle ', 'java ', 'tomasoberle', 7),
  (39, NULL, NULL, 'Jozef Hurnansky', 'java ', 'jozefhurnansky', 7),
  (40, NULL, NULL, 'Imrich Olha', 'java ', 'imricholha', 7),
  (41, NULL, NULL, 'Jan Kovalcik', 'java', 'jankovalcik', 2),
  (42, NULL, NULL, 'Vladimir Holik', 'java', 'vladimirholik', 2),
  (43, NULL, NULL, 'Milan Lison', 'qa automation', 'milanlison', 2),
  (44, NULL, NULL, 'Cyril Jusko', 'qa automation', 'cyriljusko', 2),
  (45, NULL, NULL, 'Rastislav Janosik', 'java / js', 'rastislavjanosik', 3),
  (46, NULL, NULL, 'Gabriel Polak', 'java / js', 'gabrielpolak', 4),
  (47, NULL, NULL, 'Peter Onofrej', 'java / js', 'peteronofrej', 5),
  (48, NULL, NULL, 'Milan Jacko', 'java / js', 'milanjacko', 5),
  (49, NULL, NULL, 'Lubos Jasko', 'web', 'lubosjasko', 5),
  (50, NULL, NULL, 'Peter Muszka', 'qa automation', 'petermuszka', 5),
  (51, NULL, NULL, 'Leonid Hranatov', 'java', 'leonidhranatoav', 6);
INSERT INTO USER(ID, EMAIL, FIRST_NAME, LAST_NAME, PASSWORD, TEAM_ID) VALUES
  (1, 'admin@email.com', 'admin', 'admin', '$2a$11$qwDZtD8OSyaQ5aeL.leCoOycbRTy6rO.9FFLxFFOQ2cFtL/ZZkNw.', NULL);
INSERT INTO ROLE(ID, NAME) VALUES
  (1, 'ROLE_USER'),
  (2, 'ROLE_ADMIN'),
  (3, 'ROLE_TEAMLEADER');
INSERT INTO USER_ROLES(USER_ID, ROLE_ID) VALUES
  (1, 2);
INSERT INTO PUBLIC.SPRINT(ID, END, NAME, START) VALUES
  (2497, TIMESTAMP '2018-01-31 00:00:00', 'IT', TIMESTAMP '2018-01-28 00:00:00'),
  (2499, TIMESTAMP '2018-03-10 00:00:00', 'Flik', TIMESTAMP '2018-03-05 00:00:00'),
  (2501, TIMESTAMP '2018-03-23 00:00:00', 'Jafar', TIMESTAMP '2018-03-21 00:00:00'),
  (2503, TIMESTAMP '2018-03-04 00:00:00', 'Eeyrone', TIMESTAMP '2018-03-01 00:00:00'),
  (2505, TIMESTAMP '2018-02-21 00:00:00', 'Buzz Lightyear', TIMESTAMP '2018-02-18 00:00:00'),
  (2507, TIMESTAMP '2018-03-20 00:00:00', 'Iago', TIMESTAMP '2018-03-19 00:00:00'),
  (2509, TIMESTAMP '2018-02-09 00:00:00', 'Krampus', TIMESTAMP '2018-02-06 00:00:00'),
  (2512, TIMESTAMP '2018-03-18 00:00:00', 'Hook', TIMESTAMP '2018-03-15 00:00:00'),
  (2515, TIMESTAMP '2018-02-17 00:00:00', 'Aladdin', TIMESTAMP '2018-02-15 00:00:00'),
  (2517, TIMESTAMP '2018-03-14 00:00:00', 'Gaston', TIMESTAMP '2018-03-11 00:00:00'),
  (2519, TIMESTAMP '2018-01-17 00:00:00', 'Exorcist', TIMESTAMP '2018-01-16 00:00:00'),
  (2521, TIMESTAMP '2018-01-12 00:00:00', 'Carrie (2 Sprint)', TIMESTAMP '2018-01-10 00:00:00'),
  (2523, TIMESTAMP '2018-01-20 00:00:00', 'Friday 13', TIMESTAMP '2018-01-19 00:00:00'),
  (2525, TIMESTAMP '2018-02-05 00:00:00', 'Jaws', TIMESTAMP '2018-02-01 00:00:00'),
  (2527, TIMESTAMP '2018-03-31 00:00:00', 'Kaa', TIMESTAMP '2018-03-24 00:00:00'),
  (2533, TIMESTAMP '2018-02-25 00:00:00', 'Cruella de Vil', TIMESTAMP '2018-02-22 00:00:00'),
  (2536, TIMESTAMP '2018-01-24 00:00:00', 'Grudge', TIMESTAMP '2018-01-22 00:00:00'),
  (2543, TIMESTAMP '2018-02-14 00:00:00', 'Leprechaun', TIMESTAMP '2018-02-10 00:00:00'),
  (2550, TIMESTAMP '2018-01-27 00:00:00', 'Halloween', TIMESTAMP '2018-01-25 00:00:00'),
  (2556, TIMESTAMP '2018-02-28 00:00:00', 'Dory', TIMESTAMP '2018-02-26 00:00:00'),
  (2558, TIMESTAMP '2018-01-15 00:00:00', 'Descent', TIMESTAMP '2018-01-13 00:00:00'),
  (2564, TIMESTAMP '2018-01-03 00:00:00', 'Vulture', TIMESTAMP '2018-01-01 00:00:00'),
  (2571, TIMESTAMP '2018-01-09 00:00:00', 'Babadook', TIMESTAMP '2018-01-07 00:00:00'),
  (2573, TIMESTAMP '2018-01-06 00:00:00', 'Allien', TIMESTAMP '2018-01-04 00:00:00');


INSERT INTO PUBLIC.SPRINT_DATA(ID, STORY_POINTS_CLOSED, STORY_POINTS_TAKEN, SPRINT_ID, TEAM_ID) VALUES
  (40157, 22.5, 36.0, 2497, 3),
  (40158, 20.0, 24.0, 2499, 4),
  (40159, 27.5, 28.0, 2501, 1),
  (40160, 22.0, 23.5, 2503, 3),
  (40161, 22.5, 39.0, 2505, 4),
  (40162, 27.5, 34.0, 2507, 1),
  (40163, 30.5, 36.5, 2509, 4),
  (40164, 79.0, 89.0, 2503, 2),
  (40165, 21.5, 23.0, 2512, 4),
  (40166, 18.5, 27.0, 2507, 4),
  (40167, 64.0, 58.0, 2515, 2),
  (40168, 34.0, 46.5, 2517, 5),
  (40169, 33.0, 48.0, 2519, 3),
  (40170, 55.0, 63.0, 2521, 1),
  (40171, 37.0, 59.0, 2523, 3),
  (40172, 5.5, 10.0, 2525, 3),
  (40173, 82.0, 78.0, 2527, 2),
  (40174, 16.0, 24.5, 2527, 1),
  (40175, 17.0, 27.0, 2497, 4),
  (40176, 18.5, 31.0, 2517, 1),
  (40177, 7.5, 9.0, 2525, 1),
  (40178, 36.0, 48.0, 2533, 5),
  (40179, 29.0, 38.0, 2519, 4),
  (40180, 26.0, 41.0, 2536, 4),
  (40181, 19.5, 31.0, 2533, 4),
  (40182, 22.0, 30.0, 2533, 3),
  (40183, 37.0, 44.0, 2519, 5),
  (40184, 0.0, 0.0, 2525, 4),
  (40185, 25.0, 48.0, 2536, 3),
  (40186, 34.0, 40.0, 2543, 4),
  (40187, 43.5, 44.5, 2499, 5),
  (40188, 19.5, 30.0, 2517, 3),
  (40189, 38.0, 35.0, 2509, 1),
  (40190, 32.0, 37.0, 2505, 1),
  (40191, 41.0, 50.0, 2519, 2),
  (40192, 45.0, 51.0, 2550, 1),
  (40193, 57.0, 64.0, 2521, 4),
  (40194, 37.0, 38.5, 2543, 3),
  (40195, 20.5, 44.5, 2536, 5),
  (40196, 18.0, 32.0, 2497, 2),
  (40197, 32.0, 39.0, 2556, 5),
  (40198, 24.0, 30.0, 2558, 3),
  (40199, 86.0, 101.0, 2556, 2),
  (40200, 10.0, 9.0, 2525, 5),
  (40201, 68.0, 74.0, 2505, 2),
  (40202, 23.5, 33.5, 2515, 4),
  (40204, 33.0, 33.0, 2556, 1),
  (40205, 38.0, 29.5, 2509, 5),
  (40206, 52.0, 69.0, 2523, 4),
  (40207, 58.0, 65.0, 2519, 1),
  (40208, 25.0, 40.5, 2550, 5),
  (40209, 40.0, 51.0, 2571, 1),
  (40210, 43.0, 56.0, 2573, 1),
  (40211, 33.0, 39.0, 2550, 4),
  (40212, 71.0, 71.0, 2543, 3),
  (40213, 16.0, 34.0, 2527, 4),
  (40214, 11.0, 16.0, 2558, 5),
  (40215, 82.0, 69.0, 2507, 2),
  (40216, 30.0, 35.0, 2501, 4),
  (40217, 13.5, 29.5, 2517, 4),
  (40218, 22.0, 25.0, 2515, 1),
  (40219, 40.5, 30.5, 2509, 3),
  (40220, 22.0, 30.5, 2503, 1),
  (40221, 15.0, 38.0, 2571, 2),
  (40222, 31.0, 37.5, 2533, 1),
  (40223, 5.0, 6.0, 2525, 2),
  (40224, 12.0, 13.0, 2515, 3),
  (40225, 25.0, 31.0, 2523, 1),
  (40226, 34.0, 28.5, 2505, 4),
  (40227, 65.0, 74.0, 2499, 2),
  (40228, 28.5, 22.0, 2501, 3),
  (40229, 11.0, 25.0, 2505, 3),
  (40230, 14.0, 30.0, 2558, 4),
  (40231, 62.0, 91.0, 2512, 2),
  (40232, 26.0, 22.0, 2512, 3),
  (40233, 45.5, 55.0, 2523, 5),
  (40234, 24.0, 54.0, 2558, 1),
  (40235, 24.5, 36.0, 2527, 4),
  (40236, 32.0, 46.0, 2536, 2),
  (40237, 34.0, 42.0, 2507, 4),
  (40238, 37.0, 46.0, 2527, 5),
  (40239, 14.0, 23.0, 2573, 2),
  (40240, 34.0, 48.0, 2550, 4),
  (40241, 86.0, 71.0, 2501, 4),
  (40242, 20.5, 30.0, 2499, 1),
  (40243, 12.0, 44.0, 2564, 1),
  (40244, 30.5, 51.5, 2550, 2),
  (40245, 16.5, 46.0, 2523, 3),
  (40246, 27.0, 36.5, 2543, 5),
  (40247, 40.0, 37.5, 2501, 5),
  (40248, 37.5, 32.0, 2556, 3),
  (40249, 22.0, 23.5, 2499, 3),
  (40250, 24.0, 41.0, 2497, 1),
  (40251, 76.0, 85.0, 2521, 2),
  (40252, 23.0, 47.0, 2558, 2),
  (40253, 33.0, 33.5, 2512, 1),
  (40254, 0.0, 7.0, 2509, 2),
  (40255, 25.0, 25.0, 2556, 4),
  (40256, 60.0, 100.0, 2533, 2),
  (40257, 35.5, 36.0, 2507, 5),
  (40258, 33.0, 42.5, 2536, 5),
  (40259, 53.0, 44.0, 2512, 5),
  (40260, 38.0, 68.0, 2517, 2),
  (40261, 30.5, 36.5, 2543, 1),
  (40262, 23.5, 20.5, 2515, 5),
  (40263, 31.5, 34.0, 2503, 5),
  (40264, 29.0, 35.0, 2521, 3),
  (40265, 26.0, 32.0, 2503, 4),
  (40266, 14.0, 34.5, 2497, 5);

ALTER TABLE PUBLIC.VACATION ADD CONSTRAINT PUBLIC.CONSTRAINT_CA PRIMARY KEY(ID);
ALTER TABLE PUBLIC.ROLE ADD CONSTRAINT PUBLIC.UK_EPK9IM9L9Q67XMWI4HBED25DO UNIQUE(NAME);
ALTER TABLE PUBLIC.USER ADD CONSTRAINT PUBLIC.FKBMQM8C8M2AW1VGRIJ7H0OD0OK FOREIGN KEY(TEAM_ID) REFERENCES PUBLIC.TEAM(ID) NOCHECK;
ALTER TABLE PUBLIC.FTE ADD CONSTRAINT PUBLIC.FKETXYGB99ARCU0MJUO4M8WSI6M FOREIGN KEY(TEAM_ID) REFERENCES PUBLIC.TEAM(ID) NOCHECK;
ALTER TABLE PUBLIC.SPRINT_DATA ADD CONSTRAINT PUBLIC.FKOU0COTEAD3UKB6RDTOXRO4QWP FOREIGN KEY(SPRINT_ID) REFERENCES PUBLIC.SPRINT(ID) NOCHECK;
ALTER TABLE PUBLIC.USER_ROLES ADD CONSTRAINT PUBLIC.FK55ITPPKW3I07DO3H7QOCLQD4K FOREIGN KEY(USER_ID) REFERENCES PUBLIC.USER(ID) NOCHECK;
ALTER TABLE PUBLIC.USER_ROLES ADD CONSTRAINT PUBLIC.FKRHFOVTCIQ1L558CW6UDG0H0D3 FOREIGN KEY(ROLE_ID) REFERENCES PUBLIC.ROLE(ID) NOCHECK;
ALTER TABLE PUBLIC.TOKEN ADD CONSTRAINT PUBLIC.FKE32EK7IXANAKFQSDAOKM4Q9Y2 FOREIGN KEY(USER_ID) REFERENCES PUBLIC.USER(ID) NOCHECK;
ALTER TABLE PUBLIC.SPRINT_DATA ADD CONSTRAINT PUBLIC.FKNVVT2TVF5WM2L7K9XJJD8TAX3 FOREIGN KEY(TEAM_ID) REFERENCES PUBLIC.TEAM(ID) NOCHECK;
ALTER TABLE PUBLIC.MEMBER ADD CONSTRAINT PUBLIC.FKCJTE2JN9PVO9UD2HYFGWCJA0K FOREIGN KEY(TEAM_ID) REFERENCES PUBLIC.TEAM(ID) NOCHECK;
ALTER TABLE PUBLIC.VACATION ADD CONSTRAINT PUBLIC.FK5RAVBHAPE8MIT6A0U6Y80O2NH FOREIGN KEY(MEMBER_ID) REFERENCES PUBLIC.MEMBER(ID) NOCHECK;