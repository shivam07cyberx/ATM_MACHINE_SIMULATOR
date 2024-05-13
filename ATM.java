import java.util.Scanner;

public class ATM {
    private static final Scanner scanner = new Scanner(System.in);
    private static final int MAX_TRANSACTIONS = 100;
    private static final int SAVING = 1;
    private static final int CURRENT = 2;
    private static final int EXIT = 5;

    private static int[] transactionHistory = new int[MAX_TRANSACTIONS];
    private static int transactionCount = 0;

    private static int savingBalance = 1000;
    private static int currentBalance = 2000;

    private static int pin = 6554;

    public static void main(String[] args) {
        System.out.println("Welcome to Indian Bank ATM.");
        System.out.println("Please insert your card (enter card number):");
        int cardNumber = scanner.nextInt();

        if (verifyCard(cardNumber)) {
            System.out.println("Card verified.");
            if (authenticateUser()) {
                manageSession();
            }
        } else {
            System.out.println("Invalid card number. Please contact your bank.");
        }
        scanner.close();
    }

    private static boolean verifyCard(int cardNumber) {
        return cardNumber == 1234; // Replace with actual card verification logic
    }

    private static boolean authenticateUser() {
        System.out.println("Enter your 4-digit PIN:");
        int attempts = 0;
        while (attempts < 3) {
            int enteredPin = scanner.nextInt();
            if (enteredPin == pin) {
                System.out.println("PIN verified.");
                return true;
            } else {
                System.out.println("Invalid PIN. Please try again:");
                attempts++;
            }
        }
        System.out.println("Too many invalid attempts. Card is blocked.");
        return false;
    }

    private static void manageSession() {
        boolean exit = false;
        while (!exit) {
            System.out.println("Select an option:");
            System.out.println("1. Saving Account");
            System.out.println("2. Current Account");
            System.out.println("5. Exit");
            System.out.println("Enter your choice:");

            int accountType = scanner.nextInt();
            switch (accountType) {
                case SAVING:
                    manageAccount(SAVING);
                    break;
                case CURRENT:
                    manageAccount(CURRENT);
                    break;
                case EXIT:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
        System.out.println("Thank you for using Indian Bank ATM. Have a nice day!");
    }

    private static void manageAccount(int accountType) {
        boolean back = false;
        while (!back) {
            System.out.println("Select an option:");
            System.out.println("1. Withdraw money");
            System.out.println("2. View balance");
            System.out.println("3. Change PIN");
            System.out.println("4. Transaction history");
            System.out.println("5. Back to main menu");
            System.out.println("Enter your choice:");

            int option = scanner.nextInt();
            switch (option) {
                case 1:
                    if (accountType == SAVING) {
                        savingBalance = withdrawMoney(savingBalance);
                    } else if (accountType == CURRENT) {
                        currentBalance = withdrawMoney(currentBalance);
                    }
                    break;
                case 2:
                    if (accountType == SAVING) {
                        viewBalance(savingBalance);
                    } else if (accountType == CURRENT) {
                        viewBalance(currentBalance);
                    }
                    break;
                case 3:
                    changePIN();
                    break;
                case 4:
                    showTransactionHistory();
                    break;
                case EXIT:
                    back = true;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static int withdrawMoney(int balance) {
        System.out.println("Enter amount to withdraw:");
        int withdrawAmount = scanner.nextInt();
        if (withdrawAmount > balance) {
            System.out.println("Insufficient balance.");
        } else {
            balance -= withdrawAmount;
            transactionHistory[transactionCount] = -withdrawAmount;
            transactionCount++; // Increment the transaction count
            System.out.println("Processing transaction...");
            System.out.println("Transaction successful. Available balance: " + balance);
        }
        return balance;
    }
    
    private static void showTransactionHistory() {
        System.out.println("Transaction history:");
        // Debugging statement
        System.out.println("Total transactions: " + transactionCount);
        for (int i = 0; i < transactionCount; i++) {
            if (transactionHistory[i] < 0) {
                System.out.println("Withdrawal: " + (-transactionHistory[i]));
            } else {
                System.out.println("Deposit: " + transactionHistory[i]);
            }
        }
    }
    

    private static void viewBalance(int balance) {
        System.out.println("Your account balance is: " + balance);
    }

    private static void changePIN() {
        System.out.println("Enter current PIN:");
        int currentPin = scanner.nextInt();
        if (currentPin != pin) {
            System.out.println("Invalid PIN. Please try again.");
        } else {
            System.out.println("Enter new PIN:");
            int newPin = scanner.nextInt();
            pin = newPin;
            System.out.println("PIN changed successfully.");
        }
    }
}