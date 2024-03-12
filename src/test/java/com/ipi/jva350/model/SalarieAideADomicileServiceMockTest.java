package com.ipi.jva350.model;

import com.ipi.jva350.exception.SalarieException;
import com.ipi.jva350.repository.SalarieAideADomicileRepository;
import com.ipi.jva350.service.SalarieAideADomicileService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SalarieAideADomicileServiceMockTest {

    @Mock
    private SalarieAideADomicileRepository salarieAideADomicileRepository;
    @InjectMocks
    private SalarieAideADomicileService salarieService;

    // mocké :
    /*@Test
    void testAjouteConge() throws SalarieException {
        // Given :
        SalarieAideADomicile monSalarie = new SalarieAideADomicile("Paul",
                LocalDate.of(2022, 6, 28), LocalDate.of(2023, 11, 1),
                9, 2.5,
                80, 20, 8);
        // When :
        salarieService.ajouteConge(monSalarie, LocalDate.of(2024, 12, 17),
                LocalDate.of(2024, 12, 18));
        // Then :
        ArgumentCaptor<SalarieAideADomicile> salarieAideADomicileCaptor = ArgumentCaptor.forClass(SalarieAideADomicile.class);
        Mockito.verify(salarieAideADomicileRepository, Mockito.times(1)).save(salarieAideADomicileCaptor.capture()); // arg capture !
        Assertions.assertEquals(1L, salarieAideADomicileCaptor.getValue().getCongesPayesPrisAnneeNMoins1());
    }*/

    //Tests d'intégration de service
    @Test
    void testCalculeLimiteEntrepriseCongesPermis() {

        //Given :
        LocalDate moisEnCours = LocalDate.of(2024, 3, 23);
        double congesPayesAcquisAnneeNMoins1 = 10.0;
        LocalDate moisDebutContrat = LocalDate.of(2023, 1, 1);
        LocalDate premierJourDeConge = LocalDate.of(2024, 1, 1);
        LocalDate dernierJourDeConge = LocalDate.of(2024, 1, 10);
        when(salarieAideADomicileRepository.partCongesPrisTotauxAnneeNMoins1()).thenReturn(1.0);

        //When :
        long limiteConges = salarieService.calculeLimiteEntrepriseCongesPermis(moisEnCours, congesPayesAcquisAnneeNMoins1,
                moisDebutContrat, premierJourDeConge, dernierJourDeConge);

        //Then :
        Assertions.assertEquals(5, limiteConges); // Vous devez adapter cette valeur en fonction de votre logique métier
    }

}
