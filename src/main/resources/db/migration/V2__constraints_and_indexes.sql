ALTER TABLE IF EXISTS transactions
    ADD COLUMN IF NOT EXISTS category_id BIGINT;

ALTER TABLE IF EXISTS categories
    ADD COLUMN IF NOT EXISTS user_id BIGINT;

ALTER TABLE IF EXISTS accounts
    ADD COLUMN IF NOT EXISTS user_id BIGINT;

ALTER TABLE IF EXISTS budget
    ADD COLUMN IF NOT EXISTS user_id BIGINT;

ALTER TABLE IF EXISTS budget
    ADD COLUMN IF NOT EXISTS category_id BIGINT;

CREATE INDEX IF NOT EXISTS idx_categories_user_id ON categories(user_id);
CREATE INDEX IF NOT EXISTS idx_accounts_user_id ON accounts(user_id);
CREATE INDEX IF NOT EXISTS idx_budget_user_id ON budget(user_id);
CREATE INDEX IF NOT EXISTS idx_transactions_user_id ON transactions(user_id);
CREATE INDEX IF NOT EXISTS idx_transactions_date ON transactions(date);
CREATE INDEX IF NOT EXISTS idx_transactions_category_id ON transactions(category_id);

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1 FROM pg_constraint WHERE conname = 'fk_categories_user'
    ) THEN
        ALTER TABLE categories
            ADD CONSTRAINT fk_categories_user
            FOREIGN KEY (user_id) REFERENCES users(id);
    END IF;
END $$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1 FROM pg_constraint WHERE conname = 'fk_accounts_user'
    ) THEN
        ALTER TABLE accounts
            ADD CONSTRAINT fk_accounts_user
            FOREIGN KEY (user_id) REFERENCES users(id);
    END IF;
END $$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1 FROM pg_constraint WHERE conname = 'fk_budget_user'
    ) THEN
        ALTER TABLE budget
            ADD CONSTRAINT fk_budget_user
            FOREIGN KEY (user_id) REFERENCES users(id);
    END IF;
END $$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1 FROM pg_constraint WHERE conname = 'fk_budget_category'
    ) THEN
        ALTER TABLE budget
            ADD CONSTRAINT fk_budget_category
            FOREIGN KEY (category_id) REFERENCES categories(id);
    END IF;
END $$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1 FROM pg_constraint WHERE conname = 'fk_transactions_user'
    ) THEN
        ALTER TABLE transactions
            ADD CONSTRAINT fk_transactions_user
            FOREIGN KEY (user_id) REFERENCES users(id);
    END IF;
END $$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1 FROM pg_constraint WHERE conname = 'fk_transactions_category'
    ) THEN
        ALTER TABLE transactions
            ADD CONSTRAINT fk_transactions_category
            FOREIGN KEY (category_id) REFERENCES categories(id);
    END IF;
END $$;
