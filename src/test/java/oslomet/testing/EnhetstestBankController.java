package oslomet.testing;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import oslomet.testing.API.BankController;
import oslomet.testing.DAL.BankRepository;
import oslomet.testing.Models.Konto;
import oslomet.testing.Models.Kunde;
import oslomet.testing.Models.Transaksjon;
import oslomet.testing.Sikkerhet.Sikkerhet;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class EnhetstestBankController {

    @InjectMocks
    // denne skal testes
    private BankController bankController;

    @Mock
    // denne skal Mock'es
    private BankRepository repository;

    @Mock
    // denne skal Mock'es
    private Sikkerhet sjekk;


    @Test
    public void hentKundeInfo_loggetInn() {

        // arrange
        Kunde enKunde = new Kunde("01010110523",
                "Lene", "Jensen", "Askerveien 22", "3270",
                "Asker", "22224444", "HeiHei");

        when(sjekk.loggetInn()).thenReturn("01010110523");

        when(repository.hentKundeInfo(anyString())).thenReturn(enKunde);

        // act
        Kunde resultat = bankController.hentKundeInfo();

        // assert
        assertEquals(enKunde, resultat);
    }

    @Test
    public void hentKundeInfo_loggetInnFeil() {

        when(sjekk.loggetInn()).thenReturn(null);

        Kunde resultat = bankController.hentKundeInfo();

        assertNull(resultat);
    }

    @Test
    public void hentKundeInfo_IkkeloggetInn() {

        // arrange
        when(sjekk.loggetInn()).thenReturn(null);

        //act
        Kunde resultat = bankController.hentKundeInfo();

        // assert
        assertNull(resultat);
    }

    @Test
    public void hentKonti_LoggetInn()  {
        // arrange
        List<Konto> konti = new ArrayList<>();
        Konto konto1 = new Konto("105010123456", "01010110523",
                720, "Lønnskonto", "NOK", null);
        Konto konto2 = new Konto("105010123456", "12345678901",
                1000, "Lønnskonto", "NOK", null);
        konti.add(konto1);
        konti.add(konto2);

        when(sjekk.loggetInn()).thenReturn("01010110523");

        when(repository.hentKonti(anyString())).thenReturn(konti);

        // act
        List<Konto> resultat = bankController.hentKonti();

        // assert
        assertEquals(konti, resultat);
    }

    @Test
    public void hentKonti_IkkeLoggetInn()  {
        // arrange

        when(sjekk.loggetInn()).thenReturn(null);

        // act
        List<Konto> resultat = bankController.hentKonti();

        // assert
        assertNull(resultat);
    }

    @Test
    public void hentTransaksjoner_LoggetInn(){
        Konto enKonti = new Konto();
        when(sjekk.loggetInn()).thenReturn("01010110523");
        when(repository.hentTransaksjoner("01010110523", "2020-12-24", "2021-01-01")).thenReturn(enKonti);
        Konto resultat = bankController.hentTransaksjoner("01010110523", "2020-12-24", "2021-01-01");
        assertEquals(enKonti, resultat);
    }

    @Test
    public void hentTransaksjoner_LoggetInnFeil(){
        when(sjekk.loggetInn()).thenReturn("01010110523");

        Konto resultat = bankController.hentTransaksjoner("01010110523", "2020-12-24", "2021-01-01");

        assertNull(resultat);
    }

    @Test
    public void hentTransaksjoner_IkkeLoggetInn(){
        when(sjekk.loggetInn()).thenReturn(null);

        Konto resultat = bankController.hentTransaksjoner("01010110523", "2020-01-01", "2020-01-01");

        assertNull(resultat);
    }

    @Test
    public void hentSaldi_LoggetInn(){
        List<Konto> enListe = new ArrayList<>();

        Konto konto1 = new Konto("105010123456", "01010110523",
                720, "Lønnskonto", "NOK", null);
        Konto konto2 = new Konto("105010123456", "12345678901",
                1000, "Lønnskonto", "NOK", null);

        enListe.add(konto1);
        enListe.add(konto2);

        when(sjekk.loggetInn()).thenReturn("01010110523");

        when(repository.hentSaldi(anyString())).thenReturn(enListe);

        List<Konto> resultat = bankController.hentSaldi();

        assertEquals(enListe, resultat);
    }

    @Test
    public void hentSaldi_IkkeLoggetInn(){
        when(sjekk.loggetInn()).thenReturn(null);

        List<Konto> resultat = bankController.hentSaldi();

        assertNull(resultat);
    }

    @Test
    public void registrerBetaling_LoggetInn(){
        Transaksjon t = new Transaksjon();

        when(sjekk.loggetInn()).thenReturn("01010110523");

        when(repository.registrerBetaling(t)).thenReturn("OK");

        String resultat = bankController.registrerBetaling(t);

        assertEquals("OK", resultat);
    }

    @Test
    public void registrerBetaling_IkkeLoggetInn(){
        Transaksjon t = new Transaksjon();

        when(sjekk.loggetInn()).thenReturn(null);

        String resultat = bankController.registrerBetaling(t);

        assertNull(resultat);
    }

    @Test
    public void hentBetalinger_LoggetInn(){
        List<Transaksjon> enListe = new ArrayList<>();

        Transaksjon t1 = new Transaksjon();
        Transaksjon t2 = new Transaksjon();

        enListe.add(t1);
        enListe.add(t2);

        when(sjekk.loggetInn()).thenReturn("01010110523");

        when(repository.hentBetalinger("01010110523")).thenReturn(enListe);

        List<Transaksjon> resultat = bankController.hentBetalinger();

        assertEquals(enListe, resultat);
    }

    @Test
    public void hentBetalinger_IkkeLoggetInn(){
        when(sjekk.loggetInn()).thenReturn(null);

        List<Transaksjon> resultat = bankController.hentBetalinger();

        assertNull(resultat);
    }

    @Test
    public void utforBetaling_LoggetInn(){
        List<Transaksjon> enListe = new ArrayList<>();

        Transaksjon t1 = new Transaksjon();
        Transaksjon t2 = new Transaksjon();

        enListe.add(t1);
        enListe.add(t2);

        when(sjekk.loggetInn()).thenReturn("01010110523");

        when(repository.utforBetaling(2)).thenReturn("OK");

        when(repository.hentBetalinger("01010110523")).thenReturn(enListe);

        List<Transaksjon> resultat = bankController.utforBetaling(2);

        assertEquals(enListe, resultat);
    }

    @Test
    public void utforBetaling_LoggetInnFeil(){
        when(sjekk.loggetInn()).thenReturn("01010110523");

        when(repository.utforBetaling(2)).thenReturn("Feil");

        List<Transaksjon> resultat = bankController.utforBetaling(2);

        assertNull(resultat);
    }

    @Test
    public void utforBetaling_LoggetInnFeil2(){
        when(sjekk.loggetInn()).thenReturn("01010110523");

        when(repository.utforBetaling(2)).thenReturn("OK");

        when(repository.hentBetalinger("01010110523")).thenReturn(null);

        List<Transaksjon> resultat = bankController.utforBetaling(2);

        assertNull(resultat);
    }

    @Test
    public void utforBetaling_IkkeLoggetInn(){
        when(sjekk.loggetInn()).thenReturn(null);

        List<Transaksjon> resultat = bankController.utforBetaling(1);

        assertNull(resultat);
    }

    @Test
    public void endre_LoggetInn(){
        Kunde enKunde = new Kunde("01010110523",
                "Lene", "Jensen", "Askerveien 22", "3270",
                "Asker", "22224444", "HeiHei");
        when(sjekk.loggetInn()).thenReturn("01010110523");
        when(repository.endreKundeInfo(enKunde)).thenReturn("OK");

        String resultat = bankController.endre(enKunde);

        assertEquals("OK", resultat);
    }

    @Test
    public void endre_LoggetInnFeil(){
        when(sjekk.loggetInn()).thenReturn(null);

        String resultat = bankController.endre(null);

        assertNull(resultat);

    }

    @Test
    public void endre_IkkeLoggetInn(){
        Kunde enKunde = new Kunde("01010110523",
                "Lene", "Jensen", "Askerveien 22", "3270",
                "Asker", "22224444", "HeiHei");

        when(sjekk.loggetInn()).thenReturn(null);

        String resultat = bankController.endre(enKunde);

        assertNull(resultat);
    }
}

