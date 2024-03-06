--liquibase formatted sql
--changeset Klizlo:2

create table `board_game` (
    id BIGINT AUTO_INCREMENT primary key,
    name varchar(50) not null,
    age_restriction enum('3+', '7+', '12+', '16+'),
    description text,
    producer varchar(50) not null,
    min_num_of_players numeric,
    max_num_of_players numeric,
    category_id BIGINT NOT NULL,
    CONSTRAINT board_game_category_fk FOREIGN KEY (category_id) references `category`(id)
);