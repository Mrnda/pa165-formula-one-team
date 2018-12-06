package cz.muni.fi.pa165.sample.data;

import cz.muni.fi.pa165.entity.*;
import cz.muni.fi.pa165.enums.CharacteristicsType;
import cz.muni.fi.pa165.enums.ComponentType;
import cz.muni.fi.pa165.enums.DriverStatus;
import cz.muni.fi.pa165.enums.EngineerSpecialization;
import cz.muni.fi.pa165.service.*;
import cz.muni.fi.pa165.service.date.DateService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
        createManager("Ross", "Parker", "ross@parker.com", "p4ssword");

        createEngineer("Sun", "Li", "sun@engineers.com", "my-dirty-secret", EngineerSpecialization.AERODYNAMICS);
        createEngineer("Tommy", "Lee", "Tommy@engineers.com", "thisissafepassword", EngineerSpecialization.ENGINES);
        createEngineer("Orson", "Wells", "Orson@engineers.com", "bestEng1neer", EngineerSpecialization.FLUID_MECHANICS);
        createEngineer("Karen", "Gillian", "Karen@engineers.com", "helloIamCaren", EngineerSpecialization.EMISSIONS);
        createEngineer("Robert", "Ford", "Robert@engineers.com", "escort", EngineerSpecialization.THERMODYNAMICS);

        final Driver fernardoAlonso = createDriver("Fernando",
                "Alonso",
                "fernardo@alonso.cz",
                "fernardoIsNumber1",
                "Spanish",
                dateService.createDate(29, 6, 1981),
                DriverStatus.MAIN
        );
        createCharacteristicsValue(CharacteristicsType.AGGRESIVITY, 100, fernardoAlonso);
        createCharacteristicsValue(CharacteristicsType.STEERING, 50, fernardoAlonso);
        createCharacteristicsValue(CharacteristicsType.DRIVING_ON_WET, 15, fernardoAlonso);
        createCharacteristicsValue(CharacteristicsType.ENDURANCE, 4, fernardoAlonso);
        final Driver michaelSchumacher = createDriver("Michael",
                "Schumacher",
                "micheal@schumacher.cz",
                "ILoveGermany",
                "German",
                dateService.createDate(3, 1, 1969),
                DriverStatus.MAIN
        );
        createCharacteristicsValue(CharacteristicsType.AGGRESIVITY, 40, michaelSchumacher);
        createCharacteristicsValue(CharacteristicsType.STEERING, 100, michaelSchumacher);
        createCharacteristicsValue(CharacteristicsType.DRIVING_ON_WET, 20, michaelSchumacher);
        createCharacteristicsValue(CharacteristicsType.ENDURANCE, 60, michaelSchumacher);

        final Driver testDriver1 = createDriver("Miki",
                "Raikonen",
                "miki@raikonen.fi",
                "aslkfhfrer342",
                "Finish",
                dateService.createDate(3, 1, 1995),
                DriverStatus.TEST
        );
        createCharacteristicsValue(CharacteristicsType.AGGRESIVITY, 10, testDriver1);
        createCharacteristicsValue(CharacteristicsType.STEERING, 15, testDriver1);
        createCharacteristicsValue(CharacteristicsType.DRIVING_ON_WET, 20, testDriver1);
        createCharacteristicsValue(CharacteristicsType.ENDURANCE, 30, testDriver1);
        createCharacteristicsValue(CharacteristicsType.PATIENCE, 8, testDriver1);

        final Driver testDriver2 = createDriver("John",
                "Doe",
                "john@doe.com",
                "john-does-password",
                "American",
                dateService.createDate(4, 11, 1998),
                DriverStatus.TEST
        );
        createCharacteristicsValue(CharacteristicsType.AGGRESIVITY, 10, testDriver2);
        createCharacteristicsValue(CharacteristicsType.STEERING, 15, testDriver2);
        createCharacteristicsValue(CharacteristicsType.DRIVING_ON_WET, 20, testDriver2);
        createCharacteristicsValue(CharacteristicsType.ENDURANCE, 30, testDriver2);
        createCharacteristicsValue(CharacteristicsType.PATIENCE, 8, testDriver2);

        List<ComponentParameter> engine1Parameters = new ArrayList<>();
        engine1Parameters.add(createComponentParameter("Power", "114kW"));
        engine1Parameters.add(createComponentParameter("Torque", "1500Nm"));
        final Component engine1 = createComponent("Engine no.1", ComponentType.ENGINE, engine1Parameters);

        List<ComponentParameter> engine2Parameters = new ArrayList<>();
        engine2Parameters.add(createComponentParameter("Power", "180kW"));
        engine2Parameters.add(createComponentParameter("Torque", "1800Nm"));
        final Component engine2 = createComponent("Engine no.2", ComponentType.ENGINE, engine2Parameters);

        List<ComponentParameter> brakeParameters = new ArrayList<>();
        brakeParameters.add(createComponentParameter("Max brake force", "25kN"));
        final Component brake = createComponent("Brakes", ComponentType.BRAKES, brakeParameters);

        List<ComponentParameter> suspensionParameters = new ArrayList<>();
        suspensionParameters.add(createComponentParameter("Type", "Dependent"));
        final Component suspension = createComponent("Suspension", ComponentType.SUSPENSION, suspensionParameters);

        List<ComponentParameter> transmissionParameters = new ArrayList<>();
        transmissionParameters.add(createComponentParameter("Type", "Automatic"));
        transmissionParameters.add(createComponentParameter("Number of gears", "8"));
        final Component transmission = createComponent("Suspension", ComponentType.TRANSMISSION, transmissionParameters);

        List<ComponentParameter> tires1Parameters = new ArrayList<>();
        tires1Parameters.add(createComponentParameter("Manufacturer", "Michellin"));
        tires1Parameters.add(createComponentParameter("Nominal section width", "205mm"));
        final Component tires1 = createComponent("Suspension", ComponentType.TIRES, tires1Parameters);

        List<ComponentParameter> tires2Parameters = new ArrayList<>();
        tires2Parameters.add(createComponentParameter("Manufacturer", "Barum"));
        tires2Parameters.add(createComponentParameter("Nominal section width", "108mm"));
        final Component tires2 = createComponent("Suspension", ComponentType.TIRES, tires2Parameters);

        List<ComponentParameter> coverParameters = new ArrayList<>();
        coverParameters.add(createComponentParameter("Reference area", "2800 ft*ft"));
        final Component cover = createComponent("Suspension", ComponentType.TIRES, coverParameters);

        final CarSetup carSetup1 = createCarSetup(engine1, suspension, brake, transmission, tires1, cover);
        final CarSetup carSetup2 = createCarSetup(engine2, suspension, brake, transmission, tires1, cover);
        final CarSetup carSetup3 = createCarSetup(engine1, suspension, brake, transmission, tires2, cover);

        final Race pastRace = createRace(dateService.getPastDate(15), "WC Valencia", "Valencia");
        createRaceParticipation(carSetup1, michaelSchumacher, pastRace, 1);
        createRaceParticipation(carSetup2, fernardoAlonso, pastRace, 5);

        final Race futureRace = createRace(dateService.getFutureDate(30), "Germany World Championship", "Nuremberg");
        createRaceParticipation(carSetup2, fernardoAlonso, futureRace, RaceParticipation.NO_RESULT_POSITION);
        createRaceParticipation(carSetup1, michaelSchumacher, futureRace, RaceParticipation.NO_RESULT_POSITION);

        createTestDrive(carSetup1, testDriver1, "Steering maybe too stiff", dateService.getPastDate(40));
        createTestDrive(carSetup1, testDriver2, "Great power", dateService.getPastDate(40));
        createTestDrive(carSetup2, testDriver1, "Pretty good steering, ", dateService.getPastDate(30));
        createTestDrive(carSetup2, testDriver2, "Did not finish, transmission broke", dateService.getPastDate(30));
        createTestDrive(carSetup3, testDriver1, "Fast, but missing some advantages of no. 2", dateService.getPastDate(20));
        createTestDrive(carSetup3, testDriver2, "Pretty fast", dateService.getPastDate(20));
        createTestDrive(carSetup3, testDriver2, "Slides a lot on wet", dateService.getPastDate(5));
    }

    private Driver createDriver(String name,
                                String surname,
                                String email,
                                String password,
                                String nationality,
                                Date birthday,
                                DriverStatus driverStatus) {
        final Driver driver = new Driver(name, surname, email, "", nationality, birthday, driverStatus);
        driverService.register(driver, password);
        return driver;
    }

    private Engineer createEngineer(String name,
                                    String surname,
                                    String email,
                                    String password,
                                    EngineerSpecialization specialization) {
        final Engineer engineer = new Engineer(name, surname, email, "", specialization);
        engineerService.register(engineer, password);
        return engineer;
    }

    private Manager createManager(String name, String surname, String email, String password) {
        final Manager manager = new Manager(name, surname, email, "");
        managerService.register(manager, password);
        return manager;
    }

    private Component createComponent(String name, ComponentType type, List<ComponentParameter> parameters) {
        final Component component = new Component(name, type);
        for (ComponentParameter parameter : parameters) {
            component.addParameter(parameter);
        }
        componentService.add(component);
        return component;
    }

    private ComponentParameter createComponentParameter(String name,
                                                        String value) {
        final ComponentParameter componentParameter = new ComponentParameter(name, value);
        componentParameterService.add(componentParameter);
        return componentParameter;
    }

    private CarSetup createCarSetup(Component engine,
                                    Component suspension,
                                    Component brakes,
                                    Component transmission,
                                    Component tires,
                                    Component cover) {
        final CarSetup carSetup = new CarSetup(engine, suspension, brakes, transmission, tires, cover);
        carSetupService.add(carSetup);
        return carSetup;
    }

    private CharacteristicsValue createCharacteristicsValue(CharacteristicsType type, double value, Driver driver) {
        final CharacteristicsValue characteristicsValue = new CharacteristicsValue(type, value, driver);
        characteristicsValueService.add(characteristicsValue);
        return characteristicsValue;
    }

    private Race createRace(Date date, String title, String location) {
        final Race race = new Race(date, title, location);
        raceService.add(race);
        return race;
    }

    private RaceParticipation createRaceParticipation(CarSetup car, Driver driver, Race race, int resultPosition) {
        final RaceParticipation raceParticipation = new RaceParticipation(car, driver, race, resultPosition);
        raceParticipationService.add(raceParticipation);
        return raceParticipation;
    }

    private TestDrive createTestDrive(CarSetup car, Driver driver, String notes, Date date) {
        final TestDrive testDrive = new TestDrive(car, driver, notes, date);
        testDriveService.add(testDrive);
        return testDrive;
    }
}
