CREATE TABLE tickets (
    id BIGINT NOT NULL AUTO_INCREMENT,
    title VARCHAR(255) NOT NULL,
    description TEXT NOT NULL,
    ticketCategory ENUM('bug', 'feature', 'support', 'other') NOT NULL,
    createdAt DATETIME NOT NULL,
    status ENUM('open', 'in_progress', 'solved' , 'closed') NOT NULL DEFAULT 'open',
    user BIGINT NOT NULL,
    answers BIGINT,

    PRIMARY KEY (id)
);

CREATE TABLE answers (
    id BIGINT NOT NULL AUTO_INCREMENT,
    description TEXT NOT NULL,
    createdAt DATETIME NOT NULL,
    ticket BIGINT NOT NULL,
    user BIGINT NOT NULL,
    solution boolean NOT NULL DEFAULT false,

    PRIMARY KEY (id)
);

CREATE TABLE users (
    id BIGINT NOT NULL AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    tickets BIGINT,
    answers BIGINT,
    role ENUM('admin', 'user', 'moderator') NOT NULL DEFAULT 'user',

    PRIMARY KEY (id)
);
