create table spr_DiseaseCharacters
(
    ID   tinyint     not null
        primary key,
    NAME varchar(50) not null
);

INSERT INTO ambulatoryCases.spr_DiseaseCharacters (ID, NAME) VALUES (10, 'Характер процесса');
INSERT INTO ambulatoryCases.spr_DiseaseCharacters (ID, NAME) VALUES (11, 'Молниеносный');
INSERT INTO ambulatoryCases.spr_DiseaseCharacters (ID, NAME) VALUES (12, 'Острый');
INSERT INTO ambulatoryCases.spr_DiseaseCharacters (ID, NAME) VALUES (13, 'Подострый');
INSERT INTO ambulatoryCases.spr_DiseaseCharacters (ID, NAME) VALUES (14, 'Хронический');
INSERT INTO ambulatoryCases.spr_DiseaseCharacters (ID, NAME) VALUES (20, 'Цикличность процесса');
INSERT INTO ambulatoryCases.spr_DiseaseCharacters (ID, NAME) VALUES (21, 'Непрерывный');
INSERT INTO ambulatoryCases.spr_DiseaseCharacters (ID, NAME) VALUES (22, 'Рецидивирующий');
INSERT INTO ambulatoryCases.spr_DiseaseCharacters (ID, NAME) VALUES (23, 'Интермиттирующий');
INSERT INTO ambulatoryCases.spr_DiseaseCharacters (ID, NAME) VALUES (30, 'Динамика процесса');
INSERT INTO ambulatoryCases.spr_DiseaseCharacters (ID, NAME) VALUES (31, 'Регрессирующий');
INSERT INTO ambulatoryCases.spr_DiseaseCharacters (ID, NAME) VALUES (32, 'Стабильный');
INSERT INTO ambulatoryCases.spr_DiseaseCharacters (ID, NAME) VALUES (33, 'Прогрессирующий');
INSERT INTO ambulatoryCases.spr_DiseaseCharacters (ID, NAME) VALUES (40, 'Активность процесса');
INSERT INTO ambulatoryCases.spr_DiseaseCharacters (ID, NAME) VALUES (41, 'Неактивный');
INSERT INTO ambulatoryCases.spr_DiseaseCharacters (ID, NAME) VALUES (42, 'Активность минимальная');
INSERT INTO ambulatoryCases.spr_DiseaseCharacters (ID, NAME) VALUES (43, 'Активность умеренная');
INSERT INTO ambulatoryCases.spr_DiseaseCharacters (ID, NAME) VALUES (44, 'Активность высокая');
INSERT INTO ambulatoryCases.spr_DiseaseCharacters (ID, NAME) VALUES (50, 'Фаза процесса');
INSERT INTO ambulatoryCases.spr_DiseaseCharacters (ID, NAME) VALUES (51, 'Ремиссия');
INSERT INTO ambulatoryCases.spr_DiseaseCharacters (ID, NAME) VALUES (52, 'Ремиссия полная');
INSERT INTO ambulatoryCases.spr_DiseaseCharacters (ID, NAME) VALUES (53, 'Ремиссия неполная');
INSERT INTO ambulatoryCases.spr_DiseaseCharacters (ID, NAME) VALUES (54, 'Обострение');
INSERT INTO ambulatoryCases.spr_DiseaseCharacters (ID, NAME) VALUES (55, 'Рецидив');
INSERT INTO ambulatoryCases.spr_DiseaseCharacters (ID, NAME) VALUES (60, 'Степень компенсации процесса');
INSERT INTO ambulatoryCases.spr_DiseaseCharacters (ID, NAME) VALUES (61, 'Компенсированный');
INSERT INTO ambulatoryCases.spr_DiseaseCharacters (ID, NAME) VALUES (62, 'Субкомпенсированный');
INSERT INTO ambulatoryCases.spr_DiseaseCharacters (ID, NAME) VALUES (63, 'Декомпенсированный');
