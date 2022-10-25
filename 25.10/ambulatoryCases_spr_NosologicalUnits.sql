create table spr_NosologicalUnits
(
    ID         tinyint      not null
        primary key,
    NAME       varchar(200) not null,
    SHORT_NAME varchar(50)  not null
);

INSERT INTO ambulatoryCases.spr_NosologicalUnits (ID, NAME, SHORT_NAME) VALUES (1, 'Основное заболевание', 'Основной');
INSERT INTO ambulatoryCases.spr_NosologicalUnits (ID, NAME, SHORT_NAME) VALUES (2, 'Осложнение основного заболевания', 'Осложнение основного');
INSERT INTO ambulatoryCases.spr_NosologicalUnits (ID, NAME, SHORT_NAME) VALUES (3, 'Сопутствующее заболевание', 'Сопутствующий');
INSERT INTO ambulatoryCases.spr_NosologicalUnits (ID, NAME, SHORT_NAME) VALUES (4, 'Конкурирующее заболевание', 'Конкурирующий');
INSERT INTO ambulatoryCases.spr_NosologicalUnits (ID, NAME, SHORT_NAME) VALUES (5, 'Внешние причины заболеваемости и смертности', 'Внешняя причина');
INSERT INTO ambulatoryCases.spr_NosologicalUnits (ID, NAME, SHORT_NAME) VALUES (6, 'Фоновое заболевание', 'Фоновый');
INSERT INTO ambulatoryCases.spr_NosologicalUnits (ID, NAME, SHORT_NAME) VALUES (7, 'Осложнение сопутствующего заболевания', 'Осложнение сопутствующего');
