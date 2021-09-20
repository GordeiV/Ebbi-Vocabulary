USE wordsapp;

DROP TABLE IF EXISTS words;
DROP TABLE IF EXISTS vocabulary;
DROP TABLE IF EXISTS users;

CREATE TABLE users (
	id_user SERIAL,
    login varchar(100) unique not null,
    u_password varchar(255) not null,
    PRIMARY KEY(id_user)
);

CREATE TABLE vocabulary (
	id_vocabulary SERIAL,
    v_name varchar(50) not null,
    v_date DATETIME not null,
    next_repeat_time DATETIME not null,
    v_status TINYINT not null,
    id_user BIGINT unsigned not null,
    PRIMARY KEY(id_vocabulary),
    FOREIGN KEY (id_user) REFERENCES users(id_user) ON DELETE CASCADE
);


CREATE TABLE words (
	id_word SERIAL,
    foreign_word varchar(45) not null,
    native_word varchar(45) not null,
    transcription varchar(45),
    id_vocabulary BIGINT unsigned not null,
    PRIMARY KEY(id_word),
    FOREIGN KEY(id_vocabulary) REFERENCES vocabulary(id_vocabulary) ON DELETE CASCADE
);

CREATE INDEX idx_next_repeat_time ON vocabulary(next_repeat_time);
CREATE INDEX idx_id_user ON vocabulary(id_user);
CREATE INDEX idx_id_vocabulary ON words(id_vocabulary);
