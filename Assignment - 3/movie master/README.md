# Movie Master Application

A Spring Boot RESTful web service for managing production houses and films.

## Project Structure

```
movie-master/
├── src/
│   ├── main/
│   │   ├── java/com/example/moviemaster/
│   │   │   ├── entity/
│   │   │   │   ├── ProductionHouse.java
│   │   │   │   └── Film.java
│   │   │   ├── repository/
│   │   │   │   ├── ProductionHouseRepository.java
│   │   │   │   └── FilmRepository.java
│   │   │   ├── service/
│   │   │   │   ├── ProductionHouseService.java
│   │   │   │   └── FilmService.java
│   │   │   ├── controller/
│   │   │   │   ├── ProductionHouseController.java
│   │   │   │   ├── FilmController.java
│   │   │   │   └── GlobalExceptionHandler.java
│   │   │   ├── exception/
│   │   │   │   ├── ProductionHouseNotFoundException.java
│   │   │   │   ├── FilmNotFoundException.java
│   │   │   │   └── ErrorResponse.java
│   │   │   └── MovieMasterApplication.java
│   │   └── resources/
│   │       └── application.properties
│   └── test/
│       └── java/com/example/moviemaster/
├── pom.xml
└── README.md
```

## Features

### Production House Management
- Add new production houses
- Retrieve production house details by ID
- Update chairman's name
- Get production houses by film count
- Delete production houses

### Film Management
- Add new films
- Retrieve film details by ID
- Search films by director and genre
- Get films by production house name (case-insensitive)
- Update film details
- Remove films from database

## Build and Run

### Prerequisites
- Java 11 or higher
- Maven 3.6+

### Build
```bash
cd Assignment\ -\ 3/movie\ master
mvn clean install
```

### Run
```bash
mvn spring-boot:run
```

The application will start on `http://localhost:8080/api`

## API Endpoints

### Production House Endpoints

#### 1. Add Production House
```
POST /api/productionhouses
Content-Type: application/json

{
  "name": "Universal Pictures",
  "establishedYear": 1915,
  "chairmanName": "Pearlman",
  "country": "USA"
}
```

#### 2. Get Production House by ID
```
GET /api/productionhouses/{houseId}
```

#### 3. Get All Production Houses
```
GET /api/productionhouses
```

#### 4. Update Chairman Name
```
PUT /api/productionhouses/{houseId}/chairman?chairmanName=NewName
```

#### 5. Get Production Houses by Film Count
```
GET /api/productionhouses/byfilmcount/{filmCount}
```

#### 6. Delete Production House
```
DELETE /api/productionhouses/{houseId}
```

### Film Endpoints

#### 1. Add Film
```
POST /api/films
Content-Type: application/json

{
  "title": "Inception",
  "director": "Christopher Nolan",
  "genre": "Science Fiction",
  "budget": 160000000.0,
  "releaseDate": "2010-07-16",
  "productionHouse": {
    "houseId": 1
  }
}
```

#### 2. Get Film by ID
```
GET /api/films/{filmId}
```

#### 3. Get All Films
```
GET /api/films
```

#### 4. Search Films by Director and Genre
```
GET /api/films/search?director=Christopher%20Nolan&genre=Science%20Fiction
```

#### 5. Get Films by Production House Name
```
GET /api/films/productionhouse/{houseName}
```

#### 6. Update Film
```
PUT /api/films/{filmId}
Content-Type: application/json

{
  "title": "Inception",
  "director": "Christopher Nolan",
  "genre": "Science Fiction",
  "budget": 160000000.0,
  "releaseDate": "2010-07-16",
  "productionHouse": {
    "houseId": 1
  }
}
```

#### 7. Remove Film
```
DELETE /api/films/{filmId}
```

## Testing with Postman

1. **Create a Production House**
   - Method: POST
   - URL: `http://localhost:8080/api/productionhouses`
   - Body (JSON):
     ```json
     {
       "name": "Warner Bros",
       "establishedYear": 1923,
       "chairmanName": "Thomas Friedkin",
       "country": "USA"
     }
     ```

2. **Create Films**
   - Method: POST
   - URL: `http://localhost:8080/api/films`
   - Body (JSON):
     ```json
     {
       "title": "The Dark Knight",
       "director": "Christopher Nolan",
       "genre": "Action",
       "budget": 185000000.0,
       "releaseDate": "2008-07-18",
       "productionHouse": {
         "houseId": 1
       }
     }
     ```

3. **Search Films by Director and Genre**
   - Method: GET
   - URL: `http://localhost:8080/api/films/search?director=Christopher%20Nolan&genre=Action`

## Database

The application uses **H2 in-memory database** by default. You can access the H2 console at:
```
http://localhost:8080/api/h2-console
```

Connection details:
- JDBC URL: `jdbc:h2:mem:moviedb`
- User Name: `sa`
- Password: (leave empty)

## Error Handling

The application handles the following errors:

1. **NOT_FOUND (404)**: When production house or film doesn't exist
2. **BAD_REQUEST (400)**: When validation fails or invalid data is provided
3. **INTERNAL_SERVER_ERROR (500)**: For unexpected server errors

## Entity Validations

### ProductionHouse
- `name`: Not empty, max 100 characters
- `establishedYear`: Between 1800 and 2024
- `chairmanName`: Not empty, max 100 characters
- `country`: Not empty, max 100 characters

### Film
- `title`: Not empty, max 150 characters
- `director`: Not empty, max 100 characters
- `genre`: Not empty, max 100 characters
- `budget`: Must be positive
- `releaseDate`: Must be past or present date
- `productionHouse`: Required (Foreign Key)

## Technology Stack

- **Framework**: Spring Boot 2.7.0
- **Database**: H2 In-Memory Database
- **ORM**: Spring Data JPA
- **Build Tool**: Maven
- **Java Version**: 11
