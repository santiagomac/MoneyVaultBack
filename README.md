# MoneyVault (Expense tracker API)

This project is based in roadmap projects: https://roadmap.sh/projects/expense-tracker-api.
If you like, you can let me one star ⭐️.

## Technologies used in this project

- **API** - Spring Webflux.
- **Authentication** - Spring security.
- **Database** - Postgresql.

## Steps to run the project:

Follow the next steps to run the project and use it.

1. Pull the project:
    ```
    git pull https://github.com/santiagomac/MoneyVaultBack.git

    or

    git pull git@github.com:santiagomac/MoneyVaultBack.git
    ```

2. Open the project with your favorite IDE (Intellij recommended)
3. Open the folder docker, and run the next command to run the DB.
    ```
   docker-compose up -d
    ```
4. Run the project and consume the endpoints. (Postman recommended).

### Note
To create one transaction (income, expense) is needed to create one budget.

## Features:

### Authentication of the users.

* **Sign up**: Register one user by email and password.
* **Sign in**: Login by email and password, and generate the JWT.

### Track your expenses

* **Budget:**
  * Create one budget, this is required for created one transaction.
  * Get all budgets by user id.
  * Get one budget by user id.
  * Update the budget.
  * Delete the budget.
* **Transaction:**
  * Create one transaction.
  * Get all transactions by user id.
  * Get one transaction by user id.
  * Update and delete transaction.
* **Filter transactions:**
  * Past week
  * Past month
  * Last three months
  * Custom: *Start date* - *End date*

### Caategories:
* Groceries
* Leisure
* Electronics
* Utilities
* Clothing
* Health
* Others

The swagger documentation is in building. 🛠️
