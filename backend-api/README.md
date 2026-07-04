# FitMatch Backend API

**Version:** 1.0
**Last Updated:** July 5, 2026
**Base URL:** `http://localhost:8080/api`

## Table of Contents

1. [Overview](#1-overview)
2. [UML Class Diagram](#2-uml-class-diagram)
3. [API Endpoints](#3-api-endpoints)
   - [Customer Endpoints](#31-customer-endpoints)
   - [Trainer Endpoints](#32-trainer-endpoints)
   - [Training Service Endpoints](#33-training-service-endpoints)
   - [Training Session Endpoints](#34-training-session-endpoints)
   - [Review Endpoints](#35-review-endpoints)
   - [Exercise Endpoints](#36-exercise-endpoints)

---

## 1. Overview

The FitMatch Backend API is a RESTful service that provides endpoints for managing:

- **Customers**: User accounts for individuals seeking fitness services.
- **Trainers**: Professional profiles for fitness instructors.
- **Training Services**: Listings of available training programs.
- **Training Sessions**: Scheduled appointments between customers and trainers.
- **Reviews**: Feedback and ratings from customers about their training experiences.

## 2. UML Class Diagram

![UML Class Diagram](../docs/uml-class-diagram.png)

## 3. API Endpoints

### 3.1 Customer Endpoints

#### Create a New Customer

```http
POST /api/customers
```

**Request Body:**

```json
{
  "accountStatus": "active",
  "currentWeight": 150,
  "email": "johndoe@demo.com",
  "fitnessGoals": "strength",
  "fitnessLevel": "INTERMEDIATE",
  "goalWeight": 180,
  "name": "John Doe",
  "password": "password123"
}
```

**Response:**

```json
{
  "accountStatus": "active",
  "currentWeight": 150,
  "email": "johndoe@demo.com",
  "fitnessGoals": "strength",
  "fitnessLevel": "INTERMEDIATE",
  "goalWeight": 180,
  "id": 1,
  "injuriesOrHealthConcerns": null,
  "name": "John Doe",
  "password": "password123",
  "phoneNumber": null,
  "phoneNumber": null,
  "reviews": [],
  "trainingSessions": []
}
```

#### Get Customer by ID

```http
GET /api/customers/{id}
```

**Response:**

```json
{
  "accountStatus": "active",
  "currentWeight": 145,
  "email": "janedoe@demo.com",
  "fitnessGoals": "strength, recomposotion",
  "fitnessLevel": "INTERMEDIATE",
  "goalWeight": 160,
  "id": 5,
  "injuriesOrHealthConcerns": "wrist sprain",
  "name": "Jane Doe",
  "password": "password",
  "phoneNumber": "(555) 555-5555",
  "reviews": [],
  "trainingSessions": []
}
```

#### Get all Customers

```http
GET /api/customers
```

**Response:**

```json
[
  {
    "accountStatus": "active",
    "currentWeight": 150,
    "email": "johndoe@demo.com",
    "fitnessGoals": "strength",
    "fitnessLevel": "INTERMEDIATE",
    "goalWeight": null,
    "id": 3,
    "injuriesOrHealthConcerns": null,
    "name": "John Doe",
    "password": "password123",
    "phoneNumber": null,
    "reviews": [],
    "trainingSessions": []
  },
  {
    "accountStatus": "active",
    "currentWeight": 145,
    "email": "janedoe@demo.com",
    "fitnessGoals": "strength, recomposotion",
    "fitnessLevel": "INTERMEDIATE",
    "goalWeight": 160,
    "id": 5,
    "injuriesOrHealthConcerns": "wrist sprain",
    "name": "Jane Doe",
    "password": "password",
    "phoneNumber": "(555) 555-5555",
    "reviews": [],
    "trainingSessions": []
  }
]
```

#### Update Customer Personal Information

```http
PUT /api/customers/{id}/personal-info
```

**Request Body:**

```json
{
  "name": "Jane Bee Doe",
  "email": "janedoe@demo.com",
  "phoneNumber": "(555) 555-5556"
}
```

**Response:**

```json
{
  "accountStatus": "active",
  "currentWeight": 145,
  "email": "janedoe@demo.com",
  "fitnessGoals": "strength, recomposotion",
  "fitnessLevel": "INTERMEDIATE",
  "goalWeight": 160,
  "id": 5,
  "injuriesOrHealthConcerns": "wrist sprain",
  "name": "Jane Bee Doe",
  "password": "password",
  "phoneNumber": "(555) 555-5556",
  "reviews": [],
  "trainingSessions": []
}
```

#### Update Customer Fitness Information

```http
PUT /api/customers/{id}/fitness-info
```

**Request Body:**

```json
{
  "currentWeight": 150,
  "goalWeight": 160,
  "fitnessLevel": "ADVANCED",
  "fitnessGoals": "strength, recomposotion",
  "injuriesOrHealthConcerns": "wrist sprain"
}
```

**Response:**

```json
{
  "accountStatus": "active",
  "currentWeight": 150,
  "email": "janedoe@demo.com",
  "fitnessGoals": "strength, recomposotion",
  "fitnessLevel": "ADVANCED",
  "goalWeight": 160,
  "id": 5,
  "injuriesOrHealthConcerns": "wrist sprain",
  "name": "Jane Bee Doe",
  "password": "password",
  "phoneNumber": "(555) 555-5556",
  "reviews": [],
  "trainingSessions": []
}
```
