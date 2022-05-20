
insert into doctors (id, email_address, first_name, last_name, specialty, telephone_number)
values ('620e11c0-7d59-45be-85cc-0dc146532e78',
        'sponge-bob@gmail.com', 'Sponge', 'Bob', 'ORTHODONTIST', '37369666667');
INSERT INTO doctors (id, email_address, first_name, last_name, specialty, telephone_number)
VALUES ('f23e4567-e89b-12d3-a456-426614174000',
        'vusaci@gmail.com', 'Vasile', 'Usaci', 'ORTHODONTIST', '37369666666');


insert into patients(id, first_name, last_name, birth_date, phone_number)
values ('f44e4567-ef9c-12d3-a45b-52661417400a', 'Jim', 'Morrison', '1994-12-13', '+37369952147');

insert into patients(id, first_name, last_name, birth_date, phone_number)
values ('fc4ec567-ec9c-C2d3-c45b-c26c141c40cc', 'Ray', 'Charles', '1994-12-12', '+37369952149');


insert into appointments(id, operation, doctor_id, patient_id, appointment_date, start_time, end_time, primary_color, secondary_color)
values ('aa3e4567-e89b-12d3-b457-5267141750aa', 'Выдача каппы', 'f23e4567-e89b-12d3-a456-426614174000',
        'f44e4567-ef9c-12d3-a45b-52661417400a', '2021-12-12', '17:00', '18:00', '#ff1f1f', '#D1E8FF');
