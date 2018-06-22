-- name: get-by-email
select * from users where email=:email;

-- name: insert!
insert into users (email, password_hash, registration_key, state, created_at)
 values (:email, :password_hash, :registration_key, 'created', :created_at);