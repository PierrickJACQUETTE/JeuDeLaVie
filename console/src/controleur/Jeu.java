package controleur;

import java.util.Scanner;
import model.GrilleHexagonale;
import model.GrilleRectangulaire;
import model.LifeState;
import model.Regle;
import model.SuperGrille;


/**
 * Classe Jeu representant une partie a laquelle assiste l'utilisateur.
 * Cette classe gere tout ce qui est dialogue avec l'utilisateur,
 * initialisation de la grille en fonction des choix de l'utilisateur
 * Affichages successifs des differents etats de la grille et fin de partie.
 *
 */
public class Jeu {

	/**
	 * Reference vers un objet de type SuperGrille representant la grille courante.
	 */
	private SuperGrille<?, ?> alpha;
	
	/**
	 * Reference vers un objet de type Scanner permettant la lecture clavier.
	 */
	private Scanner sc;

	/**
	 * Constructeur de la clase Jeu lançant une partie.
	 * Il y a initialisation de la grille dans son etat initial.
	 * Tant qu'il y a au moins une cellule vivante on met a jour la grille et on l'affiche.
	 * Entre chaque affichage il y a un temps d'attente calcule en fonction du temps d'execution.
	 * 
	 * @throws InterruptedException Si jamais le programme est interrompu.
	 */
	public Jeu() throws InterruptedException {
		initialisation();
		int generation = 0;
		affiche(generation++);
		int tempsExecution = 0;
		int tempsAttente = 3000;
		while (alpha.OneCellALone()) {
			if (tempsAttente - tempsExecution < 0) {
				Thread.sleep(0);
			} else {
				Thread.sleep(tempsAttente - tempsExecution);
			}
			long debut = System.currentTimeMillis();
			alpha.update();
			affiche(generation++);
			long fin = System.currentTimeMillis();
			tempsExecution = (int) (fin - debut);
		}
		creer();
		recommencer();
	}

	/**
	 * Methode permettant a l'utilisateur de creer lui-meme la grille ou bien d'importer une configuration predefinie.
	 */
	private void initialisation() {
		sc = new Scanner(System.in);
		int configuration = initConditionAOuRJOuGVOuC(
				"Voulez-vous creer votre configuration ou importer?\n Creer(1) ou Importer(2)");
		if (configuration == 2) {
			int laquelle = initConditionLaquelle("Quelle configuration ?\n 0 ou 1 ou 2 ou 3 ou .... 6");
			alpha = Test.getGrille(laquelle);
			Regle.setRegle(Test.getRegle());
		}
		if (configuration == 1) {
			creer();
		}

	}

	/**
	 * Methode permettant a l'utilisateur de relancer une partie ou bien d'arreter.
	 * 
	 * @throws InterruptedException Si jamais le programme est interrompu.
	 */
	private void recommencer() throws InterruptedException {
		int jeu = initConditionAOuRJOuGVOuC("Voulez-vous recommencer\n Oui(1) ou Non(2)");
		if (jeu == 1) {
			new Jeu();
		}
	}

	/**
	 * Methode permettant la collecte des informations suivantes aupres de l'utilisateur :
	 * - Dimensions de la grille.
	 * - Type de jeu.
	 * - Topologie de la grille.
	 * - Placement aleatoire ou manuel (par l'utilisateur) des cellules vivantes au sein de la grille.
	 */
	private void creer() {
		int largeur = initConditionTaille("Taille de la largeur : ");
		int hauteur = initConditionTaille("Taille de la hauteur : ");
		int variante = initConditionVariante("Quel variante ? :\n Conway(1) ou Day&Night(2) ou HighLife(3) ou Immigration(4)");
		int type = initConditionAOuRJOuGVOuC("Quel type de grille ?\n Rectangulaire(1) ou Hexagonale(2)");
		Regle.setRegle(variante);
		alpha = (type == 1) ? new GrilleRectangulaire(hauteur, largeur) : new GrilleHexagonale(hauteur, largeur);
		int aleatoire = initConditionAOuRJOuGVOuC("Voulez-vous un placement aleatoire ?\n Oui(1) ou Non(2)");
		if (aleatoire == 1) {
			aleatoire1();
		}
		if (aleatoire == 2) {
			aleatoire2();
		}
	}

