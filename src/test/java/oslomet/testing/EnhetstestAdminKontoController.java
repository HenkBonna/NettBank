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

import javax.sql.DataSource;
import javax.xml.crypto.Data;
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

	@Test
	public void hentAlleKonti_LoggetInn_Ok(){
		// Arrange
		List<Konto> konti = new ArrayList<>();
		Konto konto1 = new Konto("1111111111", "01010110523", 10000.50,
				"Brukskonto", "NOK", null); // Setting transactions to null atm.
		Konto konto2 = new Konto("06049032119", "01010110544", 4000.40,
				"Sparekonto", "NOK", null); // Setting transactions to null atm.
		konti.add(konto1);
		konti.add(konto2);

		when(sjekk.loggetInn()).thenReturn("01010110523");
		when(adminRepository.hentAlleKonti()).thenReturn(konti);
		// Act
		List<Konto> resultat = adminKontoController.hentAlleKonti();
		// Assert
		assertEquals(konti, resultat);

	}
	@Test
	public void hentAlleKonti_loggetInn_Feil(){
		// Arrange
		List<Konto> konti = new ArrayList<>();
		Konto konto1 = new Konto("1111111111", "01010110523", 10000.50,
				"Brukskonto", "NOK", null); // Setting transactions to null atm.
		Konto konto2 = new Konto("06049032119", "01010110544", 4000.40,
				"Sparekonto", "NOK", null); // Setting transactions to null atm.
		konti.add(konto1);
		konti.add(konto2);
		// Mock stuff below - returning mock (fake) values, instead of actually calling the methods.
		when(sjekk.loggetInn()).thenReturn("01010110523"); // Mock'er sikkerhetskallet, altså ISTEDENFOR å gjøre det metoden skal, returneres heller bare en 'mock' verdi
		when(adminRepository.hentAlleKonti()).thenReturn(null); // Mock'er sikkerhetskallet, altså ISTEDENFOR å gjøre det metoden skal, returneres heller bare en 'mock' verdi
		// Act
		List<Konto> resultat = adminKontoController.hentAlleKonti();
		// Assert
		assertNull(resultat);
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

	@Test
	public void registrerKonto_LoggetInn_Ok(){
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
	public void registrerKonto_LoggetInn_Feil(){
		// Arrange
		Konto konto1 = new Konto("1111111111", "01010110523", 10000.50,
				"Brukskonto", "NOK", null); // Setting transactions to null atm.

		when(sjekk.loggetInn()).thenReturn("11111111111");
		when(adminRepository.registrerKonto(any(Konto.class))).thenReturn(null);

		// Act
		String resultat = adminKontoController.registrerKonto(konto1);

		// Assert
		assertNull(null);
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
	@Test
	public void endreKonto_LoggetInn_Ok(){
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
	public void endreKonto_LoggetInn_Feil(){
		// Arrange
		Konto konto1 = new Konto("1111111111", "01010110523", 10000.50,
				"Brukskonto", "NOK", null); // Setting transactions to null atm.

		when(sjekk.loggetInn()).thenReturn("11111111111");
		when(adminRepository.endreKonto(any(Konto.class))).thenReturn(null);
		// Act
		String resultat = adminKontoController.endreKonto(konto1);
		// Assert
		assertNull(resultat);
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

	@Test
	public void slettKonto_LoggetInn_Ok(){
		// Arrange
		when(sjekk.loggetInn()).thenReturn("OK");
		when(adminKontoController.slettKonto("01010110523")).thenReturn("OK");
		// Act
		String resultat = adminKontoController.slettKonto("01010110523");
		// Assert
		assertEquals("OK", resultat);
	}
	@Test
	public void slettKonto_LoggetInn_Feil(){
		// Arrange
		when(sjekk.loggetInn()).thenReturn("OK");
		when(adminKontoController.slettKonto("01010110523")).thenReturn(null);
		// Act
		String resultat = adminKontoController.slettKonto("01010110523");
		// Assert
		assertNull(resultat);
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

	@Test
	public void initDB_OK() {
		// Arrange
		when(adminKontoController.initDB()).thenReturn("OK");

		// Act
		String result = adminRepository.initDB(any(DataSource.class));

		// Assert
		assertEquals("OK", result);
	}

	@Test
	public void initDB_Feil() {
		// Arrange
		when(adminKontoController.initDB()).thenReturn("Feil");

		// Act
		String result = adminRepository.initDB(null);

		// Assert
		assertEquals("Feil", result);
	}
}
