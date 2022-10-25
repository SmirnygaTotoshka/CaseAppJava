create table spr_VisitPlace
(
    ID   tinyint      not null
        primary key,
    NAME varchar(200) null
);

INSERT INTO ambulatoryCases.spr_VisitPlace (ID, NAME) VALUES (1, 'Амбулаторно-поликлиническое учреждение');
INSERT INTO ambulatoryCases.spr_VisitPlace (ID, NAME) VALUES (2, 'На дому');
INSERT INTO ambulatoryCases.spr_VisitPlace (ID, NAME) VALUES (3, 'По месту вызова');
INSERT INTO ambulatoryCases.spr_VisitPlace (ID, NAME) VALUES (4, 'Центр здоровья');
INSERT INTO ambulatoryCases.spr_VisitPlace (ID, NAME) VALUES (5, 'Дневной стационар при поликлинике');
INSERT INTO ambulatoryCases.spr_VisitPlace (ID, NAME) VALUES (6, 'Дневной стационар при стационаре');
INSERT INTO ambulatoryCases.spr_VisitPlace (ID, NAME) VALUES (7, 'Стационар');
INSERT INTO ambulatoryCases.spr_VisitPlace (ID, NAME) VALUES (8, 'Дистанционно');
INSERT INTO ambulatoryCases.spr_VisitPlace (ID, NAME) VALUES (9, 'Иные медицинские организации');
INSERT INTO ambulatoryCases.spr_VisitPlace (ID, NAME) VALUES (10, 'Организация социального обслуживания, оказывающая социальные услуги в стационарной форме социального обслуживания');
INSERT INTO ambulatoryCases.spr_VisitPlace (ID, NAME) VALUES (11, 'Исправительное учреждение');
INSERT INTO ambulatoryCases.spr_VisitPlace (ID, NAME) VALUES (12, 'Мобильная медицинская бригада');