	/**
	 * Methode laissant le choix des dimensions de la grille par l'utilisateur.
	 * Tant que l'utilisateur saisira des dimensions négatives il y a redemande de saisie.
	 * 
	 * @param string
	 * 			  Chaine de caracteres proposant a l'utilisateur de saisir une dimension pour la grille.
	 * 
	 * @return Un entier representant la hauteur ou la largeur de la grille qui sera cree.
	 */
	private int initConditionTaille(String string) {
		int taille;
		do {
			System.out.println(string);
			taille = sc.nextInt();
		} while (!noTaille(taille));
		return taille;
	}

	/**
	 * Methode traitant le choix du type de la grille ainsi que du placement aleatoire ou non par l'utilisateur.
	 * Tant que l'utilisateur saisira une mauvaise information, il y a redemande de saisie.
	 * 
	 * @param string
	 * 			  Chaine de caracteres proposant a l'utilisateur de choisir le type de grille ou bien le placement aleatoire ou a la main.
	 * 
	 * @return Un entier representant le type de grille qui sera initialisee ou bien le placement aleatoire ou non des cellule vivantes dans la grille.
	 */
	private int initConditionAOuRJOuGVOuC(String string) {
		int taille = 1;
		do {
			System.out.println(string);
			taille = sc.nextInt();
		} while (!aleaOuReJeuOuGrilleValOuConfig(taille));
		return taille;
	}

	/**
	 * Methode traitant les choix parmi deux possibilites comme le choix de la topologie de la grille ou bien du placement aleatoire ou manuelle.
	 * Tant que l'utilisateur saisira une mauvaise information, il y a redemande de saisie.
	 * 
	 * @param string
	 * 			  Chaine de caracteres proposant a l'utilisateur de choisir parmi deux possibilites.
	 * 
	 * @return Un entier representant le choix de l'utilisteur, soit 1 soit 2.
	 */
	private int initConditionLaquelle(String string) {
		int taille = 0;
		do {
			System.out.println(string);
			taille = sc.nextInt();
		} while (!laquelle(taille));
		return taille;
	}

	/**
	 * Methode traitant le choix de la largeur de la grille par l'utilisateur.
	 * Tant que l'utilisateur saisira une mauvaise information, il y a redemande de saisie.
	 * 
	 * @param string
	 * 			  Chaine de caracteres proposant a l'utilisateur de choisir la largeur de la grille qui sera cree.
	 * 
	 * @return Un entier representant la largeur de la future grille.
	 */
	private int initConditionLa(String string) {
		int taille = 0;
		do {
			System.out.println(string);
			taille = sc.nextInt();
		} while (!taille(taille, alpha.getLargeur()));
		return taille;
	}

	/**
	 * Methode traitant le choix du pourcentage de cellules vivantes lors de la premiere generation dans la future grille.
	 * Tant que l'utilisateur saisira une mauvaise information, il y a redemande de saisie.
	 * 
	 * @param string
	 * 			  Chaine de caracteres proposant a l'utilisateur de choisir le pourcentage de cellules vivantes au depart.
	 * 
	 * @return Un entier representant le pourcentage de cellules vivantes qui seront placees dans la grille.
	 */
	private int initConditionPo(String string) {
		int taille = 0;
		do {
			System.out.println(string);
			taille = sc.nextInt();
		} while (!pourcentage(taille));
		return taille;
	}

	/**
	 * Methode traitant le choix de la hauteur de la grille par l'utilisateur.
	 * Tant que l'utilisateur saisira une mauvaise information, il y a redemande de saisie.
	 * 
	 * @param string
	 * 			  Chaine de caractere proposant a l'utilisateur de choisir la hauteur de la grille qui sera cree.
	 * 
	 * @return Un entier representant la hauteur de la future grille.
	 */
	private int initConditionHa(String string) {
		int taille = 0;
		do {
			System.out.println(string);
			taille = sc.nextInt();
		} while (!taille(taille, alpha.getHauteur()));
		return taille;
	}

