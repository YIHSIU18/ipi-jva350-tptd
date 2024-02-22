package com.ipi.jva350.model;

import com.ipi.jva350.exception.SalarieException;
import com.ipi.jva350.repository.SalarieAideADomicileRepository;
import com.ipi.jva350.service.SalarieAideADomicileService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.LinkedHashSet;

public class SalarieAideADomicileTest {

    @InjectMocks
    private SalarieAideADomicileService salarieService = new SalarieAideADomicileService();
    @Mock
    private SalarieAideADomicileRepository salarieAideADomicileRepository;
    //Tests unitaires simples
    @Test
    public void testALegalementDroitADesCongesPayesValue()
    {
        // Given :
        SalarieAideADomicile monSalarie = new SalarieAideADomicile("Toto",
                LocalDate.of(2023,6,28),
                LocalDate.now(),
                20,
                2.5,
                120,
                15,
                8 );
        // When : Comportement à tester, en pratique une (ou des) méthode(s) à exécuter
        boolean res = monSalarie.aLegalementDroitADesCongesPayes();
        // Then : Comparaison du résultat de la méthode ou de l'état final avec celui attendu
        Assertions.assertEquals(true, res);

    }
    @Test
    public void testALegalementDroitADesCongesPayesTrue()
    {
        // Given :
        SalarieAideADomicile monSalarie = new SalarieAideADomicile("Toto",
                LocalDate.of(2023,6,28),
                LocalDate.now(),
                20,
                2.5,
                10,
                1,
                8 );
        // When : Comportement à tester, en pratique une (ou des) méthode(s) à exécuter
        boolean res = monSalarie.aLegalementDroitADesCongesPayes();
        // Then : Comparaison du résultat de la méthode ou de l'état final avec celui attendu
        Assertions.assertEquals(true, res, "avec 10 jours travaillés en N-1 (au moins), le résultat doit être true ");

    }

    @Test
    public void testALegalementDroitADesCongesPayesFalse()
    {
        // Given :
        SalarieAideADomicile monSalarie = new SalarieAideADomicile("Toto",
                LocalDate.of(2023,6,28),
                LocalDate.now(),
                20,
                2.5,
                9,
                1,
                8 );
        // When : Comportement à tester, en pratique une (ou des) méthode(s) à exécuter
        boolean res = monSalarie.aLegalementDroitADesCongesPayes();
        // Then : Comparaison du résultat de la méthode ou de l'état final avec celui attendu
        Assertions.assertEquals(false, res, "avec 9 jours travaillés en N-1 (au moins), le résultat doit être faux ");

    }

    @ParameterizedTest
    @CsvSource({
            "'2023-12-17', '2023-12-28', 9",
            "'2023-12-17', '2024-01-08', 17"
    })

    void testCalculeJoursDeCongeDecomptesPourPlage(String dateDebut, String dateFin, int expectedNb)
    {
        // Given :
        SalarieAideADomicile monSalarie = new SalarieAideADomicile("Toto",
                LocalDate.of(2023,6,28),
                LocalDate.now(),
                20,
                2.5,
                9,
                1,
                8 );
        //When :
        LinkedHashSet<LocalDate> resNb = monSalarie.calculeJoursDeCongeDecomptesPourPlage
                (
                    LocalDate.parse(dateDebut),
                    LocalDate.parse(dateFin)
                );
        //Then :
        Assertions.assertEquals(expectedNb, resNb.size());
    }

    //Utiliser Mocks sous Junit 5
    //Tests avec mocks
    //mocké :
    @Test
    public void testAjouteConge() throws SalarieException
    {
        SalarieAideADomicileService salarieService;
        // Given :
        SalarieAideADomicile monSalarie = new SalarieAideADomicile("Toto",
                LocalDate.of(2023,6,28),
                LocalDate.now(),
                20,
                2.5,
                200,
                12,
                8 );
        salarieService.ajouteConge(monSalarie, LocalDate.of(2024,12,17),
                LocalDate.of(2024,12,12));
        //Then :
        ArgumentCaptor<SalarieAideADomicile> salarieAideADomicileArgumentCaptor = ArgumentCaptor.forClass(SalarieAideADomicile.class);
        Mockito.verify(salarieAideADomicileRepository, Mockito.times(1).save(salarieAideADomicileArgumentCaptor.capture()));
        Assertions.assertEquals(salarieAideADomicileArgumentCaptor.getValue().getCongesPayesRestantAnneeNMoins1(), 1L);

    }



}
