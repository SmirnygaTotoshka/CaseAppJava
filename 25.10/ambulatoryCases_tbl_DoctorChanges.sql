create table tbl_DoctorChanges
(
    ID         int auto_increment
        primary key,
    Doctor     int  not null,
    Date       date not null,
    StartTime  time not null,
    FinishTime time not null,
    constraint tbl_DoctorChanges_ID_uindex
        unique (ID),
    constraint tbl_DoctorChanges_tbl_Doctors_ID_fk
        foreign key (ID) references tbl_Doctors (ID)
);

INSERT INTO ambulatoryCases.tbl_DoctorChanges (ID, Doctor, Date, StartTime, FinishTime) VALUES (5, 9, '2022-10-18', '08:00:00', '14:00:00');
INSERT INTO ambulatoryCases.tbl_DoctorChanges (ID, Doctor, Date, StartTime, FinishTime) VALUES (7, 4, '2022-10-18', '08:00:00', '14:00:00');
INSERT INTO ambulatoryCases.tbl_DoctorChanges (ID, Doctor, Date, StartTime, FinishTime) VALUES (10, 4, '2022-10-21', '08:00:00', '14:00:00');
INSERT INTO ambulatoryCases.tbl_DoctorChanges (ID, Doctor, Date, StartTime, FinishTime) VALUES (11, 4, '2022-10-26', '08:00:00', '14:00:00');
INSERT INTO ambulatoryCases.tbl_DoctorChanges (ID, Doctor, Date, StartTime, FinishTime) VALUES (12, 5, '2022-10-26', '14:30:00', '20:30:00');
