create table tbl_Schedule
(
    ID       bigint auto_increment
        primary key,
    Patient  int default -100 null,
    Doctor   int              not null,
    `Change` int              not null,
    Time     time             not null,
    constraint tbl_Schedule_ID_uindex
        unique (ID),
    constraint tbl_Schedule_tbl_DoctorChanges_ID_fk
        foreign key (`Change`) references tbl_DoctorChanges (ID),
    constraint tbl_Schedule_tbl_Doctors_ID_fk
        foreign key (Doctor) references tbl_Doctors (ID),
    constraint tbl_Schedule_tbl_Patients_ID_fk
        foreign key (Patient) references tbl_Patients (ID)
);

INSERT INTO ambulatoryCases.tbl_Schedule (ID, Patient, Doctor, `Change`, Time) VALUES (27, -100, 9, 5, '08:00:00');
INSERT INTO ambulatoryCases.tbl_Schedule (ID, Patient, Doctor, `Change`, Time) VALUES (28, -100, 9, 5, '08:15:00');
INSERT INTO ambulatoryCases.tbl_Schedule (ID, Patient, Doctor, `Change`, Time) VALUES (29, -100, 9, 5, '08:30:00');
INSERT INTO ambulatoryCases.tbl_Schedule (ID, Patient, Doctor, `Change`, Time) VALUES (30, -100, 9, 5, '08:45:00');
INSERT INTO ambulatoryCases.tbl_Schedule (ID, Patient, Doctor, `Change`, Time) VALUES (31, -100, 9, 5, '09:00:00');
INSERT INTO ambulatoryCases.tbl_Schedule (ID, Patient, Doctor, `Change`, Time) VALUES (32, -100, 9, 5, '09:15:00');
INSERT INTO ambulatoryCases.tbl_Schedule (ID, Patient, Doctor, `Change`, Time) VALUES (33, -100, 9, 5, '09:30:00');
INSERT INTO ambulatoryCases.tbl_Schedule (ID, Patient, Doctor, `Change`, Time) VALUES (34, -100, 9, 5, '09:45:00');
INSERT INTO ambulatoryCases.tbl_Schedule (ID, Patient, Doctor, `Change`, Time) VALUES (35, -100, 9, 5, '10:00:00');
INSERT INTO ambulatoryCases.tbl_Schedule (ID, Patient, Doctor, `Change`, Time) VALUES (36, -100, 9, 5, '10:15:00');
INSERT INTO ambulatoryCases.tbl_Schedule (ID, Patient, Doctor, `Change`, Time) VALUES (37, -100, 9, 5, '10:30:00');
INSERT INTO ambulatoryCases.tbl_Schedule (ID, Patient, Doctor, `Change`, Time) VALUES (38, -100, 9, 5, '10:45:00');
INSERT INTO ambulatoryCases.tbl_Schedule (ID, Patient, Doctor, `Change`, Time) VALUES (39, -100, 9, 5, '11:00:00');
INSERT INTO ambulatoryCases.tbl_Schedule (ID, Patient, Doctor, `Change`, Time) VALUES (40, -100, 9, 5, '11:15:00');
INSERT INTO ambulatoryCases.tbl_Schedule (ID, Patient, Doctor, `Change`, Time) VALUES (41, -100, 9, 5, '11:30:00');
INSERT INTO ambulatoryCases.tbl_Schedule (ID, Patient, Doctor, `Change`, Time) VALUES (42, -100, 9, 5, '11:45:00');
INSERT INTO ambulatoryCases.tbl_Schedule (ID, Patient, Doctor, `Change`, Time) VALUES (43, -100, 9, 5, '12:00:00');
INSERT INTO ambulatoryCases.tbl_Schedule (ID, Patient, Doctor, `Change`, Time) VALUES (44, -100, 9, 5, '12:15:00');
INSERT INTO ambulatoryCases.tbl_Schedule (ID, Patient, Doctor, `Change`, Time) VALUES (45, -100, 9, 5, '12:30:00');
INSERT INTO ambulatoryCases.tbl_Schedule (ID, Patient, Doctor, `Change`, Time) VALUES (46, -100, 9, 5, '12:45:00');
INSERT INTO ambulatoryCases.tbl_Schedule (ID, Patient, Doctor, `Change`, Time) VALUES (47, -100, 9, 5, '13:00:00');
INSERT INTO ambulatoryCases.tbl_Schedule (ID, Patient, Doctor, `Change`, Time) VALUES (48, -100, 9, 5, '13:15:00');
INSERT INTO ambulatoryCases.tbl_Schedule (ID, Patient, Doctor, `Change`, Time) VALUES (49, -100, 9, 5, '13:30:00');
INSERT INTO ambulatoryCases.tbl_Schedule (ID, Patient, Doctor, `Change`, Time) VALUES (50, -100, 9, 5, '13:45:00');
INSERT INTO ambulatoryCases.tbl_Schedule (ID, Patient, Doctor, `Change`, Time) VALUES (75, -100, 4, 7, '08:00:00');
INSERT INTO ambulatoryCases.tbl_Schedule (ID, Patient, Doctor, `Change`, Time) VALUES (76, -100, 4, 7, '08:15:00');
INSERT INTO ambulatoryCases.tbl_Schedule (ID, Patient, Doctor, `Change`, Time) VALUES (77, -100, 4, 7, '08:30:00');
INSERT INTO ambulatoryCases.tbl_Schedule (ID, Patient, Doctor, `Change`, Time) VALUES (78, -100, 4, 7, '08:45:00');
INSERT INTO ambulatoryCases.tbl_Schedule (ID, Patient, Doctor, `Change`, Time) VALUES (79, -100, 4, 7, '09:00:00');
INSERT INTO ambulatoryCases.tbl_Schedule (ID, Patient, Doctor, `Change`, Time) VALUES (80, -100, 4, 7, '09:15:00');
INSERT INTO ambulatoryCases.tbl_Schedule (ID, Patient, Doctor, `Change`, Time) VALUES (81, -100, 4, 7, '09:30:00');
INSERT INTO ambulatoryCases.tbl_Schedule (ID, Patient, Doctor, `Change`, Time) VALUES (82, -100, 4, 7, '09:45:00');
INSERT INTO ambulatoryCases.tbl_Schedule (ID, Patient, Doctor, `Change`, Time) VALUES (83, -100, 4, 7, '10:00:00');
INSERT INTO ambulatoryCases.tbl_Schedule (ID, Patient, Doctor, `Change`, Time) VALUES (84, -100, 4, 7, '10:15:00');
INSERT INTO ambulatoryCases.tbl_Schedule (ID, Patient, Doctor, `Change`, Time) VALUES (85, -100, 4, 7, '10:30:00');
INSERT INTO ambulatoryCases.tbl_Schedule (ID, Patient, Doctor, `Change`, Time) VALUES (86, -100, 4, 7, '10:45:00');
INSERT INTO ambulatoryCases.tbl_Schedule (ID, Patient, Doctor, `Change`, Time) VALUES (87, -100, 4, 7, '11:00:00');
INSERT INTO ambulatoryCases.tbl_Schedule (ID, Patient, Doctor, `Change`, Time) VALUES (88, -100, 4, 7, '11:15:00');
INSERT INTO ambulatoryCases.tbl_Schedule (ID, Patient, Doctor, `Change`, Time) VALUES (89, -100, 4, 7, '11:30:00');
INSERT INTO ambulatoryCases.tbl_Schedule (ID, Patient, Doctor, `Change`, Time) VALUES (90, -100, 4, 7, '11:45:00');
INSERT INTO ambulatoryCases.tbl_Schedule (ID, Patient, Doctor, `Change`, Time) VALUES (91, -100, 4, 7, '12:00:00');
INSERT INTO ambulatoryCases.tbl_Schedule (ID, Patient, Doctor, `Change`, Time) VALUES (92, -100, 4, 7, '12:15:00');
INSERT INTO ambulatoryCases.tbl_Schedule (ID, Patient, Doctor, `Change`, Time) VALUES (93, -100, 4, 7, '12:30:00');
INSERT INTO ambulatoryCases.tbl_Schedule (ID, Patient, Doctor, `Change`, Time) VALUES (94, -100, 4, 7, '12:45:00');
INSERT INTO ambulatoryCases.tbl_Schedule (ID, Patient, Doctor, `Change`, Time) VALUES (95, -100, 4, 7, '13:00:00');
INSERT INTO ambulatoryCases.tbl_Schedule (ID, Patient, Doctor, `Change`, Time) VALUES (96, -100, 4, 7, '13:15:00');
INSERT INTO ambulatoryCases.tbl_Schedule (ID, Patient, Doctor, `Change`, Time) VALUES (97, -100, 4, 7, '13:30:00');
INSERT INTO ambulatoryCases.tbl_Schedule (ID, Patient, Doctor, `Change`, Time) VALUES (98, -100, 4, 7, '13:45:00');
INSERT INTO ambulatoryCases.tbl_Schedule (ID, Patient, Doctor, `Change`, Time) VALUES (147, -100, 4, 10, '08:00:00');
INSERT INTO ambulatoryCases.tbl_Schedule (ID, Patient, Doctor, `Change`, Time) VALUES (148, -100, 4, 10, '08:15:00');
INSERT INTO ambulatoryCases.tbl_Schedule (ID, Patient, Doctor, `Change`, Time) VALUES (149, -100, 4, 10, '08:30:00');
INSERT INTO ambulatoryCases.tbl_Schedule (ID, Patient, Doctor, `Change`, Time) VALUES (150, -100, 4, 10, '08:45:00');
INSERT INTO ambulatoryCases.tbl_Schedule (ID, Patient, Doctor, `Change`, Time) VALUES (151, -100, 4, 10, '09:00:00');
INSERT INTO ambulatoryCases.tbl_Schedule (ID, Patient, Doctor, `Change`, Time) VALUES (152, -100, 4, 10, '09:15:00');
INSERT INTO ambulatoryCases.tbl_Schedule (ID, Patient, Doctor, `Change`, Time) VALUES (153, -100, 4, 10, '09:30:00');
INSERT INTO ambulatoryCases.tbl_Schedule (ID, Patient, Doctor, `Change`, Time) VALUES (154, -100, 4, 10, '09:45:00');
INSERT INTO ambulatoryCases.tbl_Schedule (ID, Patient, Doctor, `Change`, Time) VALUES (155, -100, 4, 10, '10:00:00');
INSERT INTO ambulatoryCases.tbl_Schedule (ID, Patient, Doctor, `Change`, Time) VALUES (156, -100, 4, 10, '10:15:00');
INSERT INTO ambulatoryCases.tbl_Schedule (ID, Patient, Doctor, `Change`, Time) VALUES (157, -100, 4, 10, '10:30:00');
INSERT INTO ambulatoryCases.tbl_Schedule (ID, Patient, Doctor, `Change`, Time) VALUES (158, -100, 4, 10, '10:45:00');
INSERT INTO ambulatoryCases.tbl_Schedule (ID, Patient, Doctor, `Change`, Time) VALUES (159, -100, 4, 10, '11:00:00');
INSERT INTO ambulatoryCases.tbl_Schedule (ID, Patient, Doctor, `Change`, Time) VALUES (160, -100, 4, 10, '11:15:00');
INSERT INTO ambulatoryCases.tbl_Schedule (ID, Patient, Doctor, `Change`, Time) VALUES (161, -100, 4, 10, '11:30:00');
INSERT INTO ambulatoryCases.tbl_Schedule (ID, Patient, Doctor, `Change`, Time) VALUES (162, -100, 4, 10, '11:45:00');
INSERT INTO ambulatoryCases.tbl_Schedule (ID, Patient, Doctor, `Change`, Time) VALUES (163, -100, 4, 10, '12:00:00');
INSERT INTO ambulatoryCases.tbl_Schedule (ID, Patient, Doctor, `Change`, Time) VALUES (164, -100, 4, 10, '12:15:00');
INSERT INTO ambulatoryCases.tbl_Schedule (ID, Patient, Doctor, `Change`, Time) VALUES (165, -100, 4, 10, '12:30:00');
INSERT INTO ambulatoryCases.tbl_Schedule (ID, Patient, Doctor, `Change`, Time) VALUES (166, -100, 4, 10, '12:45:00');
INSERT INTO ambulatoryCases.tbl_Schedule (ID, Patient, Doctor, `Change`, Time) VALUES (167, -100, 4, 10, '13:00:00');
INSERT INTO ambulatoryCases.tbl_Schedule (ID, Patient, Doctor, `Change`, Time) VALUES (168, -100, 4, 10, '13:15:00');
INSERT INTO ambulatoryCases.tbl_Schedule (ID, Patient, Doctor, `Change`, Time) VALUES (169, -100, 4, 10, '13:30:00');
INSERT INTO ambulatoryCases.tbl_Schedule (ID, Patient, Doctor, `Change`, Time) VALUES (170, -100, 4, 10, '13:45:00');
INSERT INTO ambulatoryCases.tbl_Schedule (ID, Patient, Doctor, `Change`, Time) VALUES (171, -100, 4, 11, '08:00:00');
INSERT INTO ambulatoryCases.tbl_Schedule (ID, Patient, Doctor, `Change`, Time) VALUES (172, -100, 4, 11, '08:15:00');
INSERT INTO ambulatoryCases.tbl_Schedule (ID, Patient, Doctor, `Change`, Time) VALUES (173, -100, 4, 11, '08:30:00');
INSERT INTO ambulatoryCases.tbl_Schedule (ID, Patient, Doctor, `Change`, Time) VALUES (174, -100, 4, 11, '08:45:00');
INSERT INTO ambulatoryCases.tbl_Schedule (ID, Patient, Doctor, `Change`, Time) VALUES (175, -100, 4, 11, '09:00:00');
INSERT INTO ambulatoryCases.tbl_Schedule (ID, Patient, Doctor, `Change`, Time) VALUES (176, -100, 4, 11, '09:15:00');
INSERT INTO ambulatoryCases.tbl_Schedule (ID, Patient, Doctor, `Change`, Time) VALUES (177, -100, 4, 11, '09:30:00');
INSERT INTO ambulatoryCases.tbl_Schedule (ID, Patient, Doctor, `Change`, Time) VALUES (178, -100, 4, 11, '09:45:00');
INSERT INTO ambulatoryCases.tbl_Schedule (ID, Patient, Doctor, `Change`, Time) VALUES (179, -100, 4, 11, '10:00:00');
INSERT INTO ambulatoryCases.tbl_Schedule (ID, Patient, Doctor, `Change`, Time) VALUES (180, -100, 4, 11, '10:15:00');
INSERT INTO ambulatoryCases.tbl_Schedule (ID, Patient, Doctor, `Change`, Time) VALUES (181, -100, 4, 11, '10:30:00');
INSERT INTO ambulatoryCases.tbl_Schedule (ID, Patient, Doctor, `Change`, Time) VALUES (182, -100, 4, 11, '10:45:00');
INSERT INTO ambulatoryCases.tbl_Schedule (ID, Patient, Doctor, `Change`, Time) VALUES (183, -100, 4, 11, '11:00:00');
INSERT INTO ambulatoryCases.tbl_Schedule (ID, Patient, Doctor, `Change`, Time) VALUES (184, -100, 4, 11, '11:15:00');
INSERT INTO ambulatoryCases.tbl_Schedule (ID, Patient, Doctor, `Change`, Time) VALUES (185, -100, 4, 11, '11:30:00');
INSERT INTO ambulatoryCases.tbl_Schedule (ID, Patient, Doctor, `Change`, Time) VALUES (186, -100, 4, 11, '11:45:00');
INSERT INTO ambulatoryCases.tbl_Schedule (ID, Patient, Doctor, `Change`, Time) VALUES (187, -100, 4, 11, '12:00:00');
INSERT INTO ambulatoryCases.tbl_Schedule (ID, Patient, Doctor, `Change`, Time) VALUES (188, -100, 4, 11, '12:15:00');
INSERT INTO ambulatoryCases.tbl_Schedule (ID, Patient, Doctor, `Change`, Time) VALUES (189, -100, 4, 11, '12:30:00');
INSERT INTO ambulatoryCases.tbl_Schedule (ID, Patient, Doctor, `Change`, Time) VALUES (190, -100, 4, 11, '12:45:00');
INSERT INTO ambulatoryCases.tbl_Schedule (ID, Patient, Doctor, `Change`, Time) VALUES (191, -100, 4, 11, '13:00:00');
INSERT INTO ambulatoryCases.tbl_Schedule (ID, Patient, Doctor, `Change`, Time) VALUES (192, -100, 4, 11, '13:15:00');
INSERT INTO ambulatoryCases.tbl_Schedule (ID, Patient, Doctor, `Change`, Time) VALUES (193, -100, 4, 11, '13:30:00');
INSERT INTO ambulatoryCases.tbl_Schedule (ID, Patient, Doctor, `Change`, Time) VALUES (194, -100, 4, 11, '13:45:00');
INSERT INTO ambulatoryCases.tbl_Schedule (ID, Patient, Doctor, `Change`, Time) VALUES (195, -100, 5, 12, '14:30:00');
INSERT INTO ambulatoryCases.tbl_Schedule (ID, Patient, Doctor, `Change`, Time) VALUES (196, -100, 5, 12, '14:45:00');
INSERT INTO ambulatoryCases.tbl_Schedule (ID, Patient, Doctor, `Change`, Time) VALUES (197, 96, 5, 12, '15:00:00');
INSERT INTO ambulatoryCases.tbl_Schedule (ID, Patient, Doctor, `Change`, Time) VALUES (198, 37, 5, 12, '15:15:00');
INSERT INTO ambulatoryCases.tbl_Schedule (ID, Patient, Doctor, `Change`, Time) VALUES (199, 120, 5, 12, '15:30:00');
INSERT INTO ambulatoryCases.tbl_Schedule (ID, Patient, Doctor, `Change`, Time) VALUES (200, -100, 5, 12, '15:45:00');
INSERT INTO ambulatoryCases.tbl_Schedule (ID, Patient, Doctor, `Change`, Time) VALUES (201, -100, 5, 12, '16:00:00');
INSERT INTO ambulatoryCases.tbl_Schedule (ID, Patient, Doctor, `Change`, Time) VALUES (202, -100, 5, 12, '16:15:00');
INSERT INTO ambulatoryCases.tbl_Schedule (ID, Patient, Doctor, `Change`, Time) VALUES (203, -100, 5, 12, '16:30:00');
INSERT INTO ambulatoryCases.tbl_Schedule (ID, Patient, Doctor, `Change`, Time) VALUES (204, -100, 5, 12, '16:45:00');
INSERT INTO ambulatoryCases.tbl_Schedule (ID, Patient, Doctor, `Change`, Time) VALUES (205, -100, 5, 12, '17:00:00');
INSERT INTO ambulatoryCases.tbl_Schedule (ID, Patient, Doctor, `Change`, Time) VALUES (206, -100, 5, 12, '17:15:00');
INSERT INTO ambulatoryCases.tbl_Schedule (ID, Patient, Doctor, `Change`, Time) VALUES (207, -100, 5, 12, '17:30:00');
INSERT INTO ambulatoryCases.tbl_Schedule (ID, Patient, Doctor, `Change`, Time) VALUES (208, -100, 5, 12, '17:45:00');
INSERT INTO ambulatoryCases.tbl_Schedule (ID, Patient, Doctor, `Change`, Time) VALUES (209, -100, 5, 12, '18:00:00');
INSERT INTO ambulatoryCases.tbl_Schedule (ID, Patient, Doctor, `Change`, Time) VALUES (210, -100, 5, 12, '18:15:00');
INSERT INTO ambulatoryCases.tbl_Schedule (ID, Patient, Doctor, `Change`, Time) VALUES (211, -100, 5, 12, '18:30:00');
INSERT INTO ambulatoryCases.tbl_Schedule (ID, Patient, Doctor, `Change`, Time) VALUES (212, -100, 5, 12, '18:45:00');
INSERT INTO ambulatoryCases.tbl_Schedule (ID, Patient, Doctor, `Change`, Time) VALUES (213, -100, 5, 12, '19:00:00');
INSERT INTO ambulatoryCases.tbl_Schedule (ID, Patient, Doctor, `Change`, Time) VALUES (214, -100, 5, 12, '19:15:00');
INSERT INTO ambulatoryCases.tbl_Schedule (ID, Patient, Doctor, `Change`, Time) VALUES (215, -100, 5, 12, '19:30:00');
INSERT INTO ambulatoryCases.tbl_Schedule (ID, Patient, Doctor, `Change`, Time) VALUES (216, -100, 5, 12, '19:45:00');
INSERT INTO ambulatoryCases.tbl_Schedule (ID, Patient, Doctor, `Change`, Time) VALUES (217, -100, 5, 12, '20:00:00');
INSERT INTO ambulatoryCases.tbl_Schedule (ID, Patient, Doctor, `Change`, Time) VALUES (218, -100, 5, 12, '20:15:00');
