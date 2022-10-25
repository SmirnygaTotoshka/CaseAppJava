create table spr_AidType
(
    ID   tinyint      not null
        primary key,
    NAME varchar(100) not null
);

INSERT INTO ambulatoryCases.spr_AidType (ID, NAME) VALUES (1, 'Первичная доврачебная медико-санитарная помощь');
INSERT INTO ambulatoryCases.spr_AidType (ID, NAME) VALUES (2, 'Первичная врачебная медико-санитарная помощь');
INSERT INTO ambulatoryCases.spr_AidType (ID, NAME) VALUES (3, 'Первичная специализированная медико-санитарная помощь');
INSERT INTO ambulatoryCases.spr_AidType (ID, NAME) VALUES (4, 'Специализированная медицинская помощь');
INSERT INTO ambulatoryCases.spr_AidType (ID, NAME) VALUES (5, 'Скорая медицинская помощь');
INSERT INTO ambulatoryCases.spr_AidType (ID, NAME) VALUES (6, 'Паллиативная медицинская помощь');
INSERT INTO ambulatoryCases.spr_AidType (ID, NAME) VALUES (8, 'Высокотехнологичная специализированная медицинская помощь');
INSERT INTO ambulatoryCases.spr_AidType (ID, NAME) VALUES (9, 'Скорая специализированная медицинская помощь');
INSERT INTO ambulatoryCases.spr_AidType (ID, NAME) VALUES (10, 'Первичная медико-санитарная помощь');
INSERT INTO ambulatoryCases.spr_AidType (ID, NAME) VALUES (11, 'Паллиативная первичная доврачебная медицинская помощь');
INSERT INTO ambulatoryCases.spr_AidType (ID, NAME) VALUES (12, 'Паллиативная первичная врачебная медицинская помощь');
INSERT INTO ambulatoryCases.spr_AidType (ID, NAME) VALUES (13, 'Паллиативная специализированная медицинская помощь');
