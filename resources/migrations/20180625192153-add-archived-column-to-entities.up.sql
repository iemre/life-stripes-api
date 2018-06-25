alter table stripes
add column archived boolean default false;

alter table activities
add column archived boolean default false;
