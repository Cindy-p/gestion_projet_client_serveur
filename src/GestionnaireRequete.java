import java.util.StringTokenizer;

public class GestionnaireRequete {

	private String reponse;
	private String erreur;
	private String[] operators = { "+", "-", "*", "/" };

	public void traitementRequete(String requete) {
		erreur = "";
		reponse = "";
		int op1;
		int op2;

		if (requete.toUpperCase().equals("QUITTER")) {
			reponse = "Ok. Le serveur va s'éteindre";
			return;
		}

		for (String operator : operators) {
			StringTokenizer st = new StringTokenizer(requete, operator);
			if (st.countTokens() == 2) {
				try {
					op1 = Integer.parseInt(st.nextToken());
					op2 = Integer.parseInt(st.nextToken());
					reponse = "" + evaluate(operator.charAt(0), op1, op2);
				} catch (NumberFormatException nfe) {
					erreur = "Mauvais format de donnee";
				}
				break;
			}
		}
		if (reponse.equals("") && erreur.equals(""))
			erreur = "Erreur de syntaxe dans la requete";
	}

	public String reponse() {
		if (erreur.equals(""))
			return reponse;
		return erreur;
	}

	private int evaluate(char operator, int operande1, int operande2) {
		int resultat = 0;
		switch (operator) {
		case '+':
			resultat = operande1 + operande2;
			break;
		case '-':
			resultat = operande1 - operande2;
			break;
		case '*':
			resultat = operande1 * operande2;
			break;
		case '/':
			if (operande2 == 0)
				erreur = "Division par 0";
			else
				resultat = operande1 / operande2;
			break;
		}
		return resultat;
	}

}

