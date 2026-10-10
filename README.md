# Computer Networks Assingment#1

This uses Spring boot for backend and vanilla JS for the frontend. It uses MySQL for data storage and Docker Compose to run the backend and database.

## Run

Start the application from the project directory:

```bash
docker compose up --build -d
```

Stop the application:

```bash
docker compose down
```

## API Endpoints
URL to access frontend via nginx: `http://localhost:3000`
Base URL: `http://localhost:8080`

| Method | Endpoint | Description |
|---|---|---|
| POST | `/api/opportunities` | Create an opportunity |
| GET | `/api/opportunities` | Get all opportunities |
| GET | `/api/opportunities/{id}` | Get an opportunity by ID |
| PUT | `/api/opportunities/{id}` | Update an opportunity |
| DELETE | `/api/opportunities/{id}` | Delete an opportunity |
