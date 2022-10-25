create table tbl_Users
(
    id            int auto_increment
        primary key,
    user_login    varchar(50) not null,
    user_password varchar(50) not null,
    role          tinyint     not null,
    constraint tbl_Users_id_uindex
        unique (id),
    constraint tbl_Users_user_login_uindex
        unique (user_login)
);

INSERT INTO ambulatoryCases.tbl_Users (id, user_login, user_password, role) VALUES (1, 'van.halen', 'pretty.woman', 3);
