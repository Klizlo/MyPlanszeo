--liquibase formatted sql
--changeset Klizlo:22

insert into `board_game`(name, producer, age_restriction, min_num_of_players, max_num_of_players, category_id)
    values ('Mansion of Madness', 'Fantasy Flight Games', '12+', 1, 4, 1);

