-- Insertion des utilisateurs
INSERT INTO users (email, name, password, created_at)
VALUES
    ('user1@example.com', 'John Doe', 'password1', CURRENT_TIMESTAMP),
    ('user2@example.com', 'Jane Smith', 'password2', CURRENT_TIMESTAMP),
    ('user3@example.com', 'Alice Johnson', 'password3', CURRENT_TIMESTAMP);

-- Insertion des sujets
INSERT INTO topics (name, description, created_at)
VALUES
    ('Topic 1', 'Description for Topic 1', CURRENT_TIMESTAMP),
    ('Topic 2', 'Description for Topic 2', CURRENT_TIMESTAMP),
    ('Topic 3', 'Description for Topic 3', CURRENT_TIMESTAMP);

-- Insertion des articles
INSERT INTO posts (title, content, subject_id, author_id, created_at)
VALUES
    ('Post 1', 'Content of Post 1', 1, 1, CURRENT_TIMESTAMP),
    ('Post 2', 'Content of Post 2', 2, 2, CURRENT_TIMESTAMP),
    ('Post 3', 'Content of Post 3', 3, 3, CURRENT_TIMESTAMP);

-- Insertion des commentaires
INSERT INTO comments (content, article_id, author_id, created_at)
VALUES
    ('Comment 1 on Post 1', 1, 2, CURRENT_TIMESTAMP),
    ('Comment 2 on Post 1', 1, 3, CURRENT_TIMESTAMP),
    ('Comment 1 on Post 2', 2, 1, CURRENT_TIMESTAMP),
    ('Comment 2 on Post 2', 2, 3, CURRENT_TIMESTAMP),
    ('Comment 1 on Post 3', 3, 1, CURRENT_TIMESTAMP),
    ('Comment 2 on Post 3', 3, 2, CURRENT_TIMESTAMP);

-- Insertion des abonnements
INSERT INTO subscriptions (user_id, subject_id, created_at)
VALUES
    (1, 1, CURRENT_TIMESTAMP),  -- User 1 s'abonne à Topic 1
    (1, 2, CURRENT_TIMESTAMP),  -- User 1 s'abonne à Topic 2
    (2, 1, CURRENT_TIMESTAMP),  -- User 2 s'abonne à Topic 1
    (3, 3, CURRENT_TIMESTAMP);  -- User 3 s'abonne à Topic 3
