create table tbl_Intolerances
(
    ID            int auto_increment
        primary key,
    Patient       int     not null,
    Substance     int     not null,
    Date          date    not null,
    KindReactions tinyint not null,
    constraint tbl_Intolerances_spr_ActiveSubstances_ID_fk
        foreign key (Substance) references spr_ActiveSubstances (ID),
    constraint tbl_Intolerances_spr_PatologicalReactions_ID_fk
        foreign key (KindReactions) references spr_PatologicalReactions (ID),
    constraint tbl_Intolerances_tbl_Patients_ID_fk
        foreign key (Patient) references tbl_Patients (ID)
);

