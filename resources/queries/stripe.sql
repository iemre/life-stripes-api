-- name: get-by-id
SELECT * FROM stripes WHERE id=:id;

-- name: get-by-user-id
select * from stripes where user_id=:user_id;

-- name: insert!
insert into stripes (title, time_spent_in_minutes, user_id, relative_priority, colour_code, created_at)
 values (:title, 0, :user_id, :relative_priority, :colour_code, :created_at);

-- name: update!
update stripes set
title = coalesce(:title, title),
relative_priority = coalesce(:relative_priority, relative_priority),
colour_code = coalesce(:colour_code, colour_code)
where id=:id;
