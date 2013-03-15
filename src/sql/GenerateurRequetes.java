package sql;

import java.util.StringTokenizer;

public class GenerateurRequetes {
	private String erreur;
	private String reponse;
	private String[] typesRequetes = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M"};
	
	public void setReponse(String reponse) {
		this.reponse = reponse;
	}

	public void traitementRequete(String requete) {
		erreur = "";
		reponse = "";
		String req = "";
		boolean rep = false;
		
		if (requete.toUpperCase().equals("QUITTER")) {
			reponse = "Ok. Le serveur va s'éteindre";
			return;
		}
		
		Bdd executer = new Bdd();
		
		StringTokenizer st = new StringTokenizer(requete, "#");
		if (st.countTokens() == 2) {
			String lettreRequete = st.nextToken();
			String infosRequete = st.nextToken();
			for (String typeRequete : typesRequetes) {
				if (lettreRequete.equals(typeRequete)) {
					if (typeRequete.equals("A")) {
						StringTokenizer st2 = new StringTokenizer(infosRequete, ",");
						if (st2.countTokens() == 3) {
							//int code = Integer.parseInt(st2.nextToken());
							String nom = st2.nextToken();
							int duree = Integer.parseInt(st2.nextToken());
							int projet = Integer.parseInt(st2.nextToken());
							req = "INSERT INTO tache VALUES ( null, '" + nom + "', " + duree + ", " + projet + ")"; 
							rep = executer.executerRequeteMAJ(req);
							if( rep == true)
								reponse = "a#OK#" + infosRequete;
							else
								reponse = "a#KO#";
							
						}
					}
					else if (typeRequete.equals("B")) {
						StringTokenizer st2 = new StringTokenizer(infosRequete, ",");
						if (st2.countTokens() == 3) {
							int code = Integer.parseInt(st2.nextToken());
							String nom = st2.nextToken();
							int duree = Integer.parseInt(st2.nextToken());
							int projet = Integer.parseInt(st2.nextToken());
							req = "UPDATE tache SET tache_nom = '" + nom + "', tache_duree = " + duree + ", projet_numero = " + projet + " WHERE tache_numero = " + code; 
							rep = executer.executerRequeteMAJ(req);
							if(rep == true)
								reponse = "b#OK#" + infosRequete;
							else
								reponse = "b#KO#";
						}
					}
					else if (typeRequete.equals("C")) {
						if (infosRequete.length() == 1 || infosRequete.length() == 2) {
							int code = Integer.parseInt(infosRequete);
							req = "DELETE FROM tache WHERE tache_numero = " + code;
							rep = executer.executerRequeteMAJ(req);
							if(rep == true)
								reponse = "c#OK#" + infosRequete;
							else
								reponse = "c#KO#";
						}
					}
					else if (typeRequete.equals("D")) {
						if (infosRequete.length() > 0) {
							String nom = infosRequete;
							req = "INSERT INTO personne VALUES ( null, '" + nom + "')"; 
							rep = executer.executerRequeteMAJ(req);
							if(rep == true)
								reponse = "d#OK#" + infosRequete;
							else
								reponse = "d#KO#";
						}
					}
					else if (typeRequete.equals("E")) {
						StringTokenizer st2 = new StringTokenizer(infosRequete, ",");
						if (st2.countTokens() == 1) {
							int code = Integer.parseInt(st2.nextToken());
							String nom = st2.nextToken();
							req = "UPDATE personne SET personne_nom = '" + nom + "' WHERE personne_numero = " + code; 
							rep = executer.executerRequeteMAJ(req);
							if(rep == true)
								reponse = "e#OK#" + infosRequete;
							else
								reponse = "e#KO#";
						}
					}
					else if (typeRequete.equals("F")) {
						StringTokenizer st2 = new StringTokenizer(infosRequete, ",");
						if (st2.countTokens() == 2) {
							int code = Integer.parseInt(st2.nextToken());
							String nom = st2.nextToken();
							req = "DELETE FROM personne WHERE personne_numero = " + code + " AND personne_nom = '" + nom + "'";
							rep = executer.executerRequeteMAJ(req);
							if(rep == true)
								reponse = "f#OK#" + infosRequete;
							else
								reponse = "f#KO#";
						}
					}
					else if (typeRequete.equals("G")) {
						StringTokenizer st2 = new StringTokenizer(infosRequete, ",");
						if (st2.countTokens() == 2) {
							int codePersonne = Integer.parseInt(st2.nextToken());
							int codeTache = Integer.parseInt(st2.nextToken());
							req = "INSERT INTO affectation VALUES (" + codePersonne + ", " + codeTache + ")"; 
							rep = executer.executerRequeteMAJ(req);
							if(rep == true)
								reponse = "g#OK#" + infosRequete;
							else
								reponse = "g#KO#";
						}
					}
					//A modifier
					else if (typeRequete.equals("H")) {
						StringTokenizer st2 = new StringTokenizer(infosRequete, ",");
						if (st2.countTokens() == 2) {
							int codePersonne = Integer.parseInt(st2.nextToken());
							int codeTache = Integer.parseInt(st2.nextToken());
							req = "UPDATE affectation SET tache_numero = " +codeTache + " WHERE personne_numero = " + codePersonne; 
							rep = executer.executerRequeteMAJ(req);
							if(rep == true)
								reponse = "h#OK#" + infosRequete;
							else
								reponse = "h#KO#";
						}
					}
					else if (typeRequete.equals("I")) {
						StringTokenizer st2 = new StringTokenizer(infosRequete, ",");
						if (st2.countTokens() == 2) {
							int codePersonne = Integer.parseInt(st2.nextToken());
							int codeTache = Integer.parseInt(st2.nextToken());
							req = "DELETE FROM affectation WHERE personne_numero = " + codePersonne + " AND tache_numero = " + codeTache;
							rep = executer.executerRequeteMAJ(req);
							if(rep == true)
								reponse = "i#OK#" + infosRequete;
							else
								reponse = "i#KO#";
						}
					}
					else if (typeRequete.equals("J")) {
						StringTokenizer st2 = new StringTokenizer(infosRequete, ",");
						if (st2.countTokens() == 4) {
							int codePredecesseur = Integer.parseInt(st2.nextToken());
							int codeSuccesseur = Integer.parseInt(st2.nextToken());
							int delai = Integer.parseInt(st2.nextToken());
							int type = Integer.parseInt(st2.nextToken());
							req = "INSERT INTO contrainte VALUES (" + codePredecesseur + ", " + codeSuccesseur + ", " + delai + ", " + type + ")"; 
							rep = executer.executerRequeteMAJ(req);
							if(rep == true)
								reponse = "j#OK#" + infosRequete;
							else
								reponse = "j#KO#";
						}
					}
					//A modifier
					else if (typeRequete.equals("K")) {
						StringTokenizer st2 = new StringTokenizer(infosRequete, ",");
						if (st2.countTokens() == 4) {
							int codePredecesseur = Integer.parseInt(st2.nextToken());
							int codeSuccesseur = Integer.parseInt(st2.nextToken());
							int delai = Integer.parseInt(st2.nextToken());
							int type = Integer.parseInt(st2.nextToken());
							req = "UPDATE contrainte SET tache_numero_successeur = " +codeSuccesseur + " AND delai = " + delai + " AND classe = " + type + " WHERE tache_numero_predecesseur = " + codePredecesseur; 
							rep = executer.executerRequeteMAJ(req);
							if(rep == true)
								reponse = "k#OK#" + infosRequete;
							else
								reponse = "k#KO#";
						}
					}
					else if (typeRequete.equals("L")) {
						StringTokenizer st2 = new StringTokenizer(infosRequete, ",");
						if (st2.countTokens() == 4) {
							int codePredecesseur = Integer.parseInt(st2.nextToken());
							int codeSuccesseur = Integer.parseInt(st2.nextToken());
							int delai = Integer.parseInt(st2.nextToken());
							int type = Integer.parseInt(st2.nextToken());
							req = "DELETE FROM contrainte WHERE tache_numero_successeur = " +codeSuccesseur + " AND delai = " + delai + " AND classe = " + type + " AND tache_numero_predecesseur = " + codePredecesseur; 
							rep = executer.executerRequeteMAJ(req);
							if(rep == true)
								reponse = "l#OK#" + infosRequete;
							else
								reponse = "l#KO#";
						}
					}
					else if (typeRequete.equals("M")) {
						if (infosRequete.length() == 1) {
							int codeProjet = Integer.parseInt(infosRequete);
							req = "SELECT * FROM projet WHERE projet_numero = " + codeProjet;
							rep = executer.executerRequeteSelection(req);
							if(rep == true){
								reponse = "m#OK#";
							}else
								reponse = "m#KO#";
							
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
