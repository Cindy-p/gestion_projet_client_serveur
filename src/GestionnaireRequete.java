import java.util.StringTokenizer;

public class GestionnaireRequete {
	private String erreur;
	private String reponse;
	private String[] typesRequetes = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M"};
	
	public void traitementRequete(String requete) {
		erreur = "";
		reponse = "";
		
		if (requete.toUpperCase().equals("QUITTER")) {
			reponse = "Ok. Le serveur va s'éteindre";
			return;
		}
		
		StringTokenizer st = new StringTokenizer(requete, "#");
		if (st.countTokens() == 2) {
			String tmp = st.nextToken();
			for (String typeRequete : typesRequetes) {
				if (tmp.equals(typeRequete)) {
					reponse = tmp;
				}
				else {
					reponse = "Requête demandée incorrecte.";
				}
			}
		}
		
		if (reponse.equals("") && erreur.equals("")) {
			erreur = "Erreur de syntaxe dans la requete.";
		}
	}
	
	public String reponse() {
		if (erreur.equals("")) {
			return reponse;
		}
		return erreur;
	}
}

