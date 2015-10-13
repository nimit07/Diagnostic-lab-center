create sequence test_id start with 1;
create table mytest
(
	test_id number(4) primary key NOT NULL,	
	test_name varchar2(50),
	test_rate number(6,3),
	normal_value number(6,3)
);
create sequence doctor_id start with 500;
create table doctor
(
	doctor_id number(5) primary key NOT NULL,
	doctor_name varchar(20),
	comission_per number(10,2)
);
create sequence patient_id start with 2000;
create table patient
(
	patient_id number(10) primary key NOT NULL,
	first_name varchar2(30),
	last_name varchar2(30),
	registration_date date,
	age number(3),
	sex varchar(6),
	no_of_test number(2),
	contact_no varchar2(20),
	address varchar2(100),
	doctor_id number(5),
	constraint fk40 foreign key(doctor_id) references doctor(doctor_id)
);

create table patient_test
(
	patient_id number(10),
	test_id number(4),
	constraint fk47 foreign key(patient_id) references patient(patient_id),
	constraint fk48 foreign key(test_id) references mytest(test_id)
);


create table commission
(	
	doctor_id number(5),
	comm_amnt number(10,3),
	constraint fk49 foreign key(doctor_id) references doctor(doctor_id)
);


create table report
(
	patient_id number(10),
	test_id number(4),
	result varchar2(500),
	constraint fk50 foreign key(patient_id) references patient(patient_id),
	constraint fk51 foreign key(test_id) references mytest(test_id)
);

create table login
(
	username varchar2(100),
	password varchar2(100)


);

