create table stripes(
  id serial primary key,
  title text,
  time_spent_in_minutes integer,
  user_id serial references users(id),
  relative_priority integer,
  state varchar(255),
  created_at timestamp
);