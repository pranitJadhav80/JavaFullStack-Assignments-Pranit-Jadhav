package GeneralizationSpecializationAssignment;

class BankAccount {
    String accountNumber;
    double balance;

    public BankAccount(String accountNumber, double balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposited successfully: " + amount + ", New Balance: " + balance);
        } else {
            System.out.println("Deposit amount must be positive.");
        }
    }

    public void withDraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Withdrawn successfully: " + amount + ", New Balance: " + balance);
        } else {
            System.out.println("Insufficient balance.");
        }
    }

    public void checkBalance() {
        System.out.println("Balance for account " + accountNumber + ": " + balance);
    }
}

class SavingsAccount extends BankAccount {
    double interestRate;

    public SavingsAccount(String accountNumber, double balance, double interestRate) {
        super(accountNumber, balance);
        this.interestRate = interestRate;
    }

    public void applyInterest() {
        double interest = balance * interestRate;
        balance += interest;
        System.out.println("Interest of $" + interest + " applied, New Balance: " + balance);
    }
}

class CheckingAccount extends BankAccount {
    double fee;

    public CheckingAccount(String accountNumber, double balance, double fee) {
        super(accountNumber, balance);
        this.fee = fee;
    }

    @Override
    public void withDraw(double amount) {
        super.withDraw(amount);
        if (amount > 0 && amount <= balance) {
            balance -= fee; // Subtract fee after withdrawal
            System.out.println("Withdrawal of " + amount + " with fee of " + fee + ", New Balance: " + balance);
        } else {
            System.out.println("Insufficient balance.");
        }
    }
}

class SIPAccount extends BankAccount {
    double monthlyInvestment;

    public SIPAccount(String accountNumber, double balance, double monthlyInvestment) {
        super(accountNumber, balance);
        this.monthlyInvestment = monthlyInvestment;
    }

    public void investMonthly() {
        balance += monthlyInvestment;
        System.out.println("Monthly SIP investment of $" + monthlyInvestment + ", New Balance: " + balance);
    }
}

class BusinessAccount extends BankAccount {
    double overdraftLimit;

    public BusinessAccount(String accountNumber, double balance, double overdraftLimit) {
        super(accountNumber, balance);
        this.overdraftLimit = overdraftLimit;
    }

    @Override
    public void withDraw(double amount) {
        if (amount > 0 && amount <= balance + overdraftLimit) {
            balance -= amount;
            System.out.println("Withdrawn: " + amount + ", New Balance: " + balance);
        } else {
            System.out.println("Insufficient funds, including overdraft.");
        }
    }
}

class TradingAccount extends BankAccount {
    double transactionFee;

    public TradingAccount(String accountNumber, double balance, double transactionFee) {
        super(accountNumber, balance);
        this.transactionFee = transactionFee;
    }

    public void buyShares(double amount) {
        if (amount + transactionFee <= balance) {
            balance -= (amount + transactionFee);
            System.out.println(
                    "Bought shares worth: " + amount + " with fee of " + transactionFee + ", New Balance: " + balance);
        } else {
            System.out.println("Insufficient balance to buy shares.");
        }
    }

    public void sellShares(double amount) {
        balance += (amount - transactionFee);
        System.out.println(
                "Sold shares worth: " + amount + " with fee of " + transactionFee + ", New Balance: " + balance);
    }
}

public class Gen_Spe_code2 {

    public static void main(String[] args) {
        SavingsAccount sa = new SavingsAccount("SA123", 5000, 0.03);
        sa.deposit(1000);
        sa.applyInterest();

        CheckingAccount ca = new CheckingAccount("CA456", 4000, 2.5);
        ca.deposit(1000);
        ca.withDraw(200);

        SIPAccount sip = new SIPAccount("SIP789", 1000, 500);
        sip.investMonthly();

        BusinessAccount ba = new BusinessAccount("BA101", 5000, 2000);
        ba.deposit(2000);
        ba.withDraw(6000);

        TradingAccount ta = new TradingAccount("TA202", 15000, 50);
        ta.deposit(5000);
        ta.buyShares(4000);
        ta.sellShares(3000);
    }
}
