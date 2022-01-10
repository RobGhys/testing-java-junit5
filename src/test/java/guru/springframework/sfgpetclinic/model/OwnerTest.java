package guru.springframework.sfgpetclinic.model;

import guru.springframework.sfgpetclinic.CustomArgsProvider;
import guru.springframework.sfgpetclinic.ModelTests;
import guru.springframework.sfgpetclinic.controllers.OwnerType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.util.stream.Stream;

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

    @DisplayName("Value Source Test")
    @ParameterizedTest(name = "{displayName} - [{index}] {arguments}")
    @ValueSource(strings = {"Spring", "Framework", "Rob"})
    void testValueSource(String value) {
        System.out.println(value);
    }

    // Use a enum Source
    @DisplayName("Enum Source Test")
    @ParameterizedTest(name = "{displayName} - [{index}] {arguments}")
    @EnumSource(OwnerType.class)
    void enumTest(OwnerType ownerType) {
        System.out.println(ownerType);
    }

    // Use a method Source
    @DisplayName("Method Provider Test")
    @ParameterizedTest(name = "{displayName} - [{index}] {arguments}")
    @MethodSource("getArgs")
    void fromMethodTest(String countryName, int val1, int val2) {
        System.out.println(countryName + " = " + val1 + ", " + val2);
    }

    static Stream<Arguments> getArgs() {
        return Stream.of(
                Arguments.of("BE", 1, 1),
                Arguments.of("NL", 2, 4),
                Arguments.of("FR", 3, 5));
    }

    @DisplayName("Custom Provider Test")
    @ParameterizedTest(name = "{displayName} - [{index}] {arguments}")
    @ArgumentsSource(CustomArgsProvider.class)
    void fromCustomProviderTest(String countryName, int val1, int val2) {
        System.out.println(countryName + " = " + val1 + ", " + val2);
    }
}