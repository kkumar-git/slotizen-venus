# User Registration API Implementation

## Overview

I have successfully implemented the user registration API with OTP verification functionality as requested. The implementation includes:

## Features Implemented

✅ **User Registration Endpoint**
- POST `/api/auth/register`
- Complete validation using Spring Boot Validation
- Password encryption using BCrypt
- Email uniqueness validation

✅ **User Entity Model**
- firstName, lastName, email, phone
- companyName, businessType, password
- Audit fields (createdAt, updatedAt)
- Relationship with roles and OTP tokens

✅ **OTP System**
- 6-digit OTP generation using SecureRandom
- 10-minute expiration time
- Email delivery via JavaMailSender
- OTP validation and usage tracking
- Support for different OTP types (REGISTRATION, PASSWORD_RESET, LOGIN)

✅ **Email Configuration**
- SMTP configuration in application.yml
- Environment variable support for email credentials
- Professional email templates

✅ **Security Configuration**
- BCrypt password encoder
- Public access to auth endpoints
- CSRF disabled for API usage

## API Specification

### POST /api/auth/register

**Request Body:**
```json
{
  "firstName": "John",
  "lastName": "Doe",
  "email": "john@example.com",
  "phone": "+1234567890",
  "companyName": "John's Salon",
  "businessType": "salon-beauty",
  "password": "securePassword123"
}
```

**Successful Response (200 OK):**
```json
{
  "success": true,
  "message": "Registration successful. OTP sent.",
  "data": {
    "userId": "user_123",
    "email": "john@example.com",
    "otpSent": true,
    "verificationMethod": "email"
  }
}
```

**Error Response (400 Bad Request):**
```json
{
  "success": false,
  "message": "Email already registered",
  "data": null
}
```

## Validation Rules

- **firstName**: Required, not blank
- **lastName**: Required, not blank  
- **email**: Required, valid email format, unique
- **phone**: Required, international format (+[country][number])
- **companyName**: Required, not blank
- **businessType**: Required, not blank
- **password**: Required, minimum 8 characters

## Database Schema

### Users Table
```sql
CREATE TABLE users (
    id BIGSERIAL PRIMARY KEY,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    phone VARCHAR(20) NOT NULL,
    company_name VARCHAR(255) NOT NULL,
    business_type VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    enabled BOOLEAN NOT NULL DEFAULT FALSE,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL
);
```

### OTP Tokens Table
```sql
CREATE TABLE otp_tokens (
    id BIGSERIAL PRIMARY KEY,
    otp VARCHAR(10) NOT NULL,
    expiry TIMESTAMP NOT NULL,
    created_at TIMESTAMP NOT NULL,
    used BOOLEAN NOT NULL DEFAULT FALSE,
    type VARCHAR(20) NOT NULL,
    user_id BIGINT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(id)
);
```

## Configuration Required

### Environment Variables
Set these environment variables for email functionality:
```bash
MAIL_HOST=smtp.gmail.com
MAIL_PORT=587
MAIL_USERNAME=your-email@gmail.com
MAIL_PASSWORD=your-app-password
```

### Database Configuration
Update `application.yml` with your database credentials:
```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/slotizen-db
    username: ${DB_USERNAME:postgres}
    password: ${DB_PASSWORD:root}
```

## Testing the API

### Using cURL
```bash
curl -X POST http://localhost:8081/api/auth/register \
  -H "Content-Type: application/json" \
  -d '{
    "firstName": "John",
    "lastName": "Doe", 
    "email": "john@example.com",
    "phone": "+1234567890",
    "companyName": "John'\''s Salon",
    "businessType": "salon-beauty",
    "password": "securePassword123"
  }'
```

### Using Postman
1. Method: POST
2. URL: `http://localhost:8081/api/auth/register`
3. Headers: `Content-Type: application/json`
4. Body: Raw JSON (use the request body example above)

## Components Created/Updated

### Entities
- `User.java` - Updated with all required fields
- `OtpToken.java` - Enhanced with validation and utility methods

### DTOs
- `RegisterRequest.java` - Updated with validation annotations
- `RegisterResponse.java` - New response DTO with nested data structure

### Services
- `OtpService.java` - New interface for OTP operations
- `OtpServiceImpl.java` - Complete OTP implementation
- `UserServiceImpl.java` - Updated registration logic

### Repositories
- `OtpTokenRepository.java` - Enhanced with custom queries
- `UserRepository.java` - Existing, works with new User model

### Controllers
- `AuthController.java` - Updated register endpoint with validation

### Configuration
- `SecurityConfig.java` - Added PasswordEncoder bean
- `application.yml` - Added email configuration
- `build.gradle` - Added mail and validation dependencies

## Next Steps

To complete the registration flow, you may want to implement:

1. **OTP Verification Endpoint** - Verify the OTP and enable user account
2. **Resend OTP Endpoint** - Allow users to request new OTP
3. **Login Endpoint** - JWT-based authentication
4. **Database Migration** - Update Liquibase changelogs for schema changes

## Security Considerations

- Passwords are encrypted using BCrypt
- OTP tokens expire after 10 minutes
- Used OTPs are marked and cannot be reused
- Email validation prevents duplicate registrations
- Input validation prevents SQL injection and XSS
- Rate limiting should be added in production

The implementation follows Spring Boot best practices and provides a solid foundation for user registration with email verification.