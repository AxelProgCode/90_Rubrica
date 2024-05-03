/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany._rubrica;

import eccezioni.EccezionePosizioneNonValida;
import eccezioni.EccezionePosizioneOccupata;
import java.time.LocalDate;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Studente
 */
public class PaginaTest
{
    private Pagina pagina;
    private Contatto contatto1;
    private Contatto contatto2;

    @BeforeEach
    public void setUp() throws EccezionePosizioneNonValida, EccezionePosizioneOccupata
    {
        pagina = new Pagina();
        contatto1 = new Contatto("Rossi", "Mario", "M", "1234567890", "mario.rossi@email.com", "2000-01-01");
        contatto2 = new Contatto("Bianchi", "Luigi", "M", "0987654321", "luigi.bianchi@email.com", "1990-01-01");
	pagina.setContatto(contatto1, 0);
    }

    @Test
    public void testSetContatto() throws Exception
    {
	pagina.setContatto(contatto1, 0);
        assertEquals(contatto1, pagina.getContatto(0));
    }

    @Test
    public void testGetContatto() throws Exception
    {
	assertEquals(contatto1, pagina.getContatto(0));
    }

    @Test
    public void testModificaContatto() throws Exception
    {
	pagina.modificaContatto(0, contatto2);
	assertEquals(contatto2, pagina.getContatto(0));
    }

    @Test
    public void testRimuoviContatto() throws Exception
    {
	pagina.rimuoviContatto(0);
	assertEquals(null, pagina.getContatto(0));
    }

    @Test
    public void testGetNumMaxContatti()
    {
	assertEquals(10, pagina.getNumMaxContatti());
    }

    @Test
    public void testGetNumContatti()
    {
	assertEquals(2, pagina.getNumContatti());
    }
}