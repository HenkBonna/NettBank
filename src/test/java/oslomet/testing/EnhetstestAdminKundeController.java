package oslomet.testing;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import oslomet.testing.API.AdminKundeController;

import oslomet.testing.DAL.AdminRepository;

import oslomet.testing.Models.Kunde;
import oslomet.testing.Sikkerhet.Sikkerhet;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class EnhetstestAdminKundeController {


    @InjectMocks
    private AdminKundeController adminKundeController;

    @Mock
    private AdminRepository adminRepository;

    @Mock
    private Sikkerhet sjekk;

    @Test
    public void hentAlle_loggetInn() {
        //arrange
        Kunde kunde1 = new Kunde("1234567910", "Hans", "Hansen", "Hansensvei 1", "1111", "Hansbydga", "12345678", "pass");
        Kunde kunde2 = new Kunde("1234567911", "Line", "Hansen", "Linessvei 1", "2222", "Linebydga", "11121314", "pass123");
        ArrayList<Kunde> kundeliste = new ArrayList<>();
        kundeliste.add(kunde1);
        kundeliste.add(kunde2);

        when(sjekk.loggetInn()).thenReturn("Admin");
        Mockito.when(adminRepository.hentAlleKunder()).thenReturn(kundeliste);


        //act
        List<Kunde> resultat = adminKundeController.hentAlle();

        //assert
        assertEquals(kundeliste,resultat);

    }

    @Test
    public void hentAlle_ikkeLoggetInn() {
        //arrange
        when(sjekk.loggetInn()).thenReturn(null);

        //act
        List<Kunde> resultat = adminKundeController.hentAlle();

        //assert
        assertNull(resultat);
    }

    @Test
    public void lagreKunde_loggetInn() {
        //arrange
        Kunde kunde = new Kunde("1234567910", "Hans", "Hansen", "Hansensvei 1", "1111", "Hansbydga", "12345678", "pass");
        when(sjekk.loggetInn()).thenReturn("Admin");
        Mockito.when(adminRepository.registrerKunde(kunde)).thenReturn("OK");


        //act
        String resultat = adminKundeController.lagreKunde(kunde);
        System.out.println(resultat);

        //assert
        assertEquals("OK", resultat);

    }

    @Test
    public void lagreKunde_ikkeLoggetInn() {
        //arrange
        Kunde kunde = new Kunde("1234567910", "Hans", "Hansen", "Hansensvei 1", "1111", "Hansbydga", "12345678", "pass");
        when(sjekk.loggetInn()).thenReturn(null);

        //act
        String resultat = adminKundeController.lagreKunde(kunde);

        //assert
        assertEquals("Ikke logget inn", resultat);

    }



}
