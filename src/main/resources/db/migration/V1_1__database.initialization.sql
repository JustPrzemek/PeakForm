
CREATE TABLE users (
                       id SERIAL PRIMARY KEY,
                       email VARCHAR(255) UNIQUE NOT NULL,
                       password_hash VARCHAR(255) NOT NULL,
                       username VARCHAR(100) NOT NULL,
                       age INT CHECK (age > 0),
                       weight FLOAT CHECK (weight > 0),
                       height FLOAT CHECK (height > 0),
                       goal VARCHAR(50) CHECK (goal IN ('reduction', 'bulk', 'maintenance')),
                       created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);


CREATE TABLE meals (
                       id SERIAL PRIMARY KEY,
                       user_id BIGINT NOT NULL,
                       name VARCHAR(255) NOT NULL,
                       date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                       calories FLOAT CHECK (calories >= 0),
                       protein FLOAT CHECK (protein >= 0),
                       carbs FLOAT CHECK (carbs >= 0),
                       fat FLOAT CHECK (fat >= 0),
                       FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);


CREATE TABLE exercises (
                           id SERIAL PRIMARY KEY,
                           name VARCHAR(255) NOT NULL,
                           muscle_group VARCHAR(100) NOT NULL,
                           difficulty INT CHECK (difficulty BETWEEN 1 AND 5)
);


CREATE TABLE workout_plans (
                               id SERIAL PRIMARY KEY,
                               user_id BIGINT NOT NULL,
                               name VARCHAR(255) NOT NULL,
                               created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                               FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);


CREATE TABLE workout_plan_exercises (
                                        id SERIAL PRIMARY KEY,
                                        workout_plan_id BIGINT NOT NULL,
                                        exercise_id BIGINT NOT NULL,
                                        sets INT CHECK (sets > 0),
                                        reps INT CHECK (reps > 0),
                                        rest_time INT CHECK (rest_time >= 0),
                                        FOREIGN KEY (workout_plan_id) REFERENCES workout_plans(id) ON DELETE CASCADE,
                                        FOREIGN KEY (exercise_id) REFERENCES exercises(id) ON DELETE CASCADE
);


CREATE TABLE posts (
                       id SERIAL PRIMARY KEY,
                       user_id BIGINT NOT NULL,
                       content TEXT NOT NULL,
                       created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                       FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);


CREATE TABLE comments (
                          id SERIAL PRIMARY KEY,
                          post_id BIGINT NOT NULL,
                          user_id BIGINT NOT NULL,
                          content TEXT NOT NULL,
                          created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                          FOREIGN KEY (post_id) REFERENCES posts(id) ON DELETE CASCADE,
                          FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);
