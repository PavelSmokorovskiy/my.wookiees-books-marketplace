### Objective

Implementation of a bookstore REST API using Java and Spring.

### Brief

Lohgarra, a Wookie from Kashyyyk, has a great idea. She wants to build a marketplace that allows her and her friends to
self-publish their adventures and sell them online to other Wookies. The profits would then be collected and donated to purchase
medical supplies for an impoverished Ewok settlement.

### Tasks

-   Implement assignment using:
    -   Language: **Java**
    -   Framework: **Spring**
-   Implement a REST API returning JSON or XML based on the `Content-Type` header
-   Implement a custom user model with a "author pseudonym" field
-   Implement a book model. Each book should have a title, description, author (your custom user model), cover image and price
    -   Choose the data type for each field that makes the most sense
-   Provide an endpoint to authenticate with the API using username, password and return a JWT
-   Implement REST endpoints for the `/books` resource
    -   No authentication required
    -   Allows only GET (List/Detail) operations
    -   Make the List resource searchable with query parameters
-   Provide REST resources for the authenticated user
    -   Implement the typical CRUD operations for this resource
    -   Implement an endpoint to unpublish a book (DELETE)
-   Implement API tests for all endpoints

### Evaluation Criteria

-   **Java** best practices
-   Using a framework best practices followed for models, configuration and tests
-   API tests for all implemented endpoints
-   Users may only unpublish their own books
-   User _Darth Vader_ is unable to publish his work on Wookie Books

### CodeSubmit

Organized, design, tests and code documentation

*********************
### Step to reproduce
1. Get list of books
GET: http://localhost:8080/books?title=book_3,book_2
REQUEST: 
RESPONSE:
{
        "bookId": 3,
        "title": "book_1",
        "description": "description_1",
        "image": "imageURL_1",
        "price": 10.5
},
{
        "bookId": 4,
        "title": "book_2",
        "description": "description_2",
        "image": "imageURL_2",
        "price": 20.88
},
{
        "bookId": 5,
        "title": "book_3",
        "description": "description_3",
        "image": "imageURL_3",
        "price": 12.34
}

2. Register user:
POST: http://localhost:8080/register
REQUEST:
{
    "username": "username",
    "password": "password"
}
RESPONSE:
{
    "id": 6,
    "username": "username",
    "email": null,
    "books": []
}

3. Authenticate user:
POST: http://localhost:8080/authenticate
REQUEST:
{
    "username": "username",
    "password": "password"
}
RESPONSE:
{
    "token": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ1c2VybmFtZSIsImV4cCI6MTU5NjYyNDQyNiwiaWF0IjoxNTk2NjA2NDI2fQ.x-Tc8q-etdMTSkI5XnApfbloulFcwa2xj6KPHvNOqBkXmdkVo6Kh-xgqTxULGZNZIwybWSqbvRWxwDgWzIpNlw"
}

4. Publish a new Book
POST: http://localhost:8080/books/
REQUEST: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ1c2VybmFtZSIsImV4cCI6MTU5NjYyNDQyNiwiaWF0IjoxNTk2NjA2NDI2fQ.x-Tc8q-etdMTSkI5XnApfbloulFcwa2xj6KPHvNOqBkXmdkVo6Kh-xgqTxULGZNZIwybWSqbvRWxwDgWzIpNlw
RESPONSE:
{
    "bookId": 7,
    "title": "title_1",
    "description": "description_1",
    "image": "image_1",
    "price": 0.5
}

5. Check or published?
GET: http://localhost:8080/books?username=username
REQUEST: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ1c2VybmFtZSIsImV4cCI6MTU5NjYyNDQyNiwiaWF0IjoxNTk2NjA2NDI2fQ.x-Tc8q-etdMTSkI5XnApfbloulFcwa2xj6KPHvNOqBkXmdkVo6Kh-xgqTxULGZNZIwybWSqbvRWxwDgWzIpNlw
RESPONSE: 
{
        "bookId": 7,
        "title": "title_1",
        "description": "description_1",
        "image": "image_1",
        "price": 0.5
}

6. Update a book
PUT: http://localhost:8080/books
REQUEST: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ1c2VybmFtZSIsImV4cCI6MTU5NjYyNDQyNiwiaWF0IjoxNTk2NjA2NDI2fQ.x-Tc8q-etdMTSkI5XnApfbloulFcwa2xj6KPHvNOqBkXmdkVo6Kh-xgqTxULGZNZIwybWSqbvRWxwDgWzIpNlw
{
    "bookId": 7,
    "title": "title_888888",
    "description": "description_1",
    "image": "image_1",
    "price": 0.5
}
RESPONSE: 
{
    "bookId": 7,
    "title": "title_888888",
    "description": "description_1",
    "image": "image_1",
    "price": 0.5
}

7. Check changes
GET: http://localhost:8080/books?username=username
REQUEST: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ1c2VybmFtZSIsImV4cCI6MTU5NjYyNDQyNiwiaWF0IjoxNTk2NjA2NDI2fQ.x-Tc8q-etdMTSkI5XnApfbloulFcwa2xj6KPHvNOqBkXmdkVo6Kh-xgqTxULGZNZIwybWSqbvRWxwDgWzIpNlw
RESPONSE: 
{
        "bookId": 7,
        "title": "title_888888",
        "description": "description_1",
        "image": "image_1",
        "price": 0.5
}

8. Unpublish a book
DELETE: http://localhost:8080/books?bookId=7
REQUEST: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ1c2VybmFtZSIsImV4cCI6MTU5NjYyNDQyNiwiaWF0IjoxNTk2NjA2NDI2fQ.x-Tc8q-etdMTSkI5XnApfbloulFcwa2xj6KPHvNOqBkXmdkVo6Kh-xgqTxULGZNZIwybWSqbvRWxwDgWzIpNlw
RESPONSE: 

9. 5. Check or published?
   GET: http://localhost:8080/books?username=username
   REQUEST: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ1c2VybmFtZSIsImV4cCI6MTU5NjYyNDQyNiwiaWF0IjoxNTk2NjA2NDI2fQ.x-Tc8q-etdMTSkI5XnApfbloulFcwa2xj6KPHvNOqBkXmdkVo6Kh-xgqTxULGZNZIwybWSqbvRWxwDgWzIpNlw
   RESPONSE: 
{}
