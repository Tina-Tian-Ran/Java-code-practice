// File name: Reminder.java
// Author: Roth -- CS2201 @Vanderbilt
// Description: Implementation of a simple Reminder class. The Reminder class is derived
//              from the Date class, so every Reminder object is also a Date object. 
//              Essentially, a reminder is nothing but a date with a string.
//              Reminder objects are immutable -- once created they cannot be changed.

public class Reminder extends Date {
    
    // Every Reminder is a Date that has an associated message
    private final String mMsg;

    
    /**
     * Reminder()
     * The default constructor.
     * Will initialize using today's date and an empty message
     */
    public Reminder() {
        super();
        mMsg = "";
    }

    
    /**
     * Reminder(Date date, String msg)
     * Alternate constructor that will use the specified date and message.
     * @param date -- the date to initialize this reminder
     * @param msg -- the message of the reminder
     */
    public Reminder(Date date, String msg) {
        super(date);
        mMsg = msg==null ? "" : msg;
    }

    
    /**
     * Reminder(String dateStr, String msg)
     * Alternate constructor that will use the specified date and message.
     * @param dateStr -- the date string to initialize this reminder
     * @param msg -- the message of the reminder
     */
    public Reminder(String dateStr, String msg) {
        super(dateStr);
        mMsg = msg==null ? "" : msg;
    }

    
    /**
     * Reminder(Reminder otherReminder)
     * Alternate constructor that initializes the Reminder object to the data contained in 
     *   another Reminder object
     * @param otherReminder -- the Reminder object whose data is to be copied/used
     */
    public Reminder(Reminder otherReminder) {
        super(otherReminder);
        mMsg = otherReminder.mMsg;
    }


    /**
     * clone() 
     * Return a clone of this Reminder object.
     * @return the clone.
     */
    public Reminder clone() {
        return new Reminder(this);
    }

    
    /**
     * getDate()
     * Accessor method to get the Date of the Reminder
     * @return -- the Date of the Reminder
     */
    public Date getDate() {
        return new Date(this);
    }

    
    /**
     * getMsg()
     * Accessor method to get the message of the Reminder
     * @return -- the message of the Reminder, or "No Message" if the message is empty
     */
    public String getMsg() {
        if (mMsg.isEmpty()) {
            return "No Message";
        } else {
            return mMsg;
        }
    }

    
    /**
     * toString()
     * Convert the Reminder object to a printable form. 
     * @return a string representation of the Reminder object
     */
    public String toString() {
        return "On " + super.toString() + ": " + getMsg();
    }

    
    /**
     * equals(Object other)
     * Test equality
     * @param other -- the object to be compared
     * @return true if the two Reminders have the same date and message; false otherwise.
     */
    public boolean equals(Object other) {
        if (!(other instanceof Reminder)) {
            return false;
        }
        Reminder otherRem = (Reminder) other;
        return super.equals(otherRem) && getMsg().equals(otherRem.getMsg());
    }

    /**
     * plusDays(int n)
     * Add n days to this Reminder. If n is positive, we move to a future date. If n is negative
     * we move to a past date; i.e., plusDays(+1) moves to the next day, plusDays(-1) moves
     * to the prior day. 
     * Throws a RuntimeException if the resulting year is not valid.
     * @param n -- the number of days to move, can be positive or negative
     * @return -- the new Reminder
     */
    public Reminder plusDays(int n) {
        return new Reminder(super.plusDays(n), getMsg());

    }


    /**
     * plusMonths(int n)
     * Add n months to this Reminder. If n is positive, we move to a future date. If n is negative
     * we move to a past date; i.e., plusMonths(+1) moves to the next month, plusMonths(-1) moves
     * to the prior month. If moving to a month with fewer days, and the day is beyond the max
     * for that month, the day will be reset to the last day for that month. 
     * Throws a RuntimeException if the resulting year is not valid.
     * @param n -- the number of months to move, can be positive or negative
     * @return -- the new Reminder
     */
    public Reminder plusMonths(int n) {
        return new Reminder(super.plusMonths(n), getMsg());
    }


    /**
     * plusYears(int n)
     * Add n years to this Reminder. If n is positive, we move to a future date. If n is negative
     * we move to a past date; i.e., plusYears(+1) moves to the next year, plusYears(-1) moves
     * to the prior year. 
     * Throws a RuntimeException if the resulting year is not valid.
     * @param n -- the number of years to move, can be positive or negative
     * @return -- the new Reminder
     */
    public Reminder plusYears(int n) {
        return new Reminder(super.plusYears(n), getMsg());
    }
}
