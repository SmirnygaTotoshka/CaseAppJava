create table spr_DiagnosisStage
(
    ID   tinyint     not null
        primary key,
    NAME varchar(50) not null
);

INSERT INTO ambulatoryCases.spr_DiagnosisStage (ID, NAME) VALUES (1, 'Предварительный диагноз');
INSERT INTO ambulatoryCases.spr_DiagnosisStage (ID, NAME) VALUES (2, 'Этапный клинический диагноз');
INSERT INTO ambulatoryCases.spr_DiagnosisStage (ID, NAME) VALUES (3, 'Заключительный клинический диагноз');
INSERT INTO ambulatoryCases.spr_DiagnosisStage (ID, NAME) VALUES (4, 'Патологоанатомический диагноз');
INSERT INTO ambulatoryCases.spr_DiagnosisStage (ID, NAME) VALUES (5, 'Судебно-медицинский диагноз');
