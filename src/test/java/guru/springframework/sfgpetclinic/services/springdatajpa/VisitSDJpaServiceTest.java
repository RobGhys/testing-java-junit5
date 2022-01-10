package guru.springframework.sfgpetclinic.services.springdatajpa;

import guru.springframework.sfgpetclinic.model.Visit;
import guru.springframework.sfgpetclinic.repositories.VisitRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class VisitSDJpaServiceTest {

    @Mock
    VisitRepository visitRepository;

    @InjectMocks
    VisitSDJpaService service;

    @DisplayName("Test findAll")
    @Test
    void findAllTest() {
        Visit visit = new Visit();
        Set<Visit> visits = new HashSet<>();

        visits.add(visit);

        assertThat(visits).isNotNull();

        when(visitRepository.findAll()).thenReturn(visits);

        Set<Visit> foundVisits = service.findAll();

        verify(visitRepository).findAll();

        assertThat(foundVisits).hasSize(1);
    }

    @DisplayName("Test save")
    @Test
    void saveTest() {
        Visit visit = new Visit();

        when(visitRepository.save(any(Visit.class))).thenReturn(visit);

        Visit savedVisit = service.save(new Visit());

        verify(visitRepository).save(any(Visit.class));

        assertThat(savedVisit).isNotNull();
    }

    @DisplayName("Test delete")
    @Test
    void deleteByObjectTest() {
        Visit visit = new Visit();
        service.delete(visit);

        verify(visitRepository).delete(any(Visit.class));
    }

    @DisplayName("Test findById")
    @Test
    void findById() {
        Visit visit = new Visit();

        when(visitRepository.findById(1l)).thenReturn(Optional.of(visit));

        Visit foundVisit = service.findById(1l);

        assertThat(foundVisit).isNotNull();
        verify(visitRepository).findById(1l);
    }

    @DisplayName("Test deleteById")
    @Test
    void deleteById() {
        service.deleteById(1l);

        verify(visitRepository, atLeastOnce()).deleteById(1l);
    }
}