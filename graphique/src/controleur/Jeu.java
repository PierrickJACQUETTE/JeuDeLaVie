package controleur;

import model.GrilleHexagonale;
import model.GrilleRectangulaire;
import model.LifeState;
import model.Regle;
import model.SuperGrille;
import vue.Vue;

/**
 * Classe initialisant la grille selon les choix de l'utilisateur et g�rant le cours d'une partie.
 *
 */
public class Jeu {

	/**
	 * Reference sur la vue courante de la partie en cours.
	 */
	private Vue vue;
	
	/**
	 * Entier representant la largeur de la grille (valeur par defaut 4 colonnes).
	 */
	private int largeur = 4;
	
	/**
	 * Entier representant la hauteur de la grille (valeur par defaut 3 lignes).
	 */
	private int hauteur = 3;
	
	/**
	 * Entier permettant de compter le nombre de generations passees depuis le debut de la partie(valeur par defaut 0).
	 */
	private int generation = 0;
	
	/**
	 * Reference sur la grille courante.
	 */
	private SuperGrille<?, ?> tableau;
	
	/**
	 * Entier identifiant la topologie de la grille courante.
	 * 1-Rectangulaire.
	 * 2-Hexagonal.
	 */
	private int typeGrille = 1;  
	
	/**
	 * Entier identifiant la regle appliquee dans le cadre du jeu choisi.
	 * 1-Conway.
	 * 2-DayAndNight.
	 * 3-HighLife.
	 */
	private int regle = 1;
	
	/**
	 * Entier representant le pourcentage de cellules vivantes a la premiere generation (50% par defaut).
	 */
	private double defaultPourcentage = 50;

	/**
	 * Booleen permettant de savoir si la partie est en cours.
	 */
	private boolean run;

	/**
	 * Premier constructeur de la classe Jeu initialisant la grille, les regles qui seront utilisees et la vue.
	 * 
	 * @param args
	 * 		     Tableau de chaines de caracteres representant les parametres de la parties.
	 */
	public Jeu(String[] args) {
		initialisationGrille(largeur, hauteur);
		Regle.setRegle(regle);
		vue = new Vue();
		vue.mainVue(args);
	}

	/**
	 * Second constructeur de la classe Jeu lancant l'initialisation de la grille.
	 */
	public Jeu() {
		initialisationGrille();
	}

	/**
	 * Premiere methode d'initialisation de la grille.
	 * Cette methode appelle la seconde methode d'initialisation de la grille.
	 * 
	 * @see initialisationGrille
	 * 
	 * @param l
	 * 		  Entier representant la hauteur de la grille.	
	 * @param h
	 * 		  Entier representant la longueur de la grille.
	 */
	private void initialisationGrille(int l, int h) {
		largeur = l;
		hauteur = h;
		initialisationGrille();
	}

	/**
	 * Seconde methode d'initialisation de la grille.
	 * Cette methode prend en compte les choix de l'utilisateur a propos des proprietes de la grille(topologie de la grille, placement aleatoire des cellules vivantes).
	 */
	private void initialisationGrille() {
		if (typeGrille == 1) {
			tableau = new GrilleRectangulaire(hauteur, largeur);
		}
		if (typeGrille == 2) {
			tableau = new GrilleHexagonale(hauteur, largeur);
		}
		Regle.setRegle(regle);
		int nbCellViv = (int) ((defaultPourcentage * tableau.getHauteur() * tableau.getLargeur()) / 100);
		int c = 0;
		while (c != nbCellViv) {
			int i = (int) (Math.random() * tableau.getHauteur());
			int j = (int) (Math.random() * tableau.getLargeur());
			if (tableau.getGrille()[i][j].getState() == LifeState.DEAD) {
				tableau.getGrille()[i][j].setState(LifeState.ALIVE);
				c++;
			}
		}
	}

	/**
	 * Methode testant la topologie de la grille et appelant la premiere methode d'initialisation d'une grille.
	 * 
	 * @param l
	 * 		  Hauteur de la grille.
	 * @param h
	 * 		  Largeur de la grille.
	 */		 
	public void actionTaille(int l, int h) {
		if (typeGrille == 1) {
			initialisationGrille(l, h);
		}
		if (typeGrille == 2) {
			initialisationGrille(l, h);
		}
		Regle.setRegle(regle);
	}

	/**
	 * Methode attribuant un identifiant entier a la regle du type de jeu choisi par l'utilisateur.
	 * 
	 * @param s
	 * 		  Chaine de caracteres representant le nom du jeu choisi par l'utilisateur.
	 */
	public void actionRegle(String s) {
		if (s.equals("Conway")) {
			regle = 1;
		} else if (s.equals("Day")) {
			regle = 2;
		} else if (s.equals("High")) {
			regle = 3;
		}else if(s.equals("Immigration")){
			regle =4;
		}
	}

