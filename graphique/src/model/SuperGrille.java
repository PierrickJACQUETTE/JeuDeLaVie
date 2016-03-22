package model;

/**
 * Classe abstraite représentant le patron d'une grille.
 * Cette classe implémente l'interface Grid.
 * 
 * @see Grid
 * @see GrilleRectangulaire
 * @see GrilleHexagonale
 *
 * @param <S> Etats possible pour une cellule de la grille.
 * @param <C> Cellules de la grille.
 */
public abstract class SuperGrille <S extends State, C extends Cellule> implements Grid<S,SquareGridNbh,C>{
	
	/**
	 * Référence vers un tableau bidimensionnel de cellules, représentant la grille courante. 
	 */
	protected Cellule[][] alphaCell;
	
	/**
	 * Entier representant la hauteur de la grille courante.
	 */
	protected int hauteur;
	
	/**
	 * Entier representant la largeur de la grille courante.
	 */
	protected int largeur;
	
	/**
	 * Constructeur de la classe SuperGrille creant un tableau a deux dimensions de cellules.
	 * 
	 * @param h, entier representant la hauteur de la grille.
	 * @param l, entier representant la longueur de la grille.
	 */
	public SuperGrille(int h, int l) {
		hauteur = h;
		largeur = l;
		alphaCell = new Cellule[hauteur][largeur];
		initialise();
	}
	
	/**
	 * Methode initialisant toutes les cellules de la grille en leur attribuant l'etat mort.
	 */
	private void initialise() {
		for (int i = 0; i < alphaCell.length; i++) {
			for (int j = 0; j < alphaCell[i].length; j++) {
				alphaCell[i][j] = new Cellule(LifeState.DEAD);
			}
		}
		initialiseVoisin();
	}
	
	/**
	 * Methode abstraite initialisant les voisin d'une cellule.
	 */
	protected abstract void initialiseVoisin();
	
	/**
	 * Methode abstraite creant une chaine de caracteres pour representer l'etat de la grille.
	 * L'etat de la grille est l'ensemble des etats de chaque cellule de la grille.
	 * 
	 * @see stateAsString
	 */
	@Override
	public abstract StringBuilder stateAsString();
	
	/**
	 * Methode retournant un booleen en fonction de l'etat courant de la grille.
	 * 
	 * @return True si au moins une cellule est vivante dans la grille, sinon renvoi False.
	 */
	public boolean OneCellALone() {
		for (Cell<?, ?> c[] : alphaCell) {
			for (Cell<?, ?> c2 : c) {
				if (c2.getState() == LifeState.ALIVE) {
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * Accesseur renvoyant la largeur de la grille.
	 * 
	 * @return Un entier representant largeur de la grille.
	 */
	public int getLargeur() {
		return largeur;
	}

	/**
	 * Accesseur renvoyant la hauteur de la grille.
	 *  
	 * @return Un entier representant la hauteur de la grille.
	 */
	public int getHauteur() {
		return hauteur;
	}

	/**
	 * Accesseur renvoyant une reference vers la grille courante.
	 * 
	 * @return Un tableau bidimensionnel de cellules representant la grille.
	 */
	public Cellule[][] getGrille() {
		return alphaCell;
	}
	
	/**
	 * Methode mettant a jour les etats des cellules de la grille pour la generation suivante.
	 * Parcourt la grille pour changer le prochain etat de la cellule courante.
	 * Parcourt une seconde fois la grille pour changer l'etat present de la cellule courante.
	 */
	@Override
	public void update() {
		for (int i = 0; i < alphaCell.length; i++) {
			for (int j = 0; j < alphaCell[i].length; j++) {
				alphaCell[i][j].setStateTourSuivant(alphaCell[i][j].nextState());
				;
			}
		}
		for (int i = 0; i < alphaCell.length; i++) {
			for (int j = 0; j < alphaCell[i].length; j++) {
				alphaCell[i][j].setState(alphaCell[i][j].getStateTourSuivant());
			}
		}
	}
}
