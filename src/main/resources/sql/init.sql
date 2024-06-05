DROP TABLE IF EXISTS urls;
DROP TABLE IF EXISTS users;
CREATE TABLE users(id SERIAL PRIMARY KEY, login TEXT UNIQUE NOT NULL, password TEXT NOT NULL);
CREATE TABLE urls (short_url TEXT PRIMARY KEY, long_url TEXT, user_id INTEGER,
                    FOREIGN KEY(user_id) REFERENCES users(id), cnt INTEGER);
