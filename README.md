# FX Deal Data Warehouse

## Overview
This project is a data warehouse solution for Bloomberg to analyze FX deals, developed as part of a technical assignment for ProgressSoft Corporation.

## Requirements
- Java 17
- Spring Boot
- PostgreSQL
- Docker
- Maven

## Project Structure
- `src/main/java`: Source code
- `src/test/java`: Unit tests
- `Dockerfile`: Container configuration
- `docker-compose.yml`: Multi-container Docker setup
- `Makefile`: Build and run commands

## Setup and Running
1. Clone the repository
2. Ensure Docker and Maven are installed
3. Run `make build` to compile
4. Run `make start` to launch the application

## Validation Rules
- Deal Unique ID: Must be non-empty
- Currency Codes: Must be 3-letter ISO codes
- Timestamp: Must not be in the future
- Deal Amount: Must be positive

## Testing
Run unit tests with `make test`