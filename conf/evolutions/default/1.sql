# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table buys_by_user (
  user_name                 varchar(255),
  item_name                 varchar(255),
  quantity                  integer)
;

create table item (
  id                        integer not null,
  name                      varchar(255),
  description               varchar(255),
  quantity                  integer,
  constraint pk_item primary key (id))
;

create table user_name (
  user_name                 varchar(255) not null,
  pass                      varchar(255),
  email                     varchar(255),
  constraint pk_user_name primary key (user_name))
;

create sequence item_seq;

create sequence user_name_seq;




# --- !Downs

drop table if exists buys_by_user cascade;

drop table if exists item cascade;

drop table if exists user_name cascade;

drop sequence if exists item_seq;

drop sequence if exists user_name_seq;

