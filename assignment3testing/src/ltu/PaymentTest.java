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
            testobjekt = new PaymentImpl(CalendarFactory.getCalendar("ltu.Calender2016"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static int studenloan = 7088;
    private static int subsidiary = 2816;
    private static int studenloanhalf = 3564;
    private static int subsidiaryhalf = 1396;

    // AGE REQUIREMENTS
    @Test
    public void age19() //under 20 should not receive csn #[101]
    {
        int testloan = -1;
        testloan = testobjekt.getMonthlyAmount("19970101-6969", 0, 100, 100);
        assertEquals(0, testloan);
    }

    @Test
    public void age20() //between 20 and 46 should receive full csn #[101]
    {
        int testloan = -1;
        testloan = testobjekt.getMonthlyAmount("19960101-6969", 0, 100, 100);
        assertEquals(studenloan + subsidiary, testloan);
        
    }

    @Test
    public void age46()//between 20 and 46 should receive full csn #[103]
    {
        int testloan = -1;
        testloan = testobjekt.getMonthlyAmount("19700101-6969", 0, 100, 100);
        assertEquals(studenloan + subsidiary, testloan);
        
    }

    @Test
    public void age47() //should not receive loan from the year they turn 47 #[103]
    {
        int testloan = -1;
        testloan = testobjekt.getMonthlyAmount("19690201-6969", 0, 100, 100);
        assertEquals(subsidiary, testloan);
        
    }

    @Test
    public void age56() //between 47 and 57 should receive subsidiary #[102]
    {
        int testloan = -1;
        testloan = testobjekt.getMonthlyAmount("19600101-6969", 0, 100, 100);
        assertEquals(subsidiary, testloan);
    }

    @Test
    public void age57() //from age 57 should not receive any csn #[102]
    {
        int testloan = -1;
        testloan = testobjekt.getMonthlyAmount("19590101-6969", 0, 100, 100);
        assertEquals(0, testloan);
    }

    // STUDYPACE REQUIREMENTS 

    @Test
    public void pace100() //studypace at 100% should get full csn #[203]
    {
        int testloan = -1;
        testloan = testobjekt.getMonthlyAmount("19930101-6969", 0, 100, 100);
        assertEquals(studenloan + subsidiary, testloan);
    }

    @Test
    public void paceunder100() //studypace <100% should get 50% subsidiary #[202] #[503] #[504] #[505]
    {
        int testloan = -1;
        testloan = testobjekt.getMonthlyAmount("19930101-6969", 0, 99, 100);
        assertEquals(studenloanhalf + subsidiaryhalf, testloan);
    }

    @Test
    public void paceover50() //studypace 50=<100% should get 50% subsidiary #[202] #[503] #[504]
    {
        int testloan = -1;
        testloan = testobjekt.getMonthlyAmount("19930101-6969", 0, 50, 100);
        assertEquals(studenloanhalf + subsidiaryhalf, testloan);
    }

    @Test
    public void paceunder50() //studypace <50% should not get any subsidiary (or student loan? according to the correct code) #[201]
    {
        int testloan = -1;
        testloan = testobjekt.getMonthlyAmount("19930101-6969", 0, 49, 100);
        assertEquals(0, testloan);
    }

    



    // INCOME REQUIREMENTS

    @Test
    public void pace100MaxIncome() { // st #301
        int testloan = -1;
        testloan = testobjekt.getMonthlyAmount("19930101-6969", 85813, 100, 100);
        assertTrue(0<testloan);
    }

    @Test
    public void pace101MaxIncome() { // #301
        int testloan = -1;
        testloan = testobjekt.getMonthlyAmount("19930101-6969", 85813, 101, 100);
        assertTrue(0<testloan);
    }

    @Test
    public void pace100overMaxIncome() { // #301
        int testloan = -1;
        testloan = testobjekt.getMonthlyAmount("19930101-6969", 85814, 100, 100);
        assertEquals(0, testloan);
    }

    @Test
    public void pace100underMaxIncome() { // #301
        int testloan = -1;
        testloan = testobjekt.getMonthlyAmount("19930101-6969", 85812, 100, 100);
        assertTrue(0<testloan);
    }


    @Test
    public void notfullpaceMaxIncome() { // #302
        int testloan = -1;
        testloan = testobjekt.getMonthlyAmount("19930101-6969", 128722, 99, 100);
        assertTrue(0<testloan);
    }

    @Test
    public void notfullpaceOverMaxIncome() { // #302
        int testloan = -1;
        testloan = testobjekt.getMonthlyAmount("19930101-6969", 128723, 99, 100);
        assertEquals(0, testloan);
    }

    @Test
    public void notfullpaceUnderMaxIncome() { // #302
        int testloan = -1;
        testloan = testobjekt.getMonthlyAmount("19930101-6969", 128721, 99, 100);
        assertTrue(0<testloan);
    }



    // Completion ratio requirement

    @Test
    public void completion50() { // #401
        int testloan = -1;
        testloan = testobjekt.getMonthlyAmount("19930101-6969", 0, 100, 50);
        assertTrue(0<testloan);
    }

    @Test
    public void completionOver50() { // #401
        int testloan = -1;
        testloan = testobjekt.getMonthlyAmount("19930101-6969", 0, 100, 51);
        assertTrue(0<testloan);
    }

    @Test
    public void completionUnder50() { // #401
        int testloan = -1;
        testloan = testobjekt.getMonthlyAmount("19930101-6969", 0, 100, 49);
        assertEquals(0, testloan);
    }




    // When paid requirements

    @Test
    public void payOnWeekdayJanuary() { // #401
        String payday = "";
        payday = testobjekt.getNextPaymentDay();
        assertEquals("20160129", payday);
    }

    @Test
    public void payOnWeekdayJune() { // #401
        String payday = "";
        PaymentImpl june = null;
        try {
            june = new PaymentImpl(getCalendar("ltu.Calender2016June"));
        } catch (IOException err) {

        }
        payday = june.getNextPaymentDay();
        assertEquals("20160630", payday);
    }


    


}
