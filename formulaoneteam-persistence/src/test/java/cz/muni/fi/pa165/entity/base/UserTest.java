package cz.muni.fi.pa165.entity.base;

import cz.muni.fi.pa165.dao.base.BaseTest;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.Before;
import org.junit.Test;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

/**
 * @author elderanakain (Arcadii Rubailo)
 */
public class UserTest extends BaseTest {

    private User user;

    @Before
    public void setUp() {
        user = new User() {};
    }

    @Test
    public void whenNameAndSurnameAreNotSet_returnHasFullNameFalseAndNotConfigured() {
        assertFalse(user.hasFullName());
        assertFalse(user.isConfigured());
    }

    @Test
    public void whenNameIsSet_returnHasNameTrue() {
        //when
        user.setName("name");

        //then
        assertTrue(user.hasName());
    }

    @Test
    public void whenSurnameIsSet_returnHasSurnameTrue() {
        //when
        user.setSurname("surname");

        //then
        assertTrue(user.hasSurname());
    }

    @Test
    public void whenNameAndSurnameAreSet_returnHasFullNameTrue() {
        //when
        user.setName("name");
        user.setSurname("surname");

        //then
        assertTrue(user.hasFullName());
    }

    @Test
    public void whenEmailIsSet_returnHasEmailTrue() {
        //when
        user.setEmail("email");

        //then
        assertTrue(user.hasEmail());
    }

    @Test
    public void whenPasswordIsSet_returnHasPasswordTrue() {
        //when
        user.setPasswordHash("password");

        //then
        assertTrue(user.hasPassword());
    }

    @Test
    public void whenSomeFieldsAreNotSet_returnIsNotConfigured() {
        assertFalse(user.hasName());
        assertFalse(user.hasSurname());
        assertFalse(user.hasEmail());
        assertFalse(user.hasPassword());

        assertFalse(user.isConfigured());

        //given
        user.setId(1);

        //then
        assertFalse(user.isConfigured());

        //given
        user.setName("name");

        //then
        assertFalse(user.isConfigured());

        //given
        user.setSurname("surname");

        //then
        assertFalse(user.isConfigured());

        //given
        user.setEmail("email");

        //then
        assertFalse(user.isConfigured());
    }

    @Test
    public void whenAllFieldsAreSet_returnIsConfigured() {
        //when
        user.setId(1);
        user.setName("name");
        user.setSurname("surname");
        user.setEmail("email");
        user.setPasswordHash("password");

        //then
        assertTrue(user.isConfigured());
    }

    @Test
    public void equalsContract() {
        EqualsVerifier.forClass(User.class)
                .withOnlyTheseFields("email")
                .withNonnullFields("email")
                .verify();
    }
}
