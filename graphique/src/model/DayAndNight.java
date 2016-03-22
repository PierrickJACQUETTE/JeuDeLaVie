package model;

/**
 * Classe etendant la classe Regle et définissant les regles du jeu DayAndNight.
 * 
 * @see Regle
 */
public class DayAndNight extends Regle {

	/**
	 * Constructeur de la classe DayAndNight initialisant une reference vers la cellule courante.
	 * 
	 * @param d
	 * 		  Reference de type Cellule vers la cellule courante.
	 */
	DayAndNight(Cell<?, ?> d) {
		super(d);
	}

	
	/**
	 * Methode definissant les regles de vie ou de mort de la cellule courante.
	 * 
	 * @param voisinVivant
	 * 					Entier representant le nombre de voisins vivant de la cellule courante.
	 * 
	 * @return Le nouvel etat de la cellule suite a l'application des regles du jeu DayAndNight.
	 */
	@Override
	public State regle(int... voisinVivant) {
		if (((voisinVivant[0] == 3 || voisinVivant[0] == 4 || voisinVivant[0] == 6 || voisinVivant[0] == 7 || voisinVivant[0] == 8)
				&& getCell().getState() == LifeState.ALIVE)
				|| ((voisinVivant[0] == 3 || voisinVivant[0] == 6 || voisinVivant[0] == 7 || voisinVivant[0] == 8)
						&& getCell().getState() == LifeState.DEAD)) {
			return LifeState.ALIVE;
		} else {
			return LifeState.DEAD;
		}
	}
}
