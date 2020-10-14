/**
 * This is an array-based container class with an initial capacity of 5.
 * It will automatically grow the capacity by 5 if the database is full.
 * The array shall hold different account instances in Checking, Savings or MoneyMarket.
 * @author Anthony Chen, Hoda Moustafa
 *
 */

public class AccountDatabase {
	public static final int INITIAL_SIZE = 5;
	private Account[] accounts;
	private int size;
	
	//counstructor
	public AccountDatabase() {
		accounts = new Account[INITIAL_SIZE];
		size = 0; 
	}
	
	/**
	 * Finds the index of the account, uses equals from Account to find the right
	 * account type
	 * @param account to be found
	 * @return index of account, -1 if not found
	 */
	private int find(Account account) {
		for ( int i = 0 ; i < accounts.length; i++ ) {		
			if ( accounts[i] != null && accounts[i].equals(account) ) { //check if accountDB isn't empty, and if the item is the one we want
				return i; //return the index of the item needed
			}
		}	
		return -1;
	}
	
	/**
	 * Increases the size of the Accounts array by 5 if the size is full when adding items
	 */
	private void grow() { 
		int capacity = accounts.length;
		Account [] tempArray = new Account[capacity += INITIAL_SIZE]; //tempArray with new capacity
		for ( int i = 0; i < accounts.length; i++ ) { // Transfer all accounts into new Accounts
			tempArray[i] = accounts[i];
		}		
		accounts = tempArray;	
		return;
	}
	
	/**
	 * Add an account to the Account array, no duplicates allowed (if the account has the same name),
	 * calls grow if there isn't enough space
	 * @param account to be added 
	 * @return true if the account doesn't already exist, false if account already there
	 */
	public boolean add(Account account) { 
		// Accounts is full, increase the size
		if ( size == accounts.length ) {
			grow(); 
		}
		
		//add the account to the array
		for ( int i = 0; i < accounts.length; i++) {
			if ( accounts[i] == null ) {
				accounts[i] = account;
				size++; 
				break;
			} else if ( accounts[i].equals(account) ) {
				System.out.println("Account is already in the database.");
				return false;
			}
		}
		
		
		return true;
	}
	
	/**
	 * Remove an account from the Accounts array, calls find() to get the index of the account
	 * @param account to be removed
	 * @return true if the account was removed, false otherwise
	 */
	public boolean remove(Account account) {
		//if there is nothing to remove
		if ( size == 0 ) { 
			System.out.println("Database is empty");
			return false;
		}
		
		int found = find(account); //index of account to remove
		
		if ( found >= 0 ) { //if the item exists , replace it with the last item 
			accounts[found] = null;
			accounts[found] = accounts[size-1];
			accounts[size-1] = null;
			size--;
			return true;
		} else {
			System.out.println("Account does not exist.");
			return false;
		}
		
	}
	
	/**
	 * Deposit the amount desired for a specific account, call credit() from
	 * Account to increase the balance 
	 * @param account specified that wants to deposit
	 * @param amount to deposit
	 * @return true if deposit successful, false otherwise
	 */
	public boolean deposit(Account account, double amount) {
		int found = find(account);
		
		//account does not exist
		if ( found == -1 ) {
			return false;
		}
		
		//get the balance, and call credit() to add the amount to balance
		double balance = accounts[found].getBalance();
		account.setBalance(balance);
		account.credit(amount);
		accounts[found].setBalance(account.getBalance());
		return true;
	
	}
	
	/**
	 * Withdraw amount desired for a specific account
	 * @param account specified that wants to withrdaw
	 * @param amount to withdraw
	 * @return 0 if successfully withdrew, 1 if there are insufficient funds,
	 * 			-1 if the account doesn't exist
	 */
	public int withdrawal(Account account, double amount) { 
		int found = find(account); //get the account that wants to withdraw
		
		//if the account doesn't exist 
		if ( found == -1 ) {
			return -1; 
		}
		//get the balance amount, and call debit() to decrease the balance by amount 
		Double balance = accounts[found].getBalance();
		account.setBalance(balance);
		account.debit(amount); 
		
		//if its less than 0, means that there are insufficient funds
		if ( account.getBalance() < 0 ) {
			return 1; 
		} 
		accounts[found].setBalance(account.getBalance());
		
		
		//check if the account is a MoneyMarket account, then increment the count of withdrawls
		if ( accounts[found] instanceof MoneyMarket ) {
			MoneyMarket moneyAcc = (MoneyMarket) accounts[found];
			int numberOfWithdraws = moneyAcc.getWithdrawals() + 1;
			moneyAcc.setWithdrawals(numberOfWithdraws);
		}
		
		return 0;
	}
	
