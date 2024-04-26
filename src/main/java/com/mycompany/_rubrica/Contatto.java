/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany._rubrica;

import java.io.Serializable;
import java.time.*;

/**
 * 
 * @author Plona
 */
public class Contatto implements Serializable
{
    private int idContatto;
    private static int nextId=1;
    private String cognome;
    private String nome;
    private String genere;
    private String telefono;
    private String mail;
    private String dataNascita;
    private int eta;
    
    /**
     * Costruttore
     * @param cognome
     * @param nome
     * @param telefono
     * @param mail
     * @param dataNascita
     */
    public Contatto(String cognome, String nome, String genere, String telefono, String mail, String dataNascita)
    {
	this.idContatto=nextId;
	nextId++;
	setCognome(cognome);
	setNome(nome);
        setGenere(genere);
	setTelefono(telefono);
        setMail(mail);
        setDataNascita(dataNascita);
    }
    
    /**
     * Costruttore di copia
     * @param contatto
     */
    public Contatto(Contatto contatto)
    {
	this.idContatto=contatto.getidContatto();
	this.cognome=contatto.getCognome();
	this.nome=contatto.getNome();
        this.genere=contatto.getGenere();
	this.telefono=contatto.getTelefono();
        this.mail=contatto.getMail();
        this.dataNascita=contatto.getDataNascita();
    }
    
    public void setCognome(String cognome)
    {
	this.cognome=cognome;
    }
    public void setNome(String nome)
    {
	this.nome=nome;
    }
    public void setGenere(String genere)
    {
        this.genere=genere;
    }
    public void setTelefono(String telefono)
    {
	this.telefono=telefono;
    }
    public void setMail(String mail)
    {
        this.mail = mail;
    }
    public void setDataNascita(String dataNascita)
    {
	this.dataNascita=dataNascita;
    }

    public int getidContatto()
    {
	return idContatto;
    }
    public String getCognome()
    {
	return cognome;
    }
    public String getNome()
    {
	return nome;
    }
    public String getGenere()
    {
        return genere;
    }
    public String getTelefono()
    {
	return telefono;
    }
    public String getMail()
    {
	return mail;
    }
    public String getDataNascita()
    {
	return dataNascita;
    }
    public int getEta()
    {
        LocalDate dataNascita=LocalDate.parse(this.dataNascita);
        LocalDate oggi=LocalDate.now();
        return calcolaEta(dataNascita, oggi);
    }

    /**
     * Calcola l'età di un contatto
     * @param dataNascita
     * @param oggi
     * @return 
     */
    public int calcolaEta(LocalDate dataNascita, LocalDate oggi)
    {
        return Period.between(dataNascita, oggi).getYears();
    }
    
    /**
     * Restituisce una stringa con tutti i valori degli attributi di un contatto
     * @return valore di ogni attributo
     */
    public String toString()
    {
	String s="\tID: "+getidContatto()+"\n\tCognome: "+getCognome()+"\n\tNome: "+getNome()+"\n\tGenere: "+getGenere()+"\n\tTelefono: "+getTelefono()+"\n\tMail: "+getMail()+"\n\tData di nascita: "+getDataNascita()+"\n\tEtà: "+getEta();
	return s;
    }
}