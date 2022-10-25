create table spr_Result
(
    ID   tinyint      not null
        primary key,
    NAME varchar(200) not null
);

INSERT INTO ambulatoryCases.spr_Result (ID, NAME) VALUES (1, 'Выздоровление');
INSERT INTO ambulatoryCases.spr_Result (ID, NAME) VALUES (2, 'Улучшение');
INSERT INTO ambulatoryCases.spr_Result (ID, NAME) VALUES (3, 'Без изменения');
INSERT INTO ambulatoryCases.spr_Result (ID, NAME) VALUES (4, 'Ухудшение');
INSERT INTO ambulatoryCases.spr_Result (ID, NAME) VALUES (5, 'Здоров');
INSERT INTO ambulatoryCases.spr_Result (ID, NAME) VALUES (6, 'Летальный исход');
INSERT INTO ambulatoryCases.spr_Result (ID, NAME) VALUES (7, 'Дано направление на госпитализацию');
INSERT INTO ambulatoryCases.spr_Result (ID, NAME) VALUES (8, 'Дано направление на госпитализацию по экстренным показаниям');
INSERT INTO ambulatoryCases.spr_Result (ID, NAME) VALUES (9, 'Дано направление в дневной стационар');
INSERT INTO ambulatoryCases.spr_Result (ID, NAME) VALUES (10, 'Дано направление на обследование');
INSERT INTO ambulatoryCases.spr_Result (ID, NAME) VALUES (11, 'Дано направление на консультацию');
INSERT INTO ambulatoryCases.spr_Result (ID, NAME) VALUES (12, 'Дано направление на санаторно-курортное лечение');
INSERT INTO ambulatoryCases.spr_Result (ID, NAME) VALUES (13, 'Дано направление на медицинскую реабилитацию');
INSERT INTO ambulatoryCases.spr_Result (ID, NAME) VALUES (14, 'Отказ от прохождения медицинских обследований при диспансеризации или медицинском осмотре');
