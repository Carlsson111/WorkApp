package se.lexicon.dao;

import org.junit.Before;
import org.junit.Test;
import se.lexicon.AppRole;
import se.lexicon.model.AppUser;

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
        assertEquals(0,appUserDAO.findAll().size());
    }

    @Test
    public void findByUsername() {
    }

    @Test
    public void findAll() {
    }

    @Test
    public void remove() {
    }
}