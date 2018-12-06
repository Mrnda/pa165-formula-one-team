package cz.muni.fi.pa165.sample.data;

import cz.muni.fi.pa165.entity.base.BaseEntity;
import cz.muni.fi.pa165.entity.base.User;
import cz.muni.fi.pa165.service.*;
import cz.muni.fi.pa165.service.date.DateService;
import cz.muni.fi.pa165.service.facade.base.BaseEntityService;
import cz.muni.fi.pa165.service.facade.base.BaseUserService;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import static org.mockito.Matchers.*;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;

/**
 * @author mrnda (Michal Mrnuštík)
 */

public class SampleDataLoadingImplTests {

    @Mock
    private CarSetupService carSetupService;

    @Mock
    private CharacteristicsValueService characteristicsValueService;

    @Mock
    private ComponentService componentService;

    @Mock
    private ComponentParameterService componentParameterService;

    @Mock
    private DriverService driverService;

    @Mock
    private EngineerService engineerService;

    @Mock
    private ManagerService managerService;

    @Mock
    private RaceService raceService;

    @Mock
    private RaceParticipationService raceParticipationService;

    @Mock
    private TestDriveService testDriveService;

    @Mock
    private DateService dateService;

    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    @InjectMocks
    private SampleDataLoadingFacadeImpl facade;

    @Test
    public void loadData_allEntities_hasData() {
        //Given

        //When
        facade.loadData();

        //Then
        verifyRegisterCalled(driverService);
        verifyRegisterCalled(engineerService);
        verifyRegisterCalled(managerService);
        verifyAddCalled(carSetupService);
        verifyAddCalled(characteristicsValueService);
        verifyAddCalled(componentService);
        verifyAddCalled(componentParameterService);
        verifyAddCalled(raceService);
        verifyAddCalled(raceParticipationService);
        verifyAddCalled(testDriveService);
    }

    private static void verifyAddCalled(BaseEntityService service) {
        verify(service, atLeastOnce()).add(notNull(BaseEntity.class));
    }

    private static void verifyRegisterCalled(BaseUserService service) {
        verify(service, atLeastOnce()).register(any(User.class), anyString());
    }
}
