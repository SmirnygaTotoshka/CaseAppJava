create table spr_Sex
(
    ID   tinyint     not null
        primary key,
    NAME varchar(30) not null
);

INSERT INTO ambulatoryCases.spr_Sex (ID, NAME) VALUES (1, 'Мужской');
INSERT INTO ambulatoryCases.spr_Sex (ID, NAME) VALUES (2, 'Женский');
INSERT INTO ambulatoryCases.spr_Sex (ID, NAME) VALUES (3, 'Неопределенный');
