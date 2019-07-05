CREATE TABLE IF NOT EXISTS t_note
(
    nid           INT PRIMARY KEY AUTO_INCREMENT COMMENT 'note id',
    title         VARCHAR(50) NOT NULL COMMENT 'note title',
    content       TEXT        NOT NULL COMMENT 'note content',
    is_shared     INT         NOT NULL COMMENT '0 is not shared',
    is_deleted    INT         NOT NULL COMMENT '0=NON-DELETED, 1=DELETED',
    nb_id         INT         NOT NULL COMMENT 'notebook id',
    author        VARCHAR(18) NOT NULL COMMENT 'note author',
    create_time   DATETIME    NOT NULL,
    modified_user VARCHAR(18) NOT NULL,
    modified_time DATETIME    NOT NULL
) ENGINE INNODB
  CHARACTER SET utf8mb4;