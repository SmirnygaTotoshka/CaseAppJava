create table tbl_ManifestTypes
(
    ID          int auto_increment
        primary key,
    Intolerance int     not null,
    Type        tinyint not null,
    constraint tbl_ManifestTypes_spr_ClinLooks_ID_fk
        foreign key (Type) references spr_ClinLooks (ID),
    constraint tbl_ManifestTypes_tbl_Intolerances_ID_fk
        foreign key (Intolerance) references tbl_Intolerances (ID)
);

