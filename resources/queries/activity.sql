-- name: get-by-user-id
select a.* from activities a
inner join users u on u.id=a.user_id
where u.id=:user_id;

-- name: get-by-stripe-id
select * from activities
where stripe_id=:stripe_id

-- name: insert!
insert into activities (note, start_date, end_date, stripe_id, user_id, created_at)
 values (:note, :start_date, :end_date, :stripe_id, :user_id, :created_at);
