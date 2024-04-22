/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany._rubrica;

import java.io.*;
import utilita.*;
import eccezioni.*;

/**
 *
 * @author Plona
 */
public class App
{
    public static void main(String[] args)
    {
	int voceMenuScelta;
	ConsoleInput tastiera=new ConsoleInput();
	Rubrica r1=new Rubrica();
        String cognome,nome,genere,telefono,mail,dataNascita,datiContatto;
	String[] elencoContattiNome,elencoContattiGenere;
        Contatto[] elencoContattiOrdinatiAlfabeticamente;
        Contatto cnt;
	TextFile f1;
        String nomeFileCSV="Contatti.csv";
	String nomeFileBIN="Rubrica.bin";
        String nomeFileTXT="Rubrica.txt";
        int pagina,posizione;
        
        try
        {
            System.out.print("Ciao! Chi sei? ");
            String utente=tastiera.readString();
            if(utente.equals("Admin"))
            {
                int numeroVociMenu=14;
                String[] vociMenu=new String[numeroVociMenu];
                vociMenu[0]="0 --> Esci";
                vociMenu[1]="1 --> Visualizza tutti i contatti della rubrica";
                vociMenu[2]="2 --> Aggiungi contatto (pagina, posizione)";
                vociMenu[3]="3 --> Cerca contatto (pagina, posizione)";
                vociMenu[4]="4 --> Elimina contatto (pagina, posizione)";
                vociMenu[5]="5 --> Mostra contatti con lo stesso nome (nome)";
                vociMenu[6]="6 --> Mostra contatti dello stesso genere (genere)";
                vociMenu[7]="7 --> Mostra elenco contatti ordinati alfabeticamente per cognome (A-Z)";
                vociMenu[8]="8 --> Esporta contatti su file in formato CSV";
                vociMenu[9]="9 --> Importa contatti da file in formato CSV";
                vociMenu[10]="10 --> Salva dati";
                vociMenu[11]="11 --> Carica dati";
                vociMenu[12]="12 --> Salva & Esci";
                vociMenu[13]="13 --> Stampa Rubrica (.txt)";

                Menu menu=new Menu(vociMenu);

                do{
                    voceMenuScelta=menu.sceltaMenu();
                    switch(voceMenuScelta)
                    {
                        case 0:
                            System.out.println("Arrivederci!");
                            break;
                        case 1:
                            System.out.println(r1.toString());
                            break;
                        case 2:
                            System.out.println("Inserisci dati contatto:");
                            try
                            {
                                System.out.print("Cognome --> ");
                                cognome=tastiera.readString();
                                System.out.print("Nome --> ");
                                nome=tastiera.readString();
                                System.out.print("Genere (M/F) --> ");
                                genere=tastiera.readString();
                                System.out.print("Telefono --> ");
                                telefono=tastiera.readString();
                                System.out.print("Mail --> ");
                                mail=tastiera.readString();
                                System.out.print("Data di nascita (AAAA-MM-GG) --> ");
                                dataNascita=tastiera.readString();
                                do{
                                    try
                                    {
                                        System.out.print("Pagina (0..9) --> ");
                                        pagina=tastiera.readInt();
                                        break;
                                    }
                                    catch(NumberFormatException e)
                                    {
                                        System.out.println("Errore: input non corretto!");
                                    }
                                }while(true);
                                do{
                                    try
                                    {
                                        System.out.print("Posizione (0..9) --> ");
                                        posizione=tastiera.readInt();
                                        break;
                                    }
                                    catch(NumberFormatException e)
                                    {
                                        System.out.println("Errore: input non corretto!");
                                    }
                                }while(true);
                                cnt=new Contatto(cognome, nome, genere, telefono, mail, dataNascita);
                                r1.setContatto(cnt,pagina,posizione);
                                System.out.println("Contatto inserito correttamente!");
                            }
                            catch(IOException e)
                            {
                                System.out.println("Errore: Impossibile leggere da tastiera.");
                            }
                            catch(EccezionePaginaNonValida e)
                            {
                                System.out.println("Errore: Pagina non valido!");
                            }
                            catch(EccezionePosizioneNonValida e)
                            {
                                System.out.println("Errore: Posizione non valida!");
                            }
                            catch(EccezionePosizioneOccupata e)
                            {
                                System.out.println("Errore: Posizione occupata!");
                            }
                            break;
                        case 3:
                            try
                            {
                                do{
                                    try
                                    {
                                        System.out.print("Pagina (0..9) --> ");
                                        pagina=tastiera.readInt();
                                        break;
                                    }
                                    catch(NumberFormatException e)
                                    {
                                        System.out.println("Errore: input non corretto!");
                                    }
                                }while(true);
                                do{
                                    try
                                    {
                                        System.out.print("Posizione (0..9) --> ");
                                        posizione=tastiera.readInt();
                                        break;
                                    }
                                    catch(NumberFormatException e)
                                    {
                                        System.out.println("Errore: input non corretto!");
                                    }
                                }while(true);
                                cnt=r1.getContatto(pagina,posizione);
                                System.out.println("Contatto trovato:\n"+cnt.toString());
                            }
                            catch(IOException e)
                            {
                                System.out.println("Errore: impossibile leggere da tastiera!");
                            }
                            catch(EccezionePaginaNonValida e)
                            {
                                System.out.println("Errore: Pagina non valido!");
                            }
                            catch(EccezionePosizioneNonValida e)
                            {
                                System.out.println("Errore: Posizione non valida!");
                            }
                            catch(EccezionePosizioneVuota e)
                            {
                                System.out.println("Errore: Nessun contatto presente!");
                            }
                            break;
                        case 4:
                            try
                            {
                                do{
                                    try
                                    {
                                        System.out.print("Inserisci il pagina del contatto da eliminare (0..9): ");
                                        pagina=tastiera.readInt();
                                        break;
                                    }
                                    catch(NumberFormatException e)
                                    {
                                        System.out.println("Errore: input non corretto!");
                                    }
                                }while(true);
                                do{
                                    try
                                    {
                                        System.out.print("Inserisci la posizione del contatto da eliminare (0..9): ");
                                        posizione=tastiera.readInt();
                                        break;
                                    }
                                    catch(NumberFormatException e)
                                    {
                                        System.out.println("Errore: input non corretto!");
                                    }
                                }while(true);
                                r1.rimuoviContatto(pagina, posizione);
                                System.out.println("Contatto eliminato correttamente!");
                                }
                                catch(IOException e)
                                {
                                    System.out.println("Errore: impossibile leggere da tastiera!");
                                }
                                catch(EccezionePaginaNonValida e)
                                {
                                    System.out.println("Errore: pagina non valido!");
                                }
                                catch(EccezionePosizioneNonValida e)
                                {
                                    System.out.println("Errore: posizione non valida!");
                                }
                                catch(EccezionePosizioneVuota e)
                                {
                                    System.out.println("Errore: posizione già vuota!");
                                }
                            break;
                        case 5:
                            try
                            {
                                System.out.print("Inserisci nome: ");
                                nome=tastiera.readString();
                                elencoContattiNome=r1.elencoContattiNome(nome);
                                if(elencoContattiNome!=null)
                                {
                                    System.out.println("Cognomi:");
                                    for(int i=0;i<elencoContattiNome.length;i++)
                                        System.out.println("\t"+elencoContattiNome[i]);
                                }
                                else
                                    System.out.println("Nessun contatto presente per il nome scelto");
                            }
                            catch(IOException e)
                            {
                                System.out.println("Errore: impossibile leggere da tastiera!");
                            }
                            break;
                        case 6:
                            try
                            {
                                System.out.print("Inserisci genere (M/F): ");
                                genere=tastiera.readString();
                                elencoContattiGenere=r1.elencoContattiGenere(genere);
                                if(elencoContattiGenere!=null)
                                {
                                    System.out.println("Cognomi:");
                                    for(int i=0;i<elencoContattiGenere.length;i++)
                                        System.out.println("\t"+elencoContattiGenere[i]);
                                }
                                else
                                    System.out.println("Nessun contatto presente per il genere scelto");
                            }
                            catch(IOException e)
                            {
                                System.out.println("Errore: impossibile leggere da tastiera!");
                            }
                            break;
                        case 7:
                            System.out.println("Elenco contatti ordinati per cognome (A-Z):");
                            elencoContattiOrdinatiAlfabeticamente=r1.elencoContattiOrdinatiPerCognomeAZ();
                            for(int i=0;i<elencoContattiOrdinatiAlfabeticamente.length;i++)
                            {
                                System.out.println("\n"+elencoContattiOrdinatiAlfabeticamente[i].toString());
                            }
                            break;
                        case 8:
                            try
                            {
                                f1=new TextFile(nomeFileCSV, 'W');
                                for(int i=0;i<r1.getNumPagine();i++)
                                {
                                    for(int j=0;j<r1.getNumMaxContatti(i);j++)
                                    {
                                        try
                                        {
                                            cnt=r1.getContatto(i, j);
                                            datiContatto=i+";"+j+";"+cnt.getCognome()+";"+cnt.getNome()+";"+cnt.getTelefono()+";"+cnt.getMail()+";"+cnt.getDataNascita();
                                            f1.toFile(datiContatto);
                                        }
                                        catch(FileException e)
                                        {
                                            System.out.println(e.toString());
                                        }
                                        catch(EccezionePaginaNonValida | EccezionePosizioneNonValida e)
                                        {
                                            //non si verificherà mai
                                        }
                                        catch(EccezionePosizioneVuota e)
                                        {
                                            //non fare nulla, continua
                                        }
                                    }
                                }
                                f1.closeFile();
                                System.out.println("Esportazione avvenuta correttamente!");
                            }
                            catch(IOException e)
                            {
                                System.out.println("Errore: impossibile accedere al file!");
                            }
                            break;
                        case 9:
                            try
                            {
                                f1=new TextFile(nomeFileCSV, 'R');
                                System.out.println("Caricamento...");
                                do{
                                    try
                                    {
                                        String rigaLetta=f1.fromFile();
                                        String[] datiCnt=rigaLetta.split(";");
                                        pagina=Integer.parseInt(datiCnt[0]);
                                        posizione=Integer.parseInt(datiCnt[1]);
                                        cognome=datiCnt[2];
                                        nome=datiCnt[3];
                                        genere=datiCnt[4];
                                        telefono=datiCnt[5];
                                        mail=datiCnt[6];
                                        dataNascita=datiCnt[7];
                                        cnt=new Contatto(cognome, nome, genere, telefono, mail, dataNascita);
                                        try
                                        {
                                            r1.setContatto(cnt, pagina, posizione);
                                            System.out.println("\tContatto "+cognome+" importato!");
                                        }
                                        catch(EccezionePaginaNonValida e)
                                        {
                                            System.out.println("\tErrore: pagina "+pagina+" non valido per il contatto "+cognome+"!");
                                        }
                                        catch(EccezionePosizioneNonValida e)
                                        {
                                            System.out.println("\tErrore: posizione "+posizione+" non valida per il contatto "+cognome+"!");
                                        }
                                        catch(EccezionePosizioneOccupata e)
                                        {
                                            System.out.println("\tErrore: coordinata ("+pagina+";"+posizione+") occupata! Contatto "+cognome+" non importato!");
                                        }
                                    }
                                    catch(FileException e)
                                    {
                                        System.out.println("Fine del file!");
                                        f1.closeFile();
                                        System.out.println("Importazione terminata!");
                                        break;
                                    }
                                }while(true);
                            }
                            catch(IOException e)
                            {
                                System.out.println("Errore: impossibile accedere al file!");
                            }
                            break;
                        case 10:
                            try
                            {
                                ObjectOutputStream writer=new ObjectOutputStream(new FileOutputStream(nomeFileBIN));
                                writer.writeObject(r1);
                                writer.flush();
                                writer.close();
                                System.out.println("Salvataggio effettuato con successo!");
                            }
                            catch(IOException e)
                            {
                                System.out.println("Errore: impossibile accedere al file!");
                            }
                            break;
                        case 11:
                            try
                            {
                                ObjectInputStream reader=new ObjectInputStream(new FileInputStream(nomeFileBIN));
                                r1=(Rubrica)reader.readObject();
                                reader.close();
                                System.out.println("Caricamento avvenuto con successo!");
                            }
                            catch(IOException e)
                            {
                                System.out.println("Errore: impossibile accedere al file!");
                            }
                            catch(ClassNotFoundException e)
                            {
                                System.out.println("Errore: impossibile accedere al file!");
                            }
                            break;
                        case 12:
                            try
                            {
                                ObjectOutputStream writer=new ObjectOutputStream(new FileOutputStream(nomeFileBIN));
                                writer.writeObject(r1);
                                writer.flush();
                                writer.close();
                                System.out.println("Salvataggio effettuato con successo!");
                            }
                            catch(IOException e)
                            {
                                System.out.println("Errore: impossibile accedere al file!");
                            }
                            voceMenuScelta=0;
                            System.out.println("Arrivederci!");
                            break;
                        case 13:
                            try
                            {
                                f1=new TextFile(nomeFileTXT, 'W');
                                try
                                {
                                    datiContatto=r1.toString();
                                    f1.toFile(datiContatto);
                                }
                                catch(FileException e)
                                {
                                    System.out.println(e.toString());
                                }
                                f1.closeFile();
                                System.out.println("Rubrica stampata correttamente!");
                            }
                            catch(IOException e)
                            {
                                System.out.println("Errore: impossibile accedere al file!");
                            }
                            break;
                        default:
                            System.out.println("Valore inserito non valido, riprova.");
                    }
                }while(voceMenuScelta!=0);
            }
            else
            {
                int numeroVociMenu=7;
                String[] vociMenu=new String[numeroVociMenu];
                vociMenu[0]="0 --> Esci";
                vociMenu[1]="1 --> Visualizza tutti i contatti della rubrica";
                vociMenu[2]="2 --> Cerca contatto (pagina, posizione)";
                vociMenu[3]="3 --> Mostra contatti con lo stesso nome (nome)";
                vociMenu[4]="4 --> Mostra contatti dello stesso genere (genere)";
                vociMenu[5]="5 --> Mostra elenco contatti ordinati alfabeticamente per cognome (A-Z)";
                vociMenu[6]="6 --> Stampa Rubrica (.txt)";

                Menu menu=new Menu(vociMenu);

                do{
                    voceMenuScelta=menu.sceltaMenu();
                    switch(voceMenuScelta)
                    {
                        case 0:
                            System.out.println("Arrivederci!");
                            break;
                        case 1:
                            System.out.println(r1.toString());
                            break;
                        case 2:
                            try
                            {
                                do{
                                    try
                                    {
                                        System.out.print("Pagina (0..9) --> ");
                                        pagina=tastiera.readInt();
                                        break;
                                    }
                                    catch(NumberFormatException e)
                                    {
                                        System.out.println("Errore: input non corretto!");
                                    }
                                }while(true);
                                do{
                                    try
                                    {
                                        System.out.print("Posizione (0..9) --> ");
                                        posizione=tastiera.readInt();
                                        break;
                                    }
                                    catch(NumberFormatException e)
                                    {
                                        System.out.println("Errore: input non corretto!");
                                    }
                                }while(true);
                                cnt=r1.getContatto(pagina,posizione);
                                System.out.println("Contatto trovato:\n"+cnt.toString());
                            }
                            catch(IOException e)
                            {
                                System.out.println("Errore: impossibile leggere da tastiera!");
                            }
                            catch(EccezionePaginaNonValida e)
                            {
                                System.out.println("Errore: Pagina non valido!");
                            }
                            catch(EccezionePosizioneNonValida e)
                            {
                                System.out.println("Errore: Posizione non valida!");
                            }
                            catch(EccezionePosizioneVuota e)
                            {
                                System.out.println("Errore: Nessun contatto presente!");
                            }
                            break;
                        case 3:
                            try
                            {
                                System.out.print("Inserisci nome: ");
                                nome=tastiera.readString();
                                elencoContattiNome=r1.elencoContattiNome(nome);
                                if(elencoContattiNome!=null)
                                {
                                    System.out.println("Cognomi:");
                                    for(int i=0;i<elencoContattiNome.length;i++)
                                        System.out.println("\t"+elencoContattiNome[i]);
                                }
                                else
                                    System.out.println("Nessun contatto presente per il nome scelto");
                            }
                            catch(IOException e)
                            {
                                System.out.println("Errore: impossibile leggere da tastiera!");
                            }
                            break;
                        case 4:
                            try
                            {
                                System.out.print("Inserisci genere (M/F): ");
                                genere=tastiera.readString();
                                elencoContattiGenere=r1.elencoContattiGenere(genere);
                                if(elencoContattiGenere!=null)
                                {
                                    System.out.println("Cognomi:");
                                    for(int i=0;i<elencoContattiGenere.length;i++)
                                        System.out.println("\t"+elencoContattiGenere[i]);
                                }
                                else
                                    System.out.println("Nessun contatto presente per il genere scelto");
                            }
                            catch(IOException e)
                            {
                                System.out.println("Errore: impossibile leggere da tastiera!");
                            }
                            break;
                        case 5:
                            System.out.println("Elenco contatti ordinati per cognome (A-Z):");
                            elencoContattiOrdinatiAlfabeticamente=r1.elencoContattiOrdinatiPerCognomeAZ();
                            for(int i=0;i<elencoContattiOrdinatiAlfabeticamente.length;i++)
                            {
                                System.out.println("\n"+elencoContattiOrdinatiAlfabeticamente[i].toString());
                            }
                            break;
                        case 6:
                            try
                            {
                                f1=new TextFile(nomeFileTXT, 'W');
                                try
                                {
                                    datiContatto=r1.toString();
                                    f1.toFile(datiContatto);
                                }
                                catch(FileException e)
                                {
                                    System.out.println(e.toString());
                                }
                                f1.closeFile();
                                System.out.println("Rubrica stampata correttamente!");
                            }
                            catch(IOException e)
                            {
                                System.out.println("Errore: impossibile accedere al file!");
                            }
                            break;
                        default:
                            System.out.println("Valore inserito non valido, riprova.");
                    }
                }while(voceMenuScelta!=0);
            }
        }
        catch(IOException e)
        {
            System.out.println("Errore: Impossibile leggere da tastiera.");
        }
    }
}