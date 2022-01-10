package guru.springframework.sfgpetclinic.services.springdatajpa;

import guru.springframework.sfgpetclinic.model.Speciality;
import guru.springframework.sfgpetclinic.repositories.SpecialtyRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SpecialitySDJpaServiceTest {

    @Mock
    SpecialtyRepository specialtyRepository;

    @InjectMocks
    SpecialitySDJpaService service;

    @DisplayName("Test delete")
    @Test
    void deleteByObject() {

        // Given
        Speciality speciality = new Speciality();

        // When
        service.delete(speciality);

        // Then
        then(specialtyRepository).should().delete(any(Speciality.class));
    }

    @DisplayName("Test findById")
    @Test
    void findByIdTest() {
        Speciality speciality = new Speciality();

        // If we use the findById method, then we need to return an Optional
        when(specialtyRepository.findById(1l)).thenReturn(Optional.of(speciality));

        Speciality foundSpecialy = service.findById(1l);

        // Verify that we got the instance
        assertThat(foundSpecialy).isNotNull();

        // Verify that the method was called 1x
        verify(specialtyRepository).findById(1l);
    }

    @DisplayName("Test findById BDD")
    @Test
    void findByIdBDDTest() {
        // Given
        Speciality speciality = new Speciality();

        // Configure the mocks
        given(specialtyRepository.findById(1l)).willReturn(Optional.of(speciality));

        // When
        Speciality foundSpecialy = service.findById(1l);

        // Then
        // Verify that we got the instance
        assertThat(foundSpecialy).isNotNull();

        // Verify that the method was called 1x
        //verify(specialtyRepository).findById(1l);
        //then(specialtyRepository).should().findById(anyLong());
        then(specialtyRepository).should(times(1)).findById(anyLong());
        then(specialtyRepository).shouldHaveNoMoreInteractions();
    }

    @DisplayName("Test findById Any")
    @Test
    void findByIdAnyTest() {
        Speciality speciality = new Speciality();

        // If we use the findById method, then we need to return an Optional
        when(specialtyRepository.findById(1l)).thenReturn(Optional.of(speciality));

        Speciality foundSpecialy = service.findById(1l);

        // Verify that we got the instance
        assertThat(foundSpecialy).isNotNull();

        // Verify that the method was called 1x
        verify(specialtyRepository).findById(anyLong());
    }

    @DisplayName("Test deleteById")
    @Test
    void deleteById() {

        // Given - none

        // When
        service.deleteById(1l);
        service.deleteById(1l);

        // Then
        then(specialtyRepository).should(times(2)).deleteById(1l);
        // By default, the method applies once, i.e. we want the stuff to happen 1 time
        //verify(specialtyRepository, times(2)).deleteById(1l);
    }

    @DisplayName("Test deleteById At least 1")
    @Test
    void deleteByIdAtLeastOnce() {

        // Given - none

        // When
        service.deleteById(1l);
        service.deleteById(1l);

        // Then
        then(specialtyRepository).should(atLeastOnce()).deleteById(1l);
        //verify(specialtyRepository, atLeastOnce()).deleteById(1l);
    }

    @DisplayName("Test deleteById At most 1")
    @Test
    void deleteByIdAtMostOnce() {
        // Given - none

        // When
        service.deleteById(1l);
        service.deleteById(1l);

        // Then
        then(specialtyRepository).should(atMost(5)).deleteById(1l);
        //verify(specialtyRepository, atMost(5)).deleteById(1l);
    }

    @DisplayName("Test deleteById never")
    @Test
    void deleteByINever() {
        // Given - none

        // When
        service.deleteById(1l);
        service.deleteById(1l);

        // Then
        then(specialtyRepository).should(atLeastOnce()).deleteById(1l);
        //verify(specialtyRepository, atLeastOnce()).deleteById(1l);
        then(specialtyRepository).should(never()).deleteById(5l);
        verify(specialtyRepository, never()).deleteById(5l);
    }

    @DisplayName("Test delete")
    @Test
    void testDelete() {
        service.delete(new Speciality());
    }
}