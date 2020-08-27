INSERT INTO user_crud_db.company (num_company, dat_creation) VALUES
(1, CURRENT_TIMESTAMP),
(2, CURRENT_TIMESTAMP),
(5, CURRENT_TIMESTAMP),
(7, CURRENT_TIMESTAMP),
(10, CURRENT_TIMESTAMP);

INSERT INTO user_crud_db.user (idt_company, des_email, dat_birth, dat_creation) VALUES
(1,'lombok@acme.com', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);