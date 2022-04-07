package problem3;

public class Date {

	private Integer year;
	private Integer month;
	private Integer day;

	public Date(int year, int month, int day) {
		setYear(year);
		setMonth(month);
		setDay(day);
	}
	
	/**
	 * @param year
	 * if it is positive integer,then use it to set this.year,
	 * else this is set to null-date object.
	 */
	private void setYear(int year) {
		if ( year > 0 ) {
			this.year = year;
		} else {
			setNullDate();
		}
	}

	/**
	 * @param month
	 * if it is valid,then use it to set this.month,
	 * else this is set to null-date object.
	 */
	private void setMonth(int month) {
		if (year != null && month > 0 && month < 13) {
			this.month = month;
		} else {
			setNullDate();
		}
	}

	/**
	 * @param day
	 * if it is valid,then use it to set this.day,
	 * else this is set to null-date object.
	 */
	private void setDay(int day) {
		int monthdays[] = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };

		if (year != null && month != null) {

			monthdays[1] = this.isLeapYear() ? 29 : 28;

			if (day > 0 && day <= monthdays[this.month - 1]) {
				this.day = day;
			} else {
				setNullDate();
			}

		} else {
			setNullDate();
		}
	}
	
	/**
	 * @return  the days of the year if this is valid, if not, return 0.
	 * Requires this is valid.
	 */
	public int daysOfYear() {
		int ret = 0;
        int i;
		int monthdays[] = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
		
		if (year != null && month != null && day != null) {
			monthdays[1] = this.isLeapYear() ? 29 : 28;
			for(i=0; i<month-1; i++) {
				ret += monthdays[i];
			}
        
			ret += day;
		}
		
		return ret;
   	}
	
	/**
	 * @return  0~6(Sunday~Saturday), the day of the week if this is valid, if not, return -1.
	 * Requires this is valid.
	 */
	public int dayOfWeek() {
		int ret = -1;
		int tdays;
		
		if (year != null && month != null && day != null) {
			tdays = daysOfYear();
			ret =  (year - 1 + (year - 1) / 400 - (year - 1) / 100 + (year - 1) / 4 + tdays) % 7;
		}
		
		return ret;
	}

	/**
	 * @return  true only if the year is a leap year. 
	 * Requires year to be present and non-negative integer; if not, an ItemOutOfRange is thrown.
	 */
	public boolean isLeapYear() {

		boolean result = false;

		if (year == null|| year<=0 ) {
			throw new ItemOutOfRange(
					"year is out of range. Cannot determine if leap year.");
		} else {
			if (year % 100 == 0) {
				// this is a century year
				if (year % 400 == 0) {
					result = true;
				}
			} else if (year % 4 == 0) {
				result = false;
			}
		}
		return result;
	}

	private void setNullDate() {
		this.year = null;
		this.month = null;
		this.day = null;
	}

}
