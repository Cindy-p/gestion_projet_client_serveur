import java.util.StringTokenizer;

public class GestionnaireRequete {
	public String erreur;
	public String reponse;
	private String[] typesRequetes = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M"};
	
	public void traitementRequete(String requete) {
		reponse = "";
		
		if (requete.toUpperCase().equals("QUITTER")) {
			reponse = "Ok. Le serveur va s'éteindre";
			return;
		}
		
		StringTokenizer st = new StringTokenizer(requete, "#");
		reponse = st.nextToken();
	}
	
	public String reponse() {
		if (erreur.equals(""))
			return reponse;
		return erreur;
	}
}

