package modele;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;

public class ServeurMultiClients {

	private ArrayList<Thread> connexionsClient = new ArrayList<Thread>();
	private int numeroClient = 1;
	private ServerSocket serveur = null;
	private int port = 8189;
	private boolean stopServeur = false;

	// Initialisation du serveur sur le port
	private void init() {
		try {
			serveur = new ServerSocket(port);
			System.out.println("Serveur Multi Clients 3.0");
		} catch (IOException e) {
			System.out.println("Impossibilité de créer un serveur de socket");
		}
	}
	
	public void stopServeur() {
		this.stopServeur = true;
		try {
			serveur.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// Attente d'une connexion d'un client
	// Récupération d'une socket vers le client
	private void attenteConnexionClients() {

		if (serveur != null) {

			Socket s = null;
			while (!stopServeur) {
				System.out.println("Attente de la connexion du client n°"
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

	// Permet de quitter proprement en fermant la socket et le serveur
	private void quitter() {
		System.out.println("Reçu " + new Date()
				+ " : Deconnexion des clients et fermeture du serveur");
		try {

			serveur.close();
			System.out.println("Serveur arrete ...");
		} catch (IOException e) {
			System.out.println("Problème E/S");
		}
	}

	public void lancerSession() {
		init();
		attenteConnexionClients();
		quitter();
	}

	public static void main(String[] args) {
		ServeurMultiClients serveurMultiClients = new ServeurMultiClients();
		serveurMultiClients.lancerSession();
	}
}
