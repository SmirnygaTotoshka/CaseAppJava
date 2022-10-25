create table spr_PatologicalReactions
(
    ID   tinyint      not null
        primary key,
    NAME varchar(200) not null
);

INSERT INTO ambulatoryCases.spr_PatologicalReactions (ID, NAME) VALUES (10, 'Атопическая гиперчувствительность');
INSERT INTO ambulatoryCases.spr_PatologicalReactions (ID, NAME) VALUES (20, 'Аллергические реакции');
INSERT INTO ambulatoryCases.spr_PatologicalReactions (ID, NAME) VALUES (21, 'Лекарственная аллергия');
INSERT INTO ambulatoryCases.spr_PatologicalReactions (ID, NAME) VALUES (22, 'Пищевая аллергия');
INSERT INTO ambulatoryCases.spr_PatologicalReactions (ID, NAME) VALUES (23, 'Инсектная аллергия');
INSERT INTO ambulatoryCases.spr_PatologicalReactions (ID, NAME) VALUES (24, 'Другие аллергии');
INSERT INTO ambulatoryCases.spr_PatologicalReactions (ID, NAME) VALUES (30, 'Индивидуальная непереносимость');
INSERT INTO ambulatoryCases.spr_PatologicalReactions (ID, NAME) VALUES (31, 'Лекарственная непереносимость');
INSERT INTO ambulatoryCases.spr_PatologicalReactions (ID, NAME) VALUES (32, 'Пищевая непереносимость');
INSERT INTO ambulatoryCases.spr_PatologicalReactions (ID, NAME) VALUES (33, 'Другие виды непереносимости');
