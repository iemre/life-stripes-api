select a.* from activities a
inner join users u on u.id=a.user_id
where u.id=:user_id;