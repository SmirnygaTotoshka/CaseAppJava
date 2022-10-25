create table tbl_Diagnoses
(
    ID              int auto_increment
        primary key,
    `Case`          int     not null,
    `Character`     tinyint not null,
    NosologicalUnit tinyint not null,
    VisitDate       date    not null,
    MKB             int     not null,
    Stage           tinyint not null,
    Diagnosis       text    not null,
    DiseaseType     tinyint not null,
    constraint tbl_Diagnoses_spr_DiagnosisStage_ID_fk
        foreign key (Stage) references spr_DiagnosisStage (ID),
    constraint tbl_Diagnoses_spr_DiseaseCharacters_ID_fk
        foreign key (`Character`) references spr_DiseaseCharacters (ID),
    constraint tbl_Diagnoses_spr_DiseaseType_ID_fk
        foreign key (DiseaseType) references spr_DiseaseType (ID),
    constraint tbl_Diagnoses_spr_MKB_ID_fk
        foreign key (MKB) references spr_MKB (ID),
    constraint tbl_Diagnoses_spr_NosologicalUnits_ID_fk
        foreign key (NosologicalUnit) references spr_NosologicalUnits (ID),
    constraint tbl_Diagnoses_tbl_Cases_ID_fk
        foreign key (`Case`) references tbl_Cases (ID)
);

