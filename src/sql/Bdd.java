package sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Bdd {

	private String password;
	private String user;
	private Connection conn;
	
	public Bdd (){
		String pilote = "com.mysql.jdbc.Driver";
		
		try {
			Class.forName(pilote);
			System.out.println("Chargement driver OK.");
			
			String url = "jdbc:mysql://localhost/gestion_proj";
			user = "root";
			password = "";
			
			conn = DriverManager.getConnection(url, user, password);
			System.out.println("Connexion effective.");
			
		} catch (Exception ex) {
			System.err.println("Erreur : " + ex);
		}
	}
	
	public boolean executerRequeteSelection(String sql) {
		boolean rep = true;
		
		try {
			Statement ordre = conn.createStatement();
			ResultSet resultat = ordre.executeQuery(sql);
			
			int nbCols = resultat.getMetaData().getColumnCount();
				
			// Pour chaque ligne
			while (resultat.next()) {
				// Pour chaque colonne
				for (int col = 1 ; col <= nbCols ; col++) {
					String type = resultat.getMetaData().getColumnTypeName(col);
					if (type.equals("VARCHAR"))
						System.out.print(resultat.getString(col) + "\t");
					else if (type.equals("INT UNSIGNED"))
						System.out.print(resultat.getInt(col) + "\t");
					else if (type.equals("CHAR"))
						System.out.print(resultat.getString(col) + "\t");
				} 
				System.out.println();
			} 
		} catch (Exception ex) {
			ex.printStackTrace();
			System.err.println("Erreur : " + ex);
			rep = false;
		}
		return rep;
	}
	
	public boolean executerRequeteMAJ(String req) {
		int res = -1;
		boolean rep = true;
		
		try {
			Statement ordre = conn.createStatement();
			res = ordre.executeUpdate(req);
			
		} catch (Exception ex) {
			System.err.println("Erreur : " + ex);
			rep = false;
		}
		return rep;
	}
}
