INSERT INTO authority (name, description) VALUES
  ('SUPER_ADMIN', 'Super administrator with all permissions'),
  ('ADMIN', 'Administrator with elevated permissions'),
  ('USER', 'Regular user with limited access')
ON CONFLICT (name) DO NOTHING;
