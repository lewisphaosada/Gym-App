BEGIN TRANSACTION;

DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS sessions;
DROP TABLE IF EXISTS workout;
DROP TABLE IF EXISTS goal;
DROP TABLE IF EXISTS user_goal;

--users - Contains users' info

CREATE TABLE users (
	user_id SERIAL,
	username varchar(50) NOT NULL UNIQUE,
	password_hash varchar(200) NOT NULL,
	role varchar(50) NOT NULL,
	email VARCHAR(100),
	photo VARCHAR(200),
	weight DECIMAL(5,2),
	height DECIMAL(5,2),
	sex VARCHAR(1), -- (M)ale, (F)emale, (O)ther
	bmi DECIMAL(5,2),
	CONSTRAINT PK_user PRIMARY KEY (user_id)
);

--sessions - Contains users' gym check in and duration

CREATE TABLE sessions (
session_id SERIAL,
user_id INT,
duration TIME,
date DATE, -- potentially a DATETIME?
CONSTRAINT PK_session PRIMARY KEY (session_id),
CONSTRAINT FK_user_session FOREIGN KEY (user_id) REFERENCES users
);

--workout - Contains users' specific workouts within a session


CREATE TABLE workout (
workout_id SERIAL,
session_id INT,
user_id INT,
name VARCHAR(50),
duration TIME,
weight DECIMAL(5,2),
sets INT,
reps INT,
CONSTRAINT PK_workout PRIMARY KEY (workout_id),
CONSTRAINT FK_session_workout FOREIGN KEY (session_id) REFERENCES sessions,
CONSTRAINT FK_user_workout FOREIGN KEY (user_id) REFERENCES users
);

--goal - Contains users' goals

CREATE TABLE goal (
 goal_id SERIAL,
 description VARCHAR(300),
 CONSTRAINT PK_goal PRIMARY KEY (goal_id)
);

--user_goal - Joint table joining Users & Goal
CREATE TABLE user_goal (
goal_id INT,
user_id INT,
CONSTRAINT FK_user_goal_goal FOREIGN KEY (goal_id) REFERENCES goal,
CONSTRAINT FK_user_goal_user FOREIGN KEY (user_id) REFERENCES users
);

COMMIT TRANSACTION;