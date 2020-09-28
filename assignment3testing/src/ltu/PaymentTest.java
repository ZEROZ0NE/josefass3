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
    private static int studenloan = 7088;
    private static int subsidiary =2816;
    private static int studenloanhalf = 3564;
    private static int subsidiaryhalf = 1396;

    //AGE REQUIRMENTS
    @Test
    public void age19()
    {
        int testloan = -1;
        try {
            testloan = testobjekt.getMonthlyAmount("19970101-6969", 0, 100, 100);
            assertEquals(0, testloan);
        }catch(AssertionError err){
            System.out.println(testloan);
        }
    }
    @Test
    public void age20()
    {
        int testloan = -1;
        try {
            testloan = testobjekt.getMonthlyAmount("19960101-6969", 0, 100, 100);
            assertEquals(studenloan + subsidiary, testloan);
        }catch(AssertionError err){
            System.out.println(testloan);
        }
    }
    @Test
    public void age46()
    {
        int testloan = -1;
        try {
            testloan = testobjekt.getMonthlyAmount("19700101-6969", 0, 100, 100);
            assertEquals(studenloan + subsidiary, testloan);
        }catch(AssertionError err){
            System.out.println(testloan);
        }
    }
    @Test
    public void age47()
    {
        int testloan = -1;
        try {
            testloan = testobjekt.getMonthlyAmount("19690101-6969", 0, 100, 100);
            assertEquals(subsidiary, testloan);
        }catch(AssertionError err){
            System.out.println(testloan);
        }
    }

    @Test
    public void age55()
    {
        int testloan = -1;
        try {
            testloan = testobjekt.getMonthlyAmount("19610101-6969", 0, 100, 100);
            assertEquals(subsidiary, testloan);
        }catch(AssertionError err){
            System.out.println(testloan);
        }
    }
    @Test
    public void age56()
    {
        int testloan = -1;
        try {
            testloan = testobjekt.getMonthlyAmount("19600101-6969", 0, 100, 100);
            assertEquals(0, testloan);
        }catch(AssertionError err){
            System.out.println(testloan);
        }
    }
    // STUDYPACE REQUIRMENTS
    @Test
    public void rate100()
    {
        int testloan = -1;
        try {
            testloan = testobjekt.getMonthlyAmount("19930101-6969", 0, 100, 100);
            assertEquals(studenloan + subsidiary, testloan);
        }catch(AssertionError err){
            System.out.println(testloan);
        }
    }
    @Test
    public void rateunder100()
    {
        int testloan = -1;
        try {
            testloan = testobjekt.getMonthlyAmount("19930101-6969", 0, 99, 100);
            assertEquals(studenloanhalf + subsidiaryhalf, testloan);
        }catch(AssertionError err){
            System.out.println(testloan);
        }
    }


}
