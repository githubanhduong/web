-- -- Insert records into physician table
-- INSERT INTO physician (employeeid, name, position, ssn)
-- VALUES 
-- (1, 'Dr. John Smith', 'Cardiologist', 123456789),
-- (2, 'Dr. Alice Johnson', 'Neurologist', 987654321),
-- (3, 'Dr. Robert Brown', 'Orthopedic Surgeon', 567890123);

-- -- Insert records into department table
-- INSERT INTO department (departmentid, name, head)
-- VALUES 
-- (1, 'Cardiology', 1),
-- (2, 'Neurology', 2),
-- (3, 'Orthopedics', 3);

-- -- Insert records into procedure table
-- INSERT INTO procedure (code, name, cost)
-- VALUES 
-- (1, 'Heart Surgery', 15000.00),
-- (2, 'Brain Surgery', 20000.00),
-- (3, 'Knee Replacement', 10000.00);

-- -- Insert records into affiliated_with table
-- INSERT INTO affiliated_with (physician, department, primaryaffiliation)
-- VALUES 
-- (1, 1, TRUE),
-- (2, 2, TRUE),
-- (3, 3, TRUE);

-- -- Insert records into trained_in table
-- INSERT INTO trained_in (physician, treatment, certificationdate, certificationexpires)
-- VALUES 
-- (1, 1, '2020-01-01', '2025-01-01'),
-- (2, 2, '2019-01-01', '2024-01-01'),
-- (3, 3, '2021-01-01', '2026-01-01');

-- -- Insert records into room table
-- INSERT INTO room (roomnumber, roomtype, blockfloor, blockcode, unavailable)
-- VALUES 
-- (101, 'Single', 1, 1, FALSE),
-- (102, 'Double', 1, 1, TRUE),
-- (103, 'Suite', 1, 1, FALSE);

-- -- Insert records into patient table
-- INSERT INTO patient (ssn, name, address, phone, insuranceid, pcp)

-- -- Insert records into medication table
-- INSERT INTO medication (code, name, brand, description)

-- -- Insert records into prescribes table
-- INSERT INTO prescribes (physician, patient, medication, date, appointment, dose)

-- -- Insert records into stay table
-- INSERT INTO stay (stayid, patient, room, start_time, end_time)

-- -- Insert records into appointment table
-- INSERT INTO appointment (appointmentid, patient, prepurse, physician, start_dt_time, end_dt_time, examinationroom)

-- -- Insert records into nurse table
-- INSERT INTO nurse (employeeid, name, position, registered, ssn)

-- -- Insert records into block table
-- INSERT INTO block (blockfloor, blockcode)

-- -- Insert records into on_call table
-- INSERT INTO on_call (nurse, blockfloor, blockcode, oncallstart, oncallend)

-- -- Insert records into undergoes table
-- INSERT INTO undergoes (patient, procedure, stay, date, physician, assistingnurse)
INSERT INTO "user" (username, password, role) VALUES ('admin', '$2a$11$ke5eM45l.c2CcH1pAy.DVOhS9aKqVxtIbsySMVqsFbY/naBC.OtbS', 'ADMIN');

INSERT INTO Physician(employeeid, name, position, ssn) VALUES(1,'John Dorian','Staff Internist',111111111);
INSERT INTO Physician(employeeid, name, position, ssn) VALUES(2,'Elliot Reid','Attending Physician',222222222);
INSERT INTO Physician(employeeid, name, position, ssn) VALUES(3,'Christopher Turk','Surgical Attending Physician',333333333);
INSERT INTO Physician(employeeid, name, position, ssn) VALUES(4,'Percival Cox','Senior Attending Physician',444444444);
INSERT INTO Physician(employeeid, name, position, ssn) VALUES(5,'Bob Kelso','Head Chief of Medicine',555555555);
INSERT INTO Physician(employeeid, name, position, ssn) VALUES(6,'Todd Quinlan','Surgical Attending Physician',666666666);
INSERT INTO Physician(employeeid, name, position, ssn) VALUES(7,'John Wen','Surgical Attending Physician',777777777);
INSERT INTO Physician(employeeid, name, position, ssn) VALUES(8,'Keith Dudemeister','MD Resident',888888888);
INSERT INTO Physician(employeeid, name, position, ssn) VALUES(9,'Molly Clock','Attending Psychiatrist',999999999);

