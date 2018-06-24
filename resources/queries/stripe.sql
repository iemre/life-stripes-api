-- name: get-by-id
SELECT * FROM stripes WHERE id=:id;

-- name: get-by-user-id
select * from stripes where user_id=:user_id;

-- name: insert!
insert into stripes (title, time_spent_in_minutes, user_id, relative_priority, state, created_at)
 values (:title, 0, :user_id, :relative_priority, :state, :created_at);

