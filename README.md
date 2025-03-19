# PeakForm - Fitness App

**PeakForm** is a web-based application that helps users achieve their health goals such as weight loss, muscle gain, and fitness improvement. The app generates personalized workout plans and meal plans based on a questionnaire filled out by the user. It also allows users to track their progress and interact with other users through public posts and comments. Additionally, the app integrates with food databases to manage diets and recipes.

## Features

- **Personalized workout plans**: Automatically generated based on user inputs.
- **Meal plans**: Personalized meal plans to complement fitness goals.
- **Progress tracking**: Users can log workouts and meals, track their progress.
- **Social interaction**: Users can post updates, share experiences, and comment on others' posts.
- **Food and recipe integration**: Access a variety of foods and recipes to help with meal planning.

## Tech Stack

- **Backend**: Spring Boot
- **Frontend**: React
- **Database**: PostgreSQL
- **Authentication**: JWT (JSON Web Token)
- **CI/CD**: GitHub Actions
- **Containerization**: Docker

## Installation

### Prerequisites

Before running the application, make sure you have the following installed:

- Java 17 or later
- Node.js (for the frontend)
- Docker (for containerization)
- PostgreSQL (or use Docker to run PostgreSQL)

### Backend Setup

1. Clone the repository:
   ```bash
   git clone https://github.com/yourusername/peakform.git
   cd peakform/backend
### Build the backend:

   ```bash
   ./mvnw clean install
   ```
### Create a .env file in the backend directory and add your environment variables, such as the database URL.

### Run the backend locally:

   ```bash
   ./mvnw spring-boot:run
   ```
## Frontend Setup

### Navigate to the frontend directory:

   ```bash
   cd ../frontend
   ```
### Install dependencies:

   ```bash
   npm install
   ```
### Start the frontend:

```bash
npm start
```
The app should now be running on http://localhost:3000 for the frontend and http://localhost:8080 for the backend.

## Docker Setup
If you'd like to run the application using Docker, you can do so by using Docker Compose:

1. Build and run the application using Docker Compose:

```bash
docker-compose up --build
```
2. This will start both the backend and PostgreSQL containers. The frontend will be available on port 3000.

## Database Schema
The database schema for the application is defined in the schema.sql file. It includes tables for users, workouts, meals, posts, comments, and more.

## Contributing
1. Fork the repository.
2. Create a new branch (git checkout -b feature-name).
3. Make your changes.
4. Commit your changes (git commit -am 'Add new feature').
5. Push to the branch (git push origin feature-name).
6. Create a new Pull Request.
## License
This project is licensed under the GNU General Public License v3.0 - see the [LICENSE](LICENSE) file for details.

## Acknowledgements
1. Spring Boot
2. React
3. PostgreSQL
4. Docker