drop table if exists notes;
create table notes (
    id bigint auto_increment primary key,
    content varchar(300),
    user_id bigint
);