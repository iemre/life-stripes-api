migrate:
	lein migratus migrate

runserver:
	lein ring server-headless 

up:
	docker-compose up -d
