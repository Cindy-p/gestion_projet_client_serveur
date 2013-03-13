package sql;
public class ExecutionRequete {
	private String reponse;

	public String ajouterTache(int code, String nom, int duree) {
		return reponse = "Ajouter tâche : " + code + ", " + nom + ", " + duree;
	}
	
	public String modifierTache(int code, String nom, int duree) {
		return reponse = "Modifier tâche : " + code + ", " + nom + ", " + duree;
	}
	
	public String supprimerTache(int code) {
		return reponse = "Supprimer tâche : " + code;
	}
	
	public String ajouterPersonne(int code, String nom) {
		return reponse = "Ajouter personne : " + code + ", " + nom;
	}
	
	public String modifierPersonne(int code, String nom) {
		return reponse = "Modifier personne : " + code + ", " + nom;
	}
	
	public String supprimerPersonne(int code, String nom) {
		return reponse = "Supprimer personne : " + code + ", " + nom;
	}
	
	public String ajouterAffectation(int codeTache, int codePersonne) {
		return reponse = "Ajouter affectation : " + codeTache + ", " + codePersonne;
	}
	
	public String modifierAffectation(int codeTache, int codePersonne) {
		return reponse = "Modifier affectation : " + codeTache + ", " + codePersonne;
	}
	
	public String supprimerAffectation(int codeTache, int codePersonne) {
		return reponse = "Supprimer affectation : " + codeTache + ", " + codePersonne;
	}
	
	public String ajouterContrainte(int codePredecesseur, int codeSuccesseur, int duree, int type) {
		return reponse = "Ajouter contrainte : " + codePredecesseur + ", " + codeSuccesseur + ", " +
					duree + ", " + type;
	}
	
	public String modifierContrainte(int codePredecesseur, int codeSuccesseur, int duree, int type) {
		return reponse = "Modifier contrainte : " + codePredecesseur + ", " + codeSuccesseur + ", " +
					duree + ", " + type;
	}
	
	public String supprimerContrainte(int codePredecesseur, int codeSuccesseur, int duree, int type) {
		return reponse = "Supprimer contrainte : " + codePredecesseur + ", " + codeSuccesseur + ", " +
					duree + ", " + type;
	}
	
	public String chargerProjet(int codeProjet) {
		return reponse = "Chargement projet : " + codeProjet;
	}
}
