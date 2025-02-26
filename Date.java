// File name: Date.java
// Author: Roth -- CS2201 @Vanderbilt
// Description: Implementation of a simple Date class, where every date has a
//              month, day, and year. Date objects are immutable -- once created
//              they cannot be changed.

import java.time.LocalDate;

public class Date {
    
    // Every date has a day, month, and year
    private final int mDay;
    private final int mMonth;
    private final int mYear;
    
    // how many days in each month (with 1-based indexing)
    private static final int[] days = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};


    /**
     * Date() 
     * The default constructor.
     * Will initialize the Date object to today's date.
     */
    public Date() {
        LocalDate today = LocalDate.now();
        mDay = today.getDayOfMonth();
        mMonth = today.getMonthValue();
        mYear = today.getYear();
    }

    
    /**
     * Date(int month, int day, int year)
     * Alternate constructor that initializes the Date object to the provided month, day, and year.
     * Throws IllegalArgumentException if the provided parameters do not represent a valid date.
     * @param month -- the month
     * @param day -- the day
     * @param year -- the year
     */
    public Date(int month, int day, int year) {
        verifyData(month, day, year);
        mMonth = month;
        mDay = day;
        mYear = year;
    }

    
    /**
     * Date(Date otherDate)
     * Alternate constructor that initializes the Date object to the data contained in 
     *   another Date object
     * @param otherDate -- the Date object whose data is to be copied/used
     */
    public Date(Date otherDate) {
        mMonth = otherDate.mMonth;  // the otherDate has already been verified
        mDay = otherDate.mDay;
        mYear = otherDate.mYear;
    }

    
    /**
     * Date(String dateStr)
     * Alternate constructor that initializes the Date object to the data contained in
     *   the parameter string that is expected to be in the MM/DD/YYYY format.
     * Throws IllegalArgumentException if the provided string does not represent a valid date.
     * @param dateStr -- the string contained MM/DD/YYYY formatted date information
     */
    public Date(String dateStr) {
        String[] parts = dateStr.split("/");
        if (parts.length != 3) {
            throw new IllegalArgumentException("Date string not in the format MM/DD/YYYY");
        }

        // the following 3 statements will throw exceptions if the 3 part are not integer values
        int month = Integer.parseInt(parts[0]);
        int day = Integer.parseInt(parts[1]);
        int year = Integer.parseInt(parts[2]);
        
        verifyData(month, day, year);
        mMonth = month;
        mDay = day;
        mYear = year;
    }

    
    /**
     * verifyData(int month, int day, int year)
     * Private helper function to verify that the month, day, and year represent a valid date.
     * Throws IllegalArgumentException if the provided data does not represent a valid date.
     * @param month
     * @param day
     * @param year
     */
    private static void verifyData(int month, int day, int year) {
        if (month < 1 || month > 12) {
            throw new IllegalArgumentException("Month must be a value between 1 & 12.");
        }

        if (day < 1 || day > days[month]) {
            throw new IllegalArgumentException("Day value invalid for the given month.");
        }

        if (year < 1 || year > 9999) {
            throw new IllegalArgumentException("Year must be a value between 1 & 9999.");
        }
    }

    
    /**
     * clone() 
     * Return a clone of this Date object.
     * @return the clone.
     */
    public Date clone() {
        return new Date(this);
    }


    /**
     * getMonth()
     * Accessor method to get the month of the object
     * @return the month of the Date object
     */
    public int getMonth() {
        return mMonth;
    }

    
    /**
     * getDay()
     * Accessor method to get the day of the object
     * @return the day of the Date object
     */
    public int getDay() {
        return mDay;
    }

    
    /**
     * getYear()
     * Accessor method to get the year of the object
     * @return the year of the Date object
     */
    public int getYear() {
        return mYear;
    }

    
    /**
     * toString()
     * Convert the Date object to a printable form. The MM/DD/YYYY format is used.
     * @return a string representation of the Date object
     */
    public String toString() {
        return String.format("%02d/%02d/%04d",mMonth,mDay,mYear);
    }

    
    /**
     * equals(Object other)
     * Test equality
     * @param other -- the object to be compared
     * @return true if the two Dates have the same month, day, and year; false otherwise.
     */
    public boolean equals(Object other) {
        if (!(other instanceof Date)) {
            return false;
        }
        
        Date otherDate = (Date) other;
        return mDay == otherDate.mDay &&
               mMonth == otherDate.mMonth &&
               mYear == otherDate.mYear;
    }

    
    /**
     * lessthan(Date otherDate)
     * Compare two Dates
     * @param otherDate -- the other date to be compared against
     * @return true if 'this' object is less than (comes before) the parameter Date.
     */
    public boolean lessthan(Date otherDate) {
        return mYear < otherDate.mYear ||
                (mYear == otherDate.mYear && mMonth < otherDate.mMonth) ||
                (mYear == otherDate.mYear && mMonth == otherDate.mMonth && mDay < otherDate.mDay);
    }

    
    /**
     * greaterthan(Date otherDate)
     * Compare two Dates
     * @param otherDate -- the other date to be compared against
     * @return true if 'this' object is greater than (comes after) the parameter Date.
     */
    public boolean greaterthan(Date otherDate) {
        return mYear > otherDate.mYear ||
                (mYear == otherDate.mYear && mMonth > otherDate.mMonth) ||
                (mYear == otherDate.mYear && mMonth == otherDate.mMonth && mDay > otherDate.mDay);
    }


    /**
     * plusDays(int n)
     * Add n days to this date. If n is positive, we move to a future date. If n is negative
     * we move to a past date; i.e., plusDays(+1) moves to the next day, plusDays(-1) moves
     * to the prior day. 
     * Throws a RuntimeException if the resulting year is not valid.
     * @param n -- the number of days to move, can be positive or negative
     * @return -- the new Date
     */
    public Date plusDays(int n) {
        int day = mDay;
        int month = mMonth;
        int year = mYear;
// ignore leap years
        while (n > 0) {   // adding days
            if (day + n <= days[month]) {
                day += n;
                n = 0;
            } else {
                n -= (days[month] - day + 1);
                day = 1;
                month++;
                if (month > 12) {
                    month = 1;
                    year++;
                }
            }
        }
        while (n < 0) {   // subtracting days
            if (day + n > 0) {
                day += n;
                n = 0;
            } else {
                n += (day); // ie. when jumping from Aug 13 to Jul 31, we go back 13 days
                month--;
                if (month < 1) {
                    month = 12;
                    year--;
                }
                day = days[month];
            }
        }
        if (year < 1 || year > 9999) {
            throw new RuntimeException("Year went out of bounds (<1 or >9999).");
        }
        return new Date(month, day, year);
    }


    /**
     * plusMonths(int n)
     * Add n months to this date. If n is positive, we move to a future date. If n is negative
     * we move to a past date; i.e., plusMonths(+1) moves to the next month, plusMonths(-1) moves
     * to the prior month. If moving to a month with fewer days, and the day is beyond the max
     * for that month, the day will be reset to the last day for that month. 
     * Throws a RuntimeException if the resulting year is not valid.
     * @param n -- the number of months to move, can be positive or negative
     * @return -- the new Date
     */
    public Date plusMonths(int n) {
        if (n == 0) {
            return this;
        }
        int monthCount = mYear * 12 + (mMonth - 1);
        int calcMonths = monthCount + n;
        int newYear = Math.floorDiv(calcMonths, 12);
        int newMonth = Math.floorMod(calcMonths, 12) + 1;
        int newDay = Math.min(mDay, days[newMonth]); // can't have a day greater than the last day
        if (newYear < 1 || newYear > 9999) {
            throw new RuntimeException("Year went out of bounds (<1 or >9999).");
        }
        return new Date(newMonth, newDay, newYear);
    }


    /**
     * plusYears(int n)
     * Add n years to this date. If n is positive, we move to a future date. If n is negative
     * we move to a past date; i.e., plusYears(+1) moves to the next year, plusYears(-1) moves
     * to the prior year. 
     * Throws a RuntimeException if the resulting year is not valid.
     * @param n -- the number of years to move, can be positive or negative
     * @return -- the new Date
     */
    public Date plusYears(int n) {
        int newYear = mYear + n;
        if (newYear < 1 || newYear > 9999) {
            throw new RuntimeException("Year went out of bounds (<1 or >9999).");
        }
        return new Date(mMonth, mDay, newYear);
    }

}
