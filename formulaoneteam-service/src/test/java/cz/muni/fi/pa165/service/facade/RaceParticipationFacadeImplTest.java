package cz.muni.fi.pa165.service.facade;

import cz.muni.fi.pa165.dto.RaceParticipationDTO;
import cz.muni.fi.pa165.dto.WorldChampionshipSetupDTO;
import cz.muni.fi.pa165.entity.CarSetup;
import cz.muni.fi.pa165.entity.Driver;
import cz.muni.fi.pa165.entity.RaceParticipation;
import cz.muni.fi.pa165.service.RaceParticipationService;
import cz.muni.fi.pa165.service.base.BaseFacadeTest;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.util.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.testng.AssertJUnit.assertEquals;

/**
 * @author Adel Chakouri
 */
public class RaceParticipationFacadeImplTest extends BaseFacadeTest<RaceParticipation, RaceParticipationDTO> {

    @Mock
    private RaceParticipationService raceParticipationService;

    @InjectMocks
    private RaceParticipationFacadeImpl raceParticipationFacade;

    @Override
    public void setUp() {
        super.setUp();

        when(beanMappingServiceMock.mapTo(dto, RaceParticipation.class)).thenReturn(entity);
    }

    @Test
    public void getAllRaceParticipationTest() {
        //Given
        List<RaceParticipation> listRaceParticipation = new ArrayList<>();
        listRaceParticipation.add(entity);
        when(raceParticipationService.getAll()).thenReturn(listRaceParticipation);
        List<RaceParticipationDTO> listDTORaceParticipation = new ArrayList<>();
        listDTORaceParticipation.add(dto);
        when(beanMappingServiceMock.mapTo(listRaceParticipation, RaceParticipationDTO.class)).thenReturn(listDTORaceParticipation);

        //When
        List<RaceParticipationDTO> resListRaceParticipationDTO = new ArrayList<>(raceParticipationFacade.getAll());

        //Then
        verify(raceParticipationService).getAll();
        assertEquals(resListRaceParticipationDTO.size(), 1);
        Assert.assertTrue(resListRaceParticipationDTO.contains(dto));
    }

    @Test
    public void findRaceParticipationById() {
        //Given
        when(beanMappingServiceMock.mapTo(entity, RaceParticipationDTO.class)).thenReturn(dto);
        when(raceParticipationService.findById(1)).thenReturn(entity);

        //When
        RaceParticipationDTO resRaceParticipationDTO = raceParticipationFacade.findById(entity.getId());

        //Then
        assertEquals(resRaceParticipationDTO, dto);
    }

    @Test
    public void deleteRaceParticipationTest() {
        //When
        raceParticipationFacade.remove(dto.getId());

        //Then
        verify(raceParticipationService, times(1)).remove(entity.getId());
    }

    @Test
    public void updateRaceParticipationTest() {
        //When
        raceParticipationFacade.update(dto, 1);

        //Then
        verify(raceParticipationService, times(1)).update(entity);
    }

    @Test
    public void addRaceParticipationTest() {
        //When
        when(raceParticipationService.add(entity)).thenReturn(entity);

        raceParticipationFacade.add(dto);

        //Then
        verify(raceParticipationService, times(1)).add(entity);
    }

    @Test(expected = IllegalArgumentException.class)
    public void participateInWorldChampionship_withNullValue_throwsException() {
        //When
        raceParticipationFacade.participateInWorldChampionship(null);
    }

    @Test
    public void participateInWorldChampionship_withValidData_createsTheChampionship() {
        //Given
        final WorldChampionshipSetupDTO worldChampionshipDTO = createWorldChampionshipDTO();
        final Driver driverEntity = createDriver();
        final CarSetup carSetupEntity = createCarSetup();
        when(beanMappingServiceMock.mapTo(worldChampionshipDTO.getFirstDriver(), Driver.class)).thenReturn(driverEntity);
        when(beanMappingServiceMock.mapTo(worldChampionshipDTO.getSecondDriver(), Driver.class)).thenReturn(driverEntity);
        when(beanMappingServiceMock.mapTo(worldChampionshipDTO.getFirstCarSetup(), CarSetup.class)).thenReturn(carSetupEntity);
        when(beanMappingServiceMock.mapTo(worldChampionshipDTO.getSecondCarSetup(), CarSetup.class)).thenReturn(carSetupEntity);

        //When
        final List<RaceParticipationDTO> raceParticipationDTOS = raceParticipationFacade.participateInWorldChampionship(worldChampionshipDTO);

        //Then
        verify(raceParticipationService, times(1))
                .participateInWorldChampionship(worldChampionshipDTO.getDate(),
                        worldChampionshipDTO.getLocation(),
                        Arrays.asList(Pair.of(carSetupEntity, driverEntity), Pair.of(carSetupEntity, driverEntity)));
    }

    private WorldChampionshipSetupDTO createWorldChampionshipDTO() {
        final WorldChampionshipSetupDTO worldChampionshipSetupDTO = new WorldChampionshipSetupDTO();
        worldChampionshipSetupDTO.setDate(createDate(2, 11, 2019));
        worldChampionshipSetupDTO.setLocation("Barcelona");
        worldChampionshipSetupDTO.setFirstCarSetup(createCarSetupDTO());
        worldChampionshipSetupDTO.setSecondCarSetup(createCarSetupDTO());
        worldChampionshipSetupDTO.setFirstDriver(createDriverDTO());
        worldChampionshipSetupDTO.setSecondDriver(createDriverDTO());
        return worldChampionshipSetupDTO;
    }

    @Override
    protected RaceParticipation createTestEntity() {
        return createRaceParticipation();
    }

    @Override
    protected RaceParticipationDTO createTestDTO() {
        return createRaceParticipationDTO();
    }
}
