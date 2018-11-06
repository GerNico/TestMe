CREATE TABLE COURSE_PROGRESS
(
  ID                 LONG NOT NULL AUTO_INCREMENT    primary key,
  COURSE_ID          LONG,
  SUSCRIBER_ID       LONG,
  BEGIN_DATE         DATE,
  PROGRESS_DATE      DATE,
  CONSTRAINT FK_PROGRESS_ON_COURSE FOREIGN KEY (COURSE_ID) REFERENCES COURSE (ID),
  CONSTRAINT FK_SUBSCRIBER_ON_COURSE FOREIGN KEY (SUSCRIBER_ID) REFERENCES USER (ID)
);

CREATE TABLE PASSED_TEST
(
  ID                 LONG NOT NULL AUTO_INCREMENT    primary key,
  COURSE_PROGRESS_ID LONG NOT NULL,
  TEST_ID            LONG NOT NULL,
  PASSING_DATE       DATE,
  PASSING_MODE       VARCHAR(20),
  PASSING_STATUS     VARCHAR(20),
  PASSING_SCORE      LONG,
  CONSTRAINT FK_PASSING_TEST FOREIGN KEY (TEST_ID) REFERENCES TEST (ID),
  CONSTRAINT FK_BASE_PROGRESS FOREIGN KEY (COURSE_PROGRESS_ID) REFERENCES COURSE_PROGRESS (ID)
);

CREATE TABLE ANSWER
(
  ID             LONG    NOT NULL AUTO_INCREMENT    primary key,
  QUESTION_ID    LONG    NOT NULL,
  PASSED_TEST_ID LONG    NOT NULL,
  IS_CORRECT     BOOLEAN NOT NULL,
  GIVEN_ANSWER   VARCHAR(200),
  CONSTRAINT FK_ANSWERING_QUESTION FOREIGN KEY (QUESTION_ID)  REFERENCES QUESTION (ID),
  CONSTRAINT FK_BASED_TEST_PASS FOREIGN KEY (PASSED_TEST_ID) REFERENCES PASSED_TEST (ID)
);