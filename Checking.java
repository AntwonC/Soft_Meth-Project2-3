/**
 * Subclass of Abstract super class Account. Implements the abstract methods from 
 * Account. Handles the monthly interest and fee for Checking accounts.
 * @author Anthony Chen, Hoda Moustafa
 *
 */

public class Checking extends Account {
	private boolean directDeposit;
	public static final double CHECKING_MONTHLY_FEE = 25.0;
	//constructor 
	
	public Checking(Profile holder, double balance, Date dateOpen, boolean directDeposit) {
		super(holder, balance, dateOpen);
		this.directDeposit = directDeposit;
	}
	
	/**
	 * getter method for directDeposit
	 * @return the directDeposit
	 */
	public boolean isDirectDeposit() {
		return directDeposit;
	}

	/**
	 * setter method for directDeposit
	 * @param directDeposit the directDeposit to set
	 */
	public void setDirectDeposit(boolean directDeposit) {
		this.directDeposit = directDeposit;
	}
	
	/**
	 * Calculates the monthly interest for Checking account using the formula 
	 * I = A - P, where A = P(1 + rt)
	 * @return monthly interest for Checking Account
	 */
	@Override
	public double monthlyInterest() {
		// 1/12 = 0.083333
		// 0.005 = % for Checking in decimal form
		return (this.getBalance() * (1 + (0.0005 * 0.083333))) - this.getBalance();
	}
	
	/**
	 * calculate the monthly fee for Checking account,
	 * if the directdeposit = true or if the balance 1500 or more, the 
	 * monthly fee is waived
	 * @return the monthly fee for checking account (25)
	 */
	@Override
	public double monthlyFee() {
		if ( directDeposit || this.getBalance() >= 1500) {
			return 0;			
		} 
		return CHECKING_MONTHLY_FEE; 
	}
	
	/**
	 * add the account type (Checking) to the print statement
	 * @return account info including type
	 */
	@Override
	public String toString() {
		String depositValid = "";
		if ( directDeposit ) {
			depositValid += "direct deposit account*";
		} 
		// Checking + name,balance,date from Account toString + deposit t/f
		return "*Checking*" + super.toString() + depositValid;
	}
	
	/**
	 * Check if the object is a Checking account, call equals from Account
	 * @return the first and last name of the account name
	 */
	@Override
	public boolean equals(Object obj) {		
			if ( obj instanceof Checking ) {
				return super.equals(obj) && getHolder().getFname().equals(((Checking) obj).getHolder().getFname()) 
						&& getHolder().getLname().equals(((Checking) obj).getHolder().getLname());
			}
		
		return false;
	}
}
