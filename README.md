# Contact Management App with SQLite

This project is a Spring Boot-based web application that manages contacts (full name, phone number, email, and profile picture). The app uses SQLite as the database and has features for uploading and handling images both locally and via AWS S3 in production.

## Table of Contents

- [Prerequisites](#prerequisites)
- [Setup](#setup)
    - [1. Clone the Repository](#1-clone-the-repository)
    - [2. Install Dependencies](#2-install-dependencies)
    - [3. Run the Application](#3-run-the-application)
- [Accessing the Application](#accessing-the-application)
- [Using the Application](#using-the-application)
- [Production](#production)
    - [S3 Integration](#s3-integration)
          - [Data Initialization](#data-initialization)

## Prerequisites

- **Java** (JDK 17 or higher)
- **Maven** (for building the backend)
- **Node.js** (for the Angular frontend)
- **SQLite** (included with the project, no external setup required)
- **AWS S3** (for production image storage, if applicable)

## Setup

### 1. Clone the Repository

```bash
git clone https://github.com/starlord0302/contacts-app.git
cd contacts-app
```

### 2. Install Dependencies

- **Backend dependencies (Spring Boot):**
  - Change branch to *development*
    ```bash
     git checkout development
     ```
  - Navigate to the `contacts-backend` directory and run:
     ```bash
     cd contacts-backend
     mvn clean install
     ```

- **Frontend dependencies (Angular):**
  - Navigate to the `contacts-frontend` directory and run:
     ```bash
     cd ../contacts-frontend
     npm i
     ```

### 3. Run the Application

#### Running the Backend

   ```bash
   cd contacts-backend
   mvn spring-boot:run
   ```

  This will use SQLite as the database and store images in `/uploads`.

#### Running the Frontend

  Navigate to the `contacts-fronted` directory and run:

   ```bash
   cd contacts-frontend
   npm run dev
   ```

  This will start the Angular development server on `http://localhost:4200`.

### Accessing the Application

- **Backend (API)**: `http://localhost:8080/api/contacts`
- **Frontend**: `http://localhost:4200`

## Using the Application

- **Add a Contact**: Via the frontend or API, you can add a contact by providing the full name, phone number, email, and optionally upload a profile picture.
- **List Contacts**: View all the contacts and their details.
- **Update a Contact**: Edit the details of a contact.
- **Delete a Contact**: Remove a contact from the list.

## Production

### S3 Integration

- **!Still in development!**
- In production mode, the application uploads and serves images from AWS S3.
- Before using it, make sure you have an AWS account and an S3 bucket.
- The backend is currently set up to use a bucket from the EU_North_1 region.
- Change branch to *master*
    ```bash
     git checkout master
     ```
- Copy the `.env-sample` file to `.env` and fill in the required values:
   ```bash
   cp .env-sample .env
   ```
- Configure your AWS credentials in the `.env` file:
   ```env
  S3_ACCESS_KEY=your_access_key
  S3_SECRET_KEY=your_secret_key
  S3_BUCKET_NAME=your_bucket_name
  FRONTEND_URL=frontend_url
   ```

#### Data Initialization

The app uses `data-production.sql` (for production) and `data-development.sql` (for local development) to initialize the database with sample contact data.
If you are using your own s3 bucket, make sure to change the `profile_picture` column in the `contacts` table to the correct s3 url inside the `data-production.sql file.

## Future Improvements

- Fix the Edit Contact feature in the frontend. Currently, it does not work as expected. Image will be lost when editing a contact details.
- Dockerize the application for easier deployment.
- 