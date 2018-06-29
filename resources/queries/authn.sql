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

-- name: stripes-by-token-and-resource-id
select * from
stripes res inner join auth_tokens tok
on res.user_id=tok.user_id
where tok.token=:token and
res.id=:resource_id;

-- name: activities-by-token-and-resource-id
select * from
activities res inner join auth_tokens tok
on res.user_id=tok.user_id
where tok.token=:token and
res.id=:resource_id;


-- name: tokens-by-token-and-user-id
select * from auth_tokens
where token=:token and user_id=:user_id;
