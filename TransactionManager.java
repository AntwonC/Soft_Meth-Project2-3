/**
 * Driver class for running 
 * @author Anthony Chen, Hoda Moustafa
 *
 */
import java.util.InputMismatchException;
import java.util.Scanner;

public class TransactionManager {
	public void run() {
		Scanner scan = new Scanner(System.in);
		System.out.println("Transaction processing starts.....");
		AccountDatabase accountDB = new AccountDatabase();
		
		while ( true ) {
			try {
				while ( scan.hasNextLine()) {
					String input = scan.next(); 

					if ( input.charAt(0) == 'O' && input.charAt(1) == 'C' && input.length() == 2) { //open checking
						String firstName = scan.next(); 
						String lastName = scan.next(); 
						double balance = scan.nextDouble();
						String date = scan.next(); 
						boolean directDeposit = scan.nextBoolean();
					
						//put the date in Date format, not String
						String tempMonth = "";
						int i = 0; 
						for ( i = 0; i < date.length(); i++) {
							if ( date.charAt(i) == '/' ) {
								break; 	
							} else {
								tempMonth += date.charAt(i); 
							}
						}
					
						String tempDay = "";
						for ( i += 1; i < date.length(); i++) {
							if ( date.charAt(i) == '/' ) {
								break; 	
							} else {
								tempDay += date.charAt(i); 
							}
						}
					
						String tempYear = "";
						for ( i += 1; i < date.length(); i++) {
							tempYear += date.charAt(i); 
						}
						
						//store the information in profile and make a new account
						Profile accountProfile = new Profile(firstName, lastName);
						Date accountDate = new Date(Integer.parseInt(tempYear), Integer.parseInt(tempMonth), Integer.parseInt(tempDay));
						Checking checkAccount = new Checking(accountProfile, balance, accountDate, directDeposit);
					
						//check if the date is valid
						if ( accountDate.isValid() ) {
							if ( accountDB.add(checkAccount) )	{
								System.out.println("Account opened and added to the database.");
							}
						
						} else {
							System.out.println(accountDate.toString() + " is not a valid date.");
						}
				
					} else if ( input.charAt(0) == 'O' && input.charAt(1) == 'S'  && input.length() == 2) { //open savings
						String firstName = scan.next(); 
						String lastName = scan.next(); 
						double balance = scan.nextDouble();
						String date = scan.next(); 
						boolean loyal = scan.nextBoolean();
					
						String tempMonth = "";
						int i = 0; 
						for ( i = 0; i < date.length(); i++) {
							if ( date.charAt(i) == '/' ) {
								break; 	
							} else {
								tempMonth += date.charAt(i); 
							}
						}
					
						String tempDay = "";
						for ( i += 1; i < date.length(); i++) {
							if ( date.charAt(i) == '/' ) {
								break; 	
							} else {
								tempDay += date.charAt(i); 
							}
						}
					
						String tempYear = "";
						for ( i += 1; i < date.length(); i++) {
							tempYear += date.charAt(i); 
						}
					
						//store the information in profile and make a new account
						Profile accountProfile = new Profile(firstName, lastName);
						Date accountDate = new Date(Integer.parseInt(tempYear), Integer.parseInt(tempMonth), Integer.parseInt(tempDay));
					
						Savings savingsAccount = new Savings(accountProfile, balance, accountDate, loyal);
						
						//check if the date is valid
						if ( accountDate.isValid() ) {
							if ( accountDB.add(savingsAccount) ) {
								System.out.println("Account opened and added to the database.");
							}
						} else {
							System.out.println(accountDate.toString() + " is not a valid date.");
						}
				
					} else if ( input.charAt(0) == 'O' && input.charAt(1) == 'M' && input.length() == 2) { //open moneymarket
						String firstName = scan.next();
						String lastName = scan.next(); 
						double balance = scan.nextDouble(); 
						String date = scan.next(); 
						
						String tempMonth = "";
						int i = 0; 
						for ( i = 0; i < date.length(); i++) {
							if ( date.charAt(i) == '/' ) {
								break; 	
							} else {
								tempMonth += date.charAt(i); 
							}
						}
						
						String tempDay = "";
						for ( i += 1; i < date.length(); i++) {
							if ( date.charAt(i) == '/' ) {
								break; 	
							} else {
								tempDay += date.charAt(i); 
							}
						}
					
						String tempYear = "";
						for ( i += 1; i < date.length(); i++) {
							tempYear += date.charAt(i); 
						}
					
						//store the information in profile and make a new account
						Profile accountProfile = new Profile(firstName, lastName);
						Date accountDate = new Date(Integer.parseInt(tempYear), Integer.parseInt(tempMonth), Integer.parseInt(tempDay));
					
						MoneyMarket marketAccount = new MoneyMarket(accountProfile, balance, accountDate, 0);
						
						//check if the date is valid
						if ( accountDate.isValid() ) {
							if ( accountDB.add(marketAccount) ) {
								System.out.println("Account opened and added to the database.");
							}	
						} else {
							System.out.println(accountDate.toString() + " is not a valid date.");
						}
					
					} else if ( input.charAt(0) == 'C' && input.charAt(1) == 'C' ) { //close checking
						String firstName = scan.next();
						String lastName = scan.next();
					
						Profile accountProfile = new Profile(firstName, lastName); 
						Checking checkAccount = new Checking(accountProfile, 0.0, null, false);
					
						if ( accountDB.remove(checkAccount) ) {
							System.out.println("Account closed and removed from the database.");
						}
						
					} else if ( input.charAt(0) == 'C' && input.charAt(1) == 'S'  && input.length() == 2) { //close savings
						String firstName = scan.next();
						String lastName = scan.next();
						
						Profile accountProfile = new Profile(firstName, lastName); 
						Savings savingAccount = new Savings(accountProfile, 0.0, null, false);
					
						if ( accountDB.remove(savingAccount) ) {
							System.out.println("Account closed and removed from the database.");
						}
						
					} else if ( input.charAt(0) == 'C' && input.charAt(1) == 'M'  && input.length() == 2) { //close moneymarket
						String firstName = scan.next();
						String lastName = scan.next();
						
						Profile accountProfile = new Profile(firstName, lastName); 
						MoneyMarket moneyAccount = new MoneyMarket(accountProfile, 0.0, null, 0);
						
						if ( accountDB.remove(moneyAccount) ) {
							System.out.println("Account closed and removed from the database.");
						}
					} else if ( input.charAt(0) == 'D' && input.charAt(1) == 'C'  && input.length() == 2) {  //deposit checking
						String firstName = scan.next(); 
						String lastName = scan.next(); 
						Double amountDeposit = scan.nextDouble(); 
						
						Profile accountProfile = new Profile(firstName, lastName);
						Checking checkAccount = new Checking(accountProfile, 0.0, null, false);
					
						
						if ( accountDB.deposit(checkAccount, amountDeposit) ) {
							System.out.println(amountDeposit + " deposited to account.");
						} else {
							System.out.println("Account does not exist.");
						}
					
					} else if ( input.charAt(0) == 'D' && input.charAt(1) == 'S'  && input.length() == 2) { //deposit savings
						String firstName = scan.next(); 
						String lastName = scan.next(); 
						Double amountDeposit = scan.nextDouble(); 
						
						Profile accountProfile = new Profile(firstName, lastName);
						Savings savingsAccount = new Savings(accountProfile, 0.0, null, false);
						
						if ( accountDB.deposit(savingsAccount, amountDeposit) ) {
							System.out.println(amountDeposit + " deposited to account.");
						} else {
							System.out.println("Account does not exist.");
						}
					
					} else if ( input.charAt(0) == 'D' && input.charAt(1) == 'M'  && input.length() == 2) { //deposit moneymarket
						String firstName = scan.next(); 
						String lastName = scan.next(); 
						Double amountDeposit = scan.nextDouble(); 
						
						Profile accountProfile = new Profile(firstName, lastName);
						MoneyMarket moneyAccount = new MoneyMarket(accountProfile, 0.0, null, 0);
						
						if ( accountDB.deposit(moneyAccount, amountDeposit) ) {
							System.out.println(amountDeposit + " deposited to account.");
						} else {
							System.out.println("Account does not exist.");
						}
						
					} else if ( input.charAt(0) == 'W' && input.charAt(1) == 'C'  && input.length() == 2) { //withdraw checking
						String firstName = scan.next(); 
						String lastName = scan.next(); 
						Double amountWithdrawed = scan.nextDouble(); 
						
						Profile accountProfile = new Profile(firstName, lastName);
						Checking checkAccount = new Checking(accountProfile, 0.0, null, false);
						
						int output = accountDB.withdrawal(checkAccount, amountWithdrawed);
						
						
						if ( output == 0 ) {
							System.out.println(amountWithdrawed + " withdrawn from account.");
						} else if ( output == -1 ) {
							System.out.println("Account does not exist.");
						} else if ( output == 1 ) {
							System.out.println("Insufficient funds.");
						}
						
					} else if ( input.charAt(0) == 'W' && input.charAt(1) == 'S' && input.length() == 2 ) { //withdraw savings
						String firstName = scan.next(); 
						String lastName = scan.next(); 
						Double amountWithdrawed = scan.nextDouble(); 
						
						Profile accountProfile = new Profile(firstName, lastName);
						Savings savingsAccount = new Savings(accountProfile, 0.0, null, false);
						
						int output = accountDB.withdrawal(savingsAccount, amountWithdrawed);
						if ( output == 0 ) {
							System.out.println(amountWithdrawed + " withdrawn from account.");
						} else if ( output == -1 ) {
							System.out.println("Account does not exist.");
						} else if ( output == 1 ) {
							System.out.println("Insufficient funds.");
						}
						
					} else if ( input.charAt(0) == 'W' && input.charAt(1) == 'M'  && input.length() == 2) { //withdraw moneymarket
						String firstName = scan.next(); 
						String lastName = scan.next(); 
						Double amountWithdrawed = scan.nextDouble(); 
						
						Profile accountProfile = new Profile(firstName, lastName);
						MoneyMarket moneyAccount = new MoneyMarket(accountProfile, 0.0, null, 0);
						
						int output = accountDB.withdrawal(moneyAccount, amountWithdrawed);
						
						if ( output == 0 ) {
							System.out.println(amountWithdrawed + " withdrawn from account.");
						} else if ( output == -1 ) {
							System.out.println("Account does not exist.");
						} else if ( output == 1 ) {
							System.out.println("Insufficient funds.");
						}
						
					} else if ( input.charAt(0) == 'P' && input.charAt(1) == 'A' && input.length() == 2) { //print accounts
						accountDB.printAccounts();		
						
					} else if ( input.charAt(0) == 'P' && input.charAt(1) == 'D' && input.length() == 2 ) { //print by date
						accountDB.printByDateOpen();
						
					} else if ( input.charAt(0) == 'P' && input.charAt(1) == 'N' && input.length() == 2) { //print by last name
						accountDB.printByLastName();
						
					} else if ( input.charAt(0) == 'Q' && input.length() == 1 ) { //quit
						System.out.println("Transaction processing completed.");
						return;
						
					} else { //bad commands
						System.out.println("Command '" + input + "' not supported!");
						scan.nextLine();
						
					} 
				
				}
				scan.close();
			
			} catch (InputMismatchException e) { 
				System.out.println("Input data type mismatch.");
				scan.nextLine();
				continue;
			
			} catch ( NumberFormatException e ) {
				System.out.println("Input data type mismatch.");
				scan.hasNextLine(); 
				continue;
			}
		}
			
	}
}