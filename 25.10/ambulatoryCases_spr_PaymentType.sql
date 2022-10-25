create table spr_PaymentType
(
    ID   tinyint      not null
        primary key,
    NAME varchar(200) null
);

INSERT INTO ambulatoryCases.spr_PaymentType (ID, NAME) VALUES (1, 'Средства обязательного медицинского страхования');
INSERT INTO ambulatoryCases.spr_PaymentType (ID, NAME) VALUES (3, 'Средства добровольного медицинского страхования');
INSERT INTO ambulatoryCases.spr_PaymentType (ID, NAME) VALUES (4, 'Средства пациента');
INSERT INTO ambulatoryCases.spr_PaymentType (ID, NAME) VALUES (5, 'Средства третьих физических лиц');
INSERT INTO ambulatoryCases.spr_PaymentType (ID, NAME) VALUES (6, 'Средства третьих юридических лиц');
INSERT INTO ambulatoryCases.spr_PaymentType (ID, NAME) VALUES (8, 'Средства федерального бюджета');
INSERT INTO ambulatoryCases.spr_PaymentType (ID, NAME) VALUES (9, 'Средства регионального бюджета');
INSERT INTO ambulatoryCases.spr_PaymentType (ID, NAME) VALUES (10, 'Средства обязательного социального страхования');
INSERT INTO ambulatoryCases.spr_PaymentType (ID, NAME) VALUES (11, 'Средства бюджета медицинской организации');
INSERT INTO ambulatoryCases.spr_PaymentType (ID, NAME) VALUES (12, 'Средства федерального и регионального бюджета');