INSERT INTO Department(departmentid, name, physician_id) VALUES(1,'General Medicine',4);
INSERT INTO Department(departmentid, name, physician_id) VALUES(2,'Surgery',7);
INSERT INTO Department(departmentid, name, physician_id) VALUES(3,'Psychiatry',9);

INSERT INTO Affiliated_With(physician, department, primaryaffiliation) VALUES(2,1,1);
INSERT INTO Affiliated_With(physician, department, primaryaffiliation) VALUES(1,1,1);
INSERT INTO Affiliated_With(physician, department, primaryaffiliation) VALUES(3,1,0);
INSERT INTO Affiliated_With(physician, department, primaryaffiliation) VALUES(3,2,1);
INSERT INTO Affiliated_With(physician, department, primaryaffiliation) VALUES(4,1,1);
INSERT INTO Affiliated_With(physician, department, primaryaffiliation) VALUES(5,1,1);
INSERT INTO Affiliated_With(physician, department, primaryaffiliation) VALUES(6,2,1);
INSERT INTO Affiliated_With(physician, department, primaryaffiliation) VALUES(7,1,0);
INSERT INTO Affiliated_With(physician, department, primaryaffiliation) VALUES(7,2,1);
INSERT INTO Affiliated_With(physician, department, primaryaffiliation) VALUES(8,1,1);
INSERT INTO Affiliated_With(physician, department, primaryaffiliation) VALUES(9,3,1);

INSERT INTO Procedure(code, name, cost) VALUES(1,'Reverse Rhinopodoplasty',1500.0);
INSERT INTO Procedure(code, name, cost) VALUES(2,'Obtuse Pyloric Recombobulation',3750.0);
INSERT INTO Procedure(code, name, cost) VALUES(3,'Folded Demiophtalmectomy',4500.0);
INSERT INTO Procedure(code, name, cost) VALUES(4,'Complete Walletectomy',10000.0);
INSERT INTO Procedure(code, name, cost) VALUES(5,'Obfuscated Dermogastrotomy',4899.0);
INSERT INTO Procedure(code, name, cost) VALUES(6,'Reversible Pancreomyoplasty',5600.0);
INSERT INTO Procedure(code, name, cost) VALUES(7,'Follicular Demiectomy',25.0);

INSERT INTO Patient(ssn, name, address, phone, insuranceid, physicianid) VALUES(5,'John Smith','42 Foobar Lane','555-0256',68476213,1);
INSERT INTO Patient(ssn, name, address, phone, insuranceid, physicianid) VALUES(2,'Grace Ritchie','37 Snafu Drive','555-0512',36546321,2);
INSERT INTO Patient(ssn, name, address, phone, insuranceid, physicianid) VALUES(3,'Random J. Patient','101 Omgbbq Street','555-1204',65465421,2);
INSERT INTO Patient(ssn, name, address, phone, insuranceid, physicianid) VALUES(4,'Dennis Doe','1100 Foobaz Avenue','555-2048',68421879,3);

INSERT INTO Nurse(id, name, position, registered, ssn) VALUES(101,'Carla Espinosa','Head Nurse',1,111111110);
INSERT INTO Nurse(id, name, position, registered, ssn) VALUES(102,'Laverne Roberts','Nurse',1,222222220);
INSERT INTO Nurse(id, name, position, registered, ssn) VALUES(103,'Paul Flowers','Nurse',0,333333330);

