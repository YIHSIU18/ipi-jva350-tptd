package com.ipi.jva350.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.LinkedHashSet;

public class SalarieAideADomicileTest {

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
                4,
                15,
                8 );
        // When : Comportement à tester, en pratique une (ou des) méthode(s) à exécuter
        boolean res = monSalarie.aLegalementDroitADesCongesPayes();
        // Then : Comparaison du résultat de la méthode ou de l'état final avec celui attendu
        Assertions.assertEquals(false, res, "avec 4 jours travaillés en N-1 (au moins), le résultat doit être faux ");

    }

    @Test
    public void testCalculeJoursDeCongeDecomptesPourPlage()
    {
        // Given :
        SalarieAideADomicile calcuSalarie = new SalarieAideADomicile();
        //When :
        LinkedHashSet<LocalDate> joursConge = calcuSalarie.calculeJoursDeCongeDecomptesPourPlage(
                LocalDate.of(2023,6,28),
                LocalDate.of(2023,7,31)
        );
        //Then :
        LinkedHashSet<LocalDate> joursCongeAttendus = new LinkedHashSet<>();
        joursCongeAttendus.add(LocalDate.now());
        Assertions.assertEquals(joursCongeAttendus, joursConge);

    }
}
