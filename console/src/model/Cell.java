package model;

/**
 * Interface representant le patron d'une cellule.
 * 
 * @see Cellule
 *
 * @param <S> Type representant l'etat de la cellule courante.
 * @param <N> Type representant la direction du voisin courant de la cellule.
 */
public interface Cell<S extends State, N extends Enum<N>> {
	/**
	 * Accesseur de l'etat de la cellule.
	 * 
	 * @return L'etat courant de la cellule.
	 */
	S getState();

	/**
	 * Mutateur de l'etat de la cellule.
	 * 
	 * @param state
	 *            Nouvel etat de la cellule.
	 */
	void setState(S state);

	/**
	 * Accesseur de l'etat de la cellule pour la generation suivante.
	 * 
	 * @return L'etat courant de la cellule pour la  generation suivante.
	 */
	S getStateTourSuivant();

	/**
	 * Mutateur de l'etat de la cellule pour la generation suivante.
	 * 
	 * @param state
	 *            Nouvel etat de la cellule pour la generation suivante.
	 */
	void setStateTourSuivant(S state);

	/**
	 * @param direction
	 *               Direction du voisin que l'on cherche.
	 *               
	 * @return Reference vers la cellule voisine dans la direction desiree.
	 */
	Cell<S, N> getNeighbor(N direction);

	/**
	 * Simule une transition, sans changer l'etat courant de l'objet.
	 * 
	 * @return Le prochain etat de la cellule apres avoir effectue une transition.
	 */
	S nextState();

	/**
	 * Mutateur du voisin courant de la cellule.
	 * 
	 * @param east, la direction du voisin par rapport à la cellule courante.
	 * @param c, la reference vers la cellule courante.
	 */
	void setVoisin(SquareGridNbh east, Cell<S, N> c);

}
