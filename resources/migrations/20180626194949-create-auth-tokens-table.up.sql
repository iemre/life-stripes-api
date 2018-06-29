create table auth_tokens(
id serial primary key,
user_id serial references users(id),
token text,
created_at timestamp,
duration_in_minutes integer default 360,
active boolean default true
);

create unique index token_idx on auth_tokens (token);
