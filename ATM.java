import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;


public class ATM {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        BankAccount b1 = new BankAccount(2000);
        BankAccount b2 = new BankAccount(5000);
        
        BankAccount currentAccount = b1;
        
        System.out.println("Select Account:");
        System.out.println("1. Account 1");
        System.out.println("2. Account 2");
        
        int choice1 = sc.nextInt();
        
        if(choice1 == 1) {
        	currentAccount = b1;
        }else if(choice1 == 2){
        	currentAccount = b2;
        }else {
        	System.out.println("Invalid Account Choice ! ");
        	return;
        }
        
        
   
       
        
                
   
        
        
        ArrayList<String> history = new ArrayList<>();

        int correctPin;
        try {
        	Scanner fileReader = new Scanner(new File("pin.txt"));
        	correctPin = fileReader.nextInt();
        	fileReader.close();
        }catch(FileNotFoundException e) {
        	correctPin = 1234;
        }
        
        
        while (true) {

            int attempts = 0;
            boolean authenticated = false;

            // PIN LOGIN
            while (attempts < 3) {
                System.out.println("Enter Your PIN:");
                int pin = sc.nextInt();

                if (pin == correctPin) {
                    authenticated = true;
                    System.out.println("PIN Accepted!");
                    break;
                } else {
                    attempts++;
                    System.out.println("Incorrect PIN!");
                    System.out.println("Attempts left: " + (3 - attempts));
                }
            }

            if (!authenticated) {
                System.out.println("Account Locked!");
                sc.close();
                return;
            }

            
            
            boolean logout = false;

            while (!logout) {

                System.out.println("\n===== ATM MENU =====");
                System.out.println("1. Check Balance");
                System.out.println("2. Deposit Money");
                System.out.println("3. Withdrawal Money");
                System.out.println("4. Transaction History");
                System.out.println("5. Change PIN");
                System.out.println("6. Exit");
                System.out.println("Enter Your Choice:");

                int choice = sc.nextInt();

                switch (choice) {

                    case 1:
                        System.out.println("Your Balance: " + currentAccount.getBalance());
                        break;

                    case 2:
                        System.out.println("Enter deposit Amount:");
                        double amount = sc.nextDouble();

                        boolean success = currentAccount.deposit(amount);

                        if (success) {
                            history.add("Deposited: " + amount);
                        }

                      System.out.println("Updated Balance: " + currentAccount.getBalance());
                        break;

                    case 3:
                        System.out.println("Enter Withdrawal Amount:");
                        double withdrawalAmount = sc.nextDouble();

                        boolean withdrawalSuccess =
                        		currentAccount.withdrawal(withdrawalAmount);

                        if (withdrawalSuccess) {
                            history.add("Withdrawn: " + withdrawalAmount);
                        }

                        System.out.println("Remaining Balance: " + currentAccount.getBalance());
                        break;

                    case 4:
                        System.out.println("Transaction History:");

                        if (history.isEmpty()) {
                            System.out.println("No transactions yet.");
                        } else {
                            for (String transaction : history) {
                                System.out.println(transaction);
                            }
                        }
                        break;

                    case 5:
                        int pinAttempts = 0;
                        boolean pinVerified = false;

                        while (pinAttempts < 3) {
                            System.out.println("Enter Your Current PIN:");
                            int oldPin = sc.nextInt();

                            if (oldPin == correctPin) {pinVerified = true;
                                break;
                            } else {
                                pinAttempts++;
                                System.out.println("Wrong PIN!");
                                System.out.println("Attempts left: "
                                        + (3 - pinAttempts));
                            }
                        }

                        if (pinVerified) {
                            System.out.println("Enter Your New PIN:");
                            int newPin = sc.nextInt();

                            correctPin = newPin;
                            try {
                            	FileWriter writer = new FileWriter("pin.txt");
                            	writer.write(String.valueOf(correctPin));
                            	writer.close();
                                System.out.println("PIN Changed Successfully!");

                            }catch(IOException e) {
                            	System.out.println("Error saving PIN!");
                            }

                            
                            logout = true;

                        } else {
                            System.out.println("Too many wrong attempts!");
                        }
                        break;

                    case 6:
                        System.out.println("Thanks for using ATM!");
                        sc.close();
                        return;

                    default:
                        System.out.println("Invalid Choice!");
                        break;
                }
            }

                 
            
        }
    }
}