INSERT INTO Appointment(patient, prepurse, physician, start_dt_time, end_dt_time, examinationroom) VALUES(1,101,1,'2008-04-24 10:00','2008-04-24 11:00','A');
INSERT INTO Appointment(patient, prepurse, physician, start_dt_time, end_dt_time, examinationroom) VALUES(2,101,2,'2008-04-24 10:00','2008-04-24 11:00','B');
INSERT INTO Appointment(patient, prepurse, physician, start_dt_time, end_dt_time, examinationroom) VALUES(3,NULL,4,'2008-04-26 10:00','2008-04-26 11:00','C');
INSERT INTO Appointment(patient, prepurse, physician, start_dt_time, end_dt_time, examinationroom) VALUES(4,103,2,'2008-04-26 11:00','2008-04-26 12:00','C');

INSERT INTO Medication(code, name, brand, description) VALUES(1,'Procrastin-X','X','N/A');
INSERT INTO Medication(code, name, brand, description) VALUES(2,'Thesisin','Foo Labs','N/A');
INSERT INTO Medication(code, name, brand, description) VALUES(3,'Awakin','Bar Laboratories','N/A');
INSERT INTO Medication(code, name, brand, description) VALUES(4,'Crescavitin','Baz Industries','N/A');
INSERT INTO Medication(code, name, brand, description) VALUES(5,'Melioraurin','Snafu Pharmaceuticals','N/A');

INSERT INTO Prescribes(physician, patient, medication, date, appointment, dose) VALUES(1,1,1,'2008-04-24 10:47:30',13216584,'5');
INSERT INTO Prescribes(physician, patient, medication, date, appointment, dose) VALUES(9,2,2,'2008-04-27 10:53',86213939,'10');
INSERT INTO Prescribes(physician, patient, medication, date, appointment, dose) VALUES(9,3,2,'2008-04-30 16:53',NULL,'5');

INSERT INTO Block(blockfloor, blockcode) VALUES(1,1);
INSERT INTO Block(blockfloor, blockcode) VALUES(1,2);
INSERT INTO Block(blockfloor, blockcode) VALUES(1,3);
INSERT INTO Block(blockfloor, blockcode) VALUES(2,1);
INSERT INTO Block(blockfloor, blockcode) VALUES(2,2);
INSERT INTO Block(blockfloor, blockcode) VALUES(2,3);
INSERT INTO Block(blockfloor, blockcode) VALUES(3,1);
INSERT INTO Block(blockfloor, blockcode) VALUES(3,2);
INSERT INTO Block(blockfloor, blockcode) VALUES(3,3);
INSERT INTO Block(blockfloor, blockcode) VALUES(4,1);
INSERT INTO Block(blockfloor, blockcode) VALUES(4,2);
INSERT INTO Block(blockfloor, blockcode) VALUES(4,3);

