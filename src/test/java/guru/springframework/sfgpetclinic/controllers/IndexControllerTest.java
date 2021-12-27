package guru.springframework.sfgpetclinic.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledOnJre;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.JRE;
import org.junit.jupiter.api.condition.OS;

import java.time.Duration;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

class IndexControllerTest {

    private IndexController indexController;

    @BeforeEach
    void setUp() {
        indexController = new IndexController();
    }

    @DisplayName("Test that the correct view name is displayed for index page")
    @Test
    void index() {
        // jUnit assertions
        assertEquals("index", indexController.index());
        assertNotEquals("indexxxxx", indexController.index(), "Wrong view returned");

        // unitJ assertions
        assertThat(indexController.index()).isEqualTo("index");
    }

    @DisplayName("Test wrong url")
    @Test
    void oupsHandler() {
        assertThrows(ValueNotFoundException.class,
                () -> {
                    indexController.oupsHandler();
                });
    }

    @Disabled("Demo of timeout")
    @Test
    void testTimeOut() {
        assertTimeout(Duration.ofMillis(100), () -> {
            Thread.sleep(2000);
            System.out.println("The test went here");
        });
    }

    @Disabled("Demo of timeout")
    @Test
    void testTimeOutPreempt() {
        assertTimeoutPreemptively(Duration.ofMillis(100), () -> {
            Thread.sleep(2000);
            System.out.println("If you see this message, that is bad. The test should not have went here");
        });
    }

    @Disabled("No run time variable")
    @Test
    void testAssumptionTrue() {
        assumeTrue("ROB".equalsIgnoreCase(System.getenv("ROB_RUNTIME")));
    }

    @Test
    void testAssumptionTrueAssumptionIsTrue() {
        assumeTrue("ROB".equalsIgnoreCase("ROB"));
    }

    @EnabledOnOs(OS.MAC)
    @Test
    void testOnMac() {
    }

    @EnabledOnOs(OS.WINDOWS)
    @Test
    void testOnWindows() {
    }

    @EnabledOnJre(JRE.JAVA_8)
    @Test
    void testOnJava8() {
    }

    @EnabledOnJre(JRE.JAVA_11)
    @Test
    void testOnJava11() {
    }
}