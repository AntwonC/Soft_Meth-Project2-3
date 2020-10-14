import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class MoneyMarketTest {

	@Test
	void testMonthlyInterest() {
		Profile accountProfile = new Profile("Jane", "Doe"); 
		MoneyMarket moneyAccount = new MoneyMarket(accountProfile, 500, null, 0);
		
		Profile accountProfile2 = new Profile("John", "Doe"); 
		MoneyMarket moneyAccount2 = new MoneyMarket(accountProfile2, 999, null, 0);
		
		assertEquals(0.2708322500000122, moneyAccount.monthlyInterest());
		assertEquals(0.5411228355000048, moneyAccount2.monthlyInterest());
	}

	@Test
	void testMonthlyFee() {
		Profile accountProfile = new Profile("Jane", "Doe"); 
		MoneyMarket moneyAccount = new MoneyMarket(accountProfile, 500, null, 0);
		
		Profile accountProfile2 = new Profile("John", "Doe"); 
		MoneyMarket moneyAccount2 = new MoneyMarket(accountProfile2, 2500, null, 1);
		
		Profile accountProfile3 = new Profile("Hoda", "Doe"); 
		MoneyMarket moneyAccount3 = new MoneyMarket(accountProfile3, 2500, null, 7);
		
		assertEquals(12, moneyAccount.monthlyFee());
		assertEquals(0, moneyAccount2.monthlyFee());
		assertEquals(12, moneyAccount3.monthlyFee());
		
	}

}
