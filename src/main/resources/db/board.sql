CREATE TABLE board
(
    board_key          INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    board_title           VARCHAR(255)    NOT NULL,
    board_content     TEXT    NOT NULL,
    board_date       DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    view_count      INT UNSIGNED NOT NULL DEFAULT 0,
    board_status     ENUM('ACTIVE', 'INACTIVE', 'DELETED') NOT NULL DEFAULT 'ACTIVE'
);