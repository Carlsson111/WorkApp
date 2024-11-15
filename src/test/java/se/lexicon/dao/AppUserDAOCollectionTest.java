package se.lexicon.dao;

import org.junit.Before;
import org.junit.Test;
import se.lexicon.AppRole;
import se.lexicon.model.AppUser;

import java.util.Collection;

import static org.junit.Assert.*;

public class AppUserDAOCollectionTest {
    AppUserDAO appUserDAO;

    @Before
    public void setUp(){

        appUserDAO = new AppUserDAOCollection();

    }

    @Test
    public void persist() {
        AppUser appUser = new AppUser("Carlsson111", "Password1", AppRole.ROLE_APP_USER);
        appUserDAO.persist(appUser);
        assertEquals(1,appUserDAO.findAll().size());
    }

    @Test
    public void findByUsername() {
        AppUser appUser = new AppUser("Carlsson111", "Password1", AppRole.ROLE_APP_USER);
        appUserDAO.persist(appUser);
        AppUser foundUser= appUserDAO.findByUsername("Carlsson111");
        assertEquals("Carlsson111",foundUser.getUsername());
    }

    @Test
    public void findAll() {
        AppUser appUser2 = new AppUser("Carlsson111", "Password1", AppRole.ROLE_APP_USER);
        AppUser appUser1 = new AppUser("Carlsson222", "Password23", AppRole.ROLE_APP_ADMIN);
        appUserDAO.persist(appUser1);
        appUserDAO.persist(appUser2);
        Collection<AppUser>allUsers = appUserDAO.findAll();
        assertEquals(2,allUsers.size());


    }

    @Test
    public void remove() {
        AppUser appUser1 = new AppUser("Carlsson111", "Password1", AppRole.ROLE_APP_USER);
        AppUser appUser2 = new AppUser("Carlsson222", "Password23", AppRole.ROLE_APP_ADMIN);
        appUserDAO.persist(appUser1);
        appUserDAO.persist(appUser2);
        appUserDAO.remove("Carlsson111");
        assertEquals(1, appUserDAO.findAll().size());


    }
}