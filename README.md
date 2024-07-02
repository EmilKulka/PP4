# E-Commerce Project

Welcome to the E-Commerce project developed as part of the Programming Workshop IV course at Cracow University of Economics. This project aims to provide hands-on experience in:

- **Test-Driven Development (TDD)**
- **Object-Oriented Programming (OOP) principles**
- **Programming best practices**
- **Java and Spring Boot environment**
- **PayU Api**

## Overview

In this workshop, we were building an E-Commerce application from scratch. The project covers a range of functionalities typical of an E-Commerce platform, product browsing, cart management, order processing, and more. 
**The project isn't finished and is actively maintained and continues to evolve.**


## Features

- **Payment Processing:** Integrates with PayU API for secure transaction handling.
- **Shopping Cart Management:** Manages user shopping carts, allowing addition, removal, and modification of items.
- **Offer Calculation:** Calculates pricing and generates quotations based on selected items and configurations.
- **Facade Design Pattern:**
    - **Unified Interface:** `SalesFacade` provides a unified interface to handle operations related to shopping carts, offers, payments, and reservations without exposing the complexities of individual components like `Cart`, `OfferCalculator`, `PaymentGateway`, and `ReservationRepository`.
    - **Simplified Usage:** Clients interact with `SalesFacade` methods (`getCurrentOffer`, `acceptOffer`, `addToCart`) instead of directly manipulating lower-level components, promoting code clarity and reducing potential errors.
- **Testing:**
    - **Unit Tests** 
    - **Integration Tests**  
    - **End-to-End (E2E) Tests** 


## Technologies Used

- **Java 17** 
- **Spring Boot 3.2.4**
- **JUnit 5.10.2** 
- **AssertJ 3.25.1** 
- **PayU API** 
- **H2 Database** 



