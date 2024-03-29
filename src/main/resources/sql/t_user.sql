CREATE TABLE IF NOT EXISTS t_user
(
    uid      INT PRIMARY KEY AUTO_INCREMENT COMMENT 'user id',
    username VARCHAR(18) NOT NULL UNIQUE COMMENT 'user name',
    password CHAR(64)    NOT NULL COMMENT 'SHA256',
    salt     CHAR(36)    NOT NULL COMMENT 'UUID'
) ENGINE INNODB
  CHARACTER SET utf8mb4;