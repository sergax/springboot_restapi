ALTER TABLE event
ADD FOREIGN KEY (user_id) REFERENCES user(id);

ALTER TABLE file
ADD FOREIGN KEY (event_id) REFERENCES file(id);