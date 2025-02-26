// File name: CalDemo.java
// Author: 
// VUnetid: 
// Email: 
// Class: CS2201
// Date: 
// Honor statement:
// Assignment Number: 
// Description: An executable program to try things with the Calendar class


public class CalDemo {
    public static void main(String[] args) {
        
        Date today = new Date();
        Date tomorrow = today.plusDays(+1);
        Date yesterday = today.plusDays(-1);
        Date twoDaysAgo = today.plusDays(-2);
        System.out.println("Today is " + today + ", and tomorrow is " + tomorrow);
        System.out.println("Yesterday was " + yesterday + ", and two days ago was " + twoDaysAgo);

        Calendar cal1 = new Calendar();
        
        Reminder rem = new Reminder("1/8/2024", "First day of classes");
        int index = cal1.addRem(rem);

        if (index != 0) {
            System.out.println("insert failed to return index 0 for first insertion");
            System.out.println("Returned index == " + index);
        }

        rem = new Reminder("2/7/2024", "Exam #1");
        cal1.addRem(rem);
        rem = new Reminder("4/26/2024", "Final Exam");
        cal1.addRem(rem);
        rem = new Reminder(today, "Work on my CS2201 project!!");
        cal1.addRem(rem);
        
        System.out.println("My important dates:\n" + cal1);
        
        Date newYearsEve = new Date("12/31/2024");
        
        System.out.println("Those items that are between today and the end of the year:");
        System.out.println(cal1.toString(today,newYearsEve));



        // Here is an example of using a try-catch block to test exception throwing.
        // You place your code that should generate an exception in the "try" block, and you
        // specify the exception you are catching in the "catch" expression. If an exception is
        // thrown, control will branch immediately to the "catch" block. If an exception is not
        // thrown, the "try" block will fully execute and then control goes to whatever follows
        // the try-catch block.
        // Experiment: change the number of iterations of the loop and observe the behavior.
        // Try 10 & 100.
        try {
            int attempts = 200;
            for (int i = 0; i < attempts; i++) { // attempt to insert lots of reminders 
                cal1.addRem(rem);
            }
            System.out.println("\nSuccessfully added " + attempts + " reminders to the calendar " +
                    "without an exception.");
        }
        catch (RuntimeException excpt) {
            System.out.println("\nTHIS MESSAGE SHOULD NOT BE SEEN as code should no " + 
                    "longer throw an exception.");
        }
  
    }
}