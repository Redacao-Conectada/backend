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
INSERT INTO tb_essay (user_id, up_vote, body) VALUES (1, 40, 'Redação é o processo de redigir (escrever) um texto.');
INSERT INTO tb_essay (user_id, up_vote, body) VALUES (2, 50, 'Redação é o processo de redigir (escrever) um texto.');
INSERT INTO tb_essay (user_id, up_vote, body) VALUES (3, 90, 'Redação é o processo de redigir (escrever) um texto.');

-- Comment
INSERT INTO tb_Comment (user_id, essay_id, body) VALUES (2, 2, 'Redação legal');
INSERT INTO tb_Comment (user_id, essay_id, body) VALUES (1, 1, 'Pode melhorar');

-- Pedido de virar professor
-- (nao funcionando) INSERT INTO tb_changeRoleRequest (user_id, school_registration, school_name_as_teacher, proof_img) VALUES (2, '456543', 'Fera Colegio e Curso','https://www.evidenceexplained.com/system/files/user/user199/Proof-transparent%20canstockphoto12881592.jpg');
