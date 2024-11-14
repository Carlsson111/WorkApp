package se.lexicon.dao;

import se.lexicon.AppUser;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;


public class AppUserDAOCollection implements AppUserDAO {
    private Collection<AppUser> appUserList = new ArrayList<>();

    @Override
    public void persist(AppUser appUser) {
        appUserList.add(appUser);

    }

    @Override
    public AppUser findByUsername(String username) {
        for (AppUser user : appUserList) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        } return null;
    }

    @Override
    public Collection<AppUser> findAll() {
        return new ArrayList<>(appUserList);
    }

    @Override
    public void remove(String username) {
        Iterator<AppUser> iterator = appUserList.iterator();
        while (iterator.hasNext()){
            AppUser user = iterator.next();
            if (user.getUsername().equals(username)){
                iterator.remove();
                break;
            }
        }

    }

}
