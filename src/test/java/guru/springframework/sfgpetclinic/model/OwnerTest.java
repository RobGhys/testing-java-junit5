package guru.springframework.sfgpetclinic.model;

import guru.springframework.sfgpetclinic.ModelTests;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;

class OwnerTest implements ModelTests {

    @Test
    void dependentAssertions() {

        Owner owner = new Owner(1l, "Robin", "Ghyselinck");
        owner.setCity("Brussels");
        owner.setTelephone("+32444444444");

        // jUnit 5 tests
        assertAll("Properties Test",
                () -> assertAll("Person Properties",
                        () -> assertEquals("Robin", owner.getFirstName(), "First Name Did not Match"),
                        () -> assertEquals("Ghyselinck", owner.getLastName())),
                () -> assertAll("Owner Properties",
                        () -> assertEquals("Brussels", owner.getCity()),
                        () -> assertEquals("+32444444444", owner.getTelephone()))
        );

        // hamcrest test
        assertThat(owner.getCity(), is("Brussels"));
    }
}