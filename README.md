#ExpenseTracker

*COMPANY*: CODTECH IT SOLUTIONS

*NAME*: ROHIT SASTIA

*INTERN ID*: CT04DH928

*DOMAIN*: ANDROID DEVELOPMENT

*DURATION*: 4 WEEKS

*MENTOR*: NEELA SANTHOSH

# Expense Tracker App 

## Overview

The **Expense Tracker App** is a personal finance management application developed for Android using Java and SQLite. It allows users to track and categorize their daily expenses, visualize spending trends, and manage financial data efficiently — all offline.

---

## Features

- Add and manage expense entries with details like:
  - Amount
  - Category (e.g., Food, Transport, Bills)
  - Date
  - Description (optional)
- View expense history with date and category filters
- Visualize spending with pie and bar charts (using MPAndroidChart)
- Summary dashboard for total and categorized expenses
- Local data storage using SQLite (no internet required)
- Clean, intuitive UI designed in XML
- Support for dark mode (optional enhancement)
- Edit or delete entries (optional features)

---

## Technology Stack

- **Language**: Java
- **IDE**: Android Studio
- **Database**: SQLite
- **Libraries**: 
  - [MPAndroidChart](https://github.com/PhilJay/MPAndroidChart) for data visualization

---

## Architecture

- **MainActivity** – Hosts the dashboard and navigation
- **ExpenseInputActivity** – Form to input new expenses
- **ExpenseListActivity** – Displays a list of all recorded expenses
- **DBHelper.java** – SQLite helper class for creating and managing the database
- **ChartActivity** – Displays pie/bar charts for visual analysis

---


## How It Works

1. User opens the app and views a dashboard summarizing their expense activity.
2. From the dashboard, they can navigate to add a new expense or view all existing expenses.
3. Upon adding an expense, the data is validated and inserted into the local SQLite database.
4. Users can filter expenses based on category or date range.
5. The dashboard screen displays total expenses and a pie chart showing percentage breakdown by category.
6. All data is stored locally; no internet is required.

---

## Minimum Requirements

- Android SDK: 27+
- Compile SDK: 34
- Java 8 compatibility
- Android Studio Giraffe or newer

---

## Future Enhancements

- Add cloud sync using Firebase or Google Drive
- Budget limit alerts
- CSV/Excel export of expense history
- Authentication for multiple users

---


## Author

Developed as part of Task 01 – Android Expense Tracker Project for educational purposes.

---

##Output

![Image](https://github.com/user-attachments/assets/396107f6-af59-41dd-9c12-ff6a7937692f)
![Image](https://github.com/user-attachments/assets/08c00ed1-c787-4bc9-9865-008e19dc5afe)
![Image](https://github.com/user-attachments/assets/d24ac0aa-8afd-4fb7-94cb-52729a1c36c2)
![Image](https://github.com/user-attachments/assets/5c63e4db-2185-4064-b81a-f388f65779c5)


