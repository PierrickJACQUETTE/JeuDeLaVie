package model;

/**
 * Interface representant le patron d'une grille.
 * 
 * @see SuperGrille
 *
 * @param <S>L'etat de la cellule.
 * @param <N>La direction du voisin courant de la cellule.
 * @param <C>Reference vers la cellule courante.
 */
public interface Grid<S extends State, N extends Enum<N>, C extends Cell<?, N>> {
	/**
	 * Methode executant une transition de l'automate cellulaire.
	 */
	void update();

	/**
	 * Methode representant l'etat courant de la grille sous forme d'une chaine de caracteres.
	 * 
	 * @return Chaine de caracteres representant l'etat de la grille, en vue de l'affichage.
	 */
	StringBuilder stateAsString();
}