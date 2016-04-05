package model;

/**
 * Classe etendant la classe Regle et definissant les regles du jeu HighLife.
 * 
 * @see Regle
 */
public class HighLife extends Regle {

	/**
	 * Constructeur de la classe HighLife initialisant une reference vers la cellule courante.
	 * 
	 * @param d
	 * 		  Reference de type Cellule vers la cellule courante.
	 */
	HighLife(Cell<?, ?> d) {
		super(d);
	}

	/**
	 * Methode definissant les regles de vie ou de mort de la cellule courante.
	 * 
	 * @param voisinVivant
	 * 					Entier representant le nombre de voisins vivants de la cellule courante.
	 * 
	 * @return Le nouvel etat de la cellule suite a l'application des regles du jeu HighLife.
	 */
	@Override
	public State regle(int... voisinVivant) {
		if (((voisinVivant[0] == 2 || voisinVivant[0] == 3) && getCell().getState() == LifeState.ALIVE)
				|| ((voisinVivant[0] == 3 || voisinVivant[0] == 6) && getCell().getState() == LifeState.DEAD)) {
			return LifeState.ALIVE;
		} else {
			return LifeState.DEAD;
		}
	}
}