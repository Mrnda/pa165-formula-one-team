package cz.muni.fi.pa165.service.config;

import cz.muni.fi.pa165.AppContextConfig;
import cz.muni.fi.pa165.dto.UserDTO;
import cz.muni.fi.pa165.dto.base.BaseDTO;
import cz.muni.fi.pa165.entity.base.BaseEntity;
import cz.muni.fi.pa165.entity.base.User;
import cz.muni.fi.pa165.service.ManagerServiceImpl;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.dozer.loader.api.BeanMappingBuilder;
import org.dozer.loader.api.TypeMappingOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author elderanakain (Arcadii Rubailo)
 */
@Configuration
@Import(AppContextConfig.class)
@ComponentScan(basePackageClasses = ManagerServiceImpl.class)
public class ServiceConfiguration {

    @Bean
    public Mapper dozer() {
        DozerBeanMapper dozerBeanMapper = new DozerBeanMapper();
        dozerBeanMapper.addMapping(
                new BeanMappingBuilder() {
                    @Override
                    protected void configure() {
                        mapping(BaseDTO.class, BaseEntity.class, TypeMappingOptions.oneWay()).exclude("id");
                        mapping(User.class, UserDTO.class, TypeMappingOptions.oneWay()).exclude("password");
                    }
                }
        );

        return dozerBeanMapper;
    }
}
