import cz.muni.fi.pa165.dao.Race.RaceDao;
import cz.muni.fi.pa165.dao.RaceParticipation.RaceParticipationDao;
import cz.muni.fi.pa165.dao.TestDrive.TestDriveDao;
import cz.muni.fi.pa165.dao.carsetup.CarSetupDao;
import cz.muni.fi.pa165.dao.characteristics.CharacteristicsValueDao;
import cz.muni.fi.pa165.dao.component.ComponentDao;
import cz.muni.fi.pa165.dao.componentparameter.ComponentParameterDao;
import cz.muni.fi.pa165.dao.driver.DriverDao;
import cz.muni.fi.pa165.dao.engineer.EngineerDao;
import cz.muni.fi.pa165.dao.manager.ManagerDao;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;

import static org.assertj.core.api.Assertions.*;

@ContextConfiguration(classes = {FormulaOneTeamWithSampleDataConfiguration.class})
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class SampleDataLoadingTests extends AbstractTestNGSpringContextTests {

    @Inject
    CarSetupDao carSetupDao;

    @Inject
    CharacteristicsValueDao characteristicsValueDao;

    @Inject
    ComponentDao componentDao;

    @Inject
    ComponentParameterDao componentParameterDao;

    @Inject
    DriverDao driverDao;

    @Inject
    EngineerDao engineerDao;

    @Inject
    ManagerDao managerDao;

    @Inject
    RaceDao raceDao;

    @Inject
    RaceParticipationDao raceParticipationDao;

    @Inject
    TestDriveDao testDriveDao;

    @Test
    public void loadData_allEntities_hasData() {
        assertThat(carSetupDao.findAll()).isNotEmpty();
        assertThat(characteristicsValueDao.findAll()).isNotEmpty();
        assertThat(componentDao.findAll()).isNotEmpty();
        assertThat(componentParameterDao.findAll()).isNotEmpty();
        assertThat(driverDao.findAll()).isNotEmpty();
        assertThat(engineerDao.findAll()).isNotEmpty();
        assertThat(managerDao.findAll()).isNotEmpty();
        assertThat(raceDao.findAll()).isNotEmpty();
        assertThat(raceParticipationDao.findAll()).isNotEmpty();
        assertThat(testDriveDao.findAll()).isNotEmpty();
    }
}
