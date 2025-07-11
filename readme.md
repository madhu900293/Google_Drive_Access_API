# Google Drive Access API

This project is a **Spring Boot** application that demonstrates secure integration with the **Google Drive API**. It uses **JWT authentication** for securing endpoints and **Swagger** for interactive API documentation.

## Features

- **Google Drive Integration:** Upload, download, and manage files on Google Drive.
- **JWT Authentication:** Secure REST APIs with JSON Web Tokens.
- **Swagger UI:** Interactive API documentation and testing.
- **Spring Boot:** Rapid development with production-ready features.

## Prerequisites

- Java 17 or higher
- Maven
- Google Cloud Service Account (credentials file, not committed to repo)
- [Create a Google Cloud project and enable the Drive API](https://developers.google.com/drive/api/v3/quickstart/java)

## Getting Started

1. **Clone the repository:**

   ```sh
   git clone https://github.com/madhu900293/Google_Drive_Access_API.git
   cd Google_Drive_Access_API
   ```

2. **Add your Google credentials:**

   - Place your `credentials.json` (Google Service Account) in `src/main/resources/`.
   - **Do not commit this file.** It is in `.gitignore`.

3. **Configure application properties:**

   Edit `src/main/resources/application.properties` as needed for your environment.

4. **Build and run the application:**

   ```sh
   mvn clean install
   mvn spring-boot:run
   ```

5. **Access Swagger UI:**

   Visit [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html) to explore and test the API.

## API Endpoints

- `/auth/login` – Obtain JWT token
- `/drive/upload` – Upload file to Google Drive
- `/drive/download/{fileId}` – Download file from Google Drive
- `/drive/list` – List files in Google Drive

*(Endpoints may vary; see Swagger UI for full details.)*

## Security

- All endpoints (except `/auth/login`) require a valid JWT token in the `Authorization` header.
- Never commit your `credentials.json` or any secrets to version control.

## License

This project is licensed under the MIT License.

---

**Contributions are welcome!**  
For issues or feature requests, please open an issue on GitHub.
