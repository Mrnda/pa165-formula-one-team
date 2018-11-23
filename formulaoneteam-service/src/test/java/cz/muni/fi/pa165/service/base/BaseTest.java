package cz.muni.fi.pa165.service.base;

import cz.muni.fi.pa165.service.config.ServiceConfiguration;
import org.junit.Rule;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeClass;

import java.util.Calendar;
import java.util.Date;

/**
 * @author elderanakain (Arcadii Rubailo)
 */
@ContextConfiguration(classes = ServiceConfiguration.class)
public abstract class BaseTest extends AbstractTestNGSpringContextTests {

    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    @BeforeClass
    public void setUpClass() {
        MockitoAnnotations.initMocks(this);
    }

    protected Date createDate(int day, int month, int year) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);
        return calendar.getTime();
    }
}
