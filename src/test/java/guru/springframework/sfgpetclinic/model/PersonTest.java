package guru.springframework.sfgpetclinic.model;

import guru.springframework.sfgpetclinic.ModelTests;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class PersonTest implements ModelTests {

    @Test
    void testGroupedAssertions() {
        // given
        Person person = new Person(1l, "Rob", "Ghyselinck");

        // then
        assertAll("Test Props Set",
                () -> assertEquals("Rob", person.getFirstName()),
                () -> assertEquals("Ghyselinck", person.getLastName()));
    }

    @Test
    void testGroupedAssertionsWithMessages() {
        // given
        Person person = new Person(1l, "Rob", "Ghyselinck");

        // then
        assertAll("Test Props Set",
                () -> assertEquals("Rob", person.getFirstName(), "First Name Failed"),
                () -> assertEquals("Ghyselinck", person.getLastName(), "Last Name Failed"));
    }
}