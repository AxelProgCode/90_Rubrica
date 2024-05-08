/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany._rubrica;

import java.io.Serializable;
import eccezioni.*;

/**
 *
 * @author Plona
 */
public class Pagina implements Serializable
{
    private Contatto[] contatti;
    private final static int NUM_MAX_CONTATTI=10;
    
    /**
     * Costruttore
     */
    public Pagina()
    {
	contatti=new Contatto[NUM_MAX_CONTATTI];
    }
    
    /**
     * Costruttore di copia
     * @param pagina
     */
    public Pagina(Pagina pagina)
    {
	Contatto cnt;
	contatti=new Contatto[NUM_MAX_CONTATTI];
	for(int i=0;i<NUM_MAX_CONTATTI;i++)
	{
	    try
            {
                cnt=pagina.getContatto(i);
                if(cnt!=null)
                    this.setContatto(cnt, i);
            }
	    catch(EccezionePosizioneNonValida e)
            {
                //non succederà mai
            }
            catch(EccezionePosizioneVuota e)
            {
                //non fare nulla
            }
            catch(EccezionePosizioneOccupata e)
            {
                //non succederà mai
            }
	}
    }
    
    /**
     * Nuovo costruttore
     * Restituisco una pagina con al massimo 15 contatti dall'array elencoContatti
     * @param elencoContatti
     */
    public Pagina(Contatto[] elencoContatti)
    {
	contatti=new Contatto[NUM_MAX_CONTATTI];
	int numeroContatti;
	//se i contatti da aggiungere sono troppi aggiungo solo NUM_MAX_CONTATTI
        numeroContatti=elencoContatti.length;
        if(numeroContatti>NUM_MAX_CONTATTI)
            numeroContatti=NUM_MAX_CONTATTI;
        //Copio l'i-esimo contatto dell'array
        for(int i=0;i<numeroContatti;i++)
        {
            if(elencoContatti[i]!=null)
               try
               {
                   setContatto(elencoContatti[i], i);
               } 
               catch(EccezionePosizioneNonValida e)
               {
                   //non succederà mai
               }
               catch(EccezionePosizioneOccupata e)
               {
                   //non succederà mai
               }
        }
    }
    
    /**
     * Aggiunge il contatto alla pagina in posizione "posizione"
     * @param contatto
     * @param posizione
     */
    public void setContatto(Contatto contatto, int posizione) throws EccezionePosizioneNonValida, EccezionePosizioneOccupata
    {
	if(posizione<0 || posizione>=NUM_MAX_CONTATTI)
	    throw new EccezionePosizioneNonValida();
	if(contatti[posizione]!=null)
	    throw new EccezionePosizioneOccupata();
	contatti[posizione]=new Contatto(contatto);
    }
    
    /**
     * Restituisce il contatto della pagina in posizione "posizione"
     * @param posizione
     * @return
     * se la posizione non esiste --> return null
     * se la posizione è vuota --> return null
     * se la posizione è occupata --> return contatto
     */
    public Contatto getContatto(int posizione) throws EccezionePosizioneNonValida, EccezionePosizioneVuota
    {
	Contatto cnt;
	if(posizione<0 || posizione>=NUM_MAX_CONTATTI)
	    throw new EccezionePosizioneNonValida();
	if(contatti[posizione]==null)
	    throw  new EccezionePosizioneVuota();
        cnt=contatti[posizione];
            return new Contatto(cnt); //restituisco una copia del contatto
    }
    
    /**
     * Modifica il contatto in posizione "posizione"
     * @param posizione
     * @param nuovoContatto
     * @throws EccezionePosizioneNonValida
     * @throws EccezionePosizioneVuota
     */
    public void modificaContatto(int posizione, Contatto nuovoContatto) throws EccezionePosizioneNonValida, EccezionePosizioneVuota
    {
	if(posizione<0 || posizione>=NUM_MAX_CONTATTI)
	    throw new EccezionePosizioneNonValida();
	if(contatti[posizione]==null)
	    throw new EccezionePosizioneVuota();
	contatti[posizione]=nuovoContatto;
    }
    
    /**
     * Libera la posizione "posizione"
     * @param posizione
     */
    public void rimuoviContatto(int posizione) throws EccezionePosizioneNonValida, EccezionePosizioneVuota
    {
	if(posizione<0 || posizione>=NUM_MAX_CONTATTI)
	    throw new EccezionePosizioneNonValida();
	if(contatti[posizione]==null)
	    throw new EccezionePosizioneVuota();
	contatti[posizione]=null;
    }
    
    public int getNumMaxContatti()
    {
	return NUM_MAX_CONTATTI;
    }
    
    /**
     * Restituisce il numero di contatti presenti nella pagina
     * @return
     */
    public int getNumContatti()
    {
	int numeroContattiPresenti=0;
	for(int i=0;i<NUM_MAX_CONTATTI;i++)
	{
	    if(contatti[i]!=null)
		numeroContattiPresenti++;
	}
	return numeroContattiPresenti;
    }
    
    /**
     * Restituisce true se esiste un contatto con lo stesso cognome
     * @param cognome
     * @return
     * se presente --> true
     * se assente --> false
     */
    public boolean presenzaCognome(String cognome)
    {
	for(int i=0;i<NUM_MAX_CONTATTI;i++)
	{
	    if(contatti[i]!=null)
	    {
		if(contatti[i].getCognome().equals(cognome))
		    return true;
	    }
	}
	return false;
    }
    
    /**
     * Restituisce una stringa con tutti i dati dei contatti presenti nella pagina
     * @return contatti presenti nella pagina
     */
    public String toString()
    {
	String s="";
	for(int i=0;i<NUM_MAX_CONTATTI;i++)
	{
	    if(contatti[i]==null)
		s+=i+"-->\n";
	    else
		s+=i+"-->"+contatti[i].toString()+"\n";
	}
	return s;
    }
}