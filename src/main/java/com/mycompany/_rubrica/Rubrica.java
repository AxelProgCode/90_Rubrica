/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany._rubrica;

import java.io.Serializable;
import eccezioni.*;
import utilita.*;

/**
 *
 * @author Plona
 */
public class Rubrica implements Serializable
{
    private Pagina[] pagine;
    private final static int NUM_PAGINE=10;
    
    /**
     * Costruttore
     */
    public Rubrica()
    {
	pagine=new Pagina[NUM_PAGINE];
	for(int i=0;i<NUM_PAGINE;i++)
	{
	    pagine[i]=new Pagina();
	}
    }
    
    /**
     * Costruttore di copia
     * @param rubrica 
     */
    public Rubrica(Rubrica rubrica)
    {
	pagine=new Pagina[NUM_PAGINE];
	Contatto cnt;
	for(int i=0;i<rubrica.getNumPagine();i++)
	    pagine[i]=new Pagina();
        for(int i=0;i<rubrica.getNumPagine();i++)
        {
            for(int j=0;j<rubrica.getNumMaxContatti(i);j++)
            {
                try
                {
                    cnt=rubrica.getContatto(i, j);
                    if(cnt!=null)
                        this.setContatto(cnt, i, j);
                }
                catch(EccezionePaginaNonValida e)
                {
                    //non succederà mai
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
    }

    /**
     * Inserisce il contatto nella posizione "posizione" della pagina "pagina".
     * @param contatto
     * @param pagina
     * @param posizione
     */
    public void setContatto(Contatto contatto, int pagina, int posizione) throws EccezionePaginaNonValida, EccezionePosizioneNonValida, EccezionePosizioneOccupata
    {
	if(pagina<0 || pagina>=NUM_PAGINE)
	    throw new EccezionePaginaNonValida();
	pagine[pagina].setContatto(contatto, posizione);
    }
    
    /**
     * Restituisce il contatto nella posizione "posizione" della pagina "pagina".
     * @param pagina
     * @param posizione
     * @return
     * se il contatto è presente --> restituisce l’oggetto contatto
     */
    public Contatto getContatto(int pagina, int posizione) throws EccezionePaginaNonValida, EccezionePosizioneNonValida, EccezionePosizioneVuota
    {
	Contatto cnt;
	if(pagina<0 || pagina>=NUM_PAGINE)
	    throw new EccezionePaginaNonValida();
	cnt=pagine[pagina].getContatto(posizione);
	return cnt;
    }
    
    /**
     * Modifica il contatto in posizione "posizione"
     * @param pagina
     * @param posizione
     * @param nuovoContatto
     * @throws EccezionePaginaNonValida
     * @throws EccezionePosizioneNonValida
     * @throws EccezionePosizioneVuota
     */
    public void modificaContatto(int pagina, int posizione, Contatto nuovoContatto) throws EccezionePaginaNonValida, EccezionePosizioneNonValida, EccezionePosizioneVuota
    {
	if(pagina<0 || pagina >= NUM_PAGINE)
	    throw new EccezionePaginaNonValida();
	pagine[pagina].modificaContatto(posizione, nuovoContatto);
    }
    
    /**
     * Libera la posizione "posizione" della pagina "pagina"
     * @param pagina
     * @param posizione
     */
    public void rimuoviContatto(int pagina, int posizione) throws EccezionePaginaNonValida, EccezionePosizioneNonValida, EccezionePosizioneVuota
    {
	if(pagina<0 || pagina>=NUM_PAGINE)
	    throw new EccezionePaginaNonValida();
	pagine[pagina].rimuoviContatto(posizione);
    }
    
    public int getNumPagine() //Restituisce il numero di pagine presenti nella rubrica
    {
	return NUM_PAGINE;
    }
    public int getNumMaxContatti() //Restituisce il numero massimo di contatti inseribili nella rubrica
    {
	int contaContatti=0;
	for(int i=0;i<NUM_PAGINE;i++)
	    contaContatti+=pagine[i].getNumMaxContatti();
	return contaContatti;
    }
    public int getNumMaxContatti(int pagina) //Restituisce il numero massimo di contatti inseribili nella pagina "pagina"
    {
	if(pagina<0 || pagina>NUM_PAGINE)
	    return -1; //Pagina non valida
	return pagine[pagina].getNumMaxContatti();
    }
    public int getNumContatti() //Restituisce il numero di contatti presenti nella rubrica
    {
	int contaContatti=0;
	for(int i=0;i<NUM_PAGINE;i++)
	    contaContatti+=pagine[i].getNumContatti();
	return contaContatti;
    }
    public int getNumContatti(int pagina) //Restituisce il numero di contatti presenti nella pagina "pagina"
    {
	if(pagina<0 || pagina>NUM_PAGINE)
	    return -1; //Pagina non valida
	return pagine[pagina].getNumContatti();
    }
    
    /**
     * Restituisce tutti i contatti dello stesso genere
     * @param genere
     * @return cognome dei contatti dello stesso genere
     */
    public String[] elencoContattiGenere(String genere)
    {
        //STEP 1: conto quanti contatti del genere "genere" sono presenti
	int contaContattiGenere=0;
	Contatto cnt;
	String[] elencoContattiGenere;
	for(int i=0;i<getNumPagine();i++)
	{
	    for(int j=0;j<pagine[i].getNumMaxContatti();j++)
	    {
		try
                {
                    cnt=getContatto(i, j);
                    if(cnt.getGenere().equals(genere))
			contaContattiGenere++;
                }
                catch(EccezionePaginaNonValida e)
                {
                    //non succederà mai
                }
                catch(EccezionePosizioneNonValida e)
                {
                    //non succederà mai
                }
                catch(EccezionePosizioneVuota e)
                {
                    //non fare nulla
                }
	    }
	}
        if(contaContattiGenere==0)
            return null; //non ci sono contatti di quel genere
    //STEP 2: istanzio l'array con l'elenco dei contatti dello stesso genere
	elencoContattiGenere=new String[contaContattiGenere];
	contaContattiGenere=0;
	for(int i=0;i<getNumPagine();i++)
	{
	    for(int j=0;j<pagine[i].getNumMaxContatti();j++)
	    {
		try
                {
                    cnt=getContatto(i, j);
                    if(cnt.getGenere().equals(genere))
		    {
			elencoContattiGenere[contaContattiGenere]=cnt.getCognome()+cnt.getNome();
			contaContattiGenere++;
		    }
                }
                catch(EccezionePaginaNonValida e)
                {
                    //non succederà mai
                }
                catch(EccezionePosizioneNonValida e)
                {
                    //non succederà mai
                }
                catch(EccezionePosizioneVuota e)
                {
                    //non fare nulla
                }
	    }
	}
	return elencoContattiGenere;
    }
    
    /**
     * Restituisce tutti i contatti con lo stesso nome
     * @param nome
     * @return cognome dei contatti con lo stesso nome
     */
    public String[] elencoContattiNome(String nome)
    {
    //STEP 1: conto quanti contatti col nome "nome" sono presenti
	int contaContattiNome=0;
	Contatto cnt;
	String[] elencoContattiNome;
	for(int i=0;i<getNumPagine();i++)
	{
	    for(int j=0;j<pagine[i].getNumMaxContatti();j++)
	    {
		try
                {
                    cnt=getContatto(i, j);
                    if(cnt.getNome().equals(nome))
			contaContattiNome++;
                }
                catch(EccezionePaginaNonValida e)
                {
                    //non succederà mai
                }
                catch(EccezionePosizioneNonValida e)
                {
                    //non succederà mai
                }
                catch(EccezionePosizioneVuota e)
                {
                    //non fare nulla
                }
	    }
	}
        if(contaContattiNome==0)
            return null; //non ci sono contatti con quel nome
    //STEP 2: istanzio l'array con l'elenco dei contatti con lo stesso nome
	elencoContattiNome=new String[contaContattiNome];
	contaContattiNome=0;
	for(int i=0;i<getNumPagine();i++)
	{
	    for(int j=0;j<pagine[i].getNumMaxContatti();j++)
	    {
		try
                {
                    cnt=getContatto(i, j);
                    if(cnt.getNome().equals(nome))
		    {
			elencoContattiNome[contaContattiNome]=cnt.getCognome();
			contaContattiNome++;
		    }
                }
                catch(EccezionePaginaNonValida e)
                {
                    //non succederà mai
                }
                catch(EccezionePosizioneNonValida e)
                {
                    //non succederà mai
                }
                catch(EccezionePosizioneVuota e)
                {
                    //non fare nulla
                }
	    }
	}
	return elencoContattiNome;
    }
    
    /**
     * Restituisce l'elenco dei contatti ordinati per cognome crescente (A-Z)
     * @return elenco contatti ordinati per cognome crescente
     */
    public Contatto[] elencoContattiOrdinatiPerCognomeAZ()
    {
    //STEP 1: conto quanti contatti sono presenti
	Contatto[] elencoContattiOrdinati=new Contatto[getNumContatti()];
	Contatto cnt;
	int c=0; //contatore
	for(int i=0;i<getNumPagine();i++)
	{
	    for(int j=0;j<getNumMaxContatti(i);j++)
	    {
		try
                {
                    cnt=getContatto(i, j);
                    elencoContattiOrdinati[c]=cnt;
                    c++;
                }
                catch(EccezionePaginaNonValida e)
                {
                    //non succederà mai
                }
                catch(EccezionePosizioneNonValida e)
                {
                    //non succederà mai
                }
                catch(EccezionePosizioneVuota e)
                {
                    //non fare nulla
                }
	    }
	}
    //STEP 2: istanzio l'array con i contatti ordinati
        elencoContattiOrdinati=Ordinatore.alfabeticoCrescente(elencoContattiOrdinati);
	return elencoContattiOrdinati;
    }
    
    /**
     * Restituisce una stringa con tutti i dati dei contatti presenti nella rubrica
     * @return contatti presenti nella rubrica
     */
    public String toString()
    {
	String s="";
	for(int i=0;i<NUM_PAGINE;i++)
	    s+="Pagina"+i+":\n"+pagine[i].toString();
	return s;
    }
}