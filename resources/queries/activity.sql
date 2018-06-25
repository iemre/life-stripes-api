-- name: get-by-user-id
select a.* from activities a
inner join users u on u.id=a.user_id
where u.id=:user_id;

-- name: get-by-stripe-id
select * from activities
where stripe_id=:stripe_id

-- name: insert!
insert into activities (note, start_date, length_in_minutes, stripe_id, user_id, created_at)
 values (:note, :start_date, :length_in_minutes, :stripe_id, :user_id, :created_at);

-- name: update!
update activities set
note = coalesce(:note, note),
start_date = coalesce(:start_date, start_date),
length_in_minutes = coalesce(:length_in_minutes, length_in_minutes)
where id=:id;

-- name: archive!
update activities set
archived=true
where id=:id;
