/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany._rubrica;

import java.time.LocalDate;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Plona
 */
public class ContattoTest
{
    private Contatto contatto;

    @BeforeEach
    public void setUp()
    {
        contatto = new Contatto("Rossi", "Mario", "M", "1234567890", "mario.rossi@email.com", "2000-01-01");
    }

    @Test
    public void testCostruttore() {
        assertEquals("Rossi", contatto.getCognome());
        assertEquals("Mario", contatto.getNome());
        assertEquals("M", contatto.getGenere());
        assertEquals("1234567890", contatto.getTelefono());
        assertEquals("mario.rossi@email.com", contatto.getMail());
        assertEquals("2000-01-01", contatto.getDataNascita());
    }

    @Test
    public void testSetCognome() {
        contatto.setCognome("Bianchi");
        assertEquals("Bianchi", contatto.getCognome());
    }

    @Test
    public void testSetNome() {
        contatto.setNome("Giuseppe");
        assertEquals("Giuseppe", contatto.getNome());
    }

    @Test
    public void testSetGenere() {
        contatto.setGenere("F");
        assertEquals("F", contatto.getGenere());
    }

    @Test
    public void testSetTelefono() {
        contatto.setTelefono("0987654321");
        assertEquals("0987654321", contatto.getTelefono());
    }

    @Test
    public void testSetMail() {
        contatto.setMail("giuseppe.bianchi@email.com");
        assertEquals("giuseppe.bianchi@email.com", contatto.getMail());
    }

    @Test
    public void testSetDataNascita() {
        contatto.setDataNascita("1900-01-01");
        assertEquals("1900-01-01", contatto.getDataNascita());
    }

    @Test
    public void testGetCognome() {
        assertEquals("Rossi", contatto.getCognome());
    }

    @Test
    public void testGetNome() {
        assertEquals("Mario", contatto.getNome());
    }

    @Test
    public void testGetGenere() {
        assertEquals("M", contatto.getGenere());
    }

    @Test
    public void testGetTelefono() {
        assertEquals("1234567890", contatto.getTelefono());
    }

    @Test
    public void testGetMail() {
        assertEquals("mario.rossi@email.com", contatto.getMail());
    }

    @Test
    public void testGetDataNascita() {
        assertEquals("2000-01-01", contatto.getDataNascita());
    }

    @Test
    public void testGetEta() {
        assertEquals(24, contatto.getEta());
    }
    
    @Test
    public void testCalcolaEtaCorretta() {
	LocalDate dataNascitaPassata = LocalDate.of(2006, 1, 1);
	Contatto contattoPassato = new Contatto("Rossi", "Mario", "M", "1234567890", "mario.rossi@email.com", dataNascitaPassata.toString());
	assertEquals(18, contattoPassato.getEta());

	LocalDate dataNascitaRecente = LocalDate.of(2000, 1, 1);
	Contatto contattoRecente = new Contatto("Bianchi", "Marco", "M", "0987654321", "marco.bianchi@email.com", dataNascitaRecente.toString());
	assertEquals(24, contattoRecente.getEta());
    }
}