import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import outils.Outils;

public class Client {
	private String adresseIPServeur = "127.0.0.1";
	private int port = 8189;
	private Socket emetteur = null;
	
	public Client(String adresseIPServeur, int port) {
		this.adresseIPServeur = adresseIPServeur;
		this.port = port;
	}
	
	private void init() {
		try {
			emetteur = new Socket(adresseIPServeur, port);
		} catch (UnknownHostException e) {
			System.out.println("Impossible de trouver l'h�te du serveur.");
		} catch (IOException e) {
			System.out.println("Probl�me d'E/S.");
		}
	}
	
	private PrintWriter fluxSortant() {
		if (emetteur != null) {
			try {
				return new PrintWriter(emetteur.getOutputStream());
			} catch (IOException e) {
				System.out.println("Probl�me de r�cup�ration du flux sortant sur le client.");
			}
		}
		return null;
	}

	private BufferedReader fluxEntrant() {
		if (emetteur != null) {
			try {
				return new BufferedReader(new InputStreamReader(emetteur.getInputStream()));
			} catch (IOException e) {
				System.out.println("Probl�me de r�cup�ration du flux entrant sur le client.");
			}
		}
		return null;
	}
	
	private void emissionVersServeur() {
		boolean fini = false;
		String requete = "";
		String reponse = "";
		PrintWriter fluxSortant = fluxSortant();
		BufferedReader fluxEntrant = fluxEntrant();
		
		while (!fini && fluxSortant != null) {
			requete = Outils.lireChaine("Client> ");

			// EMISSION DE L'ORDRE SUR LE SERVEUR
			fluxSortant.println(requete);
			// On force la socket � vider le buffer de son flux sortant
			fluxSortant.flush();

			// RECEPTION DE LA REPONSE DU SERVEUR
			try {
				reponse = fluxEntrant.readLine();
				System.out.println("Client> " + reponse);
			} catch (IOException e) {
				System.out.println("Probl�me d'E/S.");
			}

			if (requete.toUpperCase().equals("QUITTER")) {
				fini = true;
				// On ferme le flux sortant
				fluxSortant.close();

			}
		}
	}
	
	private void quitter() {
		System.out.println("D�connexion du client...");
		if (emetteur != null) {
			try {
				emetteur.close();
			} catch (IOException e) {
				System.out.println("Probl�me de d�connexion.");
			}
		}
		System.out.println("Client d�connect�. Bye !");
	}
	
	public void lancerSession() {
		init();
		emissionVersServeur();
		quitter();
	}
	
	public static void main(String[] args) {
		String hote = Outils.lireChaine("Adresse IP du serveur : ");
		int port = Outils.lireEntier("N� du port de connexion sur le serveur : ");
		Client client = new Client(hote, port);
		client.lancerSession();
	}

}
