# FX Deal Data Warehouse

## Overview
A Spring Boot application for managing and validating FX deals, built as a technical assignment for ProgressSoft Corporation.

## Tech Stack
- Java 17
- Spring Boot
- PostgreSQL
- Docker
- Maven

## Quick Start
```bash
# Clone repository
git clone https://github.com/hariti-asm/progressSoft.git

# Build and run with Docker
docker-compose up -d

# Access API at
http://localhost:8085
```

## API Endpoints
- POST `/deal/add`: Add new FX deal

## Deal Validation Rules
- Unique Deal ID (non-empty)
- Valid ISO currency codes (3 letters)
- Deal timestamp not in future
- Positive deal amount

## Database Schema
```sql
CREATE TABLE deals (
    id UUID PRIMARY KEY,
    from_currency VARCHAR(3),
    to_currency VARCHAR(3),
    timestamp TIMESTAMP,
    amount DECIMAL,
);
```

## Testing
```bash
# Run tests
mvn test
```

## API Request Example
```json
{
    "dealId": "123e4567-e89b-12d3-a456-426614174000",
    "fromCurrency": "USD",
    "toCurrency": "EUR",
    "timestamp": "2024-02-10T10:00:00",
    "amount": 1000.00
}
```

## Error Handling
- Invalid currency: 400 Bad Request
- Duplicate deal: 409 Conflict
- Server errors: 500 Internal Server Error

## Contributing
Submit pull requests for any improvements.