	/**
	 * Methode demandant a l'utilisateur de saisir l'une des deux dimensions de la nouvelle grille.
	 * Tant que la saisie est incorrect il y a redemande de saisie.
	 * 
	 * @param string
	 * 			  Chaine de caracteres formulant les choix qui s'offrent a l'utilisateur.
	 *  
	 * @return Un entier representant l'une des deux dimensions de la future grille.
	 */
	private int initConditionCV(String string) {
		int taille = 0;
		do {
			System.out.println(string);
			taille = sc.nextInt();
		} while (!caseVivNoJuste(alpha.getHauteur() * alpha.getLargeur(), taille));
		return taille;
	}

	/**
	 * Methode traitant le choix du type de jeu par l'utilisateur.
	 * Tant que l'utilisateur saisira une mauvaise information, il y a redemande de saisie.
	 * 
	 * @param string
	 * 			  Chaine de caracteres proposant a l'utilisateur de choisir le type de jeu.
	 * 
	 * @return Un entier representant le type de jeu qui sera lance.
	 */
	private int initConditionVariante(String string) {
		int valeur = 0;
		do {
			System.out.println(string);
			valeur = sc.nextInt();
		} while (!valeur(valeur));
		return valeur;
	}

	/**
	 * Methode verifiant qu'a la position courante il n'est pass deja une cellule vivante.
	 * 
	 * @param i
	 * 		  Entier representant la largeur a laquelle se situe la cellule dans la grille.
	 * 
	 * @param j
	 * 		 Entier representant la hauteur a laquelle se situe la cellule dans la grille.
	 * 
	 * @return Vrai si une cellule vivante peut être placae a la position i,j au sein de la grille en court d'initialisation.
	 */
	private boolean caseVivantePossible(int i, int j) {
		return (alpha.getGrille()[i][j].getState() == LifeState.ALIVE) ? false : true;
	}

	/**
	 * Methode testant si la taille saisie est correcte.
	 * 
	 * @param i
	 * 		 Entier representant la taille choisie par l'utilisateur.
	 * 
	 * @param taille
	 * 			  Entier representant la tailel maximale raisonnable pour qu'il n'y ai pas de depassement de memoire.
	 * 
	 * @return Vrai si la taille verifie les caracteristiques choisies.
	 */
	private boolean taille(int i, int taille) {
		return (!noTaille(i) || i >= taille) ? false : true;
	}

	/**
	 * Methode verifiant que la dimension de la future grille n'est pas negative.
	 * 
	 * @param i
	 * 		  Entier representant la taille saisie par l'utilisateur.
	 * 
	 * @return Vrai si la taille saisie est correcte sinon False.
	 */
	private boolean noTaille(int i) {
		if (i < 0) {
			System.out.println("Taille non valide");
			return false;
		}
		return true;
	}

	/**
	 * Methode verifiant que l'utilisateur n'a pas saisi un nombre negatif de cellules vivantes ou bien supérieur
	 * au nombre de cellules dans la grille.
	 * 
	 * @param taille
	 * 			  Entier representant le nombre de cellules dans la grille.
	 * 
	 * @param nb
	 * 		   Entier representant le nombre de cellules vivantes que veut placer l'utilisateur au sein de la grille.
	 * 
	 * @return Vrai si l'utilisateur a saisi une information correcte.
	 */
	private boolean caseVivNoJuste(int taille, int nb) {
		if (nb > taille) {
			System.out.println("Trop de cases vivantes pour la taille du plateau !!! \n Recommencez !");
			return false;
		}
		if (nb < 0) {
			System.out.println("Nombre de cases vivantes négatives !! \n Recommencez !");
			return false;
		}
		return true;
	}

