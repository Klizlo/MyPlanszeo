--liquibase formatted sql
--changeset Klizlo:1

create table `users` (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    email varchar(50) not null unique,
    password varchar not null
);