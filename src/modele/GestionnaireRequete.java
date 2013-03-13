package modele;
import java.util.StringTokenizer;
import sql.ExecutionRequete;

public class GestionnaireRequete {
	private String erreur;
	private String reponse;
	private String[] typesRequetes = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M"};
	
	public void setReponse(String reponse) {
		this.reponse = reponse;
	}

	public void traitementRequete(String requete) {
		erreur = "";
		reponse = "";
		
		if (requete.toUpperCase().equals("QUITTER")) {
			reponse = "Ok. Le serveur va s'éteindre";
			return;
		}
		
		ExecutionRequete executionRequete = new ExecutionRequete();
		StringTokenizer st = new StringTokenizer(requete, "#");
		if (st.countTokens() == 2) {
			String lettreRequete = st.nextToken();
			String infosRequete = st.nextToken();
			for (String typeRequete : typesRequetes) {
				if (lettreRequete.equals(typeRequete)) {
					if (typeRequete.equals("A")) {
						StringTokenizer st2 = new StringTokenizer(infosRequete, ",");
						if (st2.countTokens() == 3) {
							int code = Integer.parseInt(st2.nextToken());
							String nom = st2.nextToken();
							int duree = Integer.parseInt(st2.nextToken());
							reponse = executionRequete.ajouterTache(code, nom, duree);
						}
					}
					else if (typeRequete.equals("B")) {
						StringTokenizer st2 = new StringTokenizer(infosRequete, ",");
						if (st2.countTokens() == 3) {
							int code = Integer.parseInt(st2.nextToken());
							String nom = st2.nextToken();
							int duree = Integer.parseInt(st2.nextToken());
							reponse = executionRequete.modifierTache(code, nom, duree);
						}
					}
					else if (typeRequete.equals("C")) {
						if (infosRequete.length() == 1) {
							int code = Integer.parseInt(infosRequete);
							reponse = executionRequete.supprimerTache(code);
						}
					}
					else if (typeRequete.equals("D")) {
						StringTokenizer st2 = new StringTokenizer(infosRequete, ",");
						if (st2.countTokens() == 2) {
							int code = Integer.parseInt(st2.nextToken());
							String nom = st2.nextToken();
							reponse = executionRequete.ajouterPersonne(code, nom);
						}
					}
					else if (typeRequete.equals("E")) {
						StringTokenizer st2 = new StringTokenizer(infosRequete, ",");
						if (st2.countTokens() == 2) {
							int code = Integer.parseInt(st2.nextToken());
							String nom = st2.nextToken();
							reponse = executionRequete.modifierPersonne(code, nom);
						}
					}
					else if (typeRequete.equals("F")) {
						StringTokenizer st2 = new StringTokenizer(infosRequete, ",");
						if (st2.countTokens() == 2) {
							int code = Integer.parseInt(st2.nextToken());
							String nom = st2.nextToken();
							reponse = executionRequete.supprimerPersonne(code, nom);
						}
					}
					else if (typeRequete.equals("G")) {
						StringTokenizer st2 = new StringTokenizer(infosRequete, ",");
						if (st2.countTokens() == 2) {
							int codeTache = Integer.parseInt(st2.nextToken());
							int codePersonne = Integer.parseInt(st2.nextToken());
							reponse = executionRequete.ajouterAffectation(codeTache, codePersonne);
						}
					}
					else if (typeRequete.equals("H")) {
						StringTokenizer st2 = new StringTokenizer(infosRequete, ",");
						if (st2.countTokens() == 2) {
							int codeTache = Integer.parseInt(st2.nextToken());
							int codePersonne = Integer.parseInt(st2.nextToken());
							reponse = executionRequete.modifierAffectation(codeTache, codePersonne);
						}
					}
					else if (typeRequete.equals("I")) {
						StringTokenizer st2 = new StringTokenizer(infosRequete, ",");
						if (st2.countTokens() == 2) {
							int codeTache = Integer.parseInt(st2.nextToken());
							int codePersonne = Integer.parseInt(st2.nextToken());
							reponse = executionRequete.supprimerAffectation(codeTache, codePersonne);
						}
					}
					else if (typeRequete.equals("J")) {
						StringTokenizer st2 = new StringTokenizer(infosRequete, ",");
						if (st2.countTokens() == 4) {
							int codePredecesseur = Integer.parseInt(st2.nextToken());
							int codeSuccesseur = Integer.parseInt(st2.nextToken());
							int duree = Integer.parseInt(st2.nextToken());
							int type = Integer.parseInt(st2.nextToken());
							reponse = executionRequete.ajouterContrainte(codePredecesseur, codeSuccesseur, duree, type);
						}
					}
					else if (typeRequete.equals("K")) {
						StringTokenizer st2 = new StringTokenizer(infosRequete, ",");
						if (st2.countTokens() == 4) {
							int codePredecesseur = Integer.parseInt(st2.nextToken());
							int codeSuccesseur = Integer.parseInt(st2.nextToken());
							int duree = Integer.parseInt(st2.nextToken());
							int type = Integer.parseInt(st2.nextToken());
							reponse = executionRequete.modifierContrainte(codePredecesseur, codeSuccesseur, duree, type);
						}
					}
					else if (typeRequete.equals("L")) {
						StringTokenizer st2 = new StringTokenizer(infosRequete, ",");
						if (st2.countTokens() == 4) {
							int codePredecesseur = Integer.parseInt(st2.nextToken());
							int codeSuccesseur = Integer.parseInt(st2.nextToken());
							int duree = Integer.parseInt(st2.nextToken());
							int type = Integer.parseInt(st2.nextToken());
							reponse = executionRequete.supprimerContrainte(codePredecesseur, codeSuccesseur, duree, type);
						}
					}
					else if (typeRequete.equals("M")) {
						if (infosRequete.length() == 1) {
							int codeProjet = Integer.parseInt(infosRequete);
							reponse = executionRequete.chargerProjet(codeProjet);
						}
					}
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

