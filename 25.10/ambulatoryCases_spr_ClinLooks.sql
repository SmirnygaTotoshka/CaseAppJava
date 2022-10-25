create table spr_ClinLooks
(
    ID   tinyint      not null
        primary key,
    NAME varchar(100) not null
);

INSERT INTO ambulatoryCases.spr_ClinLooks (ID, NAME) VALUES (1, 'Анафилактический шок');
INSERT INTO ambulatoryCases.spr_ClinLooks (ID, NAME) VALUES (2, 'Ангионевротический отек');
INSERT INTO ambulatoryCases.spr_ClinLooks (ID, NAME) VALUES (3, 'Бронхообструктивный синдром');
INSERT INTO ambulatoryCases.spr_ClinLooks (ID, NAME) VALUES (4, 'Бронхоспазм');
INSERT INTO ambulatoryCases.spr_ClinLooks (ID, NAME) VALUES (5, 'Экзема');
INSERT INTO ambulatoryCases.spr_ClinLooks (ID, NAME) VALUES (6, 'Контактный дерматит');
INSERT INTO ambulatoryCases.spr_ClinLooks (ID, NAME) VALUES (7, 'Крапивница');
INSERT INTO ambulatoryCases.spr_ClinLooks (ID, NAME) VALUES (8, 'Эритема');
INSERT INTO ambulatoryCases.spr_ClinLooks (ID, NAME) VALUES (9, 'Конъюнктивит');
INSERT INTO ambulatoryCases.spr_ClinLooks (ID, NAME) VALUES (10, 'Ринит');
INSERT INTO ambulatoryCases.spr_ClinLooks (ID, NAME) VALUES (11, 'Поллиноз');
INSERT INTO ambulatoryCases.spr_ClinLooks (ID, NAME) VALUES (12, 'Зуд');
INSERT INTO ambulatoryCases.spr_ClinLooks (ID, NAME) VALUES (13, 'Васкулит');
INSERT INTO ambulatoryCases.spr_ClinLooks (ID, NAME) VALUES (14, 'Лихорадка');
INSERT INTO ambulatoryCases.spr_ClinLooks (ID, NAME) VALUES (15, 'Другие клинические проявления патологических реакций');
