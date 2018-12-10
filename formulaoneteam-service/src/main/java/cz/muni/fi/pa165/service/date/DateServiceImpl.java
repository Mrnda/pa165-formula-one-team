package cz.muni.fi.pa165.service.date;

import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

@Service
public class DateServiceImpl implements DateService {

    @Override
    public Date getCurrentDate() {
        return createCalendar().getTime();
    }

    @Override
    public Date getFutureDate(int numberOfDays) {
        final Calendar calendar = createCalendar();
        calendar.add(Calendar.DAY_OF_MONTH, numberOfDays);
        return calendar.getTime();
    }

    @Override
    public Date getPastDate(int numberOfDays) {
        final Calendar calendar = createCalendar();
        calendar.add(Calendar.DAY_OF_MONTH, -numberOfDays);
        return calendar.getTime();
    }

    @Override
    public Date createDate(int day, int monthIndex, int year) {
        final Calendar calendar = createCalendar();
        calendar.set(Calendar.HOUR_OF_DAY,0);
        calendar.set(Calendar.MINUTE,0);
        calendar.set(Calendar.SECOND,0);
        calendar.set(Calendar.MILLISECOND,0);
        calendar.set(year, monthIndex, day);
        return calendar.getTime();
    }

    @Override
    public Calendar createCalendarForDate(Date date) {
        final Calendar calendar = createCalendar();
        calendar.setTime(date);
        return calendar;
    }

    private Calendar createCalendar() {
        return Calendar.getInstance(TimeZone.getTimeZone("UTC"));
    }
}
