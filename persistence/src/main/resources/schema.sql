CREATE TABLE engines
(
    id    INT PRIMARY KEY AUTO_INCREMENT,
    type  VARCHAR(100)  NOT NULL,
    power DECIMAL(5, 1) NOT NULL
);


CREATE TABLE wheels
(
    id    INT PRIMARY KEY AUTO_INCREMENT,
    type  VARCHAR(100) NOT NULL,
    model VARCHAR(100) NOT NULL,
    size  INT          NOT NULL
);


CREATE TABLE car_bodies
(
    id         INT PRIMARY KEY AUTO_INCREMENT,
    color      VARCHAR(100) NOT NULL,
    type       VARCHAR(50)  NOT NULL,
    components VARCHAR(100) NOT NULL
);


CREATE TABLE cars
(
    id          INT PRIMARY KEY AUTO_INCREMENT,
    model       VARCHAR(100)   NOT NULL,
    price       DECIMAL(10, 2) NOT NULL,
    mileage     DECIMAL(10, 0) NOT NULL,
    engine_id   INT            NOT NULL,
    car_body_id INT            NOT NULL,
    wheel_id    INT            NOT NULL,
    FOREIGN KEY (engine_id) REFERENCES engines (id),
    FOREIGN KEY (car_body_id) REFERENCES car_bodies (id),
    FOREIGN KEY (wheel_id) REFERENCES wheels (id)
);


CREATE TABLE users
(
    id       integer primary key auto_increment,
    username varchar(50)  not null,
    email    varchar(50)  not null,
    password varchar(255) not null,
    role     varchar(50)  not null,
    enabled  boolean      not null
);