CREATE TABLE audit_log (
    id SERIAL PRIMARY KEY,
    performed_by INTEGER REFERENCES user_account(id),
    action TEXT NOT NULL,
    entity_type TEXT NOT NULL,
    entity_id INTEGER,
    timestamp TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    description TEXT
);