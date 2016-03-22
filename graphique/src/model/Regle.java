package model;

/**
 * Classe abstraite servant de patron pour la definition des regles de vie ou de mort d'une cellule.
 */
public abstract class Regle {

	/**
	 * Reference de type Cellule vers la cellule courante.
	 */
	private Cell<?, ?> c;

	/**
	 * Constructeur de la classe Regle initialisant la reference c vers la cellule courante.
	 * 
	 * @param d
	 * 		  La reference vers la cellule courante.
	 */
	protected Regle(Cell<?, ?> d) {
		c = d;
	}

	/**
	 * Entier static identifiant la regle a appliquer a la cellule courante par rapport au choix de jeu de la personne.
	 */
	private static int regle;

	/**
	 * Methode abstraite definissant les regles de vie ou de mort de la cellule.
	 * 
	 * @param voisinVivant
	 * 					Entier representant le nombre de voisins vivant de la cellule courante.
	 * 
	 * @return Le nouvel etat de la cellule suite a l'application des regles du jeu courant.
	 */
	protected abstract State regle(int... voisinVivant);

	/**
	 * Mutateur mettant a jour l'entier associe a la regle à utiliser.
	 * 
	 * @param i
	 * 		 L'entier i associe a la regle courante.
	 */
	public static void setRegle(int i) {
		regle = i;
	}

	/**
	 * Accesseur permettant d'acceder a l'entier identifiant la regle a utiliser.
	 * 
	 * @return L'entier associe a la regle courante.
	 */
	protected static int getRegle() {
		return regle;
	}

	/**
	 * Accesseur permettant d'acceder a la reference vers la cellule courante.
	 * 
	 * @return La reference vers la cellule courante.
	 */
	protected Cell<?, ?> getCell() {
		return c;
	}

}