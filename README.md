# Spring Boot Single Product Drone eCommerce App 🚀

Welcome to a simple yet powerful eCommerce app built with **Spring Boot**! 🛒 This application allows users to purchase a drone through the **Stripe** payment gateway, demonstrating how to securely process payments using Stripe’s **Checkout Session**.

🚧 **Note:** This project is still a **work in progress**. Stay tuned for updates! A **live testing version** of the app will be available soon. 🎉

---

## ✨ Features

- **🛍️ Single Product Showcase**: Display a single product with details and pricing.
- **💳 Stripe Payment Integration**: Seamlessly process payments using the Stripe API, ensuring secure transactions.
- **🔔 Stripe Webhook Integration**: Uses **Stripe Webhooks** to securely confirm orders and prevent unauthorized purchases.
- **📱 Responsive Design**: Built with **Thymeleaf** and **Bootstrap** for a sleek, user-friendly interface on both desktop and mobile devices.

---

## 🖼️ Application Flow

Here's a clear, visual walkthrough of the app's flow:

### 1. Main Page
The main page showcases the product with details and the option to add it to the cart.

![Main Page Screenshot](https://onyx-demo-assets.s3.us-east-1.amazonaws.com/shop_page_screenshot.png)

---

### 2. Cart
Once the product is added to the cart, users can view and proceed to checkout.

![Cart Screenshot](https://onyx-demo-assets.s3.us-east-1.amazonaws.com/cart_page_screenshot.png)

---

### 3. Stripe Checkout
The Stripe checkout page allows users to securely enter their payment details and it also collects shipping info.

![Stripe Checkout Screenshot](https://onyx-demo-assets.s3.us-east-1.amazonaws.com/payment_screenshot.png)

---

### 4. Success Order Confirmation
After completing the purchase, users are shown a confirmation page with the order identifier and success message.

![Order Confirmation Screenshot](https://onyx-demo-assets.s3.us-east-1.amazonaws.com/success_payment_screenshot.png)

---

## 🏃‍♂️ Prerequisites

Before running the application, make sure you have the following:

- **☕ Java 11+**: The application is built with **Spring Boot** and requires Java 11 or higher.
- **🔧 Maven**: Use Maven to build and manage project dependencies.
- **💳 Stripe Account**: You’ll need a **Stripe account** to get the **API keys** for payment processing.
- **🐬 MySQL Server**: To store product details and guest order information, you’ll need a **MySQL server**.

---

## 📋 Important Notes

- **Guest Checkout**: The app does not support account creation. Users are treated as guest customers, and their order information is stored in the database.
- **Application Properties File**: The `application.properties` file is not included in the repository for security reasons. You must create this file inside the `src/main/resources/` directory before running the application. This file contains important configurations such as database connection details, Stripe API keys, and session settings.
- **Expected Database Product**: The application expects to find a product with **ID 1** in the database. Make sure to insert at least one product in the database before running the app.
- **Easily Customizable for Any Product**: Although this app is set up to sell a drone, it can be used to sell **any single product**. To customize it for a different product, simply update the product details in the database and modify the product page according to the new product.

### 📄 `application.properties` Template

Below is an example of what your `application.properties` file should look like:

```properties
# Database configuration  
spring.datasource.url=  
spring.datasource.username=  
spring.datasource.password=  

# HikariCP connection pool settings  
# maxLifetime must be several seconds shorter than MySQL wait_timeout  
spring.datasource.hikari.maxLifetime=27970000  
spring.datasource.hikari.keepaliveTime=30000  

spring.jpa.hibernate.ddl-auto=update  
spring.jpa.properties.hibernate.show_sql=true  
spring.jpa.properties.hibernate.highlight_sql=true  

#servlet configuration
server.servlet.session.tracking-modes=COOKIE  

# Stripe configuration  
stripe.secretApiKey=  
# The HTTPS URL Stripe will redirect to after success/cancel payment  
stripe.domain=  
# The webhook secret you'll get when creating Stripe webhooks  
stripe.webhookSecret=  


