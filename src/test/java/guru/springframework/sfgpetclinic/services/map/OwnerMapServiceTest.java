package guru.springframework.sfgpetclinic.services.map;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.model.PetType;
import guru.springframework.sfgpetclinic.services.PetService;
import guru.springframework.sfgpetclinic.services.PetTypeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Owner Map Service Test -- ")
class OwnerMapServiceTest {

    OwnerMapService ownerMapService;
    PetTypeService petTypeService;
    PetService petService;

    @BeforeEach
    void setUp() {
        petTypeService = new PetTypeMapService();
        petService = new PetMapService();
        ownerMapService = new OwnerMapService(petTypeService, petService);

        System.out.println("@BeforeEach #1");
    }

    @Test
    void ownersAreZero() {
        int ownerCount = ownerMapService.findAll().size();

        assertThat(ownerCount).isZero();
    }

    @DisplayName("Pet Type -- ")
    @Nested
    class TestCreatePetTypes {

        @BeforeEach
        void setup() {
            PetType petType1 = new PetType(1l, "Chat");
            PetType petType2 = new PetType(2l, "Chien");
            petTypeService.save(petType1);
            petTypeService.save(petType2);

            System.out.println("@BeforeEach #2");
        }

        @Test
        void testPetCount() {
            int petTypeCount = petTypeService.findAll().size();

            assertThat(petTypeCount).isNotZero().isEqualTo(2);
        }

        @DisplayName("Save Owner -- ")
        @Nested
        class SaveOwnersTests {

            @BeforeEach
            void setup() {
                ownerMapService.save(new Owner(1l, "Bob", "Dylan"));

                System.out.println("@BeforeEach #3");
            }

            @Test
            void saveOwner() {
                Owner owner = new Owner(2l, "Tom", "Cruise");

                Owner savedOwner = ownerMapService.save(owner);

                assertThat(savedOwner).isNotNull();
            }

            @DisplayName("Find Owner -- ")
            @Nested
            class FindOwnersTest {

                @DisplayName("Find Owner")
                @Test
                void findOwner() {
                    Owner foundOwner = ownerMapService.findById(1l);

                    assertThat(foundOwner).isNotNull();
                }

                // The saveOwner() method is not run before the findOwnerNotFund()
                @DisplayName("Find Owner not found")
                @Test
                void findOwnerNotFound() {
                    Owner notFoundOwner = ownerMapService.findById(2l);

                    assertThat(notFoundOwner).isNull();
                }
            }
        }
    }

    @DisplayName("Verify still zero Owners")
    @Test
    void ownersAreStillZero() {
        int ownerCount = ownerMapService.findAll().size();

        assertThat(ownerCount).isZero();
    }
}