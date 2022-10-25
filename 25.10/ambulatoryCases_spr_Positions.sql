create table spr_Positions
(
    ID   int auto_increment
        primary key,
    NAME varchar(200) not null,
    constraint spr_Positions_ID_uindex
        unique (ID)
);

INSERT INTO ambulatoryCases.spr_Positions (ID, NAME) VALUES (1, 'Врач-терапевт участковый');
INSERT INTO ambulatoryCases.spr_Positions (ID, NAME) VALUES (2, 'Врач-педиатр участковый');
INSERT INTO ambulatoryCases.spr_Positions (ID, NAME) VALUES (3, 'Врач-хирург участковый');
INSERT INTO ambulatoryCases.spr_Positions (ID, NAME) VALUES (4, 'Заведующий отделением');
