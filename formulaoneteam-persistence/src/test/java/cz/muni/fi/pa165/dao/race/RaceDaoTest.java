package cz.muni.fi.pa165.dao.race;

import cz.muni.fi.pa165.dao.Race.RaceDao;
import cz.muni.fi.pa165.dao.base.BaseTest;
import cz.muni.fi.pa165.entity.Race;
import org.junit.Before;
import org.junit.Test;

import javax.inject.Inject;
import java.util.List;

import static junit.framework.Assert.assertNotNull;
import static org.testng.Assert.*;

/**
 * @author Théo Desjardins
 */
public class RaceDaoTest extends BaseTest {

    @Inject
    private RaceDao raceDao;

    private Race testRace;

    @Before
    public void setUp() {
        testRace = createRace(getDefaultRaceTitle());
    }

    @Test
    public void addRace() {
        //when
        raceDao.add(testRace);

        //then
        assertEquals(testRace, raceDao.findById(testRace.getId()) );
        assertNotNull(raceDao.findById(testRace.getId()));
    }

    @Test
    public void deleteRace() {
        //when
        raceDao.add(testRace);
        raceDao.delete(testRace.getId());

        //then
        assertNull(raceDao.findById(testRace.getId()));
    }

    @Test
    public void updateRace() {
        //when
        raceDao.add(testRace);
        testRace.setTitle("newTitle");
        raceDao.update(testRace);

        //then
        Race updatedRace = raceDao.findById(testRace.getId());
        assertEquals("newTitle", updatedRace.getTitle());
    }

    @Test
    public void findAllRace() {
        //given
        Race otherRace = new Race(createDate(10, 10, 1950), "otherTitle", "otherLocation");

        //when
        raceDao.add(testRace);
        raceDao.add(otherRace);

        //then
        List<Race> races = raceDao.findAll();
        assertTrue(races.contains(testRace));
        assertTrue(races.contains(otherRace));
        assertEquals(2, races.size());
    }
}
