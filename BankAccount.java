public class BankAccount {
    private double balance;
   public boolean deposit(double amount){
	   if(amount<=0) {
		   System.out.println("Invalid Deposit");
		   return false;
	   }else {
		   balance += amount;
		   return true;
		   
	   }
   }
   public double getBalance(){
return balance;
   }
    
   public BankAccount (double balance){
this.balance = balance;
   }

  public boolean withdrawal (double amount){
    if (amount <= 0){
        System.out.println("Invalid amount !");
        return false ;
    }else if(balance >= amount){
        balance -= amount;
        return true ;
       // System.out.print("Your Remaining Balance is " + balance);
    }else{
        System.out.println("Insufficient amount !");
        return false ;
    }
   }

public static void main (String[]args){
BankAccount b1 = new BankAccount(2000);
b1.withdrawal(1999);
//b1.deposit(300);
//b1.deposit(700);

    //System.out.println("Your Balance is "+ b1.getBalance());
}

}