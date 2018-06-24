alter table activities
drop column end_date;

alter table activities
add column length_in_minutes integer;