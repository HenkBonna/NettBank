package oslomet.testing;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import oslomet.testing.API.AdminKontoController;
import oslomet.testing.DAL.AdminRepository;
import oslomet.testing.Models.Konto;
import oslomet.testing.Models.Kunde;
import oslomet.testing.Sikkerhet.Sikkerhet;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class EnhetstestAdminKontoController {

	@InjectMocks
	private AdminKontoController adminKontoController;

	@Mock
	private AdminRepository adminRepository;

	@Mock
	private Sikkerhet sjekk;
	// Brukskonto, Sparekonto, Lønnskonto
	@Test
	public void hentAlleKonti_LoggetInn(){ // 11 siffer i både PersonNr og KontoNr
		// Arrange
		List<Konto> konti = new ArrayList<>();
		Konto konto1 = new Konto("1111111111", "01010110523", 10000.50,
				"Brukskonto", "NOK", null); // Setting transactions to null atm.
		Konto konto2 = new Konto("06049032119", "01010110544", 4000.40,
				"Sparekonto", "NOK", null); // Setting transactions to null atm.
		konti.add(konto1);
		konti.add(konto2);
			// mock stuff below - returning mock (fake) values, instead of actually calling the methods.
		when(sjekk.loggetInn()).thenReturn("01010110523"); // Mock'er sikkerhetskallet, altså ISTEDENFOR å gjøre det metoden skal, returneres heller bare en 'mock' verdi
		when(adminRepository.hentAlleKonti()).thenReturn(konti); // Mock'er sikkerhetskallet, altså ISTEDENFOR å gjøre det metoden skal, returneres heller bare en 'mock' verdi
		// Act
		List<Konto> resultat = adminKontoController.hentAlleKonti();
		// Assert
		assertEquals(konti, resultat);

	}
	@Test
	public void hentAlleKonti_IkkeLoggetInn(){
		// Arrange
		when(sjekk.loggetInn()).thenReturn(null);
		// Act
		List<Konto> resultat = adminKontoController.hentAlleKonti();
		// Assert
		assertNull(resultat);
	}
	/*
	@Test
	public void hentAlleKonti_OK(){ // 11 siffer i både PersonNr og KontoNr
		// Arrange
		List<Konto> konti = new ArrayList<>();
		Konto konto1 = new Konto("06049032119", "01010110523", 10000.50,
				"Brukskonto", "NOK", null); // Setting transactions to null atm.
		Konto konto2 = new Konto("06049032119", "01010110544", 4000.40,
				"Sparekonto", "NOK", null); // Setting transactions to null atm.
		// Act

		// Assert

	}
	@Test
	public void hentAlleKonti_Feil(){
		// Arrange

		// Act

		// Assert

	}
	*/

	@Test
	public void registrerKonto_LoggetInn(){
		// Arrange
		Konto konto1 = new Konto("1111111111", "01010110523", 10000.50,
				"Brukskonto", "NOK", null); // Setting transactions to null atm.

		when(sjekk.loggetInn()).thenReturn("11111111111");
		when(adminRepository.registrerKonto(any(Konto.class))).thenReturn("OK");

		// Act
		String resultat = adminKontoController.registrerKonto(konto1);

		// Assert
		assertEquals("OK", resultat);
	}
	@Test
	public void registrerKonto_IkkeLoggetInn(){
		// Arrange
		when(sjekk.loggetInn()).thenReturn(null);
		// Act
		String resultat = adminKontoController.registrerKonto(null);
		// Assert
		assertEquals("Ikke innlogget",resultat);
	}
	/*
		@Test
	public void registrerKonto_OK(){
		// Arrange

		// Act

		// Assert

	}
	@Test
	public void registrerKonto_Feil(){
		// Arrange

		// Act

		// Assert

	}
	*/
	@Test
	public void endreKonto_LoggetInn(){
		// Arrange
		Konto konto1 = new Konto("1111111111", "01010110523", 10000.50,
				"Brukskonto", "NOK", null); // Setting transactions to null atm.

		when(sjekk.loggetInn()).thenReturn("11111111111");
		when(adminRepository.endreKonto(any(Konto.class))).thenReturn("OK");
		// Act
		String resultat = adminKontoController.endreKonto(konto1);
		// Assert
		assertEquals("OK", resultat);
	}
	@Test
	public void endreKonto_IkkeLoggetInn(){
		// Arrange
		when(sjekk.loggetInn()).thenReturn(null);
		// Act
		String resultat = adminKontoController.endreKonto(null);
		// Assert
		assertEquals("Ikke innlogget", resultat);
	}
	/*
		@Test
	public void endreKonto_OK(){
		// Arrange

		// Act

		// Assert

	}
	@Test
	public void endreKonto_Feil(){
		// Arrange

		// Act

		// Assert

	}
	*/

	@Test
	public void slettKonto_LoggetInn(){
		// Arrange

		when(sjekk.loggetInn()).thenReturn("OK");
		when(adminKontoController.slettKonto("01010110523")).thenReturn("OK"); // Why any(String.class) didn't work I do not know.
		// Act
		String resultat = adminKontoController.slettKonto("01010110523");
		// Assert
		assertEquals("OK", resultat);
	}
	@Test
	public void slettKonto_IkkeLoggetInn(){
		// Arrange
		when(sjekk.loggetInn()).thenReturn(null);
		// Act
		String resultat = adminKontoController.slettKonto(null);
		// Assert
		assertEquals("Ikke innlogget", resultat);
	}
	 /*
	@Test
	public void slettKonto_OK(){
		// Arrange

		// Act

		// Assert

	}
	@Test
	public void slettKonto_Feil(){
		// Arrange

		// Act

		// Assert

	}
	  */




}
