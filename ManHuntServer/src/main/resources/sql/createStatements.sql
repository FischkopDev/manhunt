CREATE TABLE IF NOT EXISTS player(player_id INT NOT NULL AUTO_INCREMENT,
                    username TEXT,
                    role TEXT,
                    device_id TEXT,
                    accepted VARCHAR(10),
                    PRIMARY KEY(player_id));

CREATE TABLE IF NOT EXISTS player_position(lat DOUBLE,
                    lon DOUBLE,
                    time_stamp TIMESTAMP DEFAULT NOW(),
                    player_id INT,
                    FOREIGN KEY(player_id) REFERENCES player(player_id));