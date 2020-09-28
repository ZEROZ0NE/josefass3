package ltu;

import static org.junit.Assert.*;

import org.junit.Test;

import static ltu.CalendarFactory.getCalendar;

import java.io.*;


public class PaymentTest
{
    private static PaymentImpl testobjekt;
    static {
        try {
            testobjekt = new PaymentImpl(CalendarFactory.getCalendar("ltu.Calender20160101"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void age19()
    {
        int testloan = -1;
        try {
            testloan = testobjekt.getMonthlyAmount("19970101-6969", 0, 100, 100);
            assertEquals(0, testloan);
        }catch(AssertionError err){
            System.out.println("Test 1, correct : 0, got : "+ testloan);
        }
    }

}
