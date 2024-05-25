DROP TABLE urls;
DROP TABLE users;
CREATE TABLE users(id INT PRIMARY KEY auto_increment, login TEXT, password TEXT);
CREATE TABLE urls (short_url TEXT PRIMARY KEY, long_url TEXT, user_id INTEGER,
                    FOREIGN KEY(user_id) REFERENCES users(id));