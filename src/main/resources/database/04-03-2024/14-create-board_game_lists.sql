-- liquibase formatted sql
-- changeset Klizlo:14

create table `board_game_lists`(
    board_game_id BIGINT,
    board_game_list_id BIGINT
);

alter table `board_game_lists`
    add constraint board_game_list_fk1
    foreign key (board_game_id) references board_game(id);

alter table `board_game_lists`
    add constraint board_game_list_fk2
        foreign key (board_game_list_id) references board_game_list(id);