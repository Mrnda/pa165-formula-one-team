package cz.muni.fi.pa165.sample.data;

import com.google.common.collect.Sets;
import cz.muni.fi.pa165.entity.*;
import cz.muni.fi.pa165.enums.ComponentType;
import cz.muni.fi.pa165.enums.DriverStatus;
import cz.muni.fi.pa165.service.*;
import cz.muni.fi.pa165.service.date.DateService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.Set;

import static cz.muni.fi.pa165.entity.RaceParticipation.NO_RESULT_POSITION;
import static cz.muni.fi.pa165.enums.CharacteristicsType.*;
import static cz.muni.fi.pa165.enums.ComponentType.*;
import static cz.muni.fi.pa165.enums.EngineerSpecialization.*;

/**
 * @author mrnda (Michal Mrnuštík)
 */

@Service
@Transactional
public class SampleDataLoadingFacadeImpl implements SampleDataLoadingFacade {

    @Inject
    private CarSetupService carSetupService;

    @Inject
    private CharacteristicsValueService characteristicsValueService;

    @Inject
    private ComponentService componentService;

    @Inject
    private ComponentParameterService componentParameterService;

    @Inject
    private DriverService driverService;

    @Inject
    private EngineerService engineerService;

    @Inject
    private ManagerService managerService;

    @Inject
    private RaceService raceService;

    @Inject
    private RaceParticipationService raceParticipationService;

    @Inject
    private TestDriveService testDriveService;

    @Inject
    private DateService dateService;

