-- liquibase formatted sql
-- changeset Klizlo:4

create table `board_game_list` (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name varchar(50) not null,
    description text,
    user_id BIGINT not null,
    CONSTRAINT bgl_user_fk FOREIGN KEY (user_id) REFERENCES `users`(id)
);
