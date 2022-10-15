import java.util.Random;

public class Account extends AccountInformation {
    private final String acctType;
    private double minimumBalance;
    private final double PENALTY = 10.0;
    private final double TRANSACTION_CHARGE = 1.0;
    private double interestCharge;

    public Account(String name, String acctType) {
        this.acctType = acctType;

        if (isRegularAccount())
            minimumBalance = 500.0;
        else if (isInterestAccount())
            interestCharge = 3.0;
        else if (isCheckingAccount()) {
            minimumBalance = 100.0;
            interestCharge = 0.0;
        }

        setName(name);
        setAcctNumber(generateAcctNumber());
        setType(this.acctType);
        setBalance(this.minimumBalance);
    }

    private String generateAcctNumber() {
        Random random = new Random();
        long high = 9_999_999_999_999_999L;
        long low = 1_000_000_000_000_000L;
        long randomNumber = random.nextLong(high - low) + low;

        return Long.toString(randomNumber);
    }

    public void depositWithdraw(double amount, String action) {
        double balance = action.equals("Deposit") ? getBalance() + amount : getBalance() - amount;

        if (isCheckingAccount()) {
            balance -= TRANSACTION_CHARGE;
        }
        if ((isRegularAccount() || isCheckingAccount()) && balance < minimumBalance) {
            balance -= PENALTY;
        }

        setBalance(balance);
    }

    public void displayInfo() {
        System.out.printf(
                "%s\n%s\n%s\n%s\n%s\n%s%n",
                "",
                "===========================",
                "Account Name: " + getName(),
                "Account Number: " + getAcctNumber(),
                "Account Balance: " + getBalance(),
                "Account Type: " + getType()
        );

        if (isRegularAccount() || isCheckingAccount()) {
            System.out.printf(
                    "%s\n%s%n",
                    "Minimum Balance: " + minimumBalance,
                    "Penalty: " + PENALTY
            );
        }
        if (isInterestAccount() || isCheckingAccount())
            System.out.println("Interest Charge: " + interestCharge);
        if (isCheckingAccount())
            System.out.println("Transaction Charge: " + TRANSACTION_CHARGE);
    }

    private boolean isRegularAccount() {
        return acctType.equals("Regular Account");
    }

    private boolean isInterestAccount() {
        return acctType.equals("Interest Account");
    }

    private boolean isCheckingAccount() {
        return acctType.equals("Checking Account");
    }
}