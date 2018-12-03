package cz.muni.fi.pa165.service.date;

import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;

@Service
public interface DateService {
    Date getCurrentDate();
    Calendar createCalendarForDate(Date date);
}
