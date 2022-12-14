package tn.esprit.rh.achat.services;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import tn.esprit.rh.achat.entities.Operateur;
import tn.esprit.rh.achat.repositories.OperateurRepository;
import tn.esprit.rh.achat.services.OperateurServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class OperateurServiceImplMockTest {
    @InjectMocks
    OperateurServiceImpl operateurService;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this); //without this you will get NPE
    }

    @Mock
    OperateurRepository operateurRepository;


    Operateur o = new Operateur((long)1,"nom","prenom","password",null);
    Operateur o1 = new Operateur((long)2,"nom","prenom","password",null);
    Operateur o2 = new Operateur((long)3,"nom","prenom","password",null);
    List<Operateur> operateur = new ArrayList<Operateur>() {/**
     *
     */
    private static final long serialVersionUID = 1L;

        {add(o1); add(o2);}};



    @Test
    public void testGetOperateur() {
        Mockito.when(operateurRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(o));
        assertNotNull(operateurService.retrieveOperateur((long)3));
    }

    @Test
    public void testaddOperateur() {
        Mockito.when(operateurRepository.save(Mockito.any(Operateur.class))).thenReturn(o);
        assertNotNull(operateurService.addOperateur(o));
    }

    @Test
    public void testUpdateOperateur() {


        Mockito.when(operateurRepository.save(Mockito.any(Operateur.class))).thenReturn(o);
        o.setNom("Updated Libelle");
        assertNotNull(operateurService.updateOperateur(o));
        assertEquals("Updated Libelle", o.getNom());
    }


    @Test
    public void testDeleteOperateur() {
        operateurService.deleteOperateur((long)3);
        verify(operateurRepository).deleteById((long)3);
    }
}
