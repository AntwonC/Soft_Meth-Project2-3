/**
 * Subclass of Abstract super class Account. Implements the abstract methods from 
 * Account. Handles the monthly interest and fee for MoneyMarket accounts.
 * @author Anthony Chen, Hoda Moustafa
 *
 */

public class MoneyMarket extends Account{
	private int withdrawals;
	public static final double MONEY_MARKET_MONTHLY_FEE = 12.0;
	
	//constructor 
	public MoneyMarket(Profile holder, double balance, Date dateOpen, int withdrawals) {
		super(holder, balance, dateOpen);
		this.withdrawals = withdrawals;	
	}
	
	/**
	 * @return the withdrawals
	 */
	public int getWithdrawals() {
		return withdrawals;
	}

	/**
	 * @param withdrawals the withdrawals to set
	 */
	public void setWithdrawals(int withdrawals) {
		this.withdrawals = withdrawals;
	}
	
	
	/**
	 * Calculates the monthly interest for MoneyMarker account using the formula 
	 * I = A - P, where A = P(1 + rt)
	 * @return monthly interest for MoneyMarket Account
	 */
	@Override
	public double monthlyInterest() {
		// 1/12 = 0.083333
		// 0.0065 = % for MoneyMarket in decimal form 
		return (this.getBalance() * (1 + (0.0065 * 0.083333))) - this.getBalance();
	}
	
	/**
	 * Calculate the monthly fee for MoneyMarket account,
	 * if the balance 2500 or more, the monthly fee is waived
	 * if the number of withdrawls is more than 6, fee is not waived
	 * @return the monthly fee for MoneyMarket account (12)
	 */
	@Override
	public double monthlyFee() {
		if ( this.withdrawals > 6 ) {
			return MONEY_MARKET_MONTHLY_FEE; 
		} else if ( this.getBalance() >= 2500 ) {
			return 0;
		}
		return MONEY_MARKET_MONTHLY_FEE;
	}
	
	/**
	 * add the account type (MoneyMarket) to the print statement
	 * @return account info including type
	 */
	@Override
	public String toString() {
		return "*Money Market*" + super.toString()  + getWithdrawals() + " withdrawals*";
	}
	
	/**
	 * Check if the object is a MoneyMarket account, call equals from Account
	 * @return the first and last name of the account name
	 */
	@Override 
	public boolean equals(Object obj) {
		if ( obj instanceof MoneyMarket) {
			return super.equals(obj) && getHolder().getFname().equals( ((MoneyMarket) obj).getHolder().getFname()) 
					&& getHolder().getLname().equals(((MoneyMarket) obj).getHolder().getLname());
			}
		return false;
	}
}
