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
    public Calendar createCalendarForDate(Date date) {
        final Calendar calendar = createCalendar();
        calendar.setTime(date);
        return calendar;
    }

    private Calendar createCalendar() {
        return Calendar.getInstance(TimeZone.getTimeZone("UTC"));
    }
}
