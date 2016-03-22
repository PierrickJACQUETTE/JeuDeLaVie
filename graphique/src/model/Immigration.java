package model;

public class Immigration extends Regle {

	/**
	 * Constructeur de la classe Immigration initilisant une reference de type Cellule vers la cellule courante.
	 * 
	 * @param d
	 * 		  Cellule courante sur laquelle on applique les regles de la variante immigration comportant un troisieme etat.
	 */
	Immigration(Cell<?, ?> d) {
		super(d);
	}

	/**
	 * Methode definissant les conditions pour qu'une cellule vive, naisse ou meurt selon son etat present.
	 * 
	 * @param voisinVivant
	 * 					Entier representant le nombre de voisin vivants de la cellule courante.
	 * 
	 * @return Le nouvel etat de la cellule.
	 */
	@Override
	public State regle(int... voisinVivant) {
		if(getCell().getState() == LifeState.ALIVE && (voisinVivant[0]+voisinVivant[1] == 2 || voisinVivant[0]+voisinVivant[1] == 3)) {
			return LifeState.ALIVE;
		} else if(getCell().getState() == LifeState.DEAD && ((voisinVivant[0]+voisinVivant[1]) == 3)) {
			return LifeState.DAWNING;
		} else if(getCell().getState() == LifeState.DAWNING && (voisinVivant[0]+voisinVivant[1] == 2 || voisinVivant[0]+voisinVivant[1] == 3 && voisinVivant[0] > voisinVivant[1])) {
				return LifeState.ALIVE;
		} else {
			return LifeState.DEAD;
		}
	}
}