	/**
	 * Sorts the accounts according to the date opened in ascending order 
	 * using selection sort, calls the compareTo method in Date class to sort
	 */
	private void sortByDateOpen() {
		for ( int i = 0; i < size-1; i++ ) {
			int minIndex = i; 
			for ( int j = i + 1; j < size; j++) { 
				//returns -1 if the date is less than
				if ( accounts[j].getDateOpen().compareTo(accounts[minIndex].getDateOpen()) == -1 ) {
					minIndex = j;
				}
			}
			Account temp = accounts[minIndex];
			accounts[minIndex] = accounts[i];
			accounts[i] = temp;
		}
		return;
	}
	
	/**
	 * Sorts the accounts according to the last name in ascending order 
	 * using selection sort
	 */
	private void sortByLastName() { 
		for ( int i = 0; i < size-1; i++) {
			int minIndex = i; 
			for(int j = i + 1; j < size; j++) {
				if ( accounts[j].getHolder().getLname().compareTo(accounts[minIndex].getHolder().getLname()) < 0 ) {  
					System.out.println(accounts[j].getHolder().getLname());
					minIndex = j;
				} 
			}
			Account temp = accounts[minIndex];
			accounts[minIndex] = accounts[i];
			accounts[i] = temp;
		}
		return;
	}
	
	/**
	 * Calls the sorting method, and prints the array of accounts in ascending
	 * order according to their date, and calculate the new balance.
	 */
	public void printByDateOpen() {
		if ( size == 0 ) {
			System.out.println("Database is empty.");
			return;
		}
		
		sortByDateOpen();
		
		System.out.println("--Printing statements by date opened--");
		printAccounts();
		for ( int i = 0; i < accounts.length; i++) {
			if ( accounts[i] != null ) {
				System.out.println();
				System.out.println(accounts[i].toString());
				System.out.println("-interest: $ " + String.format("%.2f", accounts[i].monthlyInterest()));
				System.out.println("-fee: $ " + String.format("%.2f", accounts[i].monthlyFee()));
				double totalBalance =  (accounts[i].getBalance() + accounts[i].monthlyInterest()) - accounts[i].monthlyFee();
				
				//update the total balance
				accounts[i].setBalance(totalBalance);
				System.out.println("-new balance: $ " + String.format("%.2f", totalBalance));
				System.out.println();
			}
		}
		System.out.println("--end of printing--");
		System.out.println();
		return;
	} 
	
	/**
	 * Print the accounts from account database in ascending order according
	 * to their last name, and calculate the new balance.
	 */
	public void printByLastName() {
		if ( size == 0 ) {
			System.out.println("Database is empty.");
			return;
		}
		
		sortByLastName(); 
		System.out.println("--Printing statements by last name--");
		for ( int i = 0; i < accounts.length; i++) {
			if ( accounts[i] != null ) {
				System.out.println();
				System.out.println(accounts[i].toString());
				System.out.println("-interest: $ " + String.format("%.2f", accounts[i].monthlyInterest()));
				System.out.println("-fee: $ " + String.format("%.2f", accounts[i].monthlyFee()));
				double totalBalance =  (accounts[i].getBalance() + accounts[i].monthlyInterest()) - accounts[i].monthlyFee();
				
				//update the total balance
				accounts[i].setBalance(totalBalance);
				System.out.println("-new balance: $ " + String.format("%.2f", totalBalance));
				System.out.println();
			}
		}
		System.out.println("--end of printing--");
		System.out.println();
		return;
	}
	
	/**
	 * Print all the accounts in the AccountDB using the toString method in Account
	 */
	public void printAccounts() { 
		if ( size == 0 ) {
			System.out.println("Accounts is empty");
			return;
		}
		
		System.out.println("--Listing accounts in the database--");
		for(int i = 0; i < accounts.length; i++) {
			if ( accounts[i] != null ) {
				System.out.println(accounts[i].toString());
				
			}
		}
		System.out.println("--end of listing--");
		System.out.println();
		return;
	}
}
