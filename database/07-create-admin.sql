DO $$
BEGIN
    IF NOT EXISTS (SELECT 1 FROM user_account WHERE email = 'admin@example.com') THEN
        -- Cria o usuário admin se não existir
        INSERT INTO user_account (email, password, name, role, is_active, address)
        VALUES ('admin@example.com', 'senhaSegura', 'Admin', 'ADM', true, 'Endereço do Admin');
    END IF;
END $$;
