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
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
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
        //given
        Set<Visit> visitSet = new HashSet<>();
        given(visitRepository.findAll()).willReturn(getVisits());

        //when
        visitSet = service.findAll();

        //then
        then(visitRepository).should(times(1)).findAll();
        assertNotNull(visitSet);
        assertEquals(2, visitSet.size());
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
        //given
        Visit visit = new Visit();
        given(visitRepository.findById(1L)).willReturn(Optional.of(visit));

        //when
        visit = service.findById(1L);

        //then
        then(visitRepository).should(times(1)).findById(anyLong());
        assertNotNull(visit);
    }

    @Test
    @DisplayName("Save")
    void save() {
        //given
        Visit visit = new Visit();
        given(visitRepository.save(any(Visit.class))).willReturn(visit);

        //when
        visit = service.save(new Visit());

        //then
        assertNotNull(visit);
        then(visitRepository).should(times(1)).save(any(Visit.class));
    }

    @Test
    @DisplayName("Delete by object")
    void delete() {
        //given
        Visit visit = new Visit();

        //when
        service.delete(visit);

        //then
        then(visitRepository).should(times(1)).delete(any(Visit.class));
    }

    @Test
    @DisplayName("Delete by id")
    void deleteById() {
        //when
        service.deleteById(1L);

        //then
        then(visitRepository).should(times(1)).deleteById(anyLong());
    }
}