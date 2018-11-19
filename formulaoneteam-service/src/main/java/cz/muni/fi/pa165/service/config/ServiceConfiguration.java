package cz.muni.fi.pa165.service.config;

import cz.muni.fi.pa165.AppContextConfig;
import cz.muni.fi.pa165.service.ManagerServiceImpl;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author elderanakain (Arcadii Rubailo)
 */
@Configuration
@Import(AppContextConfig.class)
@ComponentScan(basePackageClasses=ManagerServiceImpl.class)
public class ServiceConfiguration {

	@Bean
	public Mapper dozer(){
		return new DozerBeanMapper();
	}
}

