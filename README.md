# Smart Contact Manager

A full-stack contact management web application built using **Spring Boot** that allows users to securely store, manage, and organize personal and professional contacts.

The application includes authentication, cloud image uploads, OAuth login, responsive UI, and secure contact management features.

---

## Features

* User Authentication & Authorization
* Secure Login & Registration
* Google OAuth Login
* GitHub OAuth Login
* Add, Update & Delete Contacts
* Upload Contact Images using Cloudinary
* Mark Favorite Contacts
* Search Contacts
* Responsive Dashboard UI
* Form Validation
* Session-Based Flash Messages
* Password Encryption using BCrypt
* Spring Security Integration
* Email Support
* Pagination Support

---

## Tech Stack

### Backend

* Java
* Spring Boot
* Spring MVC
* Spring Security
* Spring Data JPA
* Hibernate

### Frontend

* Thymeleaf
* Tailwind CSS
* HTML5
* CSS3
* JavaScript

### Database

* MySQL

### Cloud Services

* Cloudinary

### Build Tool

* Maven

---

## Project Architecture

The project follows the MVC architecture:

```text
Controller → Service → Repository → Database
```

---

## Folder Structure

```text
src/
 ├── main/
 │   ├── java/com/scm30/
 │   │   ├── config/
 │   │   ├── controllers/
 │   │   ├── entity/
 │   │   ├── repositories/
 │   │   ├── services/
 │   │   ├── helper/
 │   │   └── model/
 │   └── resources/
 │       ├── templates/
 │       ├── static/
 │       └── application.properties
```

---

## Installation & Setup

### 1. Clone Repository

```bash
git clone https://github.com/153426759486/Smart-Contact-Manager.git
cd Smart-Contact-Manager
```

---

### 2. Create MySQL Database

```sql
CREATE DATABASE scm;
```

---

### 3. Configure `application.properties`

```properties
# Database Configuration
spring.datasource.url=jdbc:mysql://localhost:3306/scm
spring.datasource.username=your_username
spring.datasource.password=your_password

# Hibernate
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

# Cloudinary Configuration
cloudinary.cloud.name=your_cloud_name
cloudinary.api.key=your_api_key
cloudinary.api.secret=your_api_secret

# Google OAuth
spring.security.oauth2.client.registration.google.client-id=your_google_client_id
spring.security.oauth2.client.registration.google.client-secret=your_google_client_secret

# GitHub OAuth
spring.security.oauth2.client.registration.github.client-id=your_github_client_id
spring.security.oauth2.client.registration.github.client-secret=your_github_client_secret
```

---

## Run the Project

```bash
mvn spring-boot:run
```

Open your browser:

```text
http://localhost:8080
```

---

## Main Functionalities

### Authentication System

* Secure signup and login
* OAuth login with Google & GitHub
* BCrypt password encryption
* Spring Security authentication

### Contact Management

* Add contacts
* Update contacts
* Delete contacts
* Search contacts
* Mark contacts as favorite

### Cloudinary Integration

* Upload and store contact images securely
* Organize images into cloud folders

### Validation & Security

* Form validation using Jakarta Validation
* CSRF protection
* Session-based success/error messages

---

## Screenshots

Add project screenshots here.

```md
![Dashboard](screenshots/dashboard.png)
![Add Contact](screenshots/add-contact.png)
![Profile](screenshots/profile.png)
```

---

## Future Improvements

* Dark Mode
* Contact Export (PDF/Excel)
* Email Contacts Directly
* REST API Version
* Contact Sharing
* Improved Mobile Responsiveness
* Profile Customization

---

## Learning Outcomes

This project helped in understanding:

* Spring Boot MVC Architecture
* Authentication & Authorization
* Spring Security
* Thymeleaf Templating
* JPA & Hibernate
* Cloudinary File Uploads
* Session Handling
* Form Validation
* Git & GitHub Workflow
* Full Stack Development Concepts

---

## Author

### Aman Gaur

GitHub Repository:

[https://github.com/153426759486/Smart-Contact-Manager](https://github.com/153426759486/Smart-Contact-Manager)

---

## License

This project is created for learning and educational purposes.
