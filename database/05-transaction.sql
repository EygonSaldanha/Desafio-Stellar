CREATE TABLE transaction (
    id SERIAL PRIMARY KEY,
    from_account_id INTEGER NOT NULL REFERENCES account(id),
    to_account_id INTEGER NOT NULL REFERENCES account(id),
    amount NUMERIC(15,2) NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);