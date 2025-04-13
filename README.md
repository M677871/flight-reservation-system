<<<<<<< HEAD
# flight-reservation-system
A Java-based Flight Reservation System for the CSIS-215 OOP course. Implements core OOP principles with flight scheduling, discount handling, and custom date validations.
=======
# ✈️ OOP Project – Java Flight Reservation System

## 📌 Overview

This project is a **Java-based Flight Reservation System** developed for the OOP course. It is built using Object-Oriented Programming (OOP) principles and includes core classes such as `Flight`, `DomesticFlight`, `CustomizedDate`, and `Plane`. The system supports scheduling flights, calculating costs (with discounts), and managing date/time validations.

---

## 🛠 Prerequisites

Ensure you have the following installed:

- Java JDK (v17 or later recommended)
- IntelliJ IDEA / Eclipse / any preferred IDE
- Git (for version control)

---

## 📂 Project Structure

```
/oopproject
│── /oopproject
│   ├── Flight.java                # Base class for all flights
│   ├── DomesticFlight.java        # Inherits Flight, includes domestic discount logic
│   ├── CustomizedDate.java        # Utility class for date and time handling
│   ├── Plane.java                 # Plane details (model, capacity, etc.)
│   └── Main.java                  # Entry point and test driver
│
├── .gitignore                     # Files and folders ignored by Git
├── README.md                      # Project documentation
```

---

## 🚀 Setup & Execution

1. **Clone the repository**

```bash
git clone https://github.com/M677871/flight-reservation-system.git
cd flight-reservation-system
```

2. **Open in your preferred IDE**

   - Import as a Java project
   - Ensure JDK 17+ is configured

3. **Run `Main.java`**

   - Compile and run to simulate various flight scenarios (e.g., cost calculation, time validation)

---

## 🔍 Features

- 📅 Custom date and time validation with leap year handling
- 💵 Cost calculation based on distance and discount
- ✈️ Plane attributes integrated into flights
- 🧮 Discount logic for domestic flights
- 🧪 Date comparison and equality checks
- 🧼 Input validation and data sanitization

---

## 📘 Example Usage (Output from `toString()`)

```text
Domestic Flight ;Flight Number: DF123
From: Toronto To: Vancouver
Departure: 15/4/2025 - 08:30
Arrival: 15/4/2025 - 11:45
Plane: Boeing 737
Distance: 3000.0 km
Domestic discount 10.0%
Calculated Cost: 1350.0 $.
```

---

## 🔧 Technologies Used

- Java 17
- OOP concepts (inheritance, encapsulation, abstraction)
- IDE: IntelliJ IDEA / Eclipse

---

## ✅ Best Practices Followed

- ✔️ Modular class structure
- ✔️ Input validation and error handling
- ✔️ Clean, readable code with comments
- ✔️ Testable design with reusable methods

---

## 📜 License

This project is licensed under the [MIT License](https://opensource.org/licenses/MIT).

---

## 🤝 Contributing

Feel free to fork the repo, submit pull requests, or raise issues. Contributions are welcome! 🚀

---

## 📎 Resources

- Java SE Documentation: https://docs.oracle.com/en/java/javase/
- GitHub Docs – Getting Started: https://docs.github.com/en/get-started
>>>>>>> 275ff5e (Initial commit - Java Flight Reservation System for CSIS-228)
