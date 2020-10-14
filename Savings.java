/**
 * Subclass of Abstract super class Account. Implements the abstract methods from 
 * Account. Handles the monthly interest and fee for Savings accounts.
 * @author Anthony Chen, Hoda Moustafa
 *
 */

public class Savings extends Account{

	private boolean isLoyal;
	public static final double SAVINGS_MONTHLY_FEE = 5.0;
	
	//constructor 
	public Savings(Profile holder, double balance, Date dateOpen, boolean isLoyal) {
		super(holder, balance, dateOpen);
		this.isLoyal = isLoyal;	
	}
	
	/**
	 * @return the isLoyal
	 */
	public boolean getLoyal() {
		return isLoyal;
	}

	/**
	 * @param isLoyal the isLoyal to set
	 */
	public void setLoyal(boolean isLoyal) {
		this.isLoyal = isLoyal;
	}
	
	/**
	 * Calculates the monthly interest for Savings account using the formula 
	 * I = A - P, where A = P(1 + rt)
	 * @return monthly interest for Savings Account
	 */
	@Override
	public double monthlyInterest() {
		// 1/12 = 0.083333
		// 0.0035 = % for Savings in decimal form if customer is loyal
		if ( this.isLoyal ) {
			return (this.getBalance() * (1 + (0.0035 * 0.083333))) - this.getBalance();
		}
		
		// 0.0025 = % for Savings in decimal form if customer isn't loyal
		return (this.getBalance() * (1 + (0.0025 * 0.083333))) - this.getBalance();
	}

	/**
	 * Calculate the monthly fee for Savings account,
	 * if the balance 300 or more, the monthly fee is waived
	 * @return the monthly fee for Savings account (5)
	 */
	@Override
	public double monthlyFee() {	
		if ( this.getBalance() >= 300 ) {
			return 0; 
		}
	
		return SAVINGS_MONTHLY_FEE;
	}
	
	/**
	 * add the account type (Savings) to the print statement
	 * @return account info including type
	 */
	@Override
	public String toString() {
		String loyalValid = "";
		if ( isLoyal ) {
			loyalValid += "special Savings account*";
		} 
		
		return "*Savings*"  + super.toString()  + loyalValid ;
	}
	
	/**
	 * Check if the object is a Savings account, call equals from Account
	 * @return the first and last name of the account name
	 */
	@Override
	public boolean equals(Object obj) {
		if ( obj instanceof Savings) {
			return super.equals(obj) && getHolder().getFname().equals( ((Savings) obj).getHolder().getFname()) 
					&& getHolder().getLname().equals(((Savings) obj).getHolder().getLname());
		}
		
		return false;
	}
}
