-- 1) Add missing email column to users
ALTER TABLE users ADD COLUMN IF NOT EXISTS email VARCHAR(255);

-- 2) Add user_id column to MEMBER if missing
ALTER TABLE member ADD COLUMN IF NOT EXISTS user_id BIGINT;

-- 3) Backfill admin member if exists
UPDATE member
SET user_id = 1
WHERE email = 'admin@admin.cz'
  AND user_id IS NULL;
