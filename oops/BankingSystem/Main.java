import java.util.HashSet;
import java.util.Scanner;

abstract class Account {
    String Accnum;

    public Account(String accountNumber){
        this.Accnum = accountNumber;
    }

    

    abstract void getBalance();
    abstract void depositMoney(double amount);
    abstract void withdrawMoney(double amount);

}

class SavingsAccount extends Account {
    private double balance;

    public SavingsAccount(String AccountNumber){
        super(AccountNumber);
        balance = 0.0;
    }

    public void depositMoney(double amount){

        balance = balance+amount;
        System.out.println("Rs " + amount + " deposited. Your balance is Rs " + balance);
    }

    public void withdrawMoney(double amount){
        if(amount>balance){
            System.out.println("Insufficient Balance");
        }
        else{
            System.out.println("Rs"+ amount + " withdrawn");
            this.balance -= amount;
            System.out.print("Your balance is Rs "+balance);
        }
    }

    public void getBalance(){
        System.out.println("Your Account Balance : "+ balance);
    }

}

class CreditAccount extends Account {
    
    private double debt;

    public CreditAccount(String AccountNumber){
        super(AccountNumber);
        this.debt = 0.0;
    }

    public void depositMoney(double amount){
        debt -= amount;
        System.out.print("Rs "+amount+" deposited. Your Bill is now Rs "+ debt);
        
    }

    public void withdrawMoney(double amount){
        if(amount + debt > 20000)
            System.out.println("You're exceeding limit");
        else{
            debt += amount;
            System.out.println("Rs "+amount+" withdrawn. Your bill is Rs "+debt);
        }

    }

    public void getBalance(){
        System.out.println("Your Account Debt :" + debt);
        System.out.println("Your limit : 20000");
    }
}

public class Main {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        HashSet<String> AccountNumbers = new HashSet<>();
        while(true){
            System.out.println("------");
            System.out.println("Welcome to ABC Bank");
            System.out.println(" Enter account num: ");

            String AccNum = sc.nextLine();

            if(!AccountNumbers.contains(AccNum)){
                System.out.print("New account creation:");

                System.out.print("> Account type (S)avings/ (C)redit: ");
                char AccType = sc.nextLine().charAt(0);
                Account Account;
                if(AccType == 'S'){
                    System.out.println("Savings Account Created.");
                    Account = new SavingsAccount(AccNum);
                }
                else{
                    System.out.println("Credit Account Created");;
                    Account = new CreditAccount(AccNum);
                }

                while(true){
                    int flag = 0;
                    System.out.println("1. View Balance");
                    System.out.println("2. Deposit");
                    System.out.println("3. Withdraw");
                    System.out.println("0. Main Menu");

                    System.out.print("Enter a choice: ");
                    int choice = sc.nextInt();

                    switch (choice) {
                        case 1 :
                            Account.getBalance();
                            break;

                        case 2 :
                            System.out.print("Enter deposit amount: ");
                            double depamount = sc.nextDouble();
                            Account.depositMoney(depamount);
                            break;
                        case 3 :
                            System.out.print("Enter Withdraw amount :");
                            double amount = sc.nextDouble();
                            Account.withdrawMoney(amount);
                            break;
                        case 0 :
                            flag = 1;
                            break;
                        default :
                            break;

                    }
                    if(flag==1)
                        break;

                }


                // System.out.println("Want to exit Bank: yes(1) / no(0)");
                // int opt = sc.nextInt();
                // if(opt==1)
                //     break;
            }
        }
        
        
    }
}