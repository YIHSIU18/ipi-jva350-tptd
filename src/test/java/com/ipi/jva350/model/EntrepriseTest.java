package com.ipi.jva350.model;

import com.ipi.jva350.model.Entreprise;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

public class EntrepriseTest {
    @Test
    public void testEstDansPlage() {

        Entreprise ep = new Entreprise();
        // Given :
        Boolean res = ep.estDansPlage(LocalDate.of(2023,8,1),
                LocalDate.of(2023,5,12)
                , LocalDate.of(2023,10,16));
        //Then :
        Assertions.assertEquals(true, res);


    }

    //Entreprise.estJourFerie()
    @Test
    public void testEstJourFerie()
    {
        Entreprise ep = new Entreprise();
        //Given :
        Boolean res = ep.estJourFerie(LocalDate.of(2024,5,1 ));
        //Then :
        Assertions.assertEquals(true, res);

    }
}
