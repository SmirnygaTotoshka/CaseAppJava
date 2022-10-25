create table spr_Departments
(
    ID   int auto_increment
        primary key,
    NAME varchar(200) not null,
    constraint spr_Departments_ID_uindex
        unique (ID)
);

INSERT INTO ambulatoryCases.spr_Departments (ID, NAME) VALUES (1, 'Терапевтическое');
INSERT INTO ambulatoryCases.spr_Departments (ID, NAME) VALUES (3, 'Хирургическое');
