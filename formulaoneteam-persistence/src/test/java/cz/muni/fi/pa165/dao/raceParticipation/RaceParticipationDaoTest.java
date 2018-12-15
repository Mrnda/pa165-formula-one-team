package cz.muni.fi.pa165.dao.raceParticipation;

import cz.muni.fi.pa165.dao.RaceParticipation.RaceParticipationDao;
import cz.muni.fi.pa165.dao.base.BaseTest;
import cz.muni.fi.pa165.entity.RaceParticipation;
import org.junit.Before;
import org.junit.Test;
import org.springframework.dao.InvalidDataAccessApiUsageException;

import javax.inject.Inject;
import java.util.List;

import static junit.framework.Assert.assertNotNull;
import static org.testng.Assert.*;

/**
 * @author Théo Desjardins
 */
public class RaceParticipationDaoTest extends BaseTest {

    @Inject
    private RaceParticipationDao raceParticipationDao;

    private RaceParticipation raceParticipation;

    @Before
    public void setUp() {
        raceParticipation = createRaceParticipation(getDefaultDriverEmail(), getDefaultRaceTitle());
    }

    @Test
    public void deleteRaceParticipation() {
        //when
        raceParticipationDao.add(raceParticipation);
        raceParticipationDao.delete(raceParticipation.getId());

        //then
        assertNull(raceParticipationDao.findById(raceParticipation.getId()));
    }

    @Test
    public void updateRaceParticipation() {
        //when
        raceParticipationDao.add(raceParticipation);
        raceParticipation.setResultPosition(1);
        raceParticipationDao.update(raceParticipation);

        //then
        assertEquals(1, raceParticipation.getResultPosition());
    }

    @Test
    public void addRaceParticipation() {
        //when
        raceParticipationDao.add(raceParticipation);

        //then
        assertEquals(raceParticipation, raceParticipationDao.findById(raceParticipation.getId()) );
        assertNotNull(raceParticipationDao.findById(raceParticipation.getId()));
    }

    @Test
    public void findAllRaceParticipation() {
        RaceParticipation otherParticipation = createRaceParticipation("other@email.com", "otherRaceTitle");

        //when
        raceParticipationDao.add(raceParticipation);
        raceParticipationDao.add(otherParticipation);

        //then
        List<RaceParticipation> raceParticipations = raceParticipationDao.findAll();
        assertTrue(raceParticipations.contains(raceParticipation));
        assertTrue(raceParticipations.contains(otherParticipation));
        assertEquals(2, raceParticipations.size());
    }

    @Test(expected = InvalidDataAccessApiUsageException.class)
    public void validateEntity_exceptionIsThrown() {
        //given
        RaceParticipation badRaceParticipation = null;

        //when
        raceParticipationDao.add(badRaceParticipation);
    }
}
