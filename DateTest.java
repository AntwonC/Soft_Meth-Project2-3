import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class DateTest {
	
	@Test
	void testCompareTo() {
		Date date0 = new Date(2001, 4, 1); // 4/1/2001
		Date date1 = new Date(2001, 4, 1); // 4/1/2001
		
		Date date2 = new Date(2000, 6, 7); // 6/7/2000
		Date date3 = new Date(2000, 3, 7); // 3/7/2000
		Date date4 = new Date(2000, 3, 2); // 3/2/2000
		Date date5 = new Date(2000, 6, 20); // 6/20/2000
		
		
		assertEquals(0, date0.compareTo(date1));
		
		assertEquals(-1, date2.compareTo(date1)); //less than years
		assertEquals(-1, date3.compareTo(date2)); //same year, less than month, same day
		assertEquals(-1, date2.compareTo(date5)); //same year, less than month, diff day		
		assertEquals(-1, date4.compareTo(date3)); //same year, same month, less than day
		
		assertEquals(1, date1.compareTo(date2)); //greater year
		assertEquals(1, date2.compareTo(date3)); //same year, greater month, same day
		assertEquals(1, date5.compareTo(date2)); //same year, greater month, diff day
		assertEquals(1, date3.compareTo(date4)); //same year, same month, greater day
	}

	@Test
	void testToString() {
		Date date1 = new Date(2001, 2, 3);
		Date date2 = new Date(2001, 2, 3);
		Date date3 = new Date(2002, 2, 3);
		
		assertEquals("02/03/2001", date1.toString());
		assertEquals("02/03/2001", date2.toString());
		assertEquals("02/03/2002", date3.toString());
	}

	@Test
	void testIsValid() {
		//leap years: 2000, 2004, 2008, 2012, 2016, 2020, 2024
		Date date1 = new Date(2004, 24, 29); // 24/29/2004
		Date date2 = new Date(2001, 2, 3); // 2/3/2001
		Date date3 = new Date(2002, 9, 10); // 9/10/2002
		
		Date date4 = new Date(2013, 13, 10); // 13/10/2013
		Date date5 = new Date(2013, 1, 50); // 1/50/2013
		Date date6 = new Date(2013, 4, 31); // 4/31/2013
		Date date7 = new Date(2013, 3, 30); // 4/31/2013
		
		Date date8 = new Date(2013, 2, 29); // 2/29/2013
		Date date9 = new Date(2008, 2, 29); // 2/29/2008
		
		Date date10 = new Date(2008, 2, 28); // 2/29/2008
		Date date11 = new Date(2009, 2, 28); // 2/28/2009
		
		assertEquals(false, date1.isValid());
		assertEquals(true, date2.isValid());
		assertEquals(true, date3.isValid());
		assertEquals(false, date4.isValid()); //wrong month
		assertEquals(false, date5.isValid()); //wrong day
		assertEquals(false, date6.isValid()); //wrong day for wrong month 
		
		assertEquals(true, date7.isValid()); //wrong day for wrong month
		
		assertEquals(false, date8.isValid()); //not leap 29 days in feb
		assertEquals(true, date9.isValid()); //leap 29 days in feb
		
		
		assertEquals(true, date10.isValid()); //leap 28 days in feb
		
		assertEquals(true, date11.isValid()); //not leap 28 days in feb
		 
	}
}