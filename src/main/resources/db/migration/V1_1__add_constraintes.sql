ALTER TABLE event
ADD FOREIGN KEY (user_id) REFERENCES user(id),
ADD FOREIGN KEY (file_id) REFERENCES file(id);

ALTER TABLE user_role
ADD FOREIGN KEY (user_id) REFERENCES user(id),
ADD FOREIGN KEY (role_id) REFERENCES role(id);