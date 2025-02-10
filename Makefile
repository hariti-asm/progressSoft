.PHONY: up down build run test clean

up:
	docker compose up -d

down:
	docker compose down

test:
	mvn test

clean:
	mvn clean
	rm -rf target/