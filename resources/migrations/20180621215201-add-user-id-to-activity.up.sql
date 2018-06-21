-- To support Feature PLN1
alter table activities
add column user_id serial references users(id);
