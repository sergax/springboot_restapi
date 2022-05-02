INSERT INTO user
(login, password, first_name, last_name, email)
VALUES
('serg','$2a$12$Qi6jaxizr7BA1IqIV9Llt.9O0T8HoBf2Vooz1OynIM7DW7HHunVd2','Sergey','Sergeev','s.sergeev@mail.ua'),
('alex','$2a$12$iTfQwUzjUvwrE5EiB8oiJe/zWbDBPSwAsYU1B2TXWi92XAcQrKbSG','Alexandr','Tarasenko','a.tarasenko@mail.ua'),
('den','$2a$12$2qZR.SBkexktbWcHMghEkOfbMggrw/wk5O1yWTzoCj/XLI.64C68a','Denis','Denisenko','d.denisenko@mail.ua');

INSERT INTO role
(role_name)
VALUES
('ROLE_ADMIN'),
('ROLE_MODER'),
('ROLE_USER');

INSERT INTO user_role
(user_id, role_id)
VALUES
('1','1'),
('2','2'),
('3','3');