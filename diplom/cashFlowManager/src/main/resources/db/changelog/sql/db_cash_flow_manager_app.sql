--liquibase formatted sql logicalFilePath:path-independent runOnChange:true splitStatements:true endDelimiter:"/"
--changeset syslop:1
CREATE TABLE IF NOT EXISTS ${cashFlowManager.schema}.ACCOUNTS (
    id SERIAL PRIMARY KEY,
    account_number VARCHAR(20) NOT NULL,
    balance DECIMAL(10, 2) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
    );
comment on table ${cashFlowManager.schema}.ACCOUNTS is 'Таблица для хранения информации о счетах';
comment on column ${cashFlowManager.schema}.ACCOUNTS.id is 'Идентификатор счета';
comment on column ${cashFlowManager.schema}.ACCOUNTS.account_number is 'Номер счета';
comment on column ${cashFlowManager.schema}.ACCOUNTS.balance is 'Баланс счета';
comment on column ${cashFlowManager.schema}.ACCOUNTS.created_at is 'Дата создания счета';
/

--changeset syslop:2
CREATE TABLE IF NOT EXISTS ${cashFlowManager.schema}.ACCOUNT_OWNERS (
    id SERIAL PRIMARY KEY,
    account_id INTEGER REFERENCES accounts(id) ON DELETE CASCADE,
    owner_name VARCHAR(100) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
    );
comment on table ${cashFlowManager.schema}.ACCOUNT_OWNERS is 'Таблица для хранения информации о владельцах счетов';
comment on column ${cashFlowManager.schema}.ACCOUNT_OWNERS.id is 'Идентификатор владельца счета';
comment on column ${cashFlowManager.schema}.ACCOUNT_OWNERS.account_id is 'Идентификатор счета';
comment on column ${cashFlowManager.schema}.ACCOUNT_OWNERS.owner_name is 'Имя владельца счета';
comment on column ${cashFlowManager.schema}.ACCOUNT_OWNERS.created_at is 'Дата регистрации владельца счета';
/

--changeset syslop:3
CREATE TABLE IF NOT EXISTS ${cashFlowManager.schema}.TRANSACTIONS (
    id SERIAL PRIMARY KEY,
    account_id INTEGER REFERENCES accounts(id) ON DELETE CASCADE,
    amount DECIMAL(10, 2) NOT NULL,
    operation_type VARCHAR(10) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
    );
comment on table ${cashFlowManager.schema}.TRANSACTIONS is 'Таблица для хранения информации о транзакциях';
comment on column ${cashFlowManager.schema}.TRANSACTIONS.id is 'Идентификатор транзакции';
comment on column ${cashFlowManager.schema}.TRANSACTIONS.account_id is 'Идентификатор счета';
comment on column ${cashFlowManager.schema}.TRANSACTIONS.amount is 'Количество средств во время транзакции';
comment on column ${cashFlowManager.schema}.TRANSACTIONS.operation_type is 'Тип транзакции';
comment on column ${cashFlowManager.schema}.TRANSACTIONS.created_at is 'Дата проведения транзакции';
/

--changeset syslop:4
CREATE TABLE IF NOT EXISTS ${cashFlowManager.schema}.DELETED_USERS (
    id SERIAL PRIMARY KEY,
    username VARCHAR(100) NOT NULL,
    deleted_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
    );
comment on table ${cashFlowManager.schema}.DELETED_USERS is 'Таблица для хранения удаленных пользователей';
comment on column ${cashFlowManager.schema}.DELETED_USERS.id is 'Идентификатор удаленного владельца счета';
comment on column ${cashFlowManager.schema}.DELETED_USERS.username is 'Имя удаленного владельца счета';
comment on column ${cashFlowManager.schema}.DELETED_USERS.deleted_at is 'Дата удаления владельца счета';
/

--changeset syslop:5
CREATE TABLE IF NOT EXISTS ${cashFlowManager.schema}.DELETED_ACCOUNTS (
    id SERIAL PRIMARY KEY,
    account_number VARCHAR(20) NOT NULL,
    deleted_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
    );
comment on table ${cashFlowManager.schema}.DELETED_ACCOUNTS is 'Таблица для хранения удаленных счетов';
comment on column ${cashFlowManager.schema}.DELETED_ACCOUNTS.id is 'Идентификатор удаленного счета';
comment on column ${cashFlowManager.schema}.DELETED_ACCOUNTS.account_number is 'Номер удаленного счета';
comment on column ${cashFlowManager.schema}.DELETED_ACCOUNTS.deleted_at is 'Дата удаления счета';
/
