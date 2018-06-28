-- name: insert-and-get
insert into auth_tokens (user_id, token, created_at, duration_in_minutes)
values (:user_id, :token, :created_at, :duration_in_minutes)
returning id, user_id, token, duration_in_minutes;

-- name: get-active-auth-token-by-token
select * from auth_tokens
where token=:token;

-- name: deactivate-auth-token-by-token!
update auth_tokens set active=false
where token=:token;

-- name: deactivate-auth-token-by-id!
update auth_tokens set active=false
where id=:id;
