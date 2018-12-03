package cz.muni.fi.pa165.service.base;

import cz.muni.fi.pa165.dto.*;
import cz.muni.fi.pa165.dto.base.BaseDTO;
import cz.muni.fi.pa165.entity.RaceParticipation;
import cz.muni.fi.pa165.entity.base.BaseEntity;
import cz.muni.fi.pa165.enums.ComponentType;
import cz.muni.fi.pa165.enums.DriverStatus;
import cz.muni.fi.pa165.service.BeanMappingService;
import org.junit.Before;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.Date;

/**
 * @author elderanakain (Arcadii Rubailo)
 */
public abstract class BaseFacadeTest<E extends BaseEntity, DTO extends BaseDTO> extends BaseTest {

    @Mock
    protected BeanMappingService beanMappingServiceMock;

    protected E entity;
    protected DTO dto;

    @Before
    public void setUp() {
        entity = createTestEntity();
        dto = createTestDTO();
    }

    protected DriverDetailDTO createDriverDetailDTO() {
        DriverDetailDTO driverDetailDTO = new DriverDetailDTO();
        driverDetailDTO.setName("John");
        driverDetailDTO.setSurname("Doe");
        driverDetailDTO.setEmail("John@doe.com");
        driverDetailDTO.setNationality("American");
        driverDetailDTO.setBirthday(createDate(2, 9, 1989));
        driverDetailDTO.setCharacteristics(new ArrayList<>());
        driverDetailDTO.setDriverStatus(DriverStatus.MAIN);
        return driverDetailDTO;
    }

    protected ComponentDTO createComponentDTO() {
        return createComponentDTO(ComponentType.ENGINE);
    }

    private ComponentDTO createComponentDTO(ComponentType type) {
        ComponentDTO componentDTO = new ComponentDTO();
        componentDTO.setType(type);
        componentDTO.setName("test " + type.toString());
        return componentDTO;
    }

    protected CarSetupDTO createCarSetupDTO() {
        CarSetupDTO dto = new CarSetupDTO();
        dto.setEngine(createComponentDTO(ComponentType.ENGINE));
        dto.setSuspension(createComponentDTO(ComponentType.SUSPENSION));
        dto.setBrakes(createComponentDTO(ComponentType.BRAKES));
        dto.setCover(createComponentDTO(ComponentType.COVER));
        dto.setTires(createComponentDTO(ComponentType.TIRES));
        dto.setTransmission(createComponentDTO(ComponentType.TRANSMISSION));
        return dto;
    }

    protected RaceDTO createRaceDTO() {
        RaceDTO raceDTO = new RaceDTO();
        raceDTO.setDate(new Date());
        raceDTO.setLocation("location");
        raceDTO.setTitle("GP Monaco");
        return raceDTO;
    }

    protected TestDriveDTO createTestDriveDTO() {
        TestDriveDTO testDriveDTO = new TestDriveDTO();
        testDriveDTO.setCar(createCarSetupDTO());
        testDriveDTO.setDate(new Date());
        testDriveDTO.setDriver(createDriverDetailDTO());
        testDriveDTO.setNotes("notes");
        return testDriveDTO;
    }

    protected RaceParticipationDTO creaateRaceParticipationDTO() {
        RaceParticipationDTO raceParticipationDTO = new RaceParticipationDTO();
        raceParticipationDTO.setCar(createCarSetupDTO());
        raceParticipationDTO.setDriver(createDriverDetailDTO());
        raceParticipationDTO.setRace(createRaceDTO());
        raceParticipationDTO.setResultPosition(1);
        return raceParticipationDTO;
    }

    protected abstract E createTestEntity();

    protected abstract DTO createTestDTO();
}
