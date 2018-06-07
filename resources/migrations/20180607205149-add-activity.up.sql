create table activities(
  id serial primary key,
  title text,
  start_date timestamp,
  end_date timestamp,
  stripe_id serial references stripes(id),
  created_at timestamp
);