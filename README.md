# Spring Boot Single Product eCommerce App 🚀

Welcome to a simple yet powerful eCommerce app built with **Spring Boot**! 🛒 This application allows users to purchase a single product through the **Stripe** payment gateway, demonstrating how to securely process payments using Stripe’s **Checkout Session**.

🚧 **Note:** This project is still a **work in progress**. The core functionality is in place and nearly ready for production, but some features are still being finalized. Stay tuned for updates! A **live testing version** of the app will be available soon. 🎉

---

## ✨ Features

- **🛍️ Single Product Showcase**: Display a single product with details and pricing.
- **💳 Stripe Payment Integration**: Seamlessly process payments using the Stripe API, ensuring secure transactions.
- **📱 Responsive Design**: Built with **Thymeleaf** and **Bootstrap** for a sleek, user-friendly interface on both desktop and mobile devices.
- **👤 Guest Checkout**: Users do not need to create an account to make a purchase. After checkout, customers are registered as guest users.
- **🛒 Cart Session**: Cart information is temporarily saved in the session, ensuring a smooth shopping experience during the user’s session.

---

## 🖼️ Application Flow

Here's a clear, visual walkthrough of the app's flow:

### 1. Main Page Screenshot
The main page showcases the product with details and the option to add it to the cart.

![Main Page Screenshot](https://onyx-demo-assets.s3.us-east-1.amazonaws.com/screenshot_onyx_main_page.png)

---

### 2. Cart Screenshot
Once the product is added to the cart, users can view and proceed to checkout.

![Cart Screenshot](https://onyx-demo-assets.s3.us-east-1.amazonaws.com/screenshot_onyx_cart.png)

---

### 3. Stripe Checkout Screenshot
The Stripe checkout page allows users to securely enter their payment details.

![Stripe Checkout Screenshot](https://onyx-demo-assets.s3.us-east-1.amazonaws.com/screenshot_onyx_stripe_checkout.png)

---

### 4. Success Order Confirmation Screenshot
After completing the purchase, users are shown a confirmation page with order details and success message.

![Order Confirmation Screenshot](https://onyx-demo-assets.s3.us-east-1.amazonaws.com/screenshot_onyx_order_confirmation.png)

---

## 🏃‍♂️ Prerequisites

Before running the application, make sure you have the following:

- **☕ Java 11+**: The application is built with **Spring Boot** and requires Java 11 or higher.
- **🔧 Maven**: Use Maven to build and manage project dependencies.
- **💳 Stripe Account**: You’ll need a **Stripe account** to get the **API keys** for payment processing.
- **🐬 MySQL Server**: To store product details and guest order information, you’ll need a **MySQL server**.

---

## 📋 Important Notes

- **Guest Checkout**: The app does not support account creation. Users are treated as guest customers and their order information is stored temporarily in the database for order history purposes.
- **Session-Based Cart**: The cart information is saved in the user's session. This is a simple solution for a single product cart but is not designed to be persistent across sessions or for use with multiple products.
- **Not Meant for Scalability**: This application is designed as a simple demonstration of the payment flow and is not intended to be highly scalable or production-ready. It is a lightweight app to showcase basic eCommerce functionality with Stripe.

---

