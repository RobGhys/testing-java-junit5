package guru.springframework.sfgpetclinic.services.springdatajpa;

import guru.springframework.sfgpetclinic.model.Speciality;
import guru.springframework.sfgpetclinic.repositories.SpecialtyRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SpecialitySDJpaServiceTest {

    @Mock
    SpecialtyRepository specialtyRepository;

    @InjectMocks
    SpecialitySDJpaService service;

    @Test
    void deleteByObject() {
        Speciality speciality = new Speciality();
        service.delete(speciality);

        // Verify that the delete method was called with any object having the Specialty class
        verify(specialtyRepository).delete(any(Speciality.class));
    }

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

    @Test
    void deleteById() {
        service.deleteById(1l);
        service.deleteById(1l);

        // By default, the method applies once, i.e. we want the stuff to happen 1 time
        verify(specialtyRepository, times(2)).deleteById(1l);
    }

    @Test
    void deleteByIdAtLeastOnce() {
        service.deleteById(1l);
        service.deleteById(1l);

        verify(specialtyRepository, atLeastOnce()).deleteById(1l);
    }

    @Test
    void deleteByIdAtMostOnce() {
        service.deleteById(1l);
        service.deleteById(1l);

        verify(specialtyRepository, atMost(5)).deleteById(1l);
    }

    @Test
    void deleteByINever() {
        service.deleteById(1l);
        service.deleteById(1l);

        verify(specialtyRepository, atLeastOnce()).deleteById(1l);
        verify(specialtyRepository, never()).deleteById(5l);
    }

    @Test
    void testDelete() {
        service.delete(new Speciality());
    }
}