INSERT INTO Room(roomnumber, roomtype, blockfloor, blockcode, unavailable) VALUES(101,'Single',1,1,0);
INSERT INTO Room(roomnumber, roomtype, blockfloor, blockcode, unavailable) VALUES(102,'Single',1,1,0);
INSERT INTO Room(roomnumber, roomtype, blockfloor, blockcode, unavailable) VALUES(103,'Single',1,1,0);
INSERT INTO Room(roomnumber, roomtype, blockfloor, blockcode, unavailable) VALUES(111,'Single',1,2,0);
INSERT INTO Room(roomnumber, roomtype, blockfloor, blockcode, unavailable) VALUES(112,'Single',1,2,1);
INSERT INTO Room(roomnumber, roomtype, blockfloor, blockcode, unavailable) VALUES(113,'Single',1,2,0);
INSERT INTO Room(roomnumber, roomtype, blockfloor, blockcode, unavailable) VALUES(121,'Single',1,3,0);
INSERT INTO Room(roomnumber, roomtype, blockfloor, blockcode, unavailable) VALUES(122,'Single',1,3,0);
INSERT INTO Room(roomnumber, roomtype, blockfloor, blockcode, unavailable) VALUES(123,'Single',1,3,0);
INSERT INTO Room(roomnumber, roomtype, blockfloor, blockcode, unavailable) VALUES(201,'Single',2,1,1);
INSERT INTO Room(roomnumber, roomtype, blockfloor, blockcode, unavailable) VALUES(202,'Single',2,1,0);
INSERT INTO Room(roomnumber, roomtype, blockfloor, blockcode, unavailable) VALUES(203,'Single',2,1,0);
INSERT INTO Room(roomnumber, roomtype, blockfloor, blockcode, unavailable) VALUES(211,'Single',2,2,0);
INSERT INTO Room(roomnumber, roomtype, blockfloor, blockcode, unavailable) VALUES(212,'Single',2,2,0);
INSERT INTO Room(roomnumber, roomtype, blockfloor, blockcode, unavailable) VALUES(213,'Single',2,2,1);
INSERT INTO Room(roomnumber, roomtype, blockfloor, blockcode, unavailable) VALUES(221,'Single',2,3,0);
INSERT INTO Room(roomnumber, roomtype, blockfloor, blockcode, unavailable) VALUES(222,'Single',2,3,0);
INSERT INTO Room(roomnumber, roomtype, blockfloor, blockcode, unavailable) VALUES(223,'Single',2,3,0);
INSERT INTO Room(roomnumber, roomtype, blockfloor, blockcode, unavailable) VALUES(301,'Single',3,1,0);
INSERT INTO Room(roomnumber, roomtype, blockfloor, blockcode, unavailable) VALUES(302,'Single',3,1,1);
INSERT INTO Room(roomnumber, roomtype, blockfloor, blockcode, unavailable) VALUES(303,'Single',3,1,0);
INSERT INTO Room(roomnumber, roomtype, blockfloor, blockcode, unavailable) VALUES(311,'Single',3,2,0);
INSERT INTO Room(roomnumber, roomtype, blockfloor, blockcode, unavailable) VALUES(312,'Single',3,2,0);
INSERT INTO Room(roomnumber, roomtype, blockfloor, blockcode, unavailable) VALUES(313,'Single',3,2,0);
INSERT INTO Room(roomnumber, roomtype, blockfloor, blockcode, unavailable) VALUES(321,'Single',3,3,1);
INSERT INTO Room(roomnumber, roomtype, blockfloor, blockcode, unavailable) VALUES(322,'Single',3,3,0);
INSERT INTO Room(roomnumber, roomtype, blockfloor, blockcode, unavailable) VALUES(323,'Single',3,3,0);
INSERT INTO Room(roomnumber, roomtype, blockcode, blockfloor, unavailable) VALUES(401,'Single',4,1,0);
INSERT INTO Room(roomnumber, roomtype, blockcode, blockfloor, unavailable) VALUES(402,'Single',4,1,1);
INSERT INTO Room(roomnumber, roomtype, blockcode, blockfloor, unavailable) VALUES(403,'Single',4,1,0);
INSERT INTO Room(roomnumber, roomtype, blockcode, blockfloor, unavailable) VALUES(411,'Single',4,2,0);
INSERT INTO Room(roomnumber, roomtype, blockcode, blockfloor, unavailable) VALUES(412,'Single',4,2,0);
INSERT INTO Room(roomnumber, roomtype, blockcode, blockfloor, unavailable) VALUES(413,'Single',4,2,0);
INSERT INTO Room(roomnumber, roomtype, blockcode, blockfloor, unavailable) VALUES(421,'Single',4,3,1);
INSERT INTO Room(roomnumber, roomtype, blockcode, blockfloor, unavailable) VALUES(422,'Single',4,3,0);
INSERT INTO Room(roomnumber, roomtype, blockcode, blockfloor, unavailable) VALUES(423,'Single',4,3,0);

INSERT INTO On_Call(nurse, blockfloor, blockcode, oncallstart, oncallend) VALUES(101,1,1,'2008-11-04 11:00','2008-11-04 19:00');
INSERT INTO On_Call(nurse, blockfloor, blockcode, oncallstart, oncallend) VALUES(101,1,2,'2008-11-04 11:00','2008-11-04 19:00');
INSERT INTO On_Call(nurse, blockfloor, blockcode, oncallstart, oncallend) VALUES(102,1,3,'2008-11-04 11:00','2008-11-04 19:00');
INSERT INTO On_Call(nurse, blockfloor, blockcode, oncallstart, oncallend) VALUES(103,1,1,'2008-11-04 19:00','2008-11-05 03:00');
INSERT INTO On_Call(nurse, blockfloor, blockcode, oncallstart, oncallend) VALUES(103,1,2,'2008-11-04 19:00','2008-11-05 03:00');
INSERT INTO On_Call(nurse, blockfloor, blockcode, oncallstart, oncallend) VALUES(103,1,3,'2008-11-04 19:00','2008-11-05 03:00');

