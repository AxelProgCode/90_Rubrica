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
        String cognome,nome,genere,telefono,mail,dataNascita,datiContatto,nuovoGenere,nuovoTelefono,nuovaMail,nuovaDataNascita;
	String[] elencoContattiNome,elencoContattiGenere;
        Contatto[] elencoContattiOrdinatiAlfabeticamente;
        Contatto cnt;
	TextFile f1;
        String nomeFileCSV="Contatti.csv";
	String nomeFileBIN="Rubrica.bin";
        String nomeFileTXT="Rubrica.txt";
        int pagina,posizione,eta,idContatto;
        
        try
        {
            System.out.print("Ciao! Chi sei? ");
            String utente=tastiera.readString();
            if(utente.equals("Admin")) //nome o password amministratore
            {
                int numeroVociMenu=15;
                String[] vociMenu=new String[numeroVociMenu];
                vociMenu[0]="0 --> Esci";
                vociMenu[1]="1 --> Visualizza tutti i contatti della rubrica";
                vociMenu[2]="2 --> Aggiungi contatto (pagina, posizione)";
                vociMenu[3]="3 --> Cerca contatto (pagina, posizione)";
		vociMenu[4]="4 --> Modifica contatto (pagina, posizione)";
                vociMenu[5]="5 --> Elimina contatto (pagina, posizione)";
                vociMenu[6]="6 --> Mostra contatti con lo stesso nome (nome)";
                vociMenu[7]="7 --> Mostra contatti dello stesso genere (genere)";
                vociMenu[8]="8 --> Mostra elenco contatti ordinati alfabeticamente per cognome (A-Z)";
                vociMenu[9]="9 --> Esporta contatti su file in formato CSV";
                vociMenu[10]="10 --> Importa contatti da file in formato CSV";
                vociMenu[11]="11 --> Salva dati";
                vociMenu[12]="12 --> Carica dati";
                vociMenu[13]="13 --> Salva & Esci";
                vociMenu[14]="14 --> Stampa Rubrica (.txt)";

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
				do{
				    System.out.print("Genere (M/F) --> ");
				    genere=tastiera.readString();
				    String regex="[MF]";
				    if(genere.matches(regex))
					break;
				    else
					System.out.println("Errore: genere non valido! (M/F)");
				}while(true);
                                do{
				    System.out.print("Telefono --> ");
				    telefono=tastiera.readString();
				    String regex = "^[0-9]{10}$";
				    if(telefono.matches(regex))
					break;
				    else
					System.out.println("Errore: telefono non valido!");
				}while(true);
                                do{
				    System.out.print("Mail --> ");
				    mail=tastiera.readString();
				    String regex="^[\\w!#$%&'*+/=?^`{|}~-]+(?:\\.[\\w!#$%&'*+/=?^`{|}~-]+)*@(?:[a-zA-Z0-9](?:[a-zA-Z0-9-]*[a-zA-Z0-9])?\\.)+[a-zA-Z]{2,}$";
				    if(mail.matches(regex))
					break;
				    else
					System.out.println("Errore: mail non valida!");
				}while(true);
                                do{
				    System.out.print("Data di nascita (AAAA-MM-GG) --> ");
				    dataNascita=tastiera.readString();
				    String regex="\\d{4}-\\d{2}-\\d{2}";
				    if(dataNascita.matches(regex))
					break;
				    else
					System.out.println("Errore: data non valida!");
				}while(true);
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
			    System.out.println("Modifica contatto:");
			    System.out.print("Pagina (0..9) --> ");
			    pagina=tastiera.readInt();
			    System.out.print("Posizione (0..9) --> ");
			    posizione=tastiera.readInt();
			    try
			    {
				Contatto contattoModificato=r1.getContatto(pagina, posizione);
				if(contattoModificato!=null)
				{
				    System.out.println("Dati contatto attuale:");
				    System.out.println(contattoModificato.toString());
				    System.out.println("Inserisci nuovi dati:");
				    System.out.print("Nuovo Cognome --> ");
				    String nuovoCognome=tastiera.readString();
				    System.out.print("Nuovo Nome --> ");
				    String nuovoNome=tastiera.readString();
				    do{
					System.out.print("Nuovo Genere (M/F) --> ");
					nuovoGenere=tastiera.readString();
					String regex="[MF]";
					if(nuovoGenere.matches(regex))
					    break;
					else
					    System.out.println("Errore: genere non valido! (M/F)");
				    }while(true);
				    do{
					System.out.print("Nuovo Telefono --> ");
					nuovoTelefono=tastiera.readString();
					String regex = "^[0-9]{10}$";
					if(nuovoTelefono.matches(regex))
					    break;
					else
					    System.out.println("Errore: telefono non valido!");
				    }while(true);
				    do{
					System.out.print("Nuova Mail --> ");
					nuovaMail=tastiera.readString();
					String regex="^[\\w!#$%&'*+/=?^`{|}~-]+(?:\\.[\\w!#$%&'*+/=?^`{|}~-]+)*@(?:[a-zA-Z0-9](?:[a-zA-Z0-9-]*[a-zA-Z0-9])?\\.)+[a-zA-Z]{2,}$";
					if(nuovaMail.matches(regex))
					    break;
					else
					    System.out.println("Errore: mail non valida!");
				    }while(true);
				    do{
					System.out.print("Nuova Data di nascita (AAAA-MM-GG) --> ");
					nuovaDataNascita=tastiera.readString();
					String regex="\\d{4}-\\d{2}-\\d{2}";
					if(nuovaDataNascita.matches(regex))
					    break;
					else
					    System.out.println("Errore: data non valida!");
				    }while(true);
				    contattoModificato.setCognome(nuovoCognome);
				    contattoModificato.setNome(nuovoNome);
				    contattoModificato.setGenere(nuovoGenere);
				    contattoModificato.setTelefono(nuovoTelefono);
				    contattoModificato.setMail(nuovaMail);
				    contattoModificato.setDataNascita(nuovaDataNascita);
				    r1.rimuoviContatto(pagina, posizione);
				    r1.setContatto(contattoModificato, pagina, posizione);
				    System.out.println("Contatto modificato con successo!");
				}
				else
				{
				    System.out.println("Errore: nessun contatto trovato!");
				}
			    }
			    catch(EccezionePosizioneVuota e)
			    {
				System.out.println("Errore: posizione vuota!");
			    }
			    catch(EccezionePaginaNonValida e)
			    {
				System.out.println("Errore: pagina non valida!");
			    }
			    catch(EccezionePosizioneNonValida e)
			    {
				System.out.println("Errore: posizione non valida!");
			    }
			    catch(EccezionePosizioneOccupata e)
			    {
				//non succederà mai
			    }
			    break;
                        case 5:
                            try
                            {
                                do{
                                    try
                                    {
                                        System.out.print("Inserisci la pagina del contatto da eliminare (0..9): ");
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
                        case 6:
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
                        case 7:
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
                        case 8:
                            System.out.println("Elenco contatti ordinati per cognome (A-Z):");
                            elencoContattiOrdinatiAlfabeticamente=r1.elencoContattiOrdinatiPerCognomeAZ();
                            for(int i=0;i<elencoContattiOrdinatiAlfabeticamente.length;i++)
                            {
                                System.out.println("\n"+elencoContattiOrdinatiAlfabeticamente[i].toString());
                            }
                            break;
                        case 9:
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
                                            datiContatto=i+";"+j+";"+cnt.getidContatto()+";"+cnt.getCognome()+";"+cnt.getNome()+";"+cnt.getGenere()+";"+cnt.getTelefono()+";"+cnt.getMail()+";"+cnt.getDataNascita()+";"+cnt.getEta();
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
                        case 10:
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
					idContatto=Integer.parseInt(datiCnt[2]);
                                        cognome=datiCnt[3];
                                        nome=datiCnt[4];
                                        genere=datiCnt[5];
                                        telefono=datiCnt[6];
                                        mail=datiCnt[7];
                                        dataNascita=datiCnt[8];
					eta=Integer.parseInt(datiCnt[9]);
                                        cnt=new Contatto(cognome, nome, genere, telefono, mail, dataNascita);
                                        try
                                        {
                                            r1.setContatto(cnt, pagina, posizione);
                                            System.out.println("\tContatto "+cognome+nome+" importato!");
                                        }
                                        catch(EccezionePaginaNonValida e)
                                        {
                                            System.out.println("\tErrore: pagina "+pagina+" non valido per il contatto "+cognome+nome+"!");
                                        }
                                        catch(EccezionePosizioneNonValida e)
                                        {
                                            System.out.println("\tErrore: posizione "+posizione+" non valida per il contatto "+cognome+nome+"!");
                                        }
                                        catch(EccezionePosizioneOccupata e)
                                        {
                                            System.out.println("\tErrore: coordinata ("+pagina+";"+posizione+") occupata! Contatto "+cognome+nome+" non importato!");
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
                        case 11:
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
                        case 12:
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
                        case 13:
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
                        case 14:
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