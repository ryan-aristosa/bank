import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int selection = getInputFromList("account");
        Scanner scanner = new Scanner(System.in);
        String[] acctType = {"Regular Account", "Interest Account", "Checking Account"};
        double amount;

        System.out.print("Enter Account Name: ");
        String name = scanner.nextLine();
        Account account = new Account(name, acctType[selection - 1]);
        account.displayInfo();

        while (true) {
            int transactType = getInputFromList("transaction");
            String[] operation = {"Deposit", "Withdraw"};

            if (transactType == 1 || transactType == 2) {
                amount = getAmountDepositWithdraw(operation[transactType - 1]);
                account.depositWithdraw(amount, operation[transactType - 1]);
                System.out.println("Current Balance: " + account.getBalance());
            } else if (transactType == 3)
                account.displayInfo();
            else if (transactType == -1) {
                System.out.println("Goodbye!");
                System.exit(0);
            }
        }
    }

    private static int getInputFromList(String action) {
        int selection;
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println();

            if (action.equals("account")) {
                System.out.printf(
                        "%s\n%s\n%s\n%s\n%s%n",
                        "Choose an Account Type to Open",
                        "[1] Regular Account",
                        "[2] Interest Account",
                        "[3] Checking Account",
                        "[-1] Exit"
                );
            } else if (action.equals("transaction")) {
                System.out.printf(
                        "%s\n%s\n%s\n%s\n%s%n",
                        "Choose an Operation to Perform",
                        "[1] Deposit",
                        "[2] Withdraw",
                        "[3] Display Account Info",
                        "[-1] Exit"
                );
            }

            System.out.print("Enter " + action + " type: ");

            try {
                selection = scanner.nextInt();

                if (selection != 1 && selection != 2 && selection != 3 && selection != -1) {
                    throw new InputNotListException("Invalid input");
                }

                if (selection == -1) {
                    System.out.println("Goodbye!");
                    System.exit(0);
                } else
                    break;
            } catch (InputMismatchException | InputNotListException e) {
                System.out.println("Invalid input");
                scanner = new Scanner(System.in);
            }
        }

        return selection;
    }

    public static double getAmountDepositWithdraw(String operation) {
        double amount;
        Scanner scanner = new Scanner(System.in);

        while (true) {
            try {
                System.out.print("Enter Amount to " + operation + ": ");
                amount = scanner.nextDouble();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input");
            } finally {
                scanner = new Scanner(System.in);
            }
        }

        return amount;
    }
}