package banking;

public class Account {
	private double balance;
	private static double interest = 0;
	public Account(){
		balance = 0;
	}
	public void deposit(double sum){
		if (sum<0){}
		else
			balance += sum;
	}
	public void withdraw(double sum){
		if (sum<0 || sum>balance){}
		else 
			balance -= sum;
	}
	public void addInterest(){
		balance += balance*interest/100;
	}
	public void transfer(Account account, double sum){
		if (sum<0 ||
				sum>this.balance){}
		else{
			this.balance-=sum;
			account.balance+=sum;
		}
	}
	public static void setInterestRate(double interest){
		Account.interest = interest;
	}
	public double getBalance(){
		return balance;
	}
}
