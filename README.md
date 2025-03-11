# Spring Boot Single Product Drone eCommerce App 🚀

Welcome to a simple yet powerful eCommerce app built with **Spring Boot**! 🛒 This application allows users to purchase a drone through the **Stripe** payment gateway, demonstrating how to securely process payments using Stripe’s **Checkout Session**.

🚧 **Note:** This project is still a **work in progress**. Stay tuned for updates! A **live testing version** of the app will be available soon. 🎉

---

## ✨ Features

- **🛍️ Single Product Showcase**: Display a single product with details and pricing.
- **💳 Stripe Payment Integration**: Seamlessly process payments using the Stripe API, ensuring secure transactions.
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

- **Guest Checkout**: The app does not support account creation. Users are treated as guest customers and their order information is stored in the database.

- **Not Meant for Scalability**: This application is designed as a simple demonstration of the payment flow and is not intended to be highly scalable or production-ready. It is a lightweight app to showcase basic eCommerce functionality with Stripe.

---

