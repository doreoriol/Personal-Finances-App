DO $$
DECLARE
    month_type TEXT;
BEGIN
    SELECT data_type
    INTO month_type
    FROM information_schema.columns
    WHERE table_schema = 'public'
      AND table_name = 'budget'
      AND column_name = 'month';

    IF month_type = 'bytea' THEN
        ALTER TABLE budget RENAME COLUMN month TO month_legacy;
        ALTER TABLE budget ADD COLUMN month VARCHAR(7);
    ELSIF month_type IS NULL THEN
        ALTER TABLE budget ADD COLUMN month VARCHAR(7);
    END IF;
END $$;

ALTER TABLE budget
    ALTER COLUMN month TYPE VARCHAR(7);
