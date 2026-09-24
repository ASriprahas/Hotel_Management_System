# 🏨 Hotel Management System

A full-stack **Hotel Management System** developed using **Spring Boot, React.js, MySQL, Spring Data JPA, and Spring Security with JWT authentication**.

The system allows users to register and log in securely, view available hotel rooms, make bookings, and manage their reservations through a React-based frontend and RESTful Spring Boot backend.

---

## 🚀 Features

### 👤 User Management

* User registration
* User login
* Secure authentication using JWT
* Role-based authorization
* User account management

### 🛏️ Room Management

* View available rooms
* Add new rooms
* Update room details
* Delete rooms
* Check room availability
* Display room information and pricing

### 📅 Booking Management

* Create hotel bookings
* View booking details
* Manage reservations
* Cancel bookings
* Associate bookings with users and rooms
* Check booking availability

### 🔐 Security

* Spring Security integration
* JWT-based authentication
* Protected REST API endpoints
* Role-based access control
* Stateless authentication

---

## 🛠️ Tech Stack

### Frontend

* React.js
* JavaScript
* HTML5
* CSS3
* Bootstrap
* React Router

### Backend

* Java
* Spring Boot
* Spring Security
* JWT
* Spring Data JPA
* Hibernate
* REST APIs
* Maven

### Database

* MySQL

### Tools

* IntelliJ IDEA
* Visual Studio Code
* Postman
* Git
* GitHub

---

## 🏗️ System Architecture

```text
              React Frontend
                    |
                    | REST API
                    ↓
             Spring Boot Backend
                    |
        ┌───────────┼────────────┐
        ↓           ↓            ↓
   Controller    Service     Security
        |           |            |
        ↓           ↓            ↓
   Repository    JPA/JPA     JWT Filter
        |           |
        └──────┬────┘
               ↓
          MySQL Database
```

---

## 🔐 Authentication Flow

The application uses **Spring Security and JWT** for secure authentication.

```text
User Login
    ↓
Spring Security
    ↓
User Credentials Validation
    ↓
JWT Token Generated
    ↓
Token Sent to Frontend
    ↓
Frontend Sends JWT with API Requests
    ↓
JWT Authentication Filter
    ↓
Token Validation
    ↓
Protected API Access
```

JWT allows the backend to authenticate users without maintaining traditional server-side sessions.

---

## 🗄️ Database Design

The application uses **MySQL** as the relational database and **Spring Data JPA/Hibernate** for ORM.

### Main Entities

```text
User
 │
 │ 1
 │
 │ *
Booking
 │
 │ *
 │
 │ 1
Room
```

### User

Stores user authentication and account information.

### Room

Stores hotel room information such as:

* Room ID
* Room type
* Room price
* Room availability
* Room details

### Booking

Stores reservation information and maintains relationships between users and rooms.

---

## 📂 Project Structure

```text
hotel-management-system/
│
├── backend/
│   ├── src/
│   │   └── main/
│   │       ├── java/
│   │       │   └── ...
│   │       └── resources/
│   │           └── application.properties
│   │
│   └── pom.xml
│
├── frontend/
│   ├── src/
│   │   ├── components/
│   │   ├── pages/
│   │   ├── services/
│   │   ├── assets/
│   │   └── ...
│   │
│   ├── package.json
│   └── vite.config.js
│
└── README.md
```

---

## ⚙️ Installation & Setup

### 1. Clone the Repository

```bash
git clone YOUR_GITHUB_REPOSITORY_URL
cd hotel-management-system
```

---

### 2. Configure MySQL

Create the database:

```sql
CREATE DATABASE LakeSide_Hotel_DB;
```

Configure the database connection in:

```text
backend/src/main/resources/application.properties
```

Example:

```properties
spring.application.name=LakeView

spring.datasource.url=jdbc:mysql://localhost:3306/LakeSide_Hotel_DB
spring.datasource.username=root
spring.datasource.password=YOUR_PASSWORD

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

> Replace `YOUR_PASSWORD` with your local MySQL password.

**Do not upload your actual database password or JWT secret to GitHub.**

---

### 3. Run the Backend

Navigate to the backend directory:

```bash
cd backend
```

Run the Spring Boot application:

```bash
mvn spring-boot:run
```

---

### 4. Run the Frontend

Open another terminal:

```bash
cd frontend
```

Install dependencies:

```bash
npm install
```

Start the React application:

```bash
npm run dev
```

The frontend will normally run at:

```text
http://localhost:5173
```

---

## 🧪 API Testing

The backend REST APIs can be tested using **Postman**.

Typical API operations include:

```text
Authentication
POST   /api/auth/register
POST   /api/auth/login

Rooms
GET    /api/rooms
POST   /api/rooms
PUT    /api/rooms/{id}
DELETE /api/rooms/{id}

Bookings
POST   /api/bookings
GET    /api/bookings
GET    /api/bookings/{id}
DELETE /api/bookings/{id}
```

> API endpoints may differ depending on the implementation.

---

## 🔑 Key Concepts Implemented

* RESTful API development
* MVC architecture
* Layered backend architecture
* Spring Boot
* Spring Data JPA
* Hibernate ORM
* MySQL database
* Entity relationships
* CRUD operations
* JWT authentication
* Spring Security
* Role-based authorization
* React frontend
* API integration
* Exception handling
* Database persistence

---

## 🔮 Future Improvements

* Online payment integration
* Email booking confirmation
* Advanced room search and filtering
* Pagination and sorting
* Admin dashboard
* Customer reviews and ratings
* Docker containerization
* Cloud deployment
* Cloud database integration
* Automated unit and integration testing

---

## 👨‍💻 Author

**Sri Prahas Annambhotla**

B.Tech – Computer Science and Business Systems
**VIT-AP University**

GitHub: https://github.com/ASriprahas

LinkedIn: https://www.linkedin.com/in/sri-prahas-0396b1330/
