CREATE TABLE department (
    departmentid INTEGER PRIMARY KEY,
    name TEXT,
    head INTEGER,
    FOREIGN KEY (head) REFERENCES physician(employeeid)
);

CREATE TABLE physician (
    employeeid INTEGER PRIMARY KEY,
    name TEXT,
    position TEXT,
    ssn INTEGER
);

CREATE TABLE affiliated_with (
    physician INTEGER,
    department INTEGER,
    primaryaffiliation BOOLEAN,
    PRIMARY KEY (physician, department),
    FOREIGN KEY (physician) REFERENCES physician(employeeid),
    FOREIGN KEY (department) REFERENCES department(departmentid)
);

CREATE TABLE procedure (
    code INTEGER PRIMARY KEY,
    name TEXT,
    cost REAL
);

CREATE TABLE trained_in (
    physician INTEGER,
    treatment INTEGER,
    certificationdate DATE,
    certificationexpires DATE,
    PRIMARY KEY (physician, treatment),
    FOREIGN KEY (physician) REFERENCES physician(employeeid),
    FOREIGN KEY (treatment) REFERENCES procedure(code)
);

CREATE TABLE room (
    roomnumber INTEGER PRIMARY KEY,
    roomtype TEXT,
    blockfloor INTEGER,
    blockcode INTEGER,
    unavailable BOOLEAN,
    FOREIGN KEY (blockfloor, blockcode) REFERENCES block(blockfloor, blockcode)
);

CREATE TABLE patient (
    ssn INTEGER PRIMARY KEY,
    name TEXT,
    address TEXT,
    phone TEXT,
    insuranceid INTEGER,
    pcp INTEGER,
    FOREIGN KEY (pcp) REFERENCES physician(employeeid)
);

CREATE TABLE medication (
    code INTEGER PRIMARY KEY,
    name TEXT,
    brand TEXT,
    description TEXT
);

CREATE TABLE prescribes (
    physician INTEGER,
    patient INTEGER,
    medication INTEGER,
    date TIMESTAMP,
    appointment INTEGER,
    dose TEXT,
    PRIMARY KEY (physician, patient, medication, date),
    FOREIGN KEY (physician) REFERENCES physician(employeeid),
    FOREIGN KEY (patient) REFERENCES patient(ssn),
    FOREIGN KEY (medication) REFERENCES medication(code),
    FOREIGN KEY (appointment) REFERENCES appointment(appointmentid)
);

CREATE TABLE stay (
    stayid INTEGER PRIMARY KEY,
    patient INTEGER,
    room INTEGER,
    start_time TIMESTAMP,
    end_time TIMESTAMP,
    FOREIGN KEY (patient) REFERENCES patient(ssn),
    FOREIGN KEY (room) REFERENCES room(roomnumber)
);

CREATE TABLE undergoes (
    patient INTEGER,
    procedure INTEGER,
    stay INTEGER,
    date TIMESTAMP,
    physician INTEGER,
    assistingnurse INTEGER,
    PRIMARY KEY (patient, procedure, stay),
    FOREIGN KEY (patient) REFERENCES patient(ssn),
    FOREIGN KEY (procedure) REFERENCES procedure(code),
    FOREIGN KEY (stay) REFERENCES stay(stayid),
    FOREIGN KEY (physician) REFERENCES physician(employeeid),
    FOREIGN KEY (assistingnurse) REFERENCES nurse(employeeid)
);

CREATE TABLE appointment (
    appointmentid INTEGER PRIMARY KEY,
    patient INTEGER,
    prepurse INTEGER,
    physician INTEGER,
    start_dt_time TIMESTAMP,
    end_dt_time TIMESTAMP,
    examinationroom TEXT,
    FOREIGN KEY (patient) REFERENCES patient(ssn),
    FOREIGN KEY (prepurse) REFERENCES nurse(employeeid),
    FOREIGN KEY (physician) REFERENCES physician(employeeid)
);

CREATE TABLE nurse (
    employeeid INTEGER PRIMARY KEY,
    name TEXT,
    position TEXT,
    registered BOOLEAN,
    ssn INTEGER
);

CREATE TABLE on_call (
    nurse INTEGER,
    blockfloor INTEGER,
    blockcode INTEGER,
    oncallstart TIMESTAMP,
    oncallend TIMESTAMP,
    PRIMARY KEY (nurse, blockfloor, blockcode, oncallstart),
    FOREIGN KEY (nurse) REFERENCES nurse(employeeid),
    FOREIGN KEY (blockfloor, blockcode) REFERENCES block(blockfloor, blockcode)
);

CREATE TABLE block (
    blockfloor INTEGER,
    blockcode INTEGER,
    PRIMARY KEY (blockfloor, blockcode)
);
