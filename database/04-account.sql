CREATE TABLE account (
    id SERIAL PRIMARY KEY,
    user_id INTEGER NOT NULL REFERENCES user_account(id) ON DELETE CASCADE,
    agency_id INTEGER NOT NULL REFERENCES agency(id) ON DELETE CASCADE,
    account_type TEXT NOT NULL CHECK (account_type IN ('PADRAO', 'ESPECIAL', 'PREMIUM')),
    balance NUMERIC(14,2) NOT NULL DEFAULT 0.00,
    credit_limit NUMERIC(14,2),
    overdraft_limit NUMERIC(14,2),
    is_active BOOLEAN NOT NULL DEFAULT TRUE,
    created_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    account_number VARCHAR(20) NOT NULL UNIQUE,
    CONSTRAINT unique_account_per_agency_user UNIQUE (agency_id, user_id)
);