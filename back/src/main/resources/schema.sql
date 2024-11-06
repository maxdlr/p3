drop database if exists p3;
create database p3;
use p3;

CREATE SEQUENCE messages_seq START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE rentals_seq START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE roles_seq START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE users_seq START WITH 1 INCREMENT BY 1;

create or replace table roles
(
    id   int          not null
        primary key,
    name varchar(255) null
);

create or replace table users
(
    created_at datetime(6)  null,
    id         bigint       not null
        primary key,
    updated_at datetime(6)  null,
    email      varchar(255) null,
    name       varchar(255) null,
    password   varchar(255) null
);

create or replace table rentals
(
    id          int           not null
        primary key,
    price       float         not null,
    surface     float         not null,
    created_at  datetime(6)   null,
    owner_id    bigint        null,
    updated_at  datetime(6)   null,
    description varchar(2000) null,
    name        varchar(255)  null,
    picture     varchar(255)  null,
    constraint FKf462yhxa9vd3m2qdmcoixg1fv
        foreign key (owner_id) references users (id)
);

create or replace table messages
(
    id         int          not null
        primary key,
    rental_id  int          null,
    created_at datetime(6)  null,
    updated_at datetime(6)  null,
    user_id    bigint       null,
    message    varchar(255) null,
    constraint UKj3v4l57l24nd1rt1nm8c27l4g
        unique (user_id),
    constraint FK3ce1i9w1rtics9wjwj8y5y3md
        foreign key (rental_id) references rentals (id),
    constraint FKpsmh6clh3csorw43eaodlqvkn
        foreign key (user_id) references users (id)
);

create or replace table users_roles
(
    role_id int    not null,
    user_id bigint not null,
    constraint FK2o0jvgh89lemvvo17cbqvdxaa
        foreign key (user_id) references users (id),
    constraint FKj6m8fwv7oqv74fcehir1a9ffy
        foreign key (role_id) references roles (id)
);


