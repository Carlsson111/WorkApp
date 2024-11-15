package se.lexicon;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import se.lexicon.model.AppUser;
import se.lexicon.model.Person;

public class PersonTest {
    //Simple test with jUnit for first time
    @Test
    public void testPersonCreation(){
        Person person = new Person(4, "Linus", "Carlsson", "Test@test.se");
        Assertions.assertEquals(4,person.getId());
        Assertions.assertEquals("Linus", person.getFirstName());
        Assertions.assertEquals("Carlsson", person.getLastName());
        Assertions.assertEquals("Test@test.se", person.getEmail());
    }

    @Test
    public void testPersonCreationWithNullFirstName() {
        new Person(4, "Null", "Carlsson", "Test@test.se");
    }
    @Test public void testToString() {
        Person person = new Person(1,"Linus", "Carlsson", "Test@test.se");
        String expectedString = "Person {id: 1, name: Linus Carlsson, email: Test@test.se}";
        Assertions.assertEquals(expectedString, person.toString());
    }
    @Test
    public void testEqualAndHashCode(){
        Person person1 = new Person(1, "Linus", "Carlsson", "Test@test.se");
        Person person2 = new Person(1, "Linus", "Carlsson", "Test@test.se");
        Assertions.assertEquals(person1, person2);
        Assertions.assertEquals(person1.hashCode(), person2.hashCode());
    }
    @Test
    public void testSetCredentials() {
        Person person = new Person(1, "John", "Doo", "john.doo@example.com");
        AppUser user = new AppUser("john_doo", "password123", AppRole.ROLE_APP_USER);
        person.setCredentials(user); Assertions.assertEquals(user, person.getCredentials());
    }


}
