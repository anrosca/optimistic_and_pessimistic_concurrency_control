create table patients
(
    id varchar(255) not null
        constraint patients_pkey
            primary key,
    first_name varchar(255),
    source varchar(255),
    last_name varchar(255),
    birth_date date not null,
    phone_number varchar(20) not null,
    is_deleted bool not null default false
);

create table doctors
(
    email_address varchar(255) not null,
    first_name varchar(255) not null,
    last_name varchar(255) not null,
    specialty varchar(255),
    telephone_number varchar(255),
    version bigint default 0,
    id varchar(255) primary key
);

create table appointments
(
    id               varchar(100) primary key,
    operation        varchar(255) not null,
    doctor_id        varchar(100) not null,
    patient_id       varchar(100) not null,
    appointment_date date         not null,
    start_time       time         not null,
    end_time         time         not null,
    details          varchar(255),
    primary_color varchar(10) ,
    secondary_color varchar(10),
    constraint doctor_fk foreign key (doctor_id) references doctors (id),
    constraint patient_fk foreign key (patient_id) references patients (id)
);

create table shedlock
(
    name       varchar(64)  not null,
    lock_until timestamp    not null,
    locked_at  timestamp    not null,
    locked_by  varchar(255) not null,
    primary key (name)
);


create index doctor_id_fk_index on appointments (doctor_id);
create index start_time_index on appointments(start_time);
create index end_time_index on appointments(end_time);
