package controleur;

import model.GrilleRectangulaire;
import model.LifeState;
import model.SuperGrille;

/**
 * Classe Test permettant d'initialiser une grille selon des parametres predefinis(Dimension, cellules vivante etc).
 *
 */
public class Test {

	/**
	 * Reference statique de type SuperGrille vers une grille qu'on aura predefinit.
	 */
	private static SuperGrille<?, ?> exemple;
	private static int regle;

	/**
	 * Methode statique initialisant la grille en choisissant une configuration parmi celles predefinies.
	 * 
	 * @param i
	 * 		  Entier representant la configuration choisie par l'utilisateur.
	 */
	private static void initialise(int i) {
		switch (i + 1) {
		case 1:
			grille1();
			break;
		case 2:
			grille2();
			break;
		case 3:
			grille3();
			break;
		case 4:
			grille4();
			break;
		case 5:
			grille5();
			break;
		case 6:
			grille6();
			break;
		case 7:
			grille7();
			break;

		}

	}

	/**
	 * Methode statique initialisant la grille courante selon la premiere configuration predefinies pour le jeu de Conway
	 */
	private static void grille1() {
		GrilleRectangulaire a = new GrilleRectangulaire(7, 7);
		a.getGrille()[0][3].setState(LifeState.ALIVE);
		a.getGrille()[1][2].setState(LifeState.ALIVE);
		a.getGrille()[1][4].setState(LifeState.ALIVE);
		a.getGrille()[2][1].setState(LifeState.ALIVE);
		a.getGrille()[2][5].setState(LifeState.ALIVE);
		a.getGrille()[3][0].setState(LifeState.ALIVE);
		a.getGrille()[3][6].setState(LifeState.ALIVE);
		a.getGrille()[4][5].setState(LifeState.ALIVE);
		a.getGrille()[4][1].setState(LifeState.ALIVE);
		a.getGrille()[5][4].setState(LifeState.ALIVE);
		a.getGrille()[5][2].setState(LifeState.ALIVE);
		a.getGrille()[6][3].setState(LifeState.ALIVE);
		regle = 1;
		exemple = a;
	}

	/**
	 * Methode statique initialisant la grille courante selon la deuxieme configuration predefinies pour le jeu de Conway
	 */
	private static void grille2() {
		GrilleRectangulaire a = new GrilleRectangulaire(20, 30);
		
		a.getGrille()[1][8].setState(LifeState.ALIVE);
		a.getGrille()[2][8].setState(LifeState.ALIVE);
		a.getGrille()[3][8].setState(LifeState.ALIVE);
		a.getGrille()[1][7].setState(LifeState.ALIVE);
		a.getGrille()[1][6].setState(LifeState.ALIVE);
		a.getGrille()[2][5].setState(LifeState.ALIVE);
		a.getGrille()[3][4].setState(LifeState.ALIVE);
		a.getGrille()[4][4].setState(LifeState.ALIVE);
		a.getGrille()[5][4].setState(LifeState.ALIVE);
		a.getGrille()[5][5].setState(LifeState.ALIVE);
		a.getGrille()[5][6].setState(LifeState.ALIVE);
		a.getGrille()[4][7].setState(LifeState.ALIVE);
		regle = 1;
		exemple = a;
	}

	/**
	 * Methode statique initialisant la grille courante selon la troisieme configuration predefinies pour le jeu de Conway
	 */
	private static void grille3() {
		GrilleRectangulaire a = new GrilleRectangulaire(11, 11);
		a.getGrille()[2][3].setState(LifeState.ALIVE);
		a.getGrille()[2][7].setState(LifeState.ALIVE);
		a.getGrille()[3][2].setState(LifeState.ALIVE);
		a.getGrille()[3][4].setState(LifeState.ALIVE);
		a.getGrille()[3][6].setState(LifeState.ALIVE);
		a.getGrille()[3][8].setState(LifeState.ALIVE);
		a.getGrille()[4][3].setState(LifeState.ALIVE);
		a.getGrille()[4][5].setState(LifeState.ALIVE);
		a.getGrille()[4][7].setState(LifeState.ALIVE);
		a.getGrille()[5][4].setState(LifeState.ALIVE);
		a.getGrille()[5][6].setState(LifeState.ALIVE);
		a.getGrille()[6][3].setState(LifeState.ALIVE);
		a.getGrille()[6][5].setState(LifeState.ALIVE);
		a.getGrille()[6][7].setState(LifeState.ALIVE);
		a.getGrille()[7][2].setState(LifeState.ALIVE);
		a.getGrille()[7][4].setState(LifeState.ALIVE);
		a.getGrille()[7][6].setState(LifeState.ALIVE);
		a.getGrille()[7][8].setState(LifeState.ALIVE);
		a.getGrille()[8][3].setState(LifeState.ALIVE);
		a.getGrille()[8][7].setState(LifeState.ALIVE);
		regle = 1;
		exemple = a;
	}

