create table spr_FamilyStatus
(
    ID   tinyint      not null
        primary key,
    NAME varchar(200) not null
);

INSERT INTO ambulatoryCases.spr_FamilyStatus (ID, NAME) VALUES (1, 'Никогда не состоял(а) в браке');
INSERT INTO ambulatoryCases.spr_FamilyStatus (ID, NAME) VALUES (2, 'Состоит в зарегистрированном браке');
INSERT INTO ambulatoryCases.spr_FamilyStatus (ID, NAME) VALUES (3, 'Состоит в незарегистрированном браке');
INSERT INTO ambulatoryCases.spr_FamilyStatus (ID, NAME) VALUES (4, 'Вдовец (вдова)');
INSERT INTO ambulatoryCases.spr_FamilyStatus (ID, NAME) VALUES (5, 'Разведен(а) официально (развод зарегистрирован)');
INSERT INTO ambulatoryCases.spr_FamilyStatus (ID, NAME) VALUES (6, 'Разошелся(лась)');
