CREATE TABLE IF NOT EXISTS t_notebook
(
    nb_id       INT PRIMARY KEY AUTO_INCREMENT COMMENT 'notebook id',
    title       VARCHAR(30) NOT NULL COMMENT 'notebook title',
    description varchar(100) COMMENT 'notebook description',
    is_deleted  INT         NOT NULL COMMENT '0=NON-DELETED, 1=DELETED',
    uid         INT         NOT NULL COMMENT 'user id'
) ENGINE INNODB
  CHARACTER SET utf8mb4;