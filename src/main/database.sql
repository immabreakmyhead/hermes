CREATE TABLE IF NOT EXISTS program (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255),
    description VARCHAR(255),
    benefits VARCHAR(255),
    costs VARCHAR(255),
    duration INT
);


CREATE TABLE IF NOT EXISTS phoneNumber (
    id SERIAL PRIMARY KEY,
    number VARCHAR(255),
    program_id INT,
    status VARCHAR(255),
    FOREIGN KEY (program_id) REFERENCES program(id)
);

CREATE TABLE IF NOT EXISTS bill (
    id SERIAL PRIMARY KEY,
    phoneNumber VARCHAR(20) NOT NULL,
    date DATE NOT NULL,
    totalAmount NUMERIC(10, 2) DEFAULT 0.0,
    isPaid BOOLEAN DEFAULT false
);

CREATE TABLE IF NOT EXISTS call (
    callID SERIAL PRIMARY KEY,
    callerNumber VARCHAR(20) NOT NULL,
    receiverNumber VARCHAR(20) NOT NULL,
    duration INTEGER,
    date DATE NOT NULL
);

CREATE TABLE IF NOT EXISTS "user" (
id SERIAL PRIMARY KEY,
username VARCHAR(255),
password VARCHAR(255),
name VARCHAR(255),
surname VARCHAR(255),
email VARCHAR(255),
role VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS seller (
    id SERIAL PRIMARY KEY,
    user_id INT,
    AM VARCHAR(255),
    FOREIGN KEY (user_id) REFERENCES "user"(id)
);

CREATE TABLE IF NOT EXISTS admin (
    id SERIAL PRIMARY KEY,
    user_id INT,
    FOREIGN KEY (user_id) REFERENCES "user"(id)
);

CREATE TABLE IF NOT EXISTS client (
    id SERIAL PRIMARY KEY,
    user_id INT,
    AM VARCHAR(255),
    phoneNumber VARCHAR(255),
    FOREIGN KEY (user_id) REFERENCES "user"(id)
);


