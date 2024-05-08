/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany._rubrica;

import eccezioni.*;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Plona
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
	pagina.setContatto(contatto2, 1);
    }
    
    @Test
    public void testSetContattoPosizioneOccupata() throws Exception
    {
        assertThrows(EccezionePosizioneOccupata.class, () -> pagina.setContatto(contatto1, 0));
    }
    
    @Test
    public void testSetContattoPosizioneNonValida() throws Exception
    {
        assertThrows(EccezionePosizioneNonValida.class, () -> pagina.setContatto(contatto1, -5));
    }

    @Test
    public void testGetContatto() throws Exception
    {
	pagina.setContatto(contatto1, 5);
	assertEquals(contatto1.getidContatto(), pagina.getContatto(5).getidContatto());
	assertEquals(contatto1.getNome(), pagina.getContatto(5).getNome());
	assertEquals(contatto1.getCognome(), pagina.getContatto(5).getCognome());
	assertEquals(contatto1.getGenere(), pagina.getContatto(5).getGenere());
	assertEquals(contatto1.getTelefono(), pagina.getContatto(5).getTelefono());
	assertEquals(contatto1.getMail(), pagina.getContatto(5).getMail());
	assertEquals(contatto1.getDataNascita(), pagina.getContatto(5).getDataNascita());
	assertEquals(contatto1.getEta(), pagina.getContatto(5).getEta());
    }

    @Test
    public void testModificaContatto() throws Exception
    {
	pagina.modificaContatto(0, contatto2);
	assertEquals(contatto2.getidContatto(), pagina.getContatto(0).getidContatto());
	assertEquals(contatto2.getNome(), pagina.getContatto(0).getNome());
	assertEquals(contatto2.getCognome(), pagina.getContatto(0).getCognome());
	assertEquals(contatto2.getGenere(), pagina.getContatto(0).getGenere());
	assertEquals(contatto2.getTelefono(), pagina.getContatto(0).getTelefono());
	assertEquals(contatto2.getMail(), pagina.getContatto(0).getMail());
	assertEquals(contatto2.getDataNascita(), pagina.getContatto(0).getDataNascita());
	assertEquals(contatto2.getEta(), pagina.getContatto(0).getEta());
    }

    @Test
    public void testRimuoviContatto() throws Exception
    {
	pagina.rimuoviContatto(0);
	assertThrows(EccezionePosizioneVuota.class, () -> pagina.getContatto(0));
    }
    
    @Test
    public void testRimuoviContattoPosizioneVuota() throws Exception
    {
	pagina.rimuoviContatto(0);
	assertThrows(EccezionePosizioneVuota.class, () -> pagina.getContatto(8));
    }
    
    @Test
    public void testRimuoviContattoPosizioneNonValida() throws Exception
    {
	pagina.rimuoviContatto(0);
	assertThrows(EccezionePosizioneNonValida.class, () -> pagina.getContatto(-2));
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