package soundwave;

import static org.junit.Assert.*;

import org.junit.Ignore;
import org.junit.Test;
import soundwave.model.User;
import soundwave.repository.Migration;
import soundwave.views.Login;
import soundwave.views.Registration;

import java.util.HashMap;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue() {
        assertTrue( true );
    }

    /**
     * Authentication Testing
     */

    @Test
    public void registerUser() {
        Registration registration = new Registration();
        boolean isClean = registration.register("Wahyu Siwananda", "wahyu@example.com", "wahyu", "qwerty", "qwerty");

        assertTrue(isClean);
    }

    @Test
    public void registerUserInvalidConfirmationPassword() {
        Registration registration = new Registration();
        boolean isClean = registration.register("Wahyu Siwananda", "wahyu@example.com", "wahyu", "qwerty", "qwertyz");

        assertFalse(isClean);
    }

    @Test
    public void registerMultipleExistingUser() {
        Registration registration = new Registration();
        registration.register("Wahyu Siwananda", "wahyu@example.com", "wahyu", "qwerty", "qwerty");

        Registration registration2 = new Registration();
        boolean isClean = registration2.register("Wahyu Siwananda", "wahyu@example.com", "wahyu", "qwerty", "qwerty");

        assertFalse(isClean);
    }

    @Test
    public void loginUserValid() {
        Registration registration = new Registration();
        boolean registry = registration.register("Wahyu Siwananda", "wahyu@example.com", "wahyu", "qwerty", "qwerty");

        Login login = new Login();
        boolean isClean = login.login("wahyu", "qwerty");

        assertTrue(isClean);
    }

    @Test
    public void loginInvalidUserRegistry() {
        Login login = new Login();
        boolean isClean = login.login("Wahyu Siwananda", "wahyu");

        assertFalse(isClean);
    }

    @Test
    public void loginUsernameNotFound() {
        Registration registration = new Registration();
        registration.register("Wahyu Siwananda", "wahyu@example.com", "wahyu", "qwerty", "qwerty");

        Login login = new Login();
        boolean isClean = login.login("Wahyu Siwananda", "qwerty");

        assertFalse(isClean);
    }

    @Test
    public void loginInvalidPassword() {
        Registration registration = new Registration();
        registration.register("Wahyu Siwananda", "wahyu@example.com", "wahyu", "qwerty", "qwerty");

        Login login = new Login();
        boolean isClean = login.login("wahyu", "fein");

        assertFalse(isClean);
    }

    @Test
    public void differentID() {
        Registration registration = new Registration();
        registration.register("Wahyu Siwananda", "wahyu@example.com", "wahyu", "qwerty", "qwerty");

        Registration registration2 = new Registration();
        registration.register("Wahyu Siwananda", "wahyu@example.com", "Wahyu Siwananda", "qwerty", "qwerty");

        String id1 = Migration.getUsers().get("wahyu").getId();
        String id2 = Migration.getUsers().get("Wahyu Siwananda").getId();

        assertNotEquals(id1, id2);
    }

    /**
     * Debug and more
     */
    @Ignore
    @Test
    public void listTestDebug() {
        Migration migration = new Migration();
        HashMap<String, User> user = new HashMap<>();
        User wahyu = new User();
        wahyu.setEmail("wahyu@example.com");
        wahyu.setName("Wahyu Siwananda");
        wahyu.setUsername("wahyu");
        wahyu.setPassword("qwerty");

        user.put("wahyu", wahyu);
        migration.setUsers(user);

        System.out.println(migration.getUsers().get("Wahyu") == null);
    }

    @Ignore
    @Test
    public void emptyMigration() {
        Migration migration = new Migration();
        HashMap<String, User> users = migration.getUsers();
    }
}
