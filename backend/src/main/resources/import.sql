INSERT INTO tb_users (username, email, password, role) VALUES ('john', 'john@example.com', '$2a$10$eACCYoNOHEqXve8aIWT8Nu3PkMXWBaOxJ9aORUYzfMQCbVBIhZ8tG', 'USER');
INSERT INTO tb_users (username, email, password, role) VALUES ('emma', 'emma@example.com', '$2a$10$eACCYoNOHEqXve8aIWT8Nu3PkMXWBaOxJ9aORUYzfMQCbVBIhZ8tG', 'USER');

INSERT INTO tb_tasks (title, description, status, priority, deadline, assigned_user_id, done) VALUES ('Complete project proposal', 'Finish the proposal for the upcoming project.', 'PENDING', 'LOW', '2023-06-01', 1, false);
INSERT INTO tb_tasks (title, description, status, priority, deadline, assigned_user_id, done) VALUES ('Implement user authentication', 'Develop the user authentication feature for the application.', 'PENDING', 'LOW', '2023-06-10', 2, false);