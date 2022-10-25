create table spr_Purpose
(
    ID   tinyint     not null
        primary key,
    NAME varchar(20) not null
);

INSERT INTO ambulatoryCases.spr_Purpose (ID, NAME) VALUES (1, 'заболевание');
INSERT INTO ambulatoryCases.spr_Purpose (ID, NAME) VALUES (2, 'профосмотр');
INSERT INTO ambulatoryCases.spr_Purpose (ID, NAME) VALUES (3, 'патронаж');
INSERT INTO ambulatoryCases.spr_Purpose (ID, NAME) VALUES (4, 'другое');
