package se.lexicon.dao;

import se.lexicon.model.AppUser;
import se.lexicon.model.Person;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Objects;


public class AppUserDAOCollection implements AppUserDAO {
    private Collection<AppUser> appUsers = new ArrayList<>();

    @Override
    public void persist(AppUser appUser) {
        appUsers.add(appUser);

    }

    @Override
    public AppUser findByUsername(String username) {
        for (AppUser user : appUsers) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        } return null;
    }

    @Override
    public Collection<AppUser> findAll() {
        return new ArrayList<>(appUsers);
    }

    @Override
    public void remove(String username) {
        AppUser toRemove = null;
        for (AppUser appUser : appUsers){
            if(Objects.equals(appUser.getUsername(), username)) toRemove = appUser;
        }
        if (toRemove != null) appUsers.remove(toRemove);
    }

}