	/**
	 * Methode attribuant un identifiant entier a la topologie de la grille choisie par l'utilisateur.
	 * 
	 * @param s
	 * 		  Chaine de caracteres representant la topologie de la grille choisie par l'utilisateur.
	 */
	public void actionGrille(String s) {
		if (s.equals("Rectangulaire")) {
			typeGrille = 1;
		} else if (s.equals("Hexagonal")) {
			typeGrille = 2;
		}
		initialisationGrille();
	}

	/**
	 * Methode lancant l'initialisation de la grille en prenant compte du pourcentage de cellules vivantes a la premier generation choisi par l'utilisateur.
	 * 
	 * @param a
	 * 		  Double representant le pourcentage de cellules vivantes lors de la premiere generation.
	 */
	public void actionPourcentage(double a) {
		defaultPourcentage = a;
		initialisationGrille();
	}

	/**
	 * Methode important une des grille predefinie.
	 * 
	 * @param a
	 * 		  Entier identifiant la grille predefinie choisie par l'utilisateur.
	 */
	public void actionImporter(int a) {
		tableau = Test.getGrille(a - 1);
		regle = Test.getRegle();

	}

	/**
	 * Methode lancant la mise a jour du compteur de generations.
	 */
	public void actionRun() {
		generation++;
	}

	/**
	 * Methode  lancant l'initialisation de la grille sans parametres particuliers.
	 */
	public void actionAleatoire() {
		initialisationGrille();
	}

	/**
	 * Methode permettant de recommencer la partie. Il y a remise a zero du compteur de generation et initialisation de la nouvelle grille.
	 */
	public void actionRecommencer(){
		generation=0;
		initialisationGrille();
	}
	
	/**
	 * Accesseur permettant d'acceder a la grille courante.
	 * 
	 * @return Une reference vers la grille courante.
	 */
	public SuperGrille<?, ?> getGrilleJeu() {
		return tableau;
	}

	/**
	 * Mutateur permettant de faire pointer notre reference vers une nouvelle grille.
	 * 
	 * @param a
	 * 		  Nouvelle grille pointee.
	 */
	public void setGrilleJeu(SuperGrille<?, ?> a) {
		tableau = a;
	}
	
	/**
	 * Mutateur permettant de modifier l'etat d'une cellule precise de la grilel courante.
	 * 
	 * @param a
	 * 		  Nouvel etat de la cellule.
	 * @param i
	 * 		  Ligne a laquelle la cellule appartient au sein de la grille.
	 * @param j
	 * 		  Colonne a laquelle la cellule appartient au sein de la grille.
	 */
	public void setGrilleCaseJeu(LifeState a, int i, int j) {
		tableau.getGrille()[i][j].setState(a);
	}

	/**
	 * Accesseur permettant d'acceder a la topologie courante de la grille.
	 * 
	 * @return Entier identifiant la topologie de la grille.
	 */
	public int getTypeDeGrilleJeu() {
		return typeGrille;
	}

	/**
	 * Mutateur permettant de modifier la topologie de la grille.
	 * 
	 * @param a
	 *        Entier identifiant la topologie choisie.
	 */
	public void setTypeDeGrilleJeu(int a) {
		typeGrille = a;
	}

	/**
	 * Accesseur permettant de recuperer le nombre de generations passees jusqu'a maintenant dans la partie.
	 * 
	 * @return Entier representant le nombre de tours (generations) effectues jusqu'a maintenant dans la partie.
	 */
	public int getGeneration() {
		return generation;
	}

	/**
	 * Accesseur permettant de recuperer l'identifiant de la regle courante.
	 * 
	 * @return Entier identifiant la regle courante.
	 */
	public int getRegle() {
		return regle;
	}

	/**
	 * Mutateur permettant de changer l'identifiant entier d'une regle.
	 * 
	 * @param a
	 * 		  Entier permettant d'identifier de maniere unique la regle choisie.
	 */
	public void setRegle(int a) {
		regle = a;
	}

	/**
	 * Accesseur permettant de recuperer le pourcentage choisi par l'utilisateur.
	 * 
	 * @return Double representant le pourcentage de cellules vivantes a la premiere generation.
	 */
	public double getPoucentage() {
		return defaultPourcentage;
	}

	/**
	 * Methode testant si la partie est en court.
	 * 
	 * @return Vrai si une partie est en court, Faux dans le cas contraire.
	 */
	public boolean getRun() {
		return run;
	}

	/**
	 * Mutateur de l'entier generation permettant de mettre a jour le compteur de generations de la partie courante.
	 */
	public void setGeneration() {
		generation++;
	}

}
