package guru.springframework.sfgpetclinic.services.springdatajpa;

import guru.springframework.sfgpetclinic.model.Speciality;
import guru.springframework.sfgpetclinic.model.Visit;
import guru.springframework.sfgpetclinic.repositories.VisitRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class VisitSDJpaServiceTest {

    @Mock
    VisitRepository visitRepository;

    @InjectMocks
    VisitSDJpaService service;

    @Test
    @DisplayName("Find all objects")
    void findAll() {
        Set<Visit> visitSet = new HashSet<>();
        Mockito.when(visitRepository.findAll()).thenReturn(getVisits());

        visitSet = service.findAll();

        assertNotNull(visitSet);
        assertEquals(2, visitSet.size());
        verify(visitRepository, times(1)).findAll();
    }

    private Set<Visit> getVisits() {
        Set<Visit> visitSet = new HashSet<>();

        visitSet.add(new Visit(null, LocalDate.of(2022, 5, 22)));
        visitSet.add(new Visit(null, LocalDate.of(2022, 5, 21)));

        return visitSet;
    }

    @Test
    @DisplayName("Find by id")
    void findById() {
        Visit visit = new Visit();
        Mockito.when(visitRepository.findById(1L)).thenReturn(Optional.of(visit));

        visit = service.findById(1L);

        assertNotNull(visit);
        Mockito.verify(visitRepository, times(1)).findById(anyLong());
    }

    @Test
    @DisplayName("Save")
    void save() {
        Visit visit = new Visit();
        Mockito.when(visitRepository.save(any(Visit.class))).thenReturn((visit));

        visit = service.save(new Visit());
        verify(visitRepository, times(1)).save(any(Visit.class));
        assertNotNull(visit);
    }

    @Test
    @DisplayName("Delete by object")
    void delete() {
        Visit visit = new Visit();
        service.delete(visit);
        verify(visitRepository, times(1)).delete(any(Visit.class));
    }

    @Test
    @DisplayName("Delete by id")
    void deleteById() {
        service.deleteById(1L);
        verify(visitRepository, times(1)).deleteById(anyLong());
    }
}