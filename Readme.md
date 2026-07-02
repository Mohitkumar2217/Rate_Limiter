
# 🚦 Rate Limiter - Low Level Design (Java)

A modular and extensible implementation of a **Rate Limiter** in Java demonstrating multiple rate limiting algorithms using **Object-Oriented Design**, **Strategy Pattern**, and **Factory Pattern**.

This project is designed for **Low-Level Design (LLD)** interview preparation and showcases how different rate limiting strategies can be plugged into a single service. It follows common LLD patterns for extensibility and maintainability. :contentReference[oaicite:0]{index=0}

---

## 📖 Overview

A Rate Limiter controls how many requests a user can make within a specific time interval.

Typical use cases include:

- API Gateway
- Login Protection
- OTP Verification
- Payment APIs
- Preventing DDoS attacks
- Fair resource allocation

---

## ✨ Features

- Multiple Rate Limiting Algorithms
- Strategy Design Pattern
- Factory Design Pattern
- User Tier Based Limiting
- Configurable Limits
- Easily Extensible
- Clean Package Structure
- In-Memory Implementation

---

## 🏗️ Design Patterns Used

### Strategy Pattern

Each rate limiting algorithm implements the same interface.

```
RateLimiter
    ▲
    │
 ┌──┴──────────────┐
 │                 │
TokenBucket   FixedWindow
                  │
          SlidingWindowLog
```

---

### Factory Pattern

Creates the appropriate rate limiter at runtime.

```
RateLimiterFactory
        │
        ▼
+---------------------------+
| createRateLimiter(type)   |
+---------------------------+
```

---

## 📂 Project Structure

``` 
Rate_Limiter
    ├── enums
    │   ├── RateLimitType.java
    │   └── UserTier.java
    │
    ├── factory
    │   └── RateLimiterFactory.java
    │
    ├── limiter
    │   ├── RateLimiter.java
    │   ├── TokenBucketRateLimiter.java
    │   ├── FixedWindowRateLimiter.java
    │   └── SlidingWindowLogRateLimiter.java
    │
    ├── model
    │   ├── User.java
    │   └── RateLimitConfig.java
    │
    ├── service
    │   └── RateLimiterService.java
    │
    └── Main.java
```

---

## 🧠 Algorithms Implemented

### 1. Token Bucket

- Allows bursts
- Tokens are refilled periodically
- Very efficient

**Used for**

- FREE Users

---

### 2. Fixed Window Counter

- Counts requests in a fixed interval
- Counter resets after every window

**Used for**

- PREMIUM Users

---

### 3. Sliding Window Log

- Maintains timestamps
- Most accurate
- Slightly higher memory usage

**Used for**

- PREMIUM PLUS Users

---

## 👤 User Tiers

| Tier | Algorithm |
|------|-----------|
| FREE | Token Bucket |
| PREMIUM | Fixed Window |
| PREMIUM PLUS | Sliding Window Log |

---

## ⚙️ Configuration

Example configuration:

```java
new RateLimitConfig(10, 60);
```

Meaning:

- Maximum Requests = **10**
- Time Window = **60 seconds**

---

## 🚀 Running the Project

Clone the repository

```bash
git clone <repository-url>
```

Go to the project

```bash
cd rate-limiter
```

```
Execute the `Main.java` file directly from your IDE.
```
---

## 💻 Example

```java
RateLimiterService service = new RateLimiterService();

User user = new User("user1", UserTier.FREE);

boolean allowed = service.allowRequest(user);

System.out.println(allowed);
```

---

## 📈 Time Complexity

| Algorithm | Time | Space |
|-----------|------|-------|
| Token Bucket | O(1) | O(n) |
| Fixed Window | O(1) | O(n) |
| Sliding Window Log | O(log n) / O(1)* | O(n) |

\* Depending on the underlying data structure.

---

## 📌 Advantages

- Easy to extend
- Clean architecture
- Follows SOLID principles
- Supports multiple algorithms
- Interview friendly
- Production-inspired design

---

## 🔮 Future Improvements

- Redis-backed distributed rate limiting
- Thread-safe implementation
- Leaky Bucket algorithm
- Sliding Window Counter
- Persistent storage
- REST API integration
- Spring Boot support
- Metrics & Monitoring
- Unit Tests (JUnit)

---

## 🛠️ Technologies Used

- Java
- Object-Oriented Programming
- Collections Framework
- Design Patterns 

--- 

## ⭐ If you found this project helpful

Give it a ⭐ on GitHub and feel free to fork it for your own LLD practice.

```
Happy Coding! 🚀
```