-- Users
INSERT INTO tb_user (cpf, name, email, password, birthdate, graduation, school_name, state, city) VALUES ('567.738.340-66', 'Alex', 'alex@gmail.com', '$2a$10$eACCYoNOHEqXve8aIWT8Nu3PkMXWBaOxJ9aORUYzfMQCbVBIhZ8tG', TIMESTAMP  WITH TIME ZONE '2020-07-13T20:50:07.12345Z', 'Superior Completo', 'UFCG', 'PB', 'Campina Grande');
INSERT INTO tb_user (cpf, name, email, password, birthdate, graduation, school_name, state, city) VALUES ('242.679.730-40', 'Maria', 'maria@gmail.com', '$2a$10$eACCYoNOHEqXve8aIWT8Nu3PkMXWBaOxJ9aORUYzfMQCbVBIhZ8tG', TIMESTAMP  WITH TIME ZONE '2020-07-13T20:50:07.12345Z', 'Superior Incompleto', 'UFCG', 'PB', 'Patos');
INSERT INTO tb_user (cpf, name, email, password, birthdate, graduation, school_name, state, city) VALUES ('830.592.980-92', 'Bob', 'bob@gmail.com', '$2a$10$eACCYoNOHEqXve8aIWT8Nu3PkMXWBaOxJ9aORUYzfMQCbVBIhZ8tG', TIMESTAMP  WITH TIME ZONE '2020-07-13T20:50:07.12345Z', 'Ensino Tecnico Completo', 'IFPB', 'PB', 'Joao Pessoa');

-- Roles
INSERT INTO tb_role (authority) VALUES ('ROLE_STUDENT');
INSERT INTO tb_role (authority) VALUES ('ROLE_TEACHER');
INSERT INTO tb_role (authority) VALUES ('ROLE_ADMIN');

-- Users Roles
INSERT INTO tb_user_role (user_id, role_id) VALUES (1, 1);
INSERT INTO tb_user_role (user_id, role_id) VALUES (2, 1);
INSERT INTO tb_user_role (user_id, role_id) VALUES (2, 2);
INSERT INTO tb_user_role (user_id, role_id) VALUES (3, 1);

-- Inheritance
INSERT INTO tb_student (id) VALUES (1);
INSERT INTO tb_teacher (id, school_registration, school_name_as_teacher, proof_img) VALUES (2, '456543', 'Fera Colegio e Curso', 'https://www.evidenceexplained.com/system/files/user/user199/Proof-transparent%20canstockphoto12881592.jpg');
INSERT INTO tb_admin (id) VALUES (1);

