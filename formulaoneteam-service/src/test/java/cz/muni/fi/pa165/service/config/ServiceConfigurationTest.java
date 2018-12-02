package cz.muni.fi.pa165.service.config;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author elderanakain (Arcadii Rubailo)
 */
public class ServiceConfigurationTest {

    @Test
    public void verifyDozerIsCreated() {
        //given
        ServiceConfiguration serviceConfiguration = new ServiceConfiguration();

        //then
        assertNotNull(serviceConfiguration.dozer());
    }
}