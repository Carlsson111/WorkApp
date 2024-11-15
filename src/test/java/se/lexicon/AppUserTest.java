package se.lexicon;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import se.lexicon.model.AppUser;

public class AppUserTest {

    @Test
    public void testAppUserCreation() {
        AppUser user = new AppUser("john_doe", "password123", AppRole.ROLE_APP_USER);
        Assertions.assertEquals("john_doe", user.getUsername());
        Assertions.assertEquals("password123", user.getPassword());
        Assertions.assertEquals(AppRole.ROLE_APP_USER, user.getRole());
    }

    @Test
    public void testSetUsername() {
        AppUser user = new AppUser("john_doe", "password123", AppRole.ROLE_APP_USER);
        user.setUsername("jane_doe");
        Assertions.assertEquals("jane_doe", user.getUsername());
    }

    @Test
    public void testSetPassword() {
        AppUser user = new AppUser("john_doe", "password123", AppRole.ROLE_APP_USER);
        user.setPassword("new_password");
        Assertions.assertEquals("new_password", user.getPassword());
    }

    @Test
    public void testSetRole() {
        AppUser user = new AppUser("john_doo", "password123", AppRole.ROLE_APP_USER);
        user.setRole(AppRole.ROLE_APP_ADMIN);
        Assertions.assertEquals(AppRole.ROLE_APP_ADMIN, user.getRole());
    }

    @Test
    public void testToString() {
        AppUser user = new AppUser("john_dop", "password123", AppRole.ROLE_APP_USER);
        String expected = "AppUser {username: john_dop, role: ROLE_APP_USER}";
        Assertions.assertEquals(expected, user.toString());
    }

    @Test
    public void testEqualsAndHashCode() {
        AppUser user1 = new AppUser("john_doo", "password123", AppRole.ROLE_APP_USER);
        AppUser user2 = new AppUser("john_doo", "password123", AppRole.ROLE_APP_USER);
        Assertions.assertEquals(user1, user2);
        Assertions.assertEquals(user1.hashCode(), user2.hashCode());
    }

    @Test
    public void testAppUserCreationWithNullUsername() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new AppUser(null, "password123", AppRole.ROLE_APP_USER);
        });
    }

    @Test
    public void testAppUserCreationWithEmptyUsername() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new AppUser("", "password123", AppRole.ROLE_APP_USER);
        });
    }

    @Test
    public void testAppUserCreationWithNullPassword() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new AppUser("john_dop", null, AppRole.ROLE_APP_USER);
        });
    }

    @Test
    public void testAppUserCreationWithEmptyPassword() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new AppUser("john_dop", "", AppRole.ROLE_APP_USER);
        });
    }

    @Test
    public void testAppUserCreationWithNullRole() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new AppUser("john_dop", "password123", null);
        });
    }

    @Test
    public void testSetUsernameWithNull() {
        AppUser user = new AppUser("john_doo", "password123", AppRole.ROLE_APP_USER);
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            user.setUsername(null);
        });
    }

    @Test
    public void testSetUsernameWithEmpty() {
        AppUser user = new AppUser("john_doo", "password123", AppRole.ROLE_APP_USER);
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            user.setUsername("");
        });
    }

    @Test
    public void testSetPasswordWithNull() {
        AppUser user = new AppUser("john_doo", "password123", AppRole.ROLE_APP_USER);
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            user.setPassword(null);
        });
    }

    @Test
    public void testSetPasswordWithEmpty() {
        AppUser user = new AppUser("john_doo", "password123", AppRole.ROLE_APP_USER);
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            user.setPassword("");
        });
    }

    @Test
    public void testSetRoleWithNull() {
        AppUser user = new AppUser("john_doe", "password123", AppRole.ROLE_APP_USER);
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            user.setRole(null);
        });
    }
}
