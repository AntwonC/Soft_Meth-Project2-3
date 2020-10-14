/**
 * Implements the Date class, has compareTo, isValid, and toString 
 * @author Anthony Chen, Hoda Moustafa
 *
 */

public class Date implements Comparable<Date>{
	private int year;
	private int month;
	private int day;
	
	//constructor 
	public Date (int year, int month, int day) {
		this.year = year;
		this.month = month;
		this.day = day;
	}
	
	/**
	 * @return the year
	 */
	public int getYear() {
		return year;
	}
	
	/**
	 * @param year the year to set
	 */
	public void setYear(int year) {
		this.year = year;
	}
	
	/**
	 * @return the month
	 */
	public int getMonth() {
		return month;
	}
	
	/**
	 * @param month the month to set
	 */
	public void setMonth(int month) {
		this.month = month;
	}
	
	/**
	 * @return the day
	 */
	public int getDay() {
		return day;
	}
	
	/**
	 * @param day the day to set
	 */
	public void setDay(int day) {
		this.day = day;
	}
	
	/**
	 * @param date, the date to be compared 
	 * @return 0 if they're equal, -1 if date is less than date, 1 if date is bigger than date
	 */
	@Override
	public int compareTo(Date date) { //return 0, 1, or -1
		int dayy = date.getDay();
		int monthh = date.getMonth();
		int yearr = date.getYear();

		if ( this.day == dayy && this.month == monthh && this.year == yearr) { //equal dates
			return 0;
		}
		
		// LESS THAN DATES
		if ( this.year < yearr ) { 
			return -1;
		
		//equal months 
		} else if (this.year == yearr ) {  //same year -> check the months 
			if ( this.month < monthh ) { //month is smaller -> -1 
				return -1; 
			
			}else if (this.month > monthh) { //month is bigger -> 1 
				return 1;
	
			} else if ( this.month == monthh ) { //check if the days are smaller or not
				if ( this.day < dayy) { //day is smaller -> -1
					return -1;
				} else if ( this.day > dayy ) { //day not smaller -> 1
					return 1;
				}
			}
		//GREATER THAN DATES
		} else if ( this.year > yearr ) { 
			return 1;
		}
		
		//will never reach this return if its all correct
		return 2;
		
	} 
	
	/**
	 * Changes the date to the format of mm/dd/yyyy
	 * @return the date in string format
	 */
	@Override
	public String toString() { 
		String monn = "";
		String dayy = "";
		
		//check if the month or day or less than 10, then add a 0 in front of it
		if ( this.month < 10 ) {
			monn += "0" + String.valueOf(this.month);
		} else {
			monn += String.valueOf(this.month);
		}
		
		if ( this.day < 10 ) {
			dayy += "0" + String.valueOf(this.day);
			
		} else { 
			dayy += String.valueOf(this.day); 
		}
		
		//combine all the dates together seperated by /
		return monn + "/" + dayy + "/" + String.valueOf(this.year);
	}  
	
	/**
	 * Helper method to check if a year is a leap year
	 * @param year to be checked 
	 * @return true if leap year, false otherwise
	 */
	private boolean isLeap(int year) {	
		if ( year % Month.QUADRENNIAL == 0 ) { // y%4 = 0
			if ( year % Month.CENTENNIAL == 0 )	{ //y%100 = 0
				if ( year % Month.QUATERCENTENNIAL == 0 ) { //y%400 = 0
					return true; 					
				} else {
					return false;
				}
			} else {
				return true;
			}			
		} else {			
			return false;
		}
		
	}
	
	/**
	 * Checks if the date is in valid format
	 * @return true if its a valid date, false otherwise
	 */
	public boolean isValid() {
		boolean leapYear = isLeap(this.year);
		//System.out.println("Is leap? " + leapYear);
		int monthh = this.month;
		
		//if the year is a positive number
		if ( this.year > 0 ) {
			if ( leapYear == true && monthh == Month.FEB) { //check if its a leap year and the month is feb
				if ( this.day >=1 && this.day <= (Month.DAYS_FEB + 1) ) { //days <= 29
					return true;
				}
		
			} else if ( leapYear == false && monthh == Month.FEB) { //if not a leap and month is feb
				if ( this.day >=1 && this.day <= (Month.DAYS_FEB) ) { // days <= 28
					return true;
				}
	
			} else if ( ((monthh >= 1 ) && (monthh <= Month.DEC) )) { //if not leap, just check standard month days
				if ( monthh == Month.JAN || monthh == Month.MAR || monthh == Month.MAY || monthh == Month.JUL 
				     || monthh == Month.AUG || monthh == Month.OCT || monthh == Month.DEC ) { //31 days 
					if ( this.day >= 1 && this.day <= Month.DAYS_ODD ) {
						return true;
					} else { //out of range
						return false;
					}
				} else if ( monthh == Month.APR || monthh == Month.JUN || monthh == Month.SEP || monthh == Month.NOV) { //30 days
					if ( this.day >= 1 && this.day <= Month.DAYS_EVEN) { 
						return true;
					} else { //out of range
						return false;
					}
				}
			}
	
		}
		
		return false;
	}
}