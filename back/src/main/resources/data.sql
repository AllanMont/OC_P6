-- Insertion des utilisateurs
INSERT INTO users (email, name, password, created_at)
VALUES
    ('user1@example.com', 'John Doe', 'password1', CURRENT_TIMESTAMP),
    ('user2@example.com', 'Jane Smith', 'password2', CURRENT_TIMESTAMP),
    ('user3@example.com', 'Alice Johnson', 'password3', CURRENT_TIMESTAMP);

-- Insertion des sujets
INSERT INTO topics (name, description, created_at)
VALUES
    ('JavaScript', 'Discussion and questions about JavaScript', CURRENT_TIMESTAMP),
    ('Python', 'All about Python programming and its libraries', CURRENT_TIMESTAMP),
    ('DevOps', 'Topics related to DevOps practices and tools', CURRENT_TIMESTAMP),
    ('Web Development', 'Front-end and back-end web development discussions', CURRENT_TIMESTAMP),
    ('Machine Learning', 'Everything related to Machine Learning and AI', CURRENT_TIMESTAMP);

-- Insertion des posts
INSERT INTO posts (title, content, topic_id, author_id, created_at)
VALUES
    ('Understanding Closures in JavaScript', 'Closures are a powerful feature in JavaScript. Here’s a detailed explanation...', 1, 1, CURRENT_TIMESTAMP),
    ('Best Practices for Writing Python Code', 'Writing clean and efficient Python code can greatly improve your productivity. Let’s discuss...', 2, 2, CURRENT_TIMESTAMP),
    ('Introduction to Docker for DevOps', 'Docker is a crucial tool for modern DevOps practices. This post covers the basics...', 3, 3, CURRENT_TIMESTAMP),
    ('Responsive Web Design Tips', 'Creating responsive designs is essential for modern web development. Here are some tips...', 4, 2, CURRENT_TIMESTAMP),
    ('Getting Started with TensorFlow', 'TensorFlow is a powerful library for machine learning. This post introduces the basics...', 5, 3, CURRENT_TIMESTAMP);

-- Insertion des commentaires
INSERT INTO comments (content, post_id, author_id, created_at)
VALUES
    ('Great explanation on closures, very helpful!', 1, 2, CURRENT_TIMESTAMP),
    ('I always struggled with closures, this post cleared things up.', 1, 3, CURRENT_TIMESTAMP),
    ('Thanks for sharing these Python best practices!', 2, 1, CURRENT_TIMESTAMP),
    ('This is a very comprehensive guide on Docker.', 3, 2, CURRENT_TIMESTAMP),
    ('I found this article on responsive design very insightful.', 4, 1, CURRENT_TIMESTAMP),
    ('Excellent introduction to TensorFlow!', 5, 2, CURRENT_TIMESTAMP);

-- Insertion des abonnements
INSERT INTO subscriptions (user_id, topic_id, created_at)
VALUES
    (1, 1, CURRENT_TIMESTAMP),  -- User 1 s'abonne à JavaScript
    (1, 2, CURRENT_TIMESTAMP),  -- User 1 s'abonne à Python
    (2, 1, CURRENT_TIMESTAMP),  -- User 2 s'abonne à JavaScript
    (2, 4, CURRENT_TIMESTAMP),  -- User 2 s'abonne à Web Development
    (3, 3, CURRENT_TIMESTAMP),  -- User 3 s'abonne à DevOps
    (3, 5, CURRENT_TIMESTAMP);  -- User 3 s'abonne à Machine Learning
