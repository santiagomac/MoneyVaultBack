# MoneyVault (Expense tracker API)

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

## Endpoints supported:

This is a list with the endpoints supported by the API:

1. Create one budget.
2. Get all budgets by user.
3. Get budget by id.
4. Get transactions by user.
5. Get transaction by id.
6. Update the transaction.
7. Delete transaction.
8. Sign up. (Create user)
9. Sign in. (Generate JWT)

The swagger documentation is in building. 🛠️
