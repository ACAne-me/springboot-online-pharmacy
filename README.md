
# 🛒 Online Pharmacy Based on Spring Boot

## 📋 Project Overview
This is a simplified online pharmacy management system based on Spring Boot, featuring basic functionalities for medicine management, shopping, and user administration.

## 📁 Project Structure
```
Project-paper/
├── admin/admin/        # Admin Panel frontend (Vue.js)
├── front/front/        # User Frontend (Vue.js)
├── shopping/           # Backend project (Spring Boot)
│   ├── database/       # Database scripts
│   └── src/            # Backend source code
└── .gitignore          # Git ignore rules
```

## 📦 Backend Environment Configuration and Startup

### Environment Requirements
- **JDK**: 1.8 or higher
- **Maven**: 3.6.0 or higher
- **Database**: MySQL 8

### Database Configuration
1. Ensure MySQL service is running
2. Create database:
   ```sql
   CREATE DATABASE springboothc349102 CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
   ```
3. Import database script:
   ```bash
   # Import via command line
   mysql -u root -p springboothc349102 < shopping/database/springboothc349102.sql
   
   # Or use MySQL Workbench to import shopping/database/springboothc349102.sql
   ```

### Configuration File Setup
Check and modify database connection settings in `shopping/src/main/resources/application.yml`:
```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/springboothc349102?useUnicode=true&characterEncoding=utf-8&serverTimezone=UTC
    username: root  # Replace with your MySQL username
    password: root  # Replace with your MySQL password
    driver-class-name: com.mysql.cj.jdbc.Driver
```

### Spring Boot Startup Methods

#### Method 1: Start via IDE
1. Open the `shopping` directory with IntelliJ IDEA or Eclipse
2. Wait for Maven dependencies to download completely
3. Locate the main startup class: `shopping/src/main/java/com/SpringbootSchemaApplication.java`
4. Right-click on this file and select "Run SpringbootSchemaApplication"

#### Method 2: Start via Maven Command Line
```bash
# Navigate to backend project directory
cd shopping

# Package the project
mvn clean package

# Run the packaged jar file
java -jar target/[packaged-jar-filename].jar

# Or run directly (without packaging)
mvn spring-boot:run
```

#### Method 3: Start via Script
For Windows systems, use the `mvnw.cmd` script:
```bash
cd shopping
./mvnw.cmd spring-boot:run
```

### Verify Backend Startup
After startup, visit http://localhost:8080/ to check if the API documentation or welcome page is displayed

## 🎨 Frontend Environment Configuration and Startup

### Environment Requirements
- **Node.js**: v14.21.3
- **npm**: v6.14.18

### Admin Panel Startup
```bash
# Navigate to admin panel project directory
cd admin/admin

# Install dependencies
npm install

# Start development server
npm run serve
```
Access after startup: http://localhost:8081/ (port may vary based on actual output)

### User Frontend Startup
```bash
# Navigate to user frontend project directory
cd front/front

# Install dependencies
npm install

# Start development server
npm run serve
```
Access after startup: http://localhost:8083/ (port may vary based on actual output)

## 🔑 Default Accounts

### Admin Panel
- Username: `admin`
- Password: `admin`

### User Frontend
- Example account:
  - Username: `用户名1`
  - Password: `123456`
- New users can create accounts through the registration function

## ⚙️ Current Core Functions

### Basic Backend Functions
- **User Management**: User registration, login, profile management
- **Medicine Management**: Medicine CRUD, category management
- **Order Management**: Basic order creation and processing
- **Shopping Cart**: Add, remove, modify items
- **Search**: Search medicines by name or category

### Admin Panel Functions
- **User Management**: View and manage user accounts
- **Medicine Management**: Manage medicine information
- **Order Management**: View and update order status

### User Frontend Functions
- **Medicine Browsing**: Browse medicines by category
- **Shopping**: Add to cart and checkout
- **User Center**: View order history and update profile

## ❌ Removed Features
Please note that this simplified version has removed several advanced features that were present in the original project:
- **Machine Learning Recommendation Algorithm**: Personalized medicine recommendations based on user behavior
- **Logistics Tracking**: Real-time order tracking integration with Chinese logistics APIs
- **Chinese Payment API Integration**: Alipay, WeChat Pay, and other local payment methods
- **SMS Verification**: Chinese SMS service integration for user verification
- **Complex Data Analytics**: Advanced sales forecasting and inventory optimization
- **Delivery Route Optimization**: AI-based delivery route planning

This simplification was done to reduce dependencies on region-specific services and complex algorithms, making the system more lightweight and easier to deploy in different environments.

## 🔧 Common Issues and Solutions

### Database Connection Failure
- Check if MySQL service is running
- Verify database connection information in `application.yml` is correct
- Ensure database `springboothc349102` has been created and imported with correct SQL script

### Port Conflict
- If port 8080 is occupied, modify `server.port` configuration in `application.yml`
- If frontend ports are occupied, modify `devServer.port` configuration in respective project's `vue.config.js`

### Dependency Download Failure
- Backend: Check Maven configuration to ensure access to Maven repositories
- Frontend: Try cleaning cache with `npm cache clean --force` before reinstalling

## 📋 Development Guidelines
- Backend: Follow Spring Boot best practices, use RESTful API design
- Frontend: Follow Vue.js component development specifications
- Database: Use parameterized queries to prevent SQL injection
- Code Style: Maintain consistent naming conventions and commenting habits

## 📌 Notes
- In development environment, ensure MySQL service is always running
- In production environment, it's recommended to change default account passwords and sensitive information in configuration files
- For server deployment, refer to Spring Boot and Vue.js deployment documentation
