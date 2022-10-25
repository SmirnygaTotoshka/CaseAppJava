create table spr_DiseaseType
(
    ID   tinyint     not null
        primary key,
    NAME varchar(60) not null
);

INSERT INTO ambulatoryCases.spr_DiseaseType (ID, NAME) VALUES (1, 'Впервые в жизни установленное хроническое');
INSERT INTO ambulatoryCases.spr_DiseaseType (ID, NAME) VALUES (2, 'Ранее установленное хроническое');
INSERT INTO ambulatoryCases.spr_DiseaseType (ID, NAME) VALUES (3, 'Острое');
