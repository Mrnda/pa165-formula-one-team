package cz.muni.fi.pa165.mvc.config;

import org.junit.Test;
import org.springframework.web.filter.CharacterEncodingFilter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * @author elderanakain (Arcadii Rubailo)
 */
public class MyStartInitializerTest {

    private MyStartInitializer myStartInitializer = new MyStartInitializer();

    @Test
    public void getRootConfigClasses() {
        //then
        assertEquals(myStartInitializer.getRootConfigClasses()[0], MySpringMvcConfig.class);
    }

    @Test
    public void getServletMappings() {
        //then
        assertEquals(myStartInitializer.getServletMappings()[0], "/");
    }

    @Test
    public void getServletFilters() {
        //then
        assertEquals(((CharacterEncodingFilter) myStartInitializer.getServletFilters()[0]).getEncoding(), "utf-8");
    }

    @Test
    public void getServletConfigClasses() {
        //then
        assertNull(myStartInitializer.getServletConfigClasses());
    }
}
