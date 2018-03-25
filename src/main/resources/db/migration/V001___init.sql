CREATE TABLE Users
(
  USER_ID       INT NOT NULL AUTO_INCREMENT,
  login         VARCHAR(20) UNIQUE NOT NULL ,
  email         VARCHAR(60) UNIQUE NOT NULL ,
  user_password VARCHAR(128) NOT NULL ,
  CONSTRAINT USER_PK PRIMARY KEY (USER_ID)
);

CREATE TABLE Courses
(
  COURSE_ID          INT AUTO_INCREMENT,
  COURSE_DESCRIPTION VARCHAR(300),
  CONSTRAINT Courses_PK PRIMARY KEY (COURSE_ID)
);

CREATE TABLE Subscriptions
(
  USER_ID     INT NOT NULL,
  COURSE_ID   INT NOT NULL,
  TEST_STATUS VARCHAR(30),
  CONSTRAINT Subscriptions_PK PRIMARY KEY (USER_ID, COURSE_ID),
  CONSTRAINT FK_COURSE FOREIGN KEY (COURSE_ID) REFERENCES Courses (COURSE_ID),
  CONSTRAINT FK_USER FOREIGN KEY (USER_ID)
  REFERENCES Users (USER_ID)
);

CREATE TABLE Question
(
  QUESTION_ID    INT NOT NULL AUTO_INCREMENT,
  QUESTION       VARCHAR(600),
  QUESTION_TYPE  VARCHAR(10),
  CORRECT_ANSWER VARCHAR(200),
  CONSTRAINT QUESTION_WO_PK PRIMARY KEY (QUESTION_ID)
);

CREATE TABLE Tests
(
  COURSE_ID     INT NOT NULL,
  QUESTION_ID   INT NOT NULL,
  CONSTRAINT TEST_PK PRIMARY KEY (COURSE_ID,QUESTION_ID),
  CONSTRAINT FK_Question FOREIGN KEY (QUESTION_ID)
  REFERENCES Question (QUESTION_ID),
  CONSTRAINT FK_QuestionCOURSE FOREIGN KEY (COURSE_ID)
  REFERENCES Courses (COURSE_ID)
);

CREATE TABLE OptionOfAnswer
(
  OPTION_ID   INT NOT NULL AUTO_INCREMENT,
  QUESTION_ID INT NOT NULL,
  OPTION_TEXT VARCHAR(120),
  isCorrect   CHAR(1),
  isSequenceBased   CHAR(1),
  NUMBERS_IN_Sequence INT,
  CONSTRAINT Option_PK PRIMARY KEY (OPTION_ID),
  CONSTRAINT FK_QUESTION_ID FOREIGN KEY (QUESTION_ID)
  REFERENCES Question (QUESTION_ID)
);