	/**
	 * Methode verifiant que l'utilisateur a bien choisie un l'un des deux types de grille existants.
	 * Cette methode verifie aussi que l'utilisateur a choisi soit un placement aléatoire ou bien manuel
	 * des cellules vivantes au sein de la nouvelle grille.
	 * 
	 * @param alea
	 * 			 Entier representant le choix de l'utilisateur.
	 * 
	 * @return Vrai si l'utilisateur a saisi une information reconnue par le programme.
	 */
	private boolean aleaOuReJeuOuGrilleValOuConfig(int alea) {
		if (alea == 1 || alea == 2) {
			return true;
		}
		System.out.println("Entier non reconnu, rentrer l'entier qui correspond à la réponse");
		return false;
	}

	/**
	 * Methode verifiant le choix de la configuration predefinie.
	 * Si l'utilisateur saisi une mauvaise information.
	 * 
	 * @param alea
	 * 			 Entier representant le numero de la configuration predefinie choisie.
	 * 
	 * @return Vrai si l'utilisateur a choisie une des configurations existantes.
	 */
	private boolean laquelle(int alea) {
		if (alea >= 0 && alea < 7) {
			return true;
		}
		System.out.println("Entier non reconnu, rentrer l'entier qui correspond à la réponse");
		return false;
	}

	/**
	 * Methode verifiant que le pourcentage saisi par l'utilisateur est correct.
	 * 
	 * @param pourcent
	 * 				 Entier representant le pourcentage saisi par l'utilisateur.
	 * 
	 * @return True si le pourcentage est correct False dans le cas contraire.
	 */
	private boolean pourcentage(int pourcent) {
		if (pourcent < 0 || pourcent > 100) {
			System.out.println("Rentrer un entier entre 0 et 100 inclus !");
			return false;
		}
		return true;
	}

	/**
	 * Methode verifiant que l'utilisateur à bien choisi un mode de jeu correct.
	 * 
	 * @param i
	 * 		  Entier representant le mode de jeu choisi par l'utilisateur.
	 * 
	 * @return Vrai si l'utilisateur n'a pas saisi une valeur erronee.
	 */
	private boolean valeur(int i) {
		if (i == 1 || i == 3 || i == 2 || i == 4) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Methode initialisant la grille en plaçant aleatoirement les cellules vivantes.
	 * Le nombre de cellules placees est defini selon un pourcentage que l'utilisateur
	 * aura choisi.
	 */
	private void aleatoire1() {
		int pourcentage = initConditionPo("Combien de pourcentage de cellules vivantes ?");
		int nbCellViv = (pourcentage * alpha.getHauteur() * alpha.getLargeur()) / 100;
		int c = 0;
		while (c != nbCellViv) {
			int i = (int) (Math.random() * alpha.getHauteur());
			int j = (int) (Math.random() * alpha.getLargeur());
			if (alpha.getGrille()[i][j].getState() == LifeState.DEAD) {
				alpha.getGrille()[i][j].setState(LifeState.ALIVE);
				c++;
			}
		}
	}

	/**
	 * Methode initialisant la grille en plaçant les cellules vivantes selon les ccoordonnees
	 * saisies par l'utilisateur.
	 * Pour chaque cellule a placer, l'utilisateur saisi la largeur puis la hauteur auxquelles 
	 * il souhaite placer la cellule courante.
	 */
	private void aleatoire2() {
		int nbCViv = initConditionCV("Combien de cases vivantes ?");
		for (int i = 0; i < nbCViv; i++) {
			int posLargeur = 0;
			int posHauteur = 0;
			do {
				posLargeur = initConditionLa("Ou voulez-vous mettre la case vivante " + i + "\n largeur ? : ");
				posHauteur = initConditionHa("hauteur ? :");
			} while (!caseVivantePossible(posHauteur, posLargeur));
			alpha.getGrille()[posHauteur][posLargeur].setState(LifeState.ALIVE);
		}
	}

	/**
	 * Methode affichant sous forme graphique la grille courante ainsi que le numero de la generation en cours.
	 * 
	 * @param generation
	 * 				   Entier representant le numero de la generation a afficher.
	 */
	private void affiche(int generation) {
		System.out.print(alpha.stateAsString());
		System.out.println("Generation  : " + generation + "\n");
	}
}