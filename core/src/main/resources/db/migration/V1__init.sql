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
  STORY_POINTS_CLOSED FLOAT NOT NULL,
  STORY_POINTS_TAKEN FLOAT NOT NULL,
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