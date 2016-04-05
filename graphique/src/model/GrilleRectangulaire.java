package model;

/**
 * Classe derivant de SuperGrille.
 * Cette classe definit une grille formee de cellules carrees.
 *
 * @see SuperGrille
 */
public class GrilleRectangulaire extends SuperGrille<State, Cellule> {

	/**
	 * Constructeur de la classe GrilleRectangulaire initialisant une grille dont les dimensions sont passees en parametres.
	 * 
	 * @param h
	 * 		  Entier representant la hauteur de la grille.
	 * 
	 * @param l
	 * 		  Entier representant la largeur de la grille.
	 */
	public GrilleRectangulaire(int h, int l) {
		super(h, l);
	}

	/**
	 * Methode ajoutant dans la liste des voisins de la cellule courante la cellule situee au Nord de celle-ci.
	 * 
	 * @param i
	 * 		  Entier definissant la hauteur de la cellule  dans la grille.
	 * 
	 * @param j
	 * 		  Entier definissant la largeur de la cellule  dans la grille.
	 */
	private void north(int i, int j) {
		alphaCell[i][j].setVoisin(SquareGridNbh.NORTH, (i == 0) ? alphaCell[hauteur - 1][j] : alphaCell[i - 1][j]);
	}

	/**
	 * Methode ajoutant dans la liste des voisins de la cellule courante la cellule situee a l'Est de celle-ci.
	 * 
	 * @param i
	 * 		  Entier definissant la hauteur de la cellule  dans la grille.
	 * 
	 * @param j
	 * 		  Entier definissant la largeur de la cellule  dans la grille.
	 */
	private void east(int i, int j) {
		alphaCell[i][j].setVoisin(SquareGridNbh.EAST, (j == largeur - 1) ? alphaCell[i][0] : alphaCell[i][j + 1]);
	}

	/**
	 * Methode ajoutant dans la liste des voisins de la cellule courante la cellule situee a l'Ouest de celle-ci.
	 * 
	 * @param i
	 * 		  Entier definissant la hauteur de la cellule  dans la grille.
	 * 
	 * @param j
	 * 		  Entier definissant la largeur de la cellule  dans la grille.
	 */
	private void west(int i, int j) {
		alphaCell[i][j].setVoisin(SquareGridNbh.WEST, (j == 0) ? alphaCell[i][largeur - 1] : alphaCell[i][j - 1]);
	}

	/**
	 * Methode ajoutant dans la liste des voisins de la cellule courante la cellule situee au Sud de celle-ci.
	 * 
	 * @param i
	 * 		  Entier definissant la hauteur de la cellule  dans la grille.
	 * 
	 * @param j
	 * 		  Entier definissant la largeur de la cellule  dans la grille.
	 */
	private void south(int i, int j) {
		alphaCell[i][j].setVoisin(SquareGridNbh.SOUTH, (i == hauteur - 1) ? alphaCell[0][j] : alphaCell[i + 1][j]);
	}

	/**
	 * Methode ajoutant dans la liste des voisins de la cellule courante la cellule situee au Sud-Est de celle-ci.
	 * 
	 * @param i
	 * 		  Entier definissant la hauteur de la cellule  dans la grille.
	 * 
	 * @param j
	 * 		  Entier definissant la largeur de la cellule  dans la grille.
	 */
	private void southEast(int i, int j) {
		if (i == hauteur - 1 && j == largeur - 1)
			alphaCell[i][j].setVoisin(SquareGridNbh.SOUTH_EAST, alphaCell[0][0]);
		else if (i == hauteur - 1)
			alphaCell[i][j].setVoisin(SquareGridNbh.SOUTH_EAST, alphaCell[0][j + 1]);
		else if (j == largeur - 1)
			alphaCell[i][j].setVoisin(SquareGridNbh.SOUTH_EAST, alphaCell[i + 1][0]);
		else
			alphaCell[i][j].setVoisin(SquareGridNbh.SOUTH_EAST, alphaCell[i + 1][j + 1]);
	}

