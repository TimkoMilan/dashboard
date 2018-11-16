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