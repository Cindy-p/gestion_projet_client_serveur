package modele;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Date;

import sql.GenerateurRequetes;

public class ConnecteurClient implements Runnable {

	private int numeroClient;
	private Socket connecteur = null;
	private ServeurMultiClients smc = null;

	public ConnecteurClient(Socket s, int numeroClient, ServeurMultiClients smc  ) {
		this.numeroClient = numeroClient;
		connecteur = s;
		this.smc = smc;
	}

	// Attente d'une connexion d'un client
	// R�cup�ration d'une socket vers le client

	private PrintWriter fluxSortant() {
		if (connecteur != null) {
			try {
				return new PrintWriter(connecteur.getOutputStream());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out
						.println("Probl�me de r�cup�ration du flux sortant du serveur");
			}
		}
		return null;
	}

	// Recup�ration du flux entrant sur le serveur
	// Un bufferedReader permet de lire des cha�nes d�limit�es par des \n
	private BufferedReader fluxEntrant() {
		if (connecteur != null)
			try {
				return new BufferedReader(new InputStreamReader(
						connecteur.getInputStream()));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out
						.println("Probl�me de r�cup�rartion du flux entrant sur le serveur");
			}
		return null;
	}

	// Boucle de communication en r�ception des requetes et emission des
	// r�ponses
	// On sort de la boucle si le client envoi l'ordre Quitter

	private void dialoguerAvecClient() {
		String requete = "";
		boolean fini = false;

		BufferedReader fluxEntrant = fluxEntrant();
		PrintWriter fluxSortant = fluxSortant();
		GenerateurRequetes gr = new GenerateurRequetes();

		while (!fini && fluxEntrant != null && fluxSortant != null) {
			// RECUPERATION DE LA REQUETE
			try {
				requete = fluxEntrant.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("Erreur entr�e/sortie -> Quitter");
			}
			System.out.println("Re�u du client " + numeroClient + " - "
					+ new Date() + " : " + requete);
			if (requete.toUpperCase().equals("DECONNECTER"))
			{
				fini = true;
			}
			
			if (requete.toUpperCase().equals("STOPSERVEUR"))
			{
				fini = true;
				smc.stopServeur();
			}
			
			// TRAITEMENT DE LA REQUETE
			gr.traitementRequete(requete);

			// ENVOIE DE LA REPONSE
			fluxSortant.println(gr.reponse());
			fluxSortant.flush();
		}

		// Fermeture du flux entrant
		if (fluxEntrant != null)
			try {
				fluxEntrant.close();
				fluxSortant.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("Probl�me d'E/S");
			}

	}

	// Permet de quitter proprement en fermant la socket et le serveur
	private void quitter() {
		System.out.println("Re�u " + new Date() + " : Deconnexion du client "
				+ numeroClient);
		if (connecteur != null) {
			try {
				connecteur.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("Probl�me d'E/S");
			}

		}
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		dialoguerAvecClient();
		quitter();
	}

}