	/**
	 * Methode statique initialisant la grille courante selon la quatrieme configuration predefinies pour le jeu de Conway
	 */
	private static void grille4() {
		GrilleRectangulaire a = new GrilleRectangulaire(9, 11);
		a.getGrille()[1][2].setState(LifeState.ALIVE);
		a.getGrille()[1][3].setState(LifeState.ALIVE);
		a.getGrille()[1][7].setState(LifeState.ALIVE);
		a.getGrille()[1][8].setState(LifeState.ALIVE);
		a.getGrille()[2][1].setState(LifeState.ALIVE);
		a.getGrille()[2][2].setState(LifeState.ALIVE);
		a.getGrille()[2][4].setState(LifeState.ALIVE);
		a.getGrille()[2][5].setState(LifeState.ALIVE);
		a.getGrille()[2][6].setState(LifeState.ALIVE);
		a.getGrille()[2][8].setState(LifeState.ALIVE);
		a.getGrille()[2][9].setState(LifeState.ALIVE);
		a.getGrille()[3][1].setState(LifeState.ALIVE);
		a.getGrille()[3][5].setState(LifeState.ALIVE);
		a.getGrille()[3][9].setState(LifeState.ALIVE);
		a.getGrille()[4][2].setState(LifeState.ALIVE);
		a.getGrille()[4][8].setState(LifeState.ALIVE);
		a.getGrille()[5][3].setState(LifeState.ALIVE);
		a.getGrille()[5][7].setState(LifeState.ALIVE);
		a.getGrille()[6][4].setState(LifeState.ALIVE);
		a.getGrille()[6][6].setState(LifeState.ALIVE);
		a.getGrille()[7][5].setState(LifeState.ALIVE);
		regle = 1;
		exemple = a;
	}

	/**
	 * Methode statique initialisant la grille courante selon la cinquieme configuration predefinies pour le jeu de Conway
	 */
	private static void grille5() {
		GrilleRectangulaire a = new GrilleRectangulaire(9, 20);
		a.getGrille()[1][3].setState(LifeState.ALIVE);
		a.getGrille()[2][1].setState(LifeState.ALIVE);
		a.getGrille()[2][5].setState(LifeState.ALIVE);
		a.getGrille()[3][6].setState(LifeState.ALIVE);
		a.getGrille()[4][1].setState(LifeState.ALIVE);
		a.getGrille()[4][6].setState(LifeState.ALIVE);
		a.getGrille()[5][2].setState(LifeState.ALIVE);
		a.getGrille()[5][3].setState(LifeState.ALIVE);
		a.getGrille()[5][4].setState(LifeState.ALIVE);
		a.getGrille()[5][5].setState(LifeState.ALIVE);
		a.getGrille()[5][6].setState(LifeState.ALIVE);

		a.getGrille()[1][11].setState(LifeState.ALIVE);
		a.getGrille()[2][9].setState(LifeState.ALIVE);
		a.getGrille()[2][13].setState(LifeState.ALIVE);
		a.getGrille()[3][14].setState(LifeState.ALIVE);
		a.getGrille()[4][9].setState(LifeState.ALIVE);
		a.getGrille()[4][14].setState(LifeState.ALIVE);
		a.getGrille()[5][10].setState(LifeState.ALIVE);
		a.getGrille()[5][11].setState(LifeState.ALIVE);
		a.getGrille()[5][12].setState(LifeState.ALIVE);
		a.getGrille()[5][13].setState(LifeState.ALIVE);
		a.getGrille()[5][14].setState(LifeState.ALIVE);
		regle = 1;
		exemple = a;
	}

	/**
	 * Methode statique initialisant la grille courante selon la sixieme configuration predefinies pour le jeu de Conway
	 */
	private static void grille6() {
		GrilleRectangulaire a = new GrilleRectangulaire(9, 10);
		a.getGrille()[5][5].setState(LifeState.ALIVE);
		a.getGrille()[5][4].setState(LifeState.ALIVE);
		a.getGrille()[5][3].setState(LifeState.ALIVE);
		a.getGrille()[6][3].setState(LifeState.ALIVE);
		a.getGrille()[7][3].setState(LifeState.ALIVE);
		a.getGrille()[7][4].setState(LifeState.ALIVE);
		a.getGrille()[7][5].setState(LifeState.ALIVE);
		a.getGrille()[7][6].setState(LifeState.ALIVE);
		a.getGrille()[7][7].setState(LifeState.ALIVE);
		a.getGrille()[6][7].setState(LifeState.ALIVE);
		a.getGrille()[5][7].setState(LifeState.ALIVE);

		a.getGrille()[4][7].setState(LifeState.ALIVE);
		a.getGrille()[3][7].setState(LifeState.ALIVE);
		a.getGrille()[3][6].setState(LifeState.ALIVE);
		a.getGrille()[3][5].setState(LifeState.ALIVE);
		a.getGrille()[3][4].setState(LifeState.ALIVE);
		a.getGrille()[3][3].setState(LifeState.ALIVE);
		a.getGrille()[3][2].setState(LifeState.ALIVE);
		a.getGrille()[3][1].setState(LifeState.ALIVE);
		a.getGrille()[4][1].setState(LifeState.ALIVE);
		a.getGrille()[5][1].setState(LifeState.ALIVE);
		a.getGrille()[6][1].setState(LifeState.ALIVE);
		a.getGrille()[7][1].setState(LifeState.ALIVE);
		regle = 1;
		exemple = a;
	}

