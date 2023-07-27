CREATE TABLE roles (
    id serial PRIMARY KEY,
    role_name varchar(255)
);

CREATE TABLE users (
    id serial PRIMARY KEY,
    address varchar(255) NOT NULL,
    email varchar(255) NOT NULL,
    full_name varchar(255) NOT NULL,
    note varchar(255),
    password varchar(255) NOT NULL,
    phone_number varchar(255) NOT NULL,
    status int NOT NULL,
    username varchar(255) NOT NULL,
    created timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    role_id int NOT NULL,
    FOREIGN KEY (role_id) REFERENCES roles (id)
);

CREATE TABLE donations (
    id serial PRIMARY KEY,
    code varchar(255) NOT NULL,
    created TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    description varchar(255) NOT NULL,
    start_date date NOT NULL,
    end_date date NOT NULL,
    name varchar(255) NOT NULL,
    money int NOT NULL DEFAULT 0,
    organization_name varchar(255) NOT NULL,
    phone_number varchar(255) NOT NULL,
    status int NOT NULL
);

CREATE TABLE user_donations (
    id serial PRIMARY KEY,
    created date NOT NULL DEFAULT CURRENT_DATE,
    money int NOT NULL,
    name varchar(255) NOT NULL,
    status INTEGER NOT NULL,
    text varchar(255),
    donation_id int REFERENCES donations (id) ON DELETE CASCADE,
    user_id int REFERENCES users (id) ON DELETE CASCADE
);

INSERT INTO roles (id, role_name) VALUES (1, 'ADMIN'), (2, 'USER');

INSERT INTO users (address, email, full_name, password, phone_number, status, username, role_id)
VALUES
    ('12 Quang Trung', 'admin@gmail.com', 'Nguyễn Huy', '113', '0325164899', 1, 'huynguyen', 1),
    ('21 Nguyễn Huệ', 'ngoc@gmail.com', 'Minh Ngọc', '115', '0328464852', 0, 'minhngoc', 2);

INSERT INTO donations (code, description, start_date, end_date, name, organization_name, phone_number, status)
VALUES
    ('IDE113', 'Ủng hộ trẻ em miền núi', '2023-06-06', '2023-06-09', 'Hỗ trợ trẻ em miền núi', 'Hội từ thiện', '0345671148', 1);

INSERT INTO user_donations (money, name, status, text, donation_id, user_id)
VALUES (1000000, 'ngoc', 1, 'Goodluck', 1, 2);


