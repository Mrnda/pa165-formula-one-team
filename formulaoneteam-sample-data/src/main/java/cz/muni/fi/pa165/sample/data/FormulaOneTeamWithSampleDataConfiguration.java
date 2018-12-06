package cz.muni.fi.pa165.sample.data;

import cz.muni.fi.pa165.service.config.ServiceConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

/**
 * @author mrnda (Michal Mrnuštík)
 */

@Configuration
@Import(ServiceConfiguration.class)
@ComponentScan(basePackageClasses = SampleDataLoadingFacadeImpl.class)
public class FormulaOneTeamWithSampleDataConfiguration {

    @Inject
    private SampleDataLoadingFacade sampleDataLoadingFacade;

    @PostConstruct
    public void dataLoading() {
        sampleDataLoadingFacade.loadData();
    }
}
