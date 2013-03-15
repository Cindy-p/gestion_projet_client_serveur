package sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Bdd {

	private String password;
	private String user;
	private Connection conn;

	public Bdd() {
		String pilote = "com.mysql.jdbc.Driver";

		try {
			Class.forName(pilote);
			// System.out.println("Chargement driver OK.");

			String url = "jdbc:mysql://localhost/gestion_proj";
			user = "root";
			password = "";

			conn = DriverManager.getConnection(url, user, password);
			// System.out.println("Connexion effective.");

		} catch (Exception ex) {
			System.err.println("Erreur : " + ex);
		}
	}

	public boolean executerRequeteSelection(String sql) {
		boolean rep = true;
		int num_projet = 0;
		int taille = 0;
		System.out.println("\tRequête à executer : " + sql);

		try {
			Statement ordre = conn.createStatement();
			ResultSet resultat = ordre.executeQuery(sql);

			int nbCols = resultat.getMetaData().getColumnCount();

			// Pour chaque ligne
			while (resultat.next()) {
				// Pour chaque colonne
				for (int col = 1; col <= nbCols; col++) {
					String type = resultat.getMetaData().getColumnTypeName(col);
					if (type.equals("VARCHAR"))
						System.out.println("\tDescription : "
								+ resultat.getString(col));
					else if (type.equals("INT UNSIGNED")) {
						num_projet = resultat.getInt(col);
						System.out.println("\n\tN° projet : " + num_projet);
					} else if (type.equals("CHAR"))
						System.out
								.println("\tNom : " + resultat.getString(col));
				}
				taille++;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			System.err.println("Erreur : " + ex);
			rep = false;
		}
		if (taille == 0)
			rep = false;

		System.out.println("\n\tRequête effectuée : " + rep);
		if (rep == true && num_projet != 0) {
			System.out.println("\n\tChangement de projet...");
			System.out.println("\nOpérations sur le projet n°" + num_projet);
		}
		return rep;
	}

	public boolean executerRequeteMAJ(String req) {
		int res = -1;
		boolean rep = true;

		System.out.println("\tRequête à executer : " + req);

		try {
			Statement ordre = conn.createStatement();
			res = ordre.executeUpdate(req);

			if (res == -1 || res == 0)
				rep = false;

		} catch (Exception ex) {
			System.err.println("Erreur : " + ex);
			rep = false;
		}
		System.out.println("\tRequête effectuée : " + rep);
		return rep;
	}
}
