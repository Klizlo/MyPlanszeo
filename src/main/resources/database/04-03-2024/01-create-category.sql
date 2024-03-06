--liquibase formatted sql
--changeset Klizlo:1

create table `category` (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name varchar(50) unique
);