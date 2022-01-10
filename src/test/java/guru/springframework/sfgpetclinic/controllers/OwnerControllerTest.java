package guru.springframework.sfgpetclinic.controllers;

import guru.springframework.sfgpetclinic.fauxspring.BindingResult;
import guru.springframework.sfgpetclinic.fauxspring.Model;
import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.services.OwnerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class OwnerControllerTest {

    private static final String OWNERS_CREATE_OR_UPDATE_OWNER_FORM = "owners/createOrUpdateOwnerForm";
    private static final String REDIRECT_OWNERS_5 = "redirect:/owners/5";

    @Mock
    OwnerService ownerService;

    @InjectMocks
    OwnerController ownerController;

    @Mock
    BindingResult bindingResult;

    @Captor
    ArgumentCaptor<String> stringArgumentCaptor;

    @BeforeEach
    void setUp() {
        given(ownerService.findAllByLastNameLike(stringArgumentCaptor.capture()))
                .willAnswer(invocation -> {
                    List<Owner> owners = new ArrayList<>();
                    String name = invocation.getArgument(0);

                    if (name.equals("%Dupont%")) {
                       owners.add(new Owner(1l, "Jean", "Dupont"));
                       return owners;
                    } else if (name.equals("%DontFindMe%")) {
                        // Empty list
                        return owners;
                    } else if (name.equals("%FindMe%")) {
                        owners.add(new Owner(1l, "Jean", "Dupont"));
                        owners.add(new Owner(2l, "Jean2", "Dupont2"));
                        return owners;
                    }

                    throw new RuntimeException("Invalid Argument");
        });
    }

    @Test
    void processFindFormWildcardStringAnnotationTest() {
        // Given
        Owner owner = new Owner(1l, "Jean", "Dupont");

        // When
        String viewName = ownerController.processFindForm(owner, bindingResult, null);

        // Then
        assertThat("%Dupont%").isEqualToIgnoringCase(stringArgumentCaptor.getValue());
        assertThat("redirect:/owners/1").isEqualToIgnoringCase(viewName);
    }

    @Test
    void processFindFormWildcardNotFoundTest() {
        // Given
        Owner owner = new Owner(2l, "Thomas", "DontFindMe");

        // When
        String viewName = ownerController.processFindForm(owner, bindingResult, null);

        // Then
        assertThat("%DontFindMe%").isEqualToIgnoringCase(stringArgumentCaptor.getValue());
        assertThat("owners/findOwners").isEqualToIgnoringCase(viewName);
    }

    @Test
    void processFindFormWildcardFoundTest() {
        // Given
        Owner owner = new Owner(3l, "Thomas", "FindMe");

        // When
        String viewName = ownerController.processFindForm(owner, bindingResult, Mockito.mock(Model.class));

        // Then
        assertThat("%FindMe%").isEqualToIgnoringCase(stringArgumentCaptor.getValue());
        assertThat("owners/ownersList").isEqualToIgnoringCase(viewName);
    }

    @Test
    void processCreationFormHasErrorsTest() {
        // Given
        Owner owner = new Owner(1l, "Robin", "Ghyselinck");
        given(bindingResult.hasErrors()).willReturn(true);

        // When
        String viewName = ownerController.processCreationForm(owner, bindingResult);

        // Then
        assertThat(viewName).isEqualToIgnoringCase(OWNERS_CREATE_OR_UPDATE_OWNER_FORM);
    }

    @Test
    void processCreationFormHasNoErrorsTest() {
        // Given
        Owner owner = new Owner(5l, "Robin", "Ghyselinck");
        given(bindingResult.hasErrors()).willReturn(false);
        given(ownerService.save(any())).willReturn(owner);

        // When
        String viewName = ownerController.processCreationForm(owner, bindingResult);

        // Then
        assertThat(viewName).isEqualToIgnoringCase(REDIRECT_OWNERS_5);
    }
}