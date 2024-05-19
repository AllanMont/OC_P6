# User Profile Management

This project is a web application that allows users to manage their profile and subscriptions. The application includes features for viewing and updating user profile information, managing subscriptions, and handling user authentication. The frontend is built with Angular, and the backend is built with Spring Boot.

## Table of Contents

1. [Features](#features)
2. [Technologies](#technologies)
3. [Installation](#installation)
4. [Usage](#usage)
5. [Project Structure](#project-structure)
6. [API Endpoints](#api-endpoints)

## Features

- User authentication (login/logout)
- View and update user profile information
- Manage user subscriptions
- Responsive design

## Technologies

### Frontend

- Angular
- TypeScript
- HTML/CSS
- Angular Router
- RxJS
- Tailwind CSS

### Backend

- Spring Boot
- Java
- Spring Security
- JPA/Hibernate
- MySQL

## Installation

To get a local copy up and running, follow these steps:

### Prerequisites

Make sure you have Node.js, npm, Java, and Maven installed.

### Frontend

1. Clone the repository
    ```sh
    git clone https://github.com/AllanMont/OC_P6
    ```
2a. Navigate to the project directory
    ```sh
    cd OC_P6
    ```
3a. Install NPM packages
    ```sh
    npm install -f
    ```

2b. Navigate to the project directory
    ```sh
    cd OC_P6
    ```
3b. Install Maven dependencies
    ```sh
    mvn clean install
    ```

## Usage

### Frontend

To run the application locally, use the following command:

```sh
ng serve
```

Then open your browser and navigate to http://localhost:4200.

### Backend
To run the backend application, use the following command:

```sh
mvn spring-boot:run
```

The backend server will start on http://localhost:8080.

## Project Structure

### Frontend

```
src/
│
├── app/
│   ├── core/
│   │   ├── services/
│   │   │   ├── authentication.service.ts
│   │   │   ├── subscription.service.ts
│   │   │   └── user.service.ts
│   │   └── guards/
│   │       └── auth.guard.ts
│   ├── profile/
│   │   ├── profile.component.html
│   │   ├── profile.component.scss
│   │   └── profile.component.ts
│   ├── app.component.html
│   ├── app.component.scss
│   ├── app.component.ts
│   ├── app.module.ts
│   ├── app-routing.module.ts
│   └── ...
│
├── assets/
│
├── environments/
│
├── index.html
│
└── styles.css
```

### Backend

```
src/
│
├── main/
│   ├── java/
│   │   └── com/
│   │       └── openclassrooms/
│   │           └── mddapi/
│   │               ├── controller/
│   │               │   └── UserController.java
│   │               ├── model/
│   │               │   └── User.java
│   │               ├── service/
│   │               │   └── UserService.java
│   │               └── MddApiApplication.java
│   └── resources/
│       ├── application.properties
│       └── schema.sql
│       └── data.sql
```

# API Endpoints

## Frontend Services

### AuthenticationService
- infoUser(): Retrieves the authenticated user's profile information.

### UserService

- getUserById(id: number): Retrieves a user's profile by ID.

- updateUser(user: any): Updates the user's profile.

### SubscriptionService
- getSubscriptionsByUserId(): Retrieves subscriptions for the authenticated user.
- getTopicById(topicId: number): Retrieves topic information by ID.
- deleteSubscription(subscriptionId: number): Deletes a subscription by ID.

### Backend Controllers

#### UserController

```
GET /users/{id}           # Retrieves a user's profile by ID.
PUT /users                # Updates the user's profile.
```

#### SubscriptionController
```
GET /subscriptions # Verify if subscription exists
GET /subscriptions/user   # Retrieves subscriptions for the authenticated user.
GET /subscriptions/topic?topicId   # Retrieves subscriptions for the ID topic
POST/subscriptions/subscribe # Creates a new subscription.
DELETE /subscriptions?idSubscription={id} # Deletes a subscription by ID.
```

#### PostController
```
GET /posts/all              # Retrieves all posts.
GET /posts/{id}           # Retrieves a specific post by ID.
GET /posts/topic/{id}           # Retrieves all posts by topicId
GET /posts/topics           # Retrieves all posts by topics Ids
POST /posts               # Creates a new post.
DELETE /posts/{id}        # Deletes a post by ID.
```

#### CommentController
```
GET /comments/post/{postId} # Retrieves all comments for a specific post.
POST /comments              # Creates a new comment.
DELETE /comments/{id}       # Deletes a comment by ID.
```

#### TopicController
``` 
GET /topics # Retrieves all topics.
GET /topics/{id} # Retrieves topic by ID
POST /topics # Creates a new topic.
