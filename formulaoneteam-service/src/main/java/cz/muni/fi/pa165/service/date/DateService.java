package cz.muni.fi.pa165.service.date;

import java.util.Calendar;
import java.util.Date;

public interface DateService {

    Date getCurrentDate();

    Date getFutureDate(int numberOfDays);

    Date getPastDate(int numberOfDays);

    Date createDate(int day, int monthIndex, int year);
    Calendar createCalendarForDate(Date date);
}
