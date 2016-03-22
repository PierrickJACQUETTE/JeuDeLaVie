package model;

/**
 * Classe etendant la classe Regle, definissant les conditions de vie ou de mort d'une cellule
 * selon les proprietes definies par Conway.
 * 
 * @see Regle
 */
public class Conway extends Regle {

	/**
	 * Constructeur de la classe Conway initilisant une reference de type Cellule vers la cellule courante.
	 * 
	 * @param d
	 * 		  Cellule courante sur laquelle on applique les regles de Conway.
	 */
	Conway(Cell<?, ?> d) {
		super(d);
	}

	/**
	 * Methode definissant les conditions pour qu'une cellule vive ou meurt selon son etat present.
	 * 
	 * @param voisinVivant
	 * 					Entier representant le nombre de voisin vivants de la cellule courante.
	 * 
	 * @return Le nouvel etat de la cellule.
	 */
	@Override
	public State regle(int... voisinVivant) {
		if (((voisinVivant[0] == 2 || voisinVivant[0] == 3) && getCell().getState() == LifeState.ALIVE)
				|| (voisinVivant[0] == 3 && getCell().getState() == LifeState.DEAD)) {
			return LifeState.ALIVE;
		} else {
			return LifeState.DEAD;
		}

	}
}
