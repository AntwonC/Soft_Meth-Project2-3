/**
 * Abstract class for defining the common features of all account types,
 * calculates the debit/credit, turns the input in string format, and 
 * checks if its a particular account type.
 * @author Anthony Chen, Hoda Moustafa
 *
 */

public abstract class Account {
	private Profile holder;
	private double balance;
	private Date dateOpen;
	
	public Account(Profile holder, double balance, Date dateOpen) {
		this.holder = holder;
		this.balance = balance;
		this.dateOpen = dateOpen;
	}
	
	/**
	 * @return the holder
	 */
	public Profile getHolder() {
		return holder;
	}

	/**
	 * @param holder the holder to set
	 */
	public void setHolder(Profile holder) {
		this.holder = holder;
	}

	/**
	 * @return the balance
	 */
	public double getBalance() {
		return balance;
	}

	/**
	 * @param balance the balance to set
	 */
	public void setBalance(double balance) {
		this.balance = balance;
	}

	/**
	 * @return the dateOpen
	 */
	public Date getDateOpen() {
		return dateOpen;
	} 

	/**
	 * @param dateOpen the dateOpen to set
	 */
	public void setDateOpen(Date dateOpen) {
		this.dateOpen = dateOpen;
	}
	
	/**
	 * Decrease the total balance by amount
	 * @param amount to be decreased
	 */
	public void debit(double amount) {
		this.balance -= amount;
		return; 
	}   
	
	/**
	 * Increase the total balance by amount
	 * @param amount to be added
	 */
	public void credit(double amount) { //increase the balance by amount 
		this.balance += amount;
		return;
	}
	
	/**
	 * gets the name, balance, and date of the account in the format "Fname Lname* $balance*mm/dd/yyyy*"
	 * @return the items in correct format   
	 */
	@Override
	public String toString() { 
		return getHolder().getFname() + " " + getHolder().getLname() + "* $" 
				+ String.format("%.2f", getBalance()) + "*" + getDateOpen() + "* ";
	}
	
	/**
	 * Compares the accounts and checks if its in the Accounts
	 * @return the first and last name of the account 
	 */
	@Override
	public boolean equals(Object obj) {
		if ( obj.getClass() == getClass() ) {
			return getHolder().getFname().equals( ((Account) obj).holder.getFname())
					&& getHolder().getLname().equals( ((Account) obj).holder.getLname());
		}
		
		return false;
	}
	
	/**
	 * Monthly interest implemented in the subclasses
	 * @return the monthly interest
	 */
	public abstract double monthlyInterest(); 
	
	/**
	 * Monthly fee implemented in the subclasses
	 * @return the monthly fee
	 */
	public abstract double monthlyFee();
	
}