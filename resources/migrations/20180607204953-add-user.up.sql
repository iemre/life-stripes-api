create table users(
  id serial primary key,
  email text,
  password_hash text,
  created_at timestamp
);