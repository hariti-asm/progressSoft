# FX Deal Data Warehouse

## Overview
A Spring Boot application for managing and validating Foreign Exchange (FX) deals. The system ensures data integrity through comprehensive validation and provides a RESTful API for deal management.

## Tech Stack
- Java 17
- Spring Boot 3.2.2
- PostgreSQL
- Docker & Docker Compose
- Maven
- Lombok & MapStruct
- JUnit 5 & Mockito

## Project Structure
```
├── src/
│   ├── main/java/com/progresssoft/
│   │   ├── controller/
│   │   │   ├── DealController.java
│   │   │   └── GlobalExceptionHandler.java
│   │   ├── dto/
│   │   │   ├── ApiError.java
│   │   │   ├── DealRequestDto.java
│   │   │   ├── DealResponseDto.java
│   │   │   └── ErrorResponse.java
│   │   ├── entity/
│   │   │   └── Deal.java
│   │   ├── exception/
│   │   │   └── DealAlreadyExistsException.java
│   │   ├── mapper/
│   │   │   └── DealMapper.java
│   │   ├── repository/
│   │   │   └── DealRepository.java
│   │   ├── service/
│   │   │   ├── DealService.java
│   │   │   └── DealServiceImpl.java
│   │   └── ProgressSoftApplication.java
│   └── test/
│       └── java/com/progresssoft/service/
│           └── DealServiceImplTest.java
```

## Quick Start

### Prerequisites
- Docker and Docker Compose
- Java 17
- Maven
- Make (optional)

### Installation & Running
```bash
# Clone repository
git clone https://github.com/hariti-asm/progressSoft.git
cd progressSoft

# Using Make commands
make up      # Start containers
make down    # Stop containers
make test    # Run tests
make clean   # Clean project

# OR using Docker directly
docker-compose up -d
```

## API Documentation

### Endpoints

#### Add New Deal
```http
POST /api/v1/deals
```

##### Request Body
```json
{
    "id": 1,
    "fromCurrencyIsoCode": "USD",
    "toCurrencyIsoCode": "EUR",
    "dealTimestamp": "2024-02-10T10:00:00",
    "dealAmount": 1000.00
}
```

##### Response
```json
{
    "id": 1,
    "fromCurrencyIsoCode": "USD",
    "toCurrencyIsoCode": "EUR",
    "dealTimestamp": "2024-02-10T10:00:00",
    "dealAmount": 1000.00
}
```

### Validation Rules
- **ID**: Required, positive number
- **Currency Codes**: 3 uppercase letters, following ISO standards
- **Timestamp**: Not null, must be in past or present
- **Amount**: Positive number, not null

### Error Responses

#### Deal Already Exists
```json
{
    "message": "A deal with the ID 1 already exists",
    "timestamp": "2024-02-10T10:00:00",
    "status": 409
}
```

#### Validation Error
```json
{
    "message": "Validation failed",
    "timestamp": "2024-02-10T10:00:00",
    "status": 400,
    "errors": [
        "From Currency ISO Code must be 3 uppercase letters",
        "Deal Amount must be positive"
    ]
}
```

## Database Configuration

### PostgreSQL Settings
```yaml
url: jdbc:postgresql://localhost:5432/deals
username: postgres
password: postgres
```

### Schema
```sql
CREATE TABLE deals (
    id BIGINT PRIMARY KEY,
    from_currency_iso_code VARCHAR(3) NOT NULL,
    to_currency_iso_code VARCHAR(3) NOT NULL,
    deal_timestamp TIMESTAMP NOT NULL,
    deal_amount DECIMAL NOT NULL
);
```

## Testing
The project includes comprehensive unit tests for the service layer, focusing on:
- Deal creation validation
- Duplicate detection
- Currency code validation
- Error handling

```bash
# Run all tests
mvn test

# Run specific test class
mvn test -Dtest=DealServiceImplTest
```

## Docker Support
The project includes Docker configuration for easy deployment:
- PostgreSQL container
- Application-specific environment variables
- Volume mapping for data persistence

## Development

### Building
```bash
mvn clean install
```

### Code Style
- Follow Java conventions
- Use Lombok annotations for boilerplate reduction
- MapStruct for object mapping
- Comprehensive logging with SLF4J

## Contributing
1. Fork the repository
2. Create your feature branch
3. Commit your changes
4. Push to the branch
5. Create a new Pull Request

## License
This project is licensed under the MIT License - see the LICENSE file for details.