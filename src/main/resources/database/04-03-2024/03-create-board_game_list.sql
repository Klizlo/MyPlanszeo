-- liquibase formatted sql
-- changeset Klizlo:3

create table `board_game_list` (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name varchar(50) not null
);
