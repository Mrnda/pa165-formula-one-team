package cz.muni.fi.pa165.service;

import cz.muni.fi.pa165.dao.TestDrive.TestDriveDao;
import cz.muni.fi.pa165.entity.CarSetup;
import cz.muni.fi.pa165.entity.Driver;
import cz.muni.fi.pa165.entity.TestDrive;
import cz.muni.fi.pa165.service.base.BaseEntityServiceImpl;
import cz.muni.fi.pa165.service.exceptions.FormulaOneTeamException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Adel Chakouri
 */
@Service
public class TestDriveServiceImpl extends BaseEntityServiceImpl<TestDrive, TestDriveDao> implements TestDriveService {

    @Override
    public void validateEntity(TestDrive entity) throws FormulaOneTeamException {
        if (entity == null || !entity.isConfigured()) {
            throw new FormulaOneTeamException("TestDrive entity is null or not configured");
        }
    }

    @Override
    public Map<CarSetup, List<String>> getNotesForDriver(Driver driver) {
        List<TestDrive> drives = dao.findAll()
                .stream()
                .filter(d -> d.getDriver().equals(driver))
                .collect(Collectors.toList());

        Map<CarSetup, List<String>> groupedNotes = new HashMap<>();

        for (TestDrive drive : drives) {
            if (groupedNotes.containsKey(drive.getCarSetup())) {
                groupedNotes.get(drive.getCarSetup()).add(drive.getNotes());
            } else {
                List<String> notes = new ArrayList<>();
                notes.add(drive.getNotes());

                groupedNotes.put(drive.getCarSetup(), notes);
            }
        }

        return groupedNotes;
    }

    @Override
    public Map<Driver, List<String>> getNotesForCar(CarSetup car) {
        List<TestDrive> drives = dao.findAll()
                .stream()
                .filter(d -> d.getCarSetup().equals(car))
                .collect(Collectors.toList());

        Map<Driver, List<String>> groupedNotes = new HashMap<>();

        for (TestDrive drive : drives) {
            if (groupedNotes.containsKey(drive.getDriver())) {
                groupedNotes.get(drive.getDriver()).add(drive.getNotes());
            } else {
                List<String> notes = new ArrayList<>();
                notes.add(drive.getNotes());

                groupedNotes.put(drive.getDriver(), notes);
            }
        }

        return groupedNotes;
    }

    @Override
    protected Class<TestDrive> getEntityClass() {
        return TestDrive.class;
    }
}
