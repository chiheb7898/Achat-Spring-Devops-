package src.test.java.tn.esprit.rh.achat.services;



import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import tn.esprit.rh.achat.entities.SecteurActivite;
import tn.esprit.rh.achat.repositories.SecteurActiviteRepository;
import tn.esprit.rh.achat.services.ISecteurActiviteService;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SecteurActiviteServiceTest {
	
	
	@Mock
	SecteurActiviteRepository mockAR;
	
	@InjectMocks
	ISecteurActiviteService AS;

	@Test
	@Order(1)
	public void retrieveAllSecteurActiviteReturnsEmptyListWhenDbIsEmpty() {
		List<SecteurActivite> listSecteurActivite = AS.retrieveAllSecteurActivite();
		Assertions.assertEquals(0, listSecteurActivite.size());
	}

	@Test
	@Order(2)
	public void retrieveAllSecteurActiviteMocked() {
		// create mock list
		List<SecteurActivite> mockedList = new ArrayList<SecteurActivite>();
		mockedList.add(new SecteurActivite("1", "food"));
		mockedList.add(new SecteurActivite("2", "sport"));
		mockedList.add(new SecteurActivite("3", "music"));

		Mockito.when(mockAR.findAll()).thenReturn(mockedList);
		List<SecteurActivite> listSecteurActivite = AS.retrieveAllSecteurActivite();
		Assertions.assertEquals(3, listSecteurActivite.size());
	}

	@Test
	@Order(3)
	public void testAddDeleteRetrieveAllWithDb() {
		List<SecteurActivite> addedSecteurActivite = new ArrayList<SecteurActivite>();

		addedSecteurActivite.add(AS.addSecteurActivite(new SecteurActivite("1", "food")));
		addedSecteurActivite.add(AS.addSecteurActivite(new SecteurActivite("2", "sport")));
		addedSecteurActivite.add(AS.addSecteurActivite(new SecteurActivite("3", "music")));

		List<SecteurActivite> listSecteurActivite = AS.retrieveAllSecteurActivite();
		Assertions.assertEquals(3, listSecteurActivite.size());

		for (int i = 0; i < addedSecteurActivite.size(); i++) {
			SecteurActivite secteurActivite = addedSecteurActivite.get(i);
			AS.deleteSecteurActivite(secteurActivite.getIdSecteurActivite());
		}

		listSecteurActivite = AS.retrieveAllSecteurActivite();
		Assertions.assertEquals(0, listSecteurActivite.size());
	}

	@Test
	@Order(4)
	public void testUpdateWithDb() {
		SecteurActivite secteurActivite = AS.addSecteurActivite(new SecteurActivite("1", "food"));
		
		Assertions.assertEquals("1", secteurActivite.getCodeSecteurActivite());
		Assertions.assertEquals("food", secteurActivite.getLibelleSecteurActivite());
		
		secteurActivite.setCodeSecteurActivite("2");
		secteurActivite.setLibelleSecteurActivite("sport");
		
		SecteurActivite updatedSecteurActivite = AS.updateSecteurActivite(secteurActivite);
		
		Assertions.assertEquals(secteurActivite.getIdSecteurActivite(), updatedSecteurActivite.getIdSecteurActivite());
		Assertions.assertEquals("2", updatedSecteurActivite.getCodeSecteurActivite());
		Assertions.assertEquals("sport", updatedSecteurActivite.getLibelleSecteurActivite());
		
		AS.deleteSecteurActivite(updatedSecteurActivite.getIdSecteurActivite());
	}
	
	@Test
	@Order(5)
	public void testRetrieveByIdWithDb() {
		SecteurActivite secteurActivite = AS.addSecteurActivite(new SecteurActivite("1", "food"));
		
		SecteurActivite retrievedSecteurActivite = AS.retrieveSecteurActivite(secteurActivite.getIdSecteurActivite());
		
		Assertions.assertEquals(secteurActivite.getIdSecteurActivite(), retrievedSecteurActivite.getIdSecteurActivite());
		Assertions.assertEquals(secteurActivite.getCodeSecteurActivite(), retrievedSecteurActivite.getCodeSecteurActivite());
		Assertions.assertEquals(secteurActivite.getLibelleSecteurActivite(), retrievedSecteurActivite.getLibelleSecteurActivite());
		
		AS.deleteSecteurActivite(retrievedSecteurActivite.getIdSecteurActivite());
	}
	
	@Test
	@Order(6)
	public void testRetrieveByIdNullIfNotExistWithDb() {
		SecteurActivite retrievedSecteurActivite = AS.retrieveSecteurActivite(5L);
		
		Assertions.assertNull(retrievedSecteurActivite);
	}
}
