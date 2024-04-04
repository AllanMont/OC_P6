DROP DATABASE oc_p6;
CREATE DATABASE oc_p6;
USE oc_p6;

CREATE TABLE `users` (
    id INT AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(255) NOT NULL UNIQUE,
    name VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    created_at TIMESTAMP,
    updated_at TIMESTAMP
);

CREATE TABLE `articles` (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    content TEXT NOT NULL,
    subject_id INT,
    author_id INT,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP,
    FOREIGN KEY (subject_id) REFERENCES Subjects(id),
    FOREIGN KEY (author_id) REFERENCES Users(id)
);

CREATE TABLE `commments` (
    id INT AUTO_INCREMENT PRIMARY KEY,
    content TEXT NOT NULL,
    article_id INT,
    author_id INT,
    created_at TIMESTAMP,
    updated_at TIMESTAMP,
    FOREIGN KEY (article_id) REFERENCES Articles(id),
    FOREIGN KEY (author_id) REFERENCES Users(id)
);

CREATE TABLE `subjects` (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description VARCHAR(255) NOT NULL,
    created_at TIMESTAMP,
    updated_at TIMESTAMP
);

CREATE TABLE `subscriptions` (
    user_id INT,
    subject_id INT,
    created_at TIMESTAMP NOT NULL,
    PRIMARY KEY (user_id, subject_id),
    FOREIGN KEY (user_id) REFERENCES USERS(id),
    FOREIGN KEY (subject_id) REFERENCES SUBJECTS(id)
);