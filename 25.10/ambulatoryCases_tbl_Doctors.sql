create table tbl_Doctors
(
    ID         int auto_increment
        primary key,
    Sirname    varchar(50) not null,
    Name       varchar(50) not null,
    SecondName varchar(50) null,
    Sex        tinyint     not null,
    Birthday   date        not null,
    Position   int         not null,
    Speciality int         not null,
    Department int         not null,
    Telephone  varchar(13) not null,
    constraint tbl_Doctors_spr_Departments_ID_fk
        foreign key (Department) references spr_Departments (ID),
    constraint tbl_Doctors_spr_Positions_ID_fk
        foreign key (Position) references spr_Positions (ID),
    constraint tbl_Doctors_spr_Sex_ID_fk
        foreign key (Sex) references spr_Sex (ID),
    constraint tbl_Doctors_spr_Speciality_ID_fk
        foreign key (Speciality) references spr_Speciality (ID)
);

INSERT INTO ambulatoryCases.tbl_Doctors (ID, Sirname, Name, SecondName, Sex, Birthday, Position, Speciality, Department, Telephone) VALUES (1, 'Кузьмин', 'Любосмысл', 'Гордеевич', 1, '1950-06-04', 4, 30, 3, '79165070055');
INSERT INTO ambulatoryCases.tbl_Doctors (ID, Sirname, Name, SecondName, Sex, Birthday, Position, Speciality, Department, Telephone) VALUES (2, 'Цветков', 'Эдуард', 'Трофимович', 1, '1955-10-18', 3, 30, 3, '79126360018');
INSERT INTO ambulatoryCases.tbl_Doctors (ID, Sirname, Name, SecondName, Sex, Birthday, Position, Speciality, Department, Telephone) VALUES (3, 'Коновалов', 'Петр', 'Афанасьевич', 1, '1955-01-30', 3, 11, 3, '79795987550');
INSERT INTO ambulatoryCases.tbl_Doctors (ID, Sirname, Name, SecondName, Sex, Birthday, Position, Speciality, Department, Telephone) VALUES (4, 'Сафонов', 'Прокл', 'Эдуардович', 1, '1975-11-22', 1, 27, 1, '79759497141');
INSERT INTO ambulatoryCases.tbl_Doctors (ID, Sirname, Name, SecondName, Sex, Birthday, Position, Speciality, Department, Telephone) VALUES (5, 'Дементьев', 'Владимир', 'Бориславович', 1, '1974-05-23', 1, 27, 1, '79069910431');
INSERT INTO ambulatoryCases.tbl_Doctors (ID, Sirname, Name, SecondName, Sex, Birthday, Position, Speciality, Department, Telephone) VALUES (6, 'Михеева', 'Алина', 'Семеновна', 2, '1994-03-17', 3, 30, 3, '79872924355');
INSERT INTO ambulatoryCases.tbl_Doctors (ID, Sirname, Name, SecondName, Sex, Birthday, Position, Speciality, Department, Telephone) VALUES (7, 'Коновалова', 'Таисия', 'Юльевна', 2, '1963-10-23', 4, 27, 1, '79861168296');
INSERT INTO ambulatoryCases.tbl_Doctors (ID, Sirname, Name, SecondName, Sex, Birthday, Position, Speciality, Department, Telephone) VALUES (8, 'Муравьева', 'Полина', 'Тарасовна', 2, '1967-04-30', 1, 27, 1, '79736162663');
INSERT INTO ambulatoryCases.tbl_Doctors (ID, Sirname, Name, SecondName, Sex, Birthday, Position, Speciality, Department, Telephone) VALUES (9, 'Сафонова', 'Анастасия', 'Аскольдовна', 2, '1982-04-10', 1, 27, 1, '79727642437');
INSERT INTO ambulatoryCases.tbl_Doctors (ID, Sirname, Name, SecondName, Sex, Birthday, Position, Speciality, Department, Telephone) VALUES (10, 'Комарова', 'Вера', 'Харитоновна', 2, '1955-12-08', 1, 27, 1, '79986199999');
INSERT INTO ambulatoryCases.tbl_Doctors (ID, Sirname, Name, SecondName, Sex, Birthday, Position, Speciality, Department, Telephone) VALUES (11, 'Воробьева', 'Акулина', 'Богдановна', 2, '1974-12-31', 1, 27, 1, '79163772483');
INSERT INTO ambulatoryCases.tbl_Doctors (ID, Sirname, Name, SecondName, Sex, Birthday, Position, Speciality, Department, Telephone) VALUES (12, 'Бирюкова', 'Прасковья', 'Павловна', 2, '1985-01-22', 1, 27, 1, '79869724451');
INSERT INTO ambulatoryCases.tbl_Doctors (ID, Sirname, Name, SecondName, Sex, Birthday, Position, Speciality, Department, Telephone) VALUES (13, 'Игнатова', 'Синклитикия', 'Ждановна', 2, '1990-06-13', 1, 1, 1, '79462450953');
INSERT INTO ambulatoryCases.tbl_Doctors (ID, Sirname, Name, SecondName, Sex, Birthday, Position, Speciality, Department, Telephone) VALUES (14, 'Давыдова', 'Милица', 'Даниловна', 2, '1954-09-30', 3, 30, 3, '79689211072');
INSERT INTO ambulatoryCases.tbl_Doctors (ID, Sirname, Name, SecondName, Sex, Birthday, Position, Speciality, Department, Telephone) VALUES (15, 'Соловьева', 'Ксения', 'Робертовна', 2, '1978-10-15', 3, 30, 3, '79679822279');
