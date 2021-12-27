package guru.springframework.sfgpetclinic.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OwnerTest {

    @Test
    void dependentAssertions() {

        Owner owner = new Owner(1l, "Robin", "Ghyselinck");
        owner.setCity("Brussels");
        owner.setTelephone("+32444444444");

        assertAll("Properties Test",
                () -> assertAll("Person Properties",
                        () -> assertEquals("Robin", owner.getFirstName(), "First Name Did not Match"),
                        () -> assertEquals("Ghyselinck", owner.getLastName())),
                () -> assertAll("Owner Properties",
                        () -> assertEquals("Brussels", owner.getCity()),
                        () -> assertEquals("+32444444444", owner.getTelephone()))
        );
    }
}