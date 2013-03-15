package modele;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;

import sql.GenerateurRequetes;


public class ServeurMultiClients {
	private ArrayList<Thread> connexionsClient = new ArrayList<Thread>();
	private int numeroClient = 1;
	private Socket ecouteur = null;
	private ServerSocket serveur = null;
	private boolean stopServeur = false;

	private String adresseIP = "127.0.0.1";
	private int port = 8189;
	
	// Initialisation du serveur sur le port
	private void init() {
		try {
			serveur = new ServerSocket(port);
			System.out.println("Serveur Multi Clients");
		} catch (IOException e) {
			System.out.println("Impossibilité de créer un serveur de socket.");
		}
	}
	
	// Attente d'une connexion d'un client
	// Récupération d'une socket vers le client
	private void attenteConnexionClients() {

		if (serveur != null) {

			Socket s = null;
			while (!stopServeur) {
				System.out.println("Attente de la connexion du client n�"
						+ numeroClient + "...");
				try {
					s = serveur.accept();
					if (s != null) {
						System.out
								.println("Connexion effective avec le client "
										+ s.getRemoteSocketAddress());
						ConnecteurClient cc = new ConnecteurClient(s,
								numeroClient, this);
						Thread cct = new Thread(cc);
						connexionsClient.add(cct);
						cct.start();
						numeroClient++;
					}
				} catch (IOException e) {
					System.out.println(e.getMessage());
				}
			}
		}
	}
	
	private PrintWriter fluxSortant() {
		if (ecouteur != null) {
			try {
				return new PrintWriter(ecouteur.getOutputStream());
			} catch (IOException e) {
				System.out.println("Problème de récupération du flux sortant du serveur");
			}
		}
		return null;
	}

	// Recupération du flux entrant sur le serveur
	// Un bufferedReader permet de lire des chaînes délimitées par des \n
	private BufferedReader fluxEntrant() {
		if (ecouteur != null)
			try {
				return new BufferedReader(new InputStreamReader(ecouteur.getInputStream()));
			} catch (IOException e) {
				System.out.println("Problème de récupération du flux entrant sur le serveur");
			}
		return null;
	}
	
	// Boucle de communication en réception des requetes et emission des réponses
	// On sort de la boucle si le client envoi l'ordre Quitter
	private void ecouterClient() {
		String requete = "";
		boolean fini = false;

		BufferedReader fluxEntrant = fluxEntrant();
		PrintWriter fluxSortant = fluxSortant();
		
		int num_projet = 0;
		
		while (num_projet == 0){
			try {
				num_projet = Integer.parseInt(fluxEntrant.readLine());
				System.out.println(num_projet);
			}catch(IOException e){
				System.out.println("Erreur entrée/sortie -> Quitter");
			}
		}
		
		GenerateurRequetes gr = new GenerateurRequetes(num_projet);

		while (!fini && fluxEntrant != null && fluxSortant != null) {
			// RECUPERATION DE LA REQUETE
			try {
				requete = fluxEntrant.readLine();
			} catch (IOException e) {
				System.out.println("Erreur entrée/sortie -> Quitter");
			}
			
			System.out.println("Reçu " + new Date() + " : " + requete);
			
			if (requete.toUpperCase().equals("QUITTER"))
				fini = true;

			// TRAITEMENT DE LA REQUETE
			gr.traitementRequete(requete);

			// ENVOIE DE LA REPONSE
			fluxSortant.println(gr.reponse());
			fluxSortant.flush();
		}

		// Fermeture du flux entrant
		if (fluxEntrant != null) {
			try {
				fluxEntrant.close();
			} catch (IOException e) {
				System.out.println("Erreur de fermeture du flux entrant : " + e.getMessage());
			}
		}
	}
	
	// Permet de quitter proprement en fermant la socket et le serveur
	private void quitter() {
		System.out.println("Reçu " + new Date() + " : Deconnexion client et fermeture du serveur.");
		if (ecouteur != null) {
			try {
				ecouteur.close();
				serveur.close();
				System.out.println("Bye !");
			} catch (IOException e) {
				System.out.println("Erreur lors de l'extinction du serveur : " + e.getMessage());
			}
		}
	}
	
	public void lancerSession() {
		init();
		attenteConnexionClient();
		ecouterClient();
		quitter();
	}

	public static void main(String[] args) {
		ServeurMultiClients serveurMultiClients = new ServeurMultiClients();
		serveurMultiClients.lancerSession();
	}
}