-- Essays
INSERT INTO tb_essay (title, user_id, up_vote, body, is_anon, created_at) VALUES ('Titulo: Uma boa redação', 1, 0, 'Redação é o processo de redigir (escrever) um texto.', FALSE, TIMESTAMP  WITH TIME ZONE '2020-07-13T20:50:07.12345Z');
INSERT INTO tb_essay (title, user_id, up_vote, body, is_anon, created_at) VALUES ('Titulo: Uma pessima redação', 2, 0, 'Redação é o processo de redigir (escrever) um texto.', TRUE, TIMESTAMP  WITH TIME ZONE '2020-07-13T18:50:07.12345Z');
INSERT INTO tb_essay (title, user_id, up_vote, body, is_anon, created_at) VALUES ('Titulo: Uma redação marromenos', 3, 0, 'Redação é o processo de redigir (escrever) um texto.', FALSE, TIMESTAMP  WITH TIME ZONE '2020-07-13T21:50:07.12345Z');
INSERT INTO tb_essay (title, user_id, up_vote, body, is_anon, created_at) VALUES ('Titulo: Uma redação.', 3, 0, 'Redação é o processo de redigir (escrever) um texto.Redação é o processo de redigir (escrever) um texto.Redação é o processo de redigir (escrever) um texto.Redação é o processo de redigir (escrever) um texto.Redação é o processo de redigir (escrever) um texto.Redação é o processo de redigir (escrever) um texto.Redação é o processo de redigir (escrever) um texto.Redação é o processo de redigir (escrever) um texto.Redação é o processo de redigir (escrever) um texto.Redação é o processo de redigir (escrever) um texto.Redação é o processo de redigir (escrever) um texto.Redação é o processo de redigir (escrever) um texto.Redação é o processo de redigir (escrever) um texto.Redação é o processo de redigir (escrever) um texto.Redação é o processo de redigir (escrever) um texto.Redação é o processo de redigir (escrever) um texto.Redação é o processo de redigir (escrever) um texto.Redação é o processo de redigir (escrever) um texto.Redação é o processo de redigir (escrever) um texto.Redação é o processo de redigir (escrever) um texto.Redação é o processo de redigir (escrever) um texto.Redação é o processo de redigir (escrever) um texto.Redação é o processo de redigir (escrever) um texto.Redação é o processo de redigir (escrever) um texto.Redação é o processo de redigir (escrever) um texto.Redação é o processo de redigir (escrever) um texto.Redação é o processo de redigir (escrever) um texto.Redação é o processo de redigir (escrever) um texto.Redação é o processo de redigir (escrever) um texto.Redação é o processo de redigir (escrever) um texto.Redação é o processo de redigir (escrever) um texto.Redação é o processo de redigir (escrever) um texto.Redação é o processo de redigir (escrever) um texto.Redação é o processo de redigir (escrever) um texto.Redação é o processo de redigir (escrever) um texto.Redação é o processo de redigir (escrever) um texto.Redação é o processo de redigir (escrever) um texto.Redação é o processo de redigir (escrever) um texto.Redação é o processo de redigir (escrever) um texto.Redação é o processo de redigir (escrever) um texto.Redação é o processo de redigir (escrever) um texto.Redação é o processo de redigir (escrever) um texto.Redação é o processo de redigir (escrever) um texto.Redação é o processo de redigir (escrever) um texto.Redação é o processo de redigir (escrever) um texto.Redação é o processo de redigir (escrever) um texto.Redação é o processo de redigir (escrever) um texto.Redação é o processo de redigir (escrever) um texto.Redação é o processo de redigir (escrever) um texto.Redação é o processo de redigir (escrever) um texto.Redação é o processo de redigir (escrever) um texto.Redação é o processo de redigir (escrever) um texto.Redação é o processo de redigir (escrever) um texto.Redação é o processo de redigir (escrever) um texto.Redação é o processo de redigir (escrever) um texto.Redação é o processo de redigir (escrever) um texto.Redação é o processo de redigir (escrever) um texto.Redação é o processo de redigir (escrever) um texto.Redação é o processo de redigir (escrever) um texto.Redação é o processo de redigir (escrever) um texto.Redação é o processo de redigir (escrever) um texto.Redação é o processo de redigir (escrever) um texto.Redação é o processo de redigir (escrever) um texto.Redação é o processo de redigir (escrever) um texto.Redação é o processo de redigir (escrever) um texto.Redação é o processo de redigir (escrever) um texto.Redação é o processo de redigir (escrever) um texto.', FALSE, TIMESTAMP  WITH TIME ZONE '2020-07-13T21:50:07.12345Z');

-- Comment
INSERT INTO tb_Comment (user_id, essay_id, body, up_vote) VALUES (2, 2, 'Redação legal', 0);
INSERT INTO tb_Comment (user_id, essay_id, body, up_vote) VALUES (1, 1, 'Pode melhorar', 1);

 --Pedido de virar professor
INSERT INTO tb_change_role_request (user_id, proof_img, school_name_as_teacher,  school_registration) VALUES (2, 'https://www.evidenceexplained.com/system/files/user/user199/Proof-transparent%20canstockphoto12881592.jpg', 'Fera Colegio e Curso', '456543');

-- Corrections
INSERT INTO tb_correction (teacher_id, essay_id, created_date, first_competence_comments, first_competence_grade, second_competence_comments,
second_competence_grade, third_competence_comments, third_competence_grade, fourth_competence_comments, fourth_competence_grade,
fifth_competence_comments, fifth_competence_grade)
VALUES (2, 3, TIMESTAMP  WITH TIME ZONE '2020-07-13T20:50:07.12345Z', 'Normal', 120, 'Normal', 120, 'Normal', 120, 'Normal', 120,
'Normal', 120);

INSERT INTO tb_correction (teacher_id, essay_id, created_date, first_competence_comments, first_competence_grade, second_competence_comments,
second_competence_grade, third_competence_comments, third_competence_grade, fourth_competence_comments, fourth_competence_grade,
fifth_competence_comments, fifth_competence_grade)
VALUES (2, 1, TIMESTAMP  WITH TIME ZONE '2020-07-13T20:50:07.12345Z', 'Horrible', 0, 'Awful', 40, 'Bad', 80, 'Normal', 120,
'Good', 160);

INSERT INTO tb_correction (teacher_id, essay_id, created_date, first_competence_comments, first_competence_grade, second_competence_comments,
second_competence_grade, third_competence_comments, third_competence_grade, fourth_competence_comments, fourth_competence_grade,
fifth_competence_comments, fifth_competence_grade)
VALUES (2, 2, TIMESTAMP  WITH TIME ZONE '2020-07-13T20:50:07.12345Z', 'Excelent', 200, 'Excelent', 200, 'Excelent', 200,
'Excelent', 200, 'Excelent', 200);

-- UPDATE em essays
UPDATE tb_essay SET correction_id = 1 WHERE id = 3;
UPDATE tb_essay SET correction_id = 2 WHERE id = 1;
UPDATE tb_essay SET correction_id = 3 WHERE id = 2;
