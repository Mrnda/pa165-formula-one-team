package cz.muni.fi.pa165.rest;

import cz.muni.fi.pa165.sample.data.FormulaOneTeamWithSampleDataConfiguration;
import cz.muni.fi.pa165.service.config.ServiceConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author mrnda (Michal Mrnuštík)
 */

@EnableWebMvc
@Configuration
@Import({ServiceConfiguration.class, FormulaOneTeamWithSampleDataConfiguration.class})
@ComponentScan(basePackages = {"cz.fi.muni.pa165.rest"})
public class RootWebContext implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new AllowOriginInterceptor());
    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }
}