INSERT INTO Stay(stayid, patient, room, start_time, end_time) VALUES(3215,1,111,'2008-05-01','2008-05-04');
INSERT INTO Stay(stayid, patient, room, start_time, end_time) VALUES(3216,2,123,'2008-05-03','2008-05-14');
INSERT INTO Stay(stayid, patient, room, start_time, end_time) VALUES(3217,3,112,'2008-05-02','2008-05-03');

INSERT INTO Undergoes(patient, procedure, stay, date, physician, assistingnurse) VALUES(1,6,3215,'2008-05-02',3,101);
INSERT INTO Undergoes(patient, procedure, stay, date, physician, assistingnurse) VALUES(1,2,3215,'2008-05-03',7,101);
INSERT INTO Undergoes(patient, procedure, stay, date, physician, assistingnurse) VALUES(2,1,3217,'2008-05-07',3,102);
INSERT INTO Undergoes(patient, procedure, stay, date, physician, assistingnurse) VALUES(2,5,3217,'2008-05-09',6,NULL);
INSERT INTO Undergoes(patient, procedure, stay, date, physician, assistingnurse) VALUES(1,7,3217,'2008-05-10',7,101);
INSERT INTO Undergoes(patient, procedure, stay, date, physician, assistingnurse) VALUES(2,4,3217,'2008-05-13',3,103);

INSERT INTO Trained_In(physician, treatment, certificationdate, certificationexpires) VALUES(3,1,'2008-01-01','2008-12-31');
INSERT INTO Trained_In(physician, treatment, certificationdate, certificationexpires) VALUES(3,2,'2008-01-01','2008-12-31');
INSERT INTO Trained_In(physician, treatment, certificationdate, certificationexpires) VALUES(3,5,'2008-01-01','2008-12-31');
INSERT INTO Trained_In(physician, treatment, certificationdate, certificationexpires) VALUES(3,6,'2008-01-01','2008-12-31');
INSERT INTO Trained_In(physician, treatment, certificationdate, certificationexpires) VALUES(3,7,'2008-01-01','2008-12-31');
INSERT INTO Trained_In(physician, treatment, certificationdate, certificationexpires) VALUES(6,2,'2008-01-01','2008-12-31');
INSERT INTO Trained_In(physician, treatment, certificationdate, certificationexpires) VALUES(6,5,'2007-01-01','2007-12-31');
INSERT INTO Trained_In(physician, treatment, certificationdate, certificationexpires) VALUES(6,6,'2008-01-01','2008-12-31');
INSERT INTO Trained_In(physician, treatment, certificationdate, certificationexpires) VALUES(7,1,'2008-01-01','2008-12-31');
INSERT INTO Trained_In(physician, treatment, certificationdate, certificationexpires) VALUES(7,2,'2008-01-01','2008-12-31');
INSERT INTO Trained_In(physician, treatment, certificationdate, certificationexpires) VALUES(7,3,'2008-01-01','2008-12-31');
INSERT INTO Trained_In(physician, treatment, certificationdate, certificationexpires) VALUES(7,4,'2008-01-01','2008-12-31');
INSERT INTO Trained_In(physician, treatment, certificationdate, certificationexpires) VALUES(7,5,'2008-01-01','2008-12-31');
INSERT INTO Trained_In(physician, treatment, certificationdate, certificationexpires) VALUES(7,6,'2008-01-01','2008-12-31');
INSERT INTO Trained_In(physician, treatment, certificationdate, certificationexpires) VALUES(7,7,'2008-01-01','2008-12-31');
