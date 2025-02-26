// File name: CalTest.java
// Author:  Tian Ran
// VUnetid: tianr
// Email: ran.tian@vanderbilt.edu
// Class: CS2201
// Date: Feb 8 2025
// Honor statement:I have neither given nor received unauthorized aid concerning this homework.
// Assignment Number: project 2
// Description: test on calendar class

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CalTest {

    @Test
    public void testDefaultConstructor1() {
        Calendar cal1 = new Calendar();
        assertTrue(cal1.isEmpty());
    }

    @Test
    public void testDefaultConstructor2() {
        Calendar cal1 = new Calendar();
        assertEquals(0, cal1.getNumRem());
    }

    @Test
    public void testDefaultConstructor3() {
        Calendar cal1 = new Calendar();
        assertEquals("", cal1.toString());
    }

    @Test
    public void testToString_Format() {
        Calendar cal = new Calendar();
        Reminder rem1 = new Reminder(new Date("1/1/2024"), "Happy New Year");
        Reminder rem2 = new Reminder(new Date("1/8/2024"), "First day of classes");
        cal.addRem(rem1);
        cal.addRem(rem2);
        assertEquals("On 01/01/2024: Happy New Year\nOn 01/08/2024: First day of classes\n", cal.toString());
    }

    @Test
    public void testFindRem(){
        Calendar cal = new Calendar();
        cal.addRem(new Reminder(new Date("1/1/2025"), "First day of classes"));
        cal.addRem(new Reminder(new Date("1/2/2025"), "Second day of classes"));
        cal.addRem(new Reminder(new Date("1/3/2025"), "Third day of classes"));
        cal.addRem(new Reminder(new Date("1/3/2025"), "1Third day of classes"));

        assertEquals(1, cal.findRem(new Date("1/2/2025")));//Date parameter
        assertEquals(-1, cal.findRem(new Date("1/10/2025")));
        assertEquals(3, cal.findRem("Third day of classes"));//String parameter
        assertEquals(2, cal.findRem("1Third day of classes"));
        assertEquals(-1, cal.findRem("80 day of classes"));
        assertEquals(new Reminder(new Date("1/3/2025"), "1Third day of classes"),cal.getRem(2));
        assertThrows(IllegalArgumentException.class, ()-> cal.getRem(-3));
        assertThrows(IllegalArgumentException.class, ()-> cal.getRem(10));
        assertEquals(2, cal.count(new Date("1/3/2025")));
    }

    @Test
    public void testToString(){
        Calendar cal = new Calendar();
        assertEquals("", cal.toString(0));
        assertEquals("", cal.toString(""));
        assertEquals("", cal.toString(new Date("1/1/2025")));

        cal.addRem(new Reminder(new Date("1/1/2025"), "First day of classes"));
        cal.addRem(new Reminder(new Date("1/2/2025"), "Second day of classes"));
        cal.addRem(new Reminder(new Date("1/3/2025"), "Third day of classes"));
        cal.addRem(new Reminder(new Date("1/4/2025"), "Fourth day of classes"));
        assertEquals("On 01/01/2025: First day of classes", cal.toString(0));
        assertEquals("", cal.toString(-1));
        assertEquals("", cal.toString(10));
        assertEquals("On 01/01/2025: First day of classes\n", cal.toString("First day of classes"));
        assertEquals("On 01/01/2025: First day of classes\n", cal.toString(new Date("1/1/2025")));

        String between = "On 01/01/2025: First day of classes\n"
                + "On 01/02/2025: Second day of classes\n"
                + "On 01/03/2025: Third day of classes\n";
        assertEquals(between, cal.toString(new Date("1/1/2025"),new Date("1/3/2025")));
        assertEquals(between, cal.toString(new Date("1/3/2025"),new Date("1/1/2025")));
    }

    @Test
    public void testEquals(){
        Calendar cal1 = new Calendar();
        assertTrue(cal1.equals(cal1));
        assertFalse(cal1.equals(1));
        Calendar cal2 = new Calendar();
        cal1.addRem(new Reminder(new Date("1/1/2025"), "First day of classes"));
        cal1.addRem(new Reminder(new Date("1/2/2025"), "Second day of classes"));
        cal1.addRem(new Reminder(new Date("1/3/2025"), "Third day of classes"));
        cal1.addRem(new Reminder(new Date("1/4/2025"), "Fourth day of classes"));

        cal2.addRem(new Reminder(new Date("1/1/2025"), "First day of classes"));
        cal2.addRem(new Reminder(new Date("1/2/2025"), "Second day of classes"));
        cal2.addRem(new Reminder(new Date("1/3/2025"), "Third day of classes"));

        assertFalse(cal1.equals(cal2));
        cal2.addRem(new Reminder(new Date("1/4/2025"), "forth day of classes"));
        assertFalse(cal1.equals(cal2));

        Calendar cal3 = new Calendar();
        cal3.addRem(new Reminder(new Date("1/1/2025"), "First day of classes"));
        cal3.addRem(new Reminder(new Date("1/2/2025"), "Second day of classes"));
        cal3.addRem(new Reminder(new Date("1/3/2025"), "Third day of classes"));
        cal3.addRem(new Reminder(new Date("1/4/2025"), "Fourth day of classes"));
        assertTrue(cal1.equals(cal3));
    }

    @Test
    public void testAddandDelete(){
        Calendar cal = new Calendar();
        assertEquals(0, cal.deleteRem(0));
        assertEquals(0, cal.deleteRem(-1));
        assertEquals(0, cal.deleteRem(10));
        cal.addRem(new Reminder(new Date("1/1/2025"), "First day of classes"));
        cal.addRem(new Reminder(new Date("1/2/2025"), "Second day of classes"));
        cal.addRem(new Reminder(new Date("1/3/2025"), "Third day of classes"));
        cal.addRem(new Reminder(new Date("1/4/2025"), "Fourth day of classes"));
        assertEquals(0, cal.deleteRem(-1));
        assertEquals(0, cal.deleteRem(6));
        assertEquals(1, cal.deleteRem(1));
        assertEquals(3, cal.deleteRem());
        cal.addRem(new Reminder(new Date("2/5/2025"), "1000 day of classes"));

    }

    @Test
    public void TestDelete(){
        Calendar cal = new Calendar();
        Reminder rem1=new Reminder(new Date("1/1/2025"), "First day of classes");
        Date d1=new Date(1,1,2025);
        Date d2=new Date(1,1,2025);
        assertEquals(0, cal.deleteRem(rem1));
        assertEquals(0, cal.deleteRem("First day of classes"));
        assertEquals(0, cal.deleteRem(d1));
        assertEquals(0, cal.deleteRem(d1,d2));
        Reminder rem2=new Reminder(new Date("1/2/2025"), "Second day of classes");
        Reminder rem3=new Reminder(new Date("1/3/2025"), "third day of classes");
        Reminder rem4=new Reminder(new Date("1/4/2025"), "fourth day of classes");
        Reminder rem5=new Reminder(new Date("1/5/2025"), "fifth day of classes");
        Reminder rem6=new Reminder(new Date("1/6/2025"), "sixth day of classes");
        cal.addRem(rem1);
        cal.addRem(rem2);
        cal.addRem(rem3);
        cal.addRem(rem4);
        cal.addRem(rem4);
        cal.addRem(rem5);
        cal.addRem(rem5);
        cal.addRem(rem6);
        cal.addRem(rem6);
        Calendar cal2=cal.clone();
        assertEquals(9, cal2.getNumRem());
        assertEquals(3, cal.deleteRem(new Date("1/3/2025"),new Date("1/1/2025")));
        assertEquals(2, cal.deleteRem(new Date("1/4/2025")));
        assertEquals(2, cal.deleteRem(rem5));
        assertEquals(2, cal.deleteRem("sixth day of classes"));//delete all reminders
        cal.addRem(rem1);
        cal.addRem(rem2);
        cal.addRem(rem3);
        cal.addRem(rem4);
        cal.addRem(rem4);
        cal.addRem(rem5);
        cal.addRem(rem5);
        cal.addRem(rem6);
        cal.addRem(rem6);
        cal.mergeCal(cal2);
        assertEquals(18, cal.getNumRem());
        assertEquals(50, cal.getMaxSize());
    }

    @Test
    public void TestGrow(){
        Calendar cal = new Calendar();
        Reminder rem1=new Reminder(new Date("1/1/2025"), "First day of classes");
        cal.addRem(rem1);
        cal.grow(100);
        assertEquals(1, cal.getNumRem());
        assertEquals(100, cal.getMaxSize());
    }





}
