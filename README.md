docker run -d --name postgresql--kontener-budgetapp -e POSTGRES_DB=database -e POSTGRES_USER=postgres -e POSTGRES_PASSWORD=postgres -p 5432:5432 postgres

http://localhost:8080/swagger-ui/index.html