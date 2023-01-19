CREATE TABLE tickets (
    id BIGINT NOT NULL AUTO_INCREMENT,
    title VARCHAR(255) NOT NULL,
    description TEXT NOT NULL,
    created_at DATETIME NOT NULL,
    updated_at DATETIME DEFAULT NULL,
    closed_at DATETIME DEFAULT NULL,
    deleted_at DATETIME DEFAULT NULL,
    cloded_by BIGINT DEFAULT NULL,
    deleted_by BIGINT DEFAULT NULL,
    deleted boolean DEFAULT false,
    ticketCategory ENUM('BUG', 'FEATURE', 'SUPPORT', 'OTHER') NOT NULL,
    status ENUM('OPEN', 'IN_PROGRESS', 'SOLVED' , 'CLOSED') NOT NULL DEFAULT 'OPEN',
    priority ENUM('LOW', 'NORMAL', 'HIGH') NOT NULL DEFAULT 'NORMAL',
    user BIGINT NOT NULL,
    answers BIGINT,

    PRIMARY KEY (id)
);

CREATE TABLE answers (
    id BIGINT NOT NULL AUTO_INCREMENT,
    description TEXT NOT NULL,
    ticket BIGINT NOT NULL,
    createdAt DATETIME NOT NULL,
    updatedAt DATETIME DEFAULT NULL,
    deletedAt DATETIME DEFAULT NULL,
    deletedBy BIGINT DEFAULT NULL,
    deleted boolean DEFAULT false,
    user BIGINT NOT NULL,
    solution boolean NOT NULL DEFAULT false,

    PRIMARY KEY (id)
);

CREATE TABLE users (
    id BIGINT NOT NULL AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    tickets BIGINT,
    answers BIGINT,
    roles BIGINT,

    PRIMARY KEY (id)
);

CREATE TABLE roles (
    id BIGINT NOT NULL AUTO_INCREMENT,
    user_roles ENUM('ROLE_ADMIN', 'ROLE_USER', 'ROLE_MODERADOR') NOT NULL DEFAULT 'ROLE_USER',

    PRIMARY KEY (id)
);
