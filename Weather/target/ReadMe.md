# Weather Application

## Overview

The Weather Application is a Spring Boot project designed to fetch and store real-time weather data for various cities using the OpenWeather API. This application provides an easy way to access weather information, including temperature, weather conditions, and daily summaries for specified cities. It utilizes a MySQL database to store historical weather data and generates daily summaries of weather conditions.

## Features

- **Real-time Weather Data**: Fetches current weather data for predefined cities every 5 minutes.
- **Temperature Conversion**: Converts temperature data from Kelvin to Celsius.
- **Database Storage**: Stores weather data and summaries in a MySQL database.
- **Daily Summaries**: Generates daily weather summaries, including average, minimum, and maximum temperatures, along with dominant weather conditions.
- **RESTful API**: Provides API endpoints to retrieve daily weather summaries for specific cities.
- **Error Handling**: Includes exception handling for API requests and database operations.

## Technologies Used

- **Java**: Primary programming language for backend development.
- **Spring Boot**: Framework for building and running the application.
- **MySQL**: Database management system for storing weather data.
- **OpenWeather API**: External service to fetch weather data.
- **JUnit**: Testing framework for unit tests.
- **Maven**: Build automation tool for Java projects.

## Setup Instructions

### Prerequisites

Before you begin, ensure you have the following installed:

- **Java Development Kit (JDK)** 11 or higher
- **MySQL Server**
- **Maven**
- **OpenWeather API key** (Sign up at [OpenWeather](https://openweathermap.org/) for a free API key)

### Steps to Run the Application

#### Step 1: Clone the Repository

1. **Open your terminal or command prompt.**
2. **Run the following command to clone the project repository to your local machine:**

### Key Additions

- **Step-by-Step Instructions**: Each step for setting up the project now includes explicit instructions on what to do and what commands to run.
- **Detailed Explanations**: Added explanations for commands and configurations to ensure clarity.
- **Personalization Options**: Reminded users to replace placeholders with their actual credentials and API keys.
