
import java.util.ArrayList;
import java.util.Scanner;

class BankAccount {
    private String accountNumber;
    private String accountHolder;
    private double balance;

    public BankAccount(String accountNumber, String accountHolder, double initialBalance) {
        this.accountNumber = accountNumber;
        this.accountHolder = accountHolder;
        this.balance = initialBalance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getAccountHolder() {
        return accountHolder;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposited Rs" + amount + " into account " + accountNumber);
            System.out.println("Balance remaining Rs"+balance+" into account"+accountNumber);
        } else {
            System.out.println("Invalid deposit amount.");
            System.out.println("Balance remaining Rs"+balance+" into account "+accountNumber);
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Withdrawn Rs" + amount + " from account " + accountNumber);
            System.out.println("Balance remaining Rs"+balance+" into account "+ accountNumber);
        } else {
            System.out.println("Invalid withdrawal amount or insufficient funds.");
            System.out.println("Balance remaining Rs"+balance+" into account "+ accountNumber);
        }
    }
    public void CheckBalance(){
        System.out.println("Balance remaining Rs"+balance+" into account "+ accountNumber);
    }
}

class SavingsAccount extends BankAccount {
    private double interestRate;

    public SavingsAccount(String accountNumber, String accountHolder, double initialBalance, double interestRate) {
        super(accountNumber, accountHolder, initialBalance);
        this.interestRate = interestRate;
    }

    public void applyInterest() {
        double interest = getBalance() * interestRate;
        System.out.println("Interest of Rs" + interest + " applied to account " + getAccountNumber());
        deposit(interest);
    }
}

class Bank {
    private ArrayList<BankAccount> accounts = new ArrayList<>();

    public void addAccount(BankAccount account) {
        accounts.add(account);
    }

    public BankAccount findAccount(String accountNumber) {
        for (BankAccount account : accounts) {
            if (account.getAccountNumber().equals(accountNumber)) {
                return account;
            }
        }
        return null;
    }
}

public class BankApp {
    public static void main(String[] args) {
        Bank bank = new Bank();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n Menu:");
            System.out.println("1. Create Savings Account");
            System.out.println("2. Check Bank Balance");
            System.out.println("3. Deposit");
            System.out.println("4. Withdraw");
            System.out.println("5. Apply Interest");
            System.out.println("6. Exit");
            System.out.println(" ");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter account number: ");
                    String accNumber = scanner.nextLine();
                    System.out.print("Enter account holder name: ");
                    String accHolder = scanner.nextLine();
                    System.out.print("Enter initial balance: ");
                    double initialBalance = scanner.nextDouble();
                    System.out.print("Enter interest rate: ");
                    double interestRate = scanner.nextDouble();
                    SavingsAccount savingsAccount = new SavingsAccount(accNumber, accHolder, initialBalance, interestRate);
                    bank.addAccount(savingsAccount);

                    break;
                case 2:
                    System.out.print("Enter account number: ");
                    String CAccNumber = scanner.nextLine();
                    BankAccount CAccount = bank.findAccount(CAccNumber);
                    if (CAccount != null) {
                        CAccount.CheckBalance();
                    } else {
                        System.out.println("Account not found.");
                    }
                    break;
                case 3:
                    System.out.print("Enter account number: ");
                    String DAccNumber = scanner.nextLine();
                    BankAccount DAccount = bank.findAccount(DAccNumber);
                    if (DAccount != null) {
                        System.out.print("Enter deposit amount: ");
                        double Amount = scanner.nextDouble();
                        DAccount.deposit(Amount);
                    } else {
                        System.out.println("Account not found.");
                    }
                    break;
                case 4:
                    System.out.print("Enter account number: ");
                    String WAccNumber = scanner.nextLine();
                    BankAccount WAccount = bank.findAccount(WAccNumber);
                    if (WAccount != null) {
                        System.out.print("Enter withdrawal amount: ");
                        double Amount = scanner.nextDouble();
                        WAccount.withdraw(Amount);
                    } else {
                        System.out.println("Account not found.");
                    }
                    break;
                case 5:
                    System.out.print("Enter account number: ");
                    String IAccNumber = scanner.nextLine();
                    BankAccount IAccount = bank.findAccount(IAccNumber);
                    if (IAccount instanceof SavingsAccount) {
                        SavingsAccount savings = (SavingsAccount) IAccount;
                        savings.applyInterest();
                    } else {
                        System.out.println("Account is not a savings account.");
                    }
                    break;
                case 6:
                    System.out.println("Exiting the program.");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }
}
