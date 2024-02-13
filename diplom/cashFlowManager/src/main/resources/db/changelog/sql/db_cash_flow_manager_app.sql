--liquibase formatted sql logicalFilePath:path-independent runOnChange:true splitStatements:true endDelimiter:/

--changeset syslop:1
CREATE TABLE IF NOT EXISTS ${cashFlowManager.schema}.OWNER_PERSONAL_DATA (
    id uuid PRIMARY KEY,
    owner_surname VARCHAR(100) NOT NULL,
    owner_name VARCHAR(100) NOT NULL,
    owner_patronymic VARCHAR(100),
    email VARCHAR(100),
    phone_number VARCHAR(20),
    address VARCHAR(255),
    date_of_birth TIMESTAMP,
    gender VARCHAR(10),
    CONSTRAINT OWNER_PERSONAL_DATA_pk primary key (id)
    );
comment on table ${cashFlowManager.schema}.OWNER_PERSONAL_DATA is 'Таблица для хранения персональных данных владельцев счетов';
comment on column ${cashFlowManager.schema}.OWNER_PERSONAL_DATA.id is 'Идентификатор персональных данных владельца счета';
comment on column ${cashFlowManager.schema}.OWNER_PERSONAL_DATA.owner_surname is 'Фамилия владельца счета';
comment on column ${cashFlowManager.schema}.OWNER_PERSONAL_DATA.owner_name is 'Имя владельца счета';
comment on column ${cashFlowManager.schema}.OWNER_PERSONAL_DATA.owner_patronymic is 'Отчество владельца счета';
comment on column ${cashFlowManager.schema}.OWNER_PERSONAL_DATA.email is 'Email владельца счета';
comment on column ${cashFlowManager.schema}.OWNER_PERSONAL_DATA.phone_number is 'Номер телефона владельца счета';
comment on column ${cashFlowManager.schema}.OWNER_PERSONAL_DATA.address is 'Адрес владельца счета';
comment on column ${cashFlowManager.schema}.OWNER_PERSONAL_DATA.date_of_birth is 'Дата рождения владельца счета';
comment on column ${cashFlowManager.schema}.OWNER_PERSONAL_DATA.gender is 'Пол владельца счета';
/

--changeset syslop:2
CREATE TABLE IF NOT EXISTS ${cashFlowManager.schema}.ACCOUNT_OWNERS (
    id uuid PRIMARY KEY,
    owner_personal_data_id uuid,
    status VARCHAR(100) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT ACCOUNT_OWNERS_pk primary key (id),
    CONSTRAINT ACCOUNT_OWNERS_owner_personal_data_id_FK FOREIGN KEY (owner_personal_data_id) REFERENCES ${cashFlowManager.schema}.OWNER_PERSONAL_DATA (id) ON DELETE CASCADE
    );
comment on table ${cashFlowManager.schema}.ACCOUNT_OWNERS is 'Таблица для хранения информации о владельцах счетов';
comment on column ${cashFlowManager.schema}.ACCOUNT_OWNERS.id is 'Идентификатор владельца счета';
comment on column ${cashFlowManager.schema}.ACCOUNT_OWNERS.owner_personal_data_id is 'Идентификатор персональных данных о владельце счета';
comment on column ${cashFlowManager.schema}.ACCOUNT_OWNERS.status is 'Статус владельца счета';
comment on column ${cashFlowManager.schema}.ACCOUNT_OWNERS.created_at is 'Дата регистрации владельца счета';
/

--changeset syslop:3
CREATE TABLE IF NOT EXISTS ${cashFlowManager.schema}.ACCOUNTS (
    id uuid PRIMARY KEY,
    account_number VARCHAR(20) NOT NULL,
    account_owner_id uuid,
    balance INTEGER NOT NULL,
    currency VARCHAR(36) NOT NULL,
    status VARCHAR(100) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT ACCOUNTS_pk primary key (id),
    CONSTRAINT ACCOUNTS_account_owner_id_FK FOREIGN KEY (account_owner_id) REFERENCES ${cashFlowManager.schema}.ACCOUNT_OWNERS (id) ON DELETE CASCADE
    );
comment on table ${cashFlowManager.schema}.ACCOUNTS is 'Таблица для хранения информации о счетах';
comment on column ${cashFlowManager.schema}.ACCOUNTS.id is 'Идентификатор счета';
comment on column ${cashFlowManager.schema}.ACCOUNTS.account_number is 'Номер счета';
comment on column ${cashFlowManager.schema}.ACCOUNTS.account_owner_id is 'Идентификатор владельца счета';
comment on column ${cashFlowManager.schema}.ACCOUNTS.balance is 'Баланс счета';
comment on column ${cashFlowManager.schema}.ACCOUNTS.currency is 'Валюта счета';
comment on column ${cashFlowManager.schema}.ACCOUNTS.status is 'Статус счета';
comment on column ${cashFlowManager.schema}.ACCOUNTS.created_at is 'Дата создания счета';
/

--changeset syslop:4
CREATE TABLE IF NOT EXISTS ${cashFlowManager.schema}.TRANSACTIONS (
    id uuid PRIMARY KEY,
    account_id uuid REFERENCES ${cashFlowManager.schema}.ACCOUNTS(id) ON DELETE CASCADE,
    amount INTEGER NOT NULL,
    currency VARCHAR(36) NOT NULL,
    operation_type VARCHAR(10) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    transaction_description TEXT,
    CONSTRAINT TRANSACTIONS_pk primary key (id),
    CONSTRAINT TRANSACTIONS_account_id_FK FOREIGN KEY (account_id) REFERENCES ${cashFlowManager.schema}.ACCOUNTS (id) ON DELETE CASCADE
    );
comment on table ${cashFlowManager.schema}.TRANSACTIONS is 'Таблица для хранения информации об операциях на счете';
comment on column ${cashFlowManager.schema}.TRANSACTIONS.id is 'Идентификатор операции на счете';
comment on column ${cashFlowManager.schema}.TRANSACTIONS.account_id is 'Идентификатор счета';
comment on column ${cashFlowManager.schema}.TRANSACTIONS.amount is 'Количество средств во время операции';
comment on column ${cashFlowManager.schema}.TRANSACTIONS.currency is 'Валюта операции';
comment on column ${cashFlowManager.schema}.TRANSACTIONS.operation_type is 'Тип операции';
comment on column ${cashFlowManager.schema}.TRANSACTIONS.created_at is 'Дата проведения операции';
comment on column ${cashFlowManager.schema}.TRANSACTIONS.transaction_description is 'Описание транзакции или комментарий';
/