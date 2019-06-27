CREATE TABLE IF NOT EXISTS t_note
(
    nid           INT PRIMARY KEY AUTO_INCREMENT,
    title         VARCHAR(50) NOT NULL,
    content       TEXT        NOT NULL,
    is_shared     INT         NOT NULL COMMENT '0 is not shared',
    is_deleted    INT         NOT NULL,
    nb_id         INT         NOT NULL,
    author        VARCHAR(18) NOT NULL,
    create_time   DATETIME    NOT NULL,
    modified_user VARCHAR(18) NOT NULL,
    modified_time DATETIME    NOT NULL
);