	/**
	 * Methode statique initialisant la grille courante selon la septieme configuration predefinies pour le jeu de Conway
	 */
	private static void grille7() {
		GrilleRectangulaire a = new GrilleRectangulaire(17, 17);
		a.getGrille()[1][5].setState(LifeState.ALIVE);
		a.getGrille()[2][5].setState(LifeState.ALIVE);
		a.getGrille()[3][5].setState(LifeState.ALIVE);
		a.getGrille()[3][6].setState(LifeState.ALIVE);
		a.getGrille()[3][10].setState(LifeState.ALIVE);
		a.getGrille()[5][1].setState(LifeState.ALIVE);
		a.getGrille()[5][2].setState(LifeState.ALIVE);
		a.getGrille()[5][3].setState(LifeState.ALIVE);
		a.getGrille()[5][6].setState(LifeState.ALIVE);
		a.getGrille()[5][7].setState(LifeState.ALIVE);
		a.getGrille()[5][9].setState(LifeState.ALIVE);

		a.getGrille()[5][10].setState(LifeState.ALIVE);
		a.getGrille()[6][3].setState(LifeState.ALIVE);
		a.getGrille()[6][5].setState(LifeState.ALIVE);
		a.getGrille()[6][7].setState(LifeState.ALIVE);
		a.getGrille()[6][9].setState(LifeState.ALIVE);
		a.getGrille()[7][5].setState(LifeState.ALIVE);
		a.getGrille()[7][6].setState(LifeState.ALIVE);
		a.getGrille()[7][10].setState(LifeState.ALIVE);
		a.getGrille()[9][6].setState(LifeState.ALIVE);
		a.getGrille()[9][10].setState(LifeState.ALIVE);
		a.getGrille()[9][5].setState(LifeState.ALIVE);
		a.getGrille()[10][3].setState(LifeState.ALIVE);
		a.getGrille()[10][5].setState(LifeState.ALIVE);
		a.getGrille()[10][7].setState(LifeState.ALIVE);
		a.getGrille()[10][9].setState(LifeState.ALIVE);
		a.getGrille()[11][1].setState(LifeState.ALIVE);
		a.getGrille()[11][2].setState(LifeState.ALIVE);
		a.getGrille()[11][3].setState(LifeState.ALIVE);
		a.getGrille()[11][6].setState(LifeState.ALIVE);
		a.getGrille()[11][7].setState(LifeState.ALIVE);
		a.getGrille()[11][9].setState(LifeState.ALIVE);
		a.getGrille()[11][10].setState(LifeState.ALIVE);

		a.getGrille()[13][5].setState(LifeState.ALIVE);
		a.getGrille()[13][6].setState(LifeState.ALIVE);
		a.getGrille()[13][10].setState(LifeState.ALIVE);
		a.getGrille()[14][5].setState(LifeState.ALIVE);
		a.getGrille()[15][5].setState(LifeState.ALIVE);
		a.getGrille()[14][11].setState(LifeState.ALIVE);
		a.getGrille()[15][11].setState(LifeState.ALIVE);
		a.getGrille()[1][11].setState(LifeState.ALIVE);
		a.getGrille()[2][11].setState(LifeState.ALIVE);
		a.getGrille()[3][11].setState(LifeState.ALIVE);
		a.getGrille()[5][13].setState(LifeState.ALIVE);

		a.getGrille()[5][14].setState(LifeState.ALIVE);
		a.getGrille()[5][15].setState(LifeState.ALIVE);
		a.getGrille()[6][11].setState(LifeState.ALIVE);
		a.getGrille()[6][13].setState(LifeState.ALIVE);
		a.getGrille()[7][11].setState(LifeState.ALIVE);
		a.getGrille()[9][11].setState(LifeState.ALIVE);
		a.getGrille()[10][11].setState(LifeState.ALIVE);
		a.getGrille()[13][11].setState(LifeState.ALIVE);

		a.getGrille()[10][13].setState(LifeState.ALIVE);
		a.getGrille()[11][13].setState(LifeState.ALIVE);
		a.getGrille()[11][15].setState(LifeState.ALIVE);
		a.getGrille()[11][14].setState(LifeState.ALIVE);
		regle = 1;
		exemple = a;
	}

	/**
	 * Accesseur statique permettant d'acceder a la reference de la grille courante.
	 * 
	 * @param i
	 * 		  Entier identifiant la configuration choisie par l'utilisateur.
	 * 
	 * @return Une reference de type SuperGrille vers la grille nouvellement initialisee.
	 */
	public static SuperGrille<?, ?> getGrille(int i) {
		initialise(i);
		return exemple;
	}
	
	public static int getRegle() {
		return regle;
	}

}
