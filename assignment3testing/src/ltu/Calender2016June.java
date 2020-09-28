package ltu;

import java.util.Calendar;
import java.util.Date;

public class Calender2016June implements ICalendar
{
    @Override
    public Date getDate() {
        Calendar cal = Calendar.getInstance();
        cal.set(2016, 5, 1);
        return cal.getTime();
    }
}
