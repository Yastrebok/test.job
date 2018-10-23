CREATE DATABASE  IF NOT EXISTS test;

USE test;

CREATE TABLE image
(
  id          int auto_increment
    primary key,
  image       longblob     null,
  category    varchar(100) null,
  title       varchar(100) null,
  description varchar(100) null,
  constraint image_id_uindex
  unique (id)
)
  engine = InnoDB;
