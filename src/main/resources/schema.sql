CREATE SCHEMA user_crud_db;

CREATE SEQUENCE user_crud_db.sq_user_idt MINVALUE 1 MAXVALUE 9223372036854775807 INCREMENT BY 1 START WITH 1 NO CYCLE;
CREATE SEQUENCE user_crud_db.sq_company_idt MINVALUE 1 MAXVALUE 9223372036854775807 INCREMENT BY 1 START WITH 1 NO CYCLE;

CREATE TABLE user_crud_db.company(
    idt_company         INT8 NOT NULL DEFAULT user_crud_db.sq_company_idt.nextval,
    num_company         INT8 NOT NULL,
    dat_creation        TIMESTAMP,
    CONSTRAINT company_pkey PRIMARY KEY (idt_company)
);

CREATE TABLE user_crud_db.user (
    idt_user            INT8 NOT NULL DEFAULT user_crud_db.sq_user_idt.nextval,
    idt_company         INT8 NOT NULL,
    des_email           VARCHAR(255) NOT NULL,
    dat_birth           TIMESTAMP,
    dat_creation        TIMESTAMP,
    CONSTRAINT user_company_fkey FOREIGN KEY (idt_company) REFERENCES user_crud_db.company,
    CONSTRAINT user_pkey PRIMARY KEY (idt_user)
);

CREATE UNIQUE INDEX index_user_email ON user_crud_db.user(idt_company, des_email);