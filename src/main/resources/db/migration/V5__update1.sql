CREATE TABLE users
(
    id         SERIAL PRIMARY KEY,
    username   VARCHAR   NOT NULL,
    password   VARCHAR   NOT NULL,
    email      VARCHAR   NOT NULL,
    role       VARCHAR   NOT NULL,
    birth      DATE      NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);
CREATE TABLE posts
(
    id         SERIAL PRIMARY KEY,
    title      VARCHAR   NOT NULL,
    body       TEXT      NOT NULL,
    user_id    INTEGER   NOT NULL,
    status     VARCHAR   NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

ALTER TABLE posts
    ADD CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES users (id);

-- index on user_id for faster queries
CREATE INDEX idx_posts_user_id ON posts (user_id);


INSERT INTO users (username, password, email, role, birth)
VALUES ('an', '1234566', 'john@example.com', 'admin', '1980-04-08'),
       ('david', '1234565', 'david@example.com', 'user', '1979-04-08'),
       ('bob', '1234576', 'bob@example.com', 'user', '1340-04-08'),
       ('alice', '1234866', 'alice@example.com', 'user', '1570-04-08');


INSERT INTO posts (title, body, user_id, status, created_at)
VALUES ('First Post', 'This is the content of the first post.', 1, 'published', CURRENT_TIMESTAMP),
       ('Second Post', 'This is the content of the second post.', 2, 'draft', CURRENT_TIMESTAMP),
       ('Third Post', 'This is the content of the third post.', 1, 'published', CURRENT_TIMESTAMP),
       ('Fourth Post', 'This is the content of the fourth post.', 3, 'published', CURRENT_TIMESTAMP),
       ('Fifth Post', 'This is the content of the fifth post.', 3, 'draft', CURRENT_TIMESTAMP),
       ('Sixth Post', 'This is the content of the sixth post.', 4, 'published', CURRENT_TIMESTAMP);