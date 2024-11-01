package se.lexicon;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

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
    @Test
    public void testGetSummary() {
        Person person = new Person( 4,"Linus", "Carlsson", "Test@test.se");
        String expectedSummary = "Person {id: 4, name: Linus Carlsson, email: Test@test.se}";
        Assertions.assertEquals(expectedSummary, person.getSummary());
    }


}
