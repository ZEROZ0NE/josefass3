package ltu;

import java.util.Calendar;
import java.util.Date;

public class Calender20160101 implements ICalendar{

        @Override
        public Date getDate() {
            Calendar cal = Calendar.getInstance();
            cal.set(2016, 1, 1);
            return cal.getTime();

    }
}
