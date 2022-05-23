package guru.springframework.sfgpetclinic.controllers;

import guru.springframework.sfgpetclinic.fauxspring.BindingResult;
import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.services.OwnerService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class OwnerControllerTest {

    @Mock
    OwnerService ownerService;

    @Mock
    BindingResult bindingResult;

    @InjectMocks
    OwnerController ownerController;

    @Test
    void processCreationFormHasErrors() {
        //given
        final String expectedHasErrors = "owners/createOrUpdateOwnerForm";
        Owner owner = new Owner(1l, "Alex", "Rodrigues");
        given(bindingResult.hasErrors()).willReturn(true);

        //when
        String res = ownerController.processCreationForm(owner, bindingResult);

        //then
        then(bindingResult).should(times(1)).hasErrors();
        assertEquals(expectedHasErrors, res);
    }

    @Test
    void processCreationFormNoErrors() {
        //given
        final String expectedNoErrors =  "redirect:/owners/5";
        Owner owner = new Owner(5l, "Alex", "Rodrigues");
        given(bindingResult.hasErrors()).willReturn(false);
        given(ownerService.save(any(Owner.class))).willReturn(owner);

        //when
        String res = ownerController.processCreationForm(owner, bindingResult);

        //then
        then(bindingResult).should(times(1)).hasErrors();
        then(ownerService).should(times(1)).save(any(Owner.class));
        assertEquals(expectedNoErrors, res);

    }
}