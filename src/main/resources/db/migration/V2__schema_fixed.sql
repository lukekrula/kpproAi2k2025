-- 1) Add missing email column to users
ALTER TABLE users ADD COLUMN IF NOT EXISTS email VARCHAR(255);

-- 2) Insert admin user only if not exists
INSERT INTO users (id, username, email, password, role_id)
SELECT 1, 'admin', 'admin@admin.cz',
       '$2a$10$1pwoLkdKmxBA.z3egkwZVOnw0KCrAvF9l.D1l5tBGiKQDYi4ObHbS',
       1
    WHERE NOT EXISTS (SELECT 1 FROM users WHERE username = 'admin');
