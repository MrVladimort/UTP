package banking;

public class BankCustomer
extends Person{
	
	private Account account;
	
	public BankCustomer(Person person){
		super(person.getName());
		this.account = new Account();
	}
	
	public Account getAccount(){
		return account;
	}
	public String toString(){
		return "Klient: "+name+" stan konta "+account.getBalance();
	}
	
	private void changeStr() {
		name+="ABCD";
	}
	
	protected void deleteAccount(int a) {
		account = null;
	}
	
}
