--liquibase formatted sql
--changeset Klizlo:11

create table `category` (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name varchar(50) unique
);