CREATE SCHEMA users;

CREATE USER 'root'@'%' IDENTIFIED BY 'password';
GRANT ALL ON *.* to 'root'@'%';