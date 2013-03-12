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
							ajouterTache(code, nom, duree);
						}
					}
					else if (typeRequete.equals("B")) {
						StringTokenizer st2 = new StringTokenizer(infosRequete, ",");
						if (st2.countTokens() == 3) {
							int code = Integer.parseInt(st2.nextToken());
							String nom = st2.nextToken();
							int duree = Integer.parseInt(st2.nextToken());
							modifierTache(code, nom, duree);
						}
					}
					else if (typeRequete.equals("C")) {
						if (infosRequete.length() == 1) {
							int code = Integer.parseInt(infosRequete);
							supprimerTache(code);
						}
					}
					else if (typeRequete.equals("D")) {
						StringTokenizer st2 = new StringTokenizer(infosRequete, ",");
						if (st2.countTokens() == 2) {
							int code = Integer.parseInt(st2.nextToken());
							String nom = st2.nextToken();
							ajouterPersonne(code, nom);
						}
					}
					else if (typeRequete.equals("E")) {
						StringTokenizer st2 = new StringTokenizer(infosRequete, ",");
						if (st2.countTokens() == 2) {
							int code = Integer.parseInt(st2.nextToken());
							String nom = st2.nextToken();
							modifierPersonne(code, nom);
						}
					}
					else if (typeRequete.equals("F")) {
						StringTokenizer st2 = new StringTokenizer(infosRequete, ",");
						if (st2.countTokens() == 2) {
							int code = Integer.parseInt(st2.nextToken());
							String nom = st2.nextToken();
							supprimerPersonne(code, nom);
						}
					}
					else if (typeRequete.equals("G")) {
						StringTokenizer st2 = new StringTokenizer(infosRequete, ",");
						if (st2.countTokens() == 2) {
							int codeTache = Integer.parseInt(st2.nextToken());
							int codePersonne = Integer.parseInt(st2.nextToken());
							ajouterAffectation(codeTache, codePersonne);
						}
					}
					else if (typeRequete.equals("I")) {
						StringTokenizer st2 = new StringTokenizer(infosRequete, ",");
						if (st2.countTokens() == 2) {
							int codeTache = Integer.parseInt(st2.nextToken());
							int codePersonne = Integer.parseInt(st2.nextToken());
							modifierAffectation(codeTache, codePersonne);
						}
					}
					else if (typeRequete.equals("J")) {
						StringTokenizer st2 = new StringTokenizer(infosRequete, ",");
						if (st2.countTokens() == 2) {
							int codeTache = Integer.parseInt(st2.nextToken());
							int codePersonne = Integer.parseInt(st2.nextToken());
							supprimerAffectation(codeTache, codePersonne);
						}
					}
					else if (typeRequete.equals("K")) {
						
					}
					else if (typeRequete.equals("L")) {
						
					}
					else if (typeRequete.equals("M")) {
						
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
	
	public void ajouterTache(int code, String nom, int duree) {
		reponse = "Ajouter tâche : " + code + ", " + nom + ", " + duree;
	}
	
	public void modifierTache(int code, String nom, int duree) {
		reponse = "Modifier tâche : " + code + ", " + nom + ", " + duree;
	}
	
	public void supprimerTache(int code) {
		reponse = "Supprimer tâche : " + code;
	}
	
	public void ajouterPersonne(int code, String nom) {
		reponse = "Ajouter personne : " + code + ", " + nom;
	}
	
	public void modifierPersonne(int code, String nom) {
		reponse = "Modifier personne : " + code + ", " + nom;
	}
	
	public void supprimerPersonne(int code, String nom) {
		reponse = "Supprimer personne : " + code + ", " + nom;
	}
	
	public void ajouterAffectation(int codeTache, int codePersonne) {
		reponse = "Ajouter affectation : " + codeTache + ", " + codePersonne;
	}
	
	public void modifierAffectation(int codeTache, int codePersonne) {
		reponse = "Modifier affectation : " + codeTache + ", " + codePersonne;
	}
	
	public void supprimerAffectation(int codeTache, int codePersonne) {
		reponse = "Supprimer affectation : " + codeTache + ", " + codePersonne;
	}
	
	public void ajouterContrainte(int codePredecesseur, int codeSuccesseur, int duree, int type) {
		reponse = "Ajouter contrainte : " + codePredecesseur + ", " + codeSuccesseur + ", " +
					duree + ", " + type;
	}
	
	public void modifierContrainte(int codePredecesseur, int codeSuccesseur, int duree, int type) {
		reponse = "Modifier contrainte : " + codePredecesseur + ", " + codeSuccesseur + ", " +
					duree + ", " + type;
	}
	
	public void supprimerContrainte(int codePredecesseur, int codeSuccesseur, int duree, int type) {
		reponse = "Supprimer contrainte : " + codePredecesseur + ", " + codeSuccesseur + ", " +
					duree + ", " + type;
	}
	
	public void chargerProjet(int codeProjet) {
		reponse = "Chargement projet : " + codeProjet;
	}
}