	/**
	 * Methode ajoutant dans la liste des voisins de la cellule courante la cellule situee au Sud-Ouest de celle-ci.
	 * 
	 * @param i
	 * 		  Entier definissant la hauteur de la cellule  dans la grille.
	 * 
	 * @param j
	 * 		  Entier definissant la largeur de la cellule  dans la grille.
	 */
	private void southWest(int i, int j) {
		if (i == 0 && j == largeur - 1)
			alphaCell[i][j].setVoisin(SquareGridNbh.SOUTH_WEST, alphaCell[hauteur - 1][0]);
		else if (i == 0)
			alphaCell[i][j].setVoisin(SquareGridNbh.SOUTH_WEST, alphaCell[hauteur - 1][j + 1]);
		else if (j == largeur - 1)
			alphaCell[i][j].setVoisin(SquareGridNbh.SOUTH_WEST, alphaCell[i - 1][0]);
		else
			alphaCell[i][j].setVoisin(SquareGridNbh.SOUTH_WEST, alphaCell[i - 1][j + 1]);
	}

	/**
	 * Methode ajoutant dans la liste des voisins de la cellule courante la cellule situee au Nord-Est de celle-ci.
	 * 
	 * @param i
	 * 		  Entier definissant la hauteur de la cellule  dans la grille.
	 * 
	 * @param j
	 * 		  Entier definissant la largeur de la cellule  dans la grille.
	 */
	private void northEast(int i, int j) {
		if (i == hauteur - 1 && j == 0)
			alphaCell[i][j].setVoisin(SquareGridNbh.NORTH_EAST, alphaCell[0][largeur - 1]);
		else if (i == hauteur - 1)
			alphaCell[i][j].setVoisin(SquareGridNbh.NORTH_EAST, alphaCell[0][j - 1]);
		else if (j == 0)
			alphaCell[i][j].setVoisin(SquareGridNbh.NORTH_EAST, alphaCell[i + 1][largeur - 1]);
		else
			alphaCell[i][j].setVoisin(SquareGridNbh.NORTH_EAST, alphaCell[i + 1][j - 1]);
	}

	/**
	 * Methode ajoutant dans la liste des voisins de la cellule courante la cellule situee au Nord-Ouest de celle-ci.
	 * 
	 * @param i
	 * 		  Entier definissant la hauteur de la cellule  dans la grille.
	 * 
	 * @param j
	 * 		  Entier definissant la largeur de la cellule  dans la grille.
	 */
	private void northWest(int i, int j) {
		if (i == 0 && j == 0)
			alphaCell[i][j].setVoisin(SquareGridNbh.NORTH_WEST, alphaCell[hauteur - 1][largeur - 1]);
		else if (i == 0)
			alphaCell[i][j].setVoisin(SquareGridNbh.NORTH_WEST, alphaCell[hauteur - 1][j - 1]);
		else if (j == 0)
			alphaCell[i][j].setVoisin(SquareGridNbh.NORTH_WEST, alphaCell[i - 1][largeur - 1]);
		else
			alphaCell[i][j].setVoisin(SquareGridNbh.NORTH_WEST, alphaCell[i - 1][j - 1]);
	}

	/**
	 * Methode de la classe GrilleRectangulaire parcourant la grille pour initialiser la liste des voisins de chaque cellule.
	 */
	@Override
	protected void initialiseVoisin() {
		for (int i = 0; i < alphaCell.length; i++) {
			for (int j = 0; j < alphaCell[i].length; j++) {
				north(i, j);
				east(i, j);
				west(i, j);
				south(i, j);
				northEast(i, j);
				northWest(i, j);
				southEast(i, j);
				southWest(i, j);
			}
		}
	}

	/**
	 * Methode de la classe GrilleRectangulaire creant une chaine de caracteres representant les etats de toutes les cellules de la grille.
	 * 
	 * @return Une chaine de caracteres contenant les caracteres associes aux etats des cellules de la grille.
	 */
	@Override
	public StringBuilder stateAsString() {
		StringBuilder s = new StringBuilder();
		StringBuilder s2 = new StringBuilder();
		for (Cellule[] c : alphaCell) {
			s.append("| ");
			s2.delete(0, s2.length());
			for (Cellule c2 : c) {
				s.append(c2.getState().toChar() + " | ");
				s2.append("+---");
			}
			s.append("\n" + s2 + "+\n");
		}
		return s2.append("+\n" + s);
	}

}
