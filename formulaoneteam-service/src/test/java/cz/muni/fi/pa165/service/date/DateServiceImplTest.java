package cz.muni.fi.pa165.service.date;

import cz.muni.fi.pa165.service.base.BaseTest;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.InjectMocks;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

/**
 * @author elderanakain (Arcadii Rubailo)
 */
public class DateServiceImplTest extends BaseTest {

    @InjectMocks
    private DateServiceImpl service;

    @Test
    public void getFutureDate_returnsDateAfterCurrentDate() {
        //given
        Date currentDate = Calendar.getInstance(TimeZone.getTimeZone("UTC")).getTime();

        //when
        final Date date = service.getFutureDate(10);

        //then
        assertTrue(date.after(currentDate));
    }

    @Test
    public void getPastDate_returnsDateBeforeCurrentDate() {
        //given
        Date currentDate = Calendar.getInstance(TimeZone.getTimeZone("UTC")).getTime();

        //when
        final Date date = service.getPastDate(10);

        //then
        assertTrue(date.before(currentDate));
    }

    @Test
    public void createDate_returnsValidDate() {
        //when
        final Date date = service.createDate(2, 10, 1995);

        //then
        assertEquals(mockDate(), date);
    }

    @Ignore
    @Test
    public void whenGetCurrentTime_returnsCurrentTime() {
        //given
        Date date = Calendar.getInstance(TimeZone.getTimeZone("UTC")).getTime();

        //then
        assertTrue(datesAreEqual(date, service.getCurrentDate()));
    }

    @Test
    public void whenCreateCalendarForDate_returnsValidDate() {
        //given
        Date date = Calendar.getInstance(TimeZone.getTimeZone("UTC")).getTime();
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        calendar.setTime(date);

        //then
        assertEquals(calendar, service.createCalendarForDate(date));
    }

    private boolean datesAreEqual(Date left, Date right) {
        return left.getDay() == right.getDay()
                && left.getMonth() == right.getMonth()
                && left.getYear() == right.getYear();
    }

    private Date mockDate() {
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        calendar.set(Calendar.HOUR_OF_DAY,0);
        calendar.set(Calendar.MINUTE,0);
        calendar.set(Calendar.SECOND,0);
        calendar.set(Calendar.MILLISECOND,0);
        calendar.set(1995, Calendar.NOVEMBER, 2);
        return calendar.getTime();
    }
}
