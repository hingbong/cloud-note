CREATE TABLE IF NOT EXISTS t_notebook
(
    nb_id       INT PRIMARY KEY AUTO_INCREMENT,
    title       VARCHAR(30) NOT NULL,
    description varchar(100),
    is_deleted  INT         NOT NULL COMMENT '0=NON-DELETED, 1=DELETE',
    uid         INT         NOT NULL
) ENGINE INNODB
  CHARACTER SET utf8mb4;