    @Override
    public void loadData() {
        managerService.register(new Manager("Ross", "Parker", "ross@parker.com"), "p4ssword");

        engineerService.register(new Engineer("Sun", "Li", "sun@engineers.com", AERODYNAMICS), "my-dirty-secret");
        engineerService.register(new Engineer("Tommy", "Lee", "Tommy@engineers.com", ENGINES), "thisissafepassword");
        engineerService.register(new Engineer("Orson", "Wells", "Orson@engineers.com", FLUID_MECHANICS), "bestEng1neer");
        engineerService.register(new Engineer("Karen", "Gillian", "Karen@engineers.com", EMISSIONS), "helloIamCaren");
        engineerService.register(new Engineer("Robert", "Ford", "Robert@engineers.com", THERMODYNAMICS), "escort");

        final Driver fernardoAlonso = driverService.register(
                new Driver(
                        "Fernando",
                        "Alonso",
                        "fernardo@alonso.cz",
                        "Spanish",
                        dateService.createDate(29, 6, 1981),
                        DriverStatus.MAIN,
                        characteristicsValueService.add(Sets.newHashSet(
                                new CharacteristicsValue(AGGRESIVITY, 100),
                                new CharacteristicsValue(STEERING, 50),
                                new CharacteristicsValue(DRIVING_ON_WET, 15),
                                new CharacteristicsValue(ENDURANCE, 4)
                        ))
                ),
                "fernardoIsNumber1"
        );

        final Driver michaelSchumacher = driverService.register(
                new Driver(
                        "Michael",
                        "Schumacher",
                        "micheal@schumacher.cz",
                        "German",
                        dateService.createDate(3, 1, 1969),
                        DriverStatus.MAIN,
                        characteristicsValueService.add(Sets.newHashSet(
                                new CharacteristicsValue(AGGRESIVITY, 40),
                                new CharacteristicsValue(STEERING, 100),
                                new CharacteristicsValue(DRIVING_ON_WET, 20),
                                new CharacteristicsValue(ENDURANCE, 60)
                        ))
                ),
                "ILoveGermany"
        );

        final Driver testDriver1 = driverService.register(
                new Driver(
                        "Miki",
                        "Raikonen",
                        "miki@raikonen.fi",
                        "Finnish",
                        dateService.createDate(3, 1, 1995),
                        DriverStatus.TEST,
                        characteristicsValueService.add(Sets.newHashSet(
                                new CharacteristicsValue(AGGRESIVITY, 10),
                                new CharacteristicsValue(STEERING, 15),
                                new CharacteristicsValue(DRIVING_ON_WET, 20),
                                new CharacteristicsValue(ENDURANCE, 30),
                                new CharacteristicsValue(PATIENCE, 8)
                        ))

                ),
                "aslkfhfrer342"
        );

        final Driver testDriver2 = driverService.register(
                new Driver(
                        "John",
                        "Doe",
                        "john@doe.com",
                        "American",
                        dateService.createDate(4, 11, 1998),
                        DriverStatus.TEST,
                        characteristicsValueService.add(Sets.newHashSet(
                                new CharacteristicsValue(AGGRESIVITY, 10),
                                new CharacteristicsValue(STEERING, 15),
                                new CharacteristicsValue(DRIVING_ON_WET, 20),
                                new CharacteristicsValue(ENDURANCE, 30),
                                new CharacteristicsValue(PATIENCE, 8)
                        ))
                ),
                "john-does-password"
        );

        final Component engine1 = createComponent(
                "Engine no.1",
                ENGINE,
                componentParameterService.add(Sets.newHashSet(
                        new ComponentParameter("Power", "114kW"),
                        new ComponentParameter("Torque", "1500Nm")
                ))
        );

        final Component engine2 = createComponent(
                "Engine no.2",
                ENGINE,
                componentParameterService.add(Sets.newHashSet(
                        new ComponentParameter("Torque", "1800Nm"),
                        new ComponentParameter("Power", "180kW")
                ))
        );

        final Component brake = createComponent(
                "Brakes",
                BRAKES,
                Sets.newHashSet(componentParameterService.add(new ComponentParameter("Max brake force", "25kN")))
        );

        final Component suspension = createComponent(
                "Suspension",
                SUSPENSION,
                Sets.newHashSet(componentParameterService.add(new ComponentParameter("Type", "Dependent")))
        );

        final Component transmission = createComponent(
                "Suspension",
                TRANSMISSION,
                componentParameterService.add(Sets.newHashSet(
                        new ComponentParameter("Type", "Automatic"),
                        new ComponentParameter("Number of gears", "8")
                ))
        );

        final Component tires1 = createComponent(
                "Suspension",
                TIRES,
                componentParameterService.add(Sets.newHashSet(
                        new ComponentParameter("Manufacturer", "Michellin"),
                        new ComponentParameter("Nominal section width", "205mm")
                ))
        );

        final Component tires2 = createComponent(
                "Suspension",
                TIRES,
                componentParameterService.add(Sets.newHashSet(
                        new ComponentParameter("Manufacturer", "Barum"),
                        new ComponentParameter("Nominal section width", "108mm")
                ))
        );

        final Component cover = createComponent(
                "Suspension",
                TIRES,
                Sets.newHashSet(componentParameterService.add(new ComponentParameter("Reference area", "2800 ft*ft")))
        );

        CarSetup carSetup1 = carSetupService.add(new CarSetup(engine1, suspension, brake, transmission, tires1, cover));
        CarSetup carSetup2 = carSetupService.add(new CarSetup(engine2, suspension, brake, transmission, tires1, cover));
        CarSetup carSetup3 = carSetupService.add(new CarSetup(engine1, suspension, brake, transmission, tires2, cover));

        Race pastRace = raceService.add(
                new Race(dateService.getPastDate(15), "WC Valencia", "Valencia")
        );
        Race futureRace = raceService.add(
                new Race(dateService.getFutureDate(30), "Germany World Championship", "Nuremberg")
        );

        raceParticipationService.add(Sets.newHashSet(
                new RaceParticipation(carSetup1, michaelSchumacher, pastRace, 1),
                new RaceParticipation(carSetup2, fernardoAlonso, pastRace, 5),
                new RaceParticipation(carSetup2, fernardoAlonso, futureRace, NO_RESULT_POSITION),
                new RaceParticipation(carSetup1, michaelSchumacher, futureRace, NO_RESULT_POSITION)
        ));

        testDriveService.add(Sets.newHashSet(
                new TestDrive(carSetup1, testDriver1, "Steering maybe too stiff", dateService.getPastDate(40)),
                new TestDrive(carSetup1, testDriver2, "Great power", dateService.getPastDate(40)),
                new TestDrive(carSetup2, testDriver1, "Pretty good steering, ", dateService.getPastDate(30)),
                new TestDrive(carSetup2, testDriver2, "Did not finish, transmission broke", dateService.getPastDate(30)),
                new TestDrive(carSetup3, testDriver1, "Fast, but missing some advantages of no. 2", dateService.getPastDate(20)),
                new TestDrive(carSetup3, testDriver2, "Pretty fast", dateService.getPastDate(20)),
                new TestDrive(carSetup3, testDriver2, "Slides a lot on wet", dateService.getPastDate(5))
        ));
    }

    private Component createComponent(String name, ComponentType type, Set<ComponentParameter> parameters) {
        final Component component = new Component(name, type);
        for (ComponentParameter parameter : parameters) {
            component.addParameter(parameter);
        }
        componentService.add(component);
        return component;
    }
}
