package model;

import java.util.HashMap;
import java.util.Map.Entry;

/**
 * Classe implementant l'interface Cell, representant le type Cellule. 
 * Une grille est entierement remplie de cellules.
 * 
 * @see Cell
 */
public class Cellule implements Cell<State, SquareGridNbh> {
	
	/**
	 * L'etat actuel de la cellule courante.
	 */
	private State actuel;
	
	/**
	 * L'etat de la cellule pour la generation suivante.
	 */
	private State tourSuivant;
	
	/**
	 * HashMap contenant les voisins de la cellule associee.
	 */
	private HashMap<SquareGridNbh, Cellule> voisins = new HashMap<SquareGridNbh, Cellule>();

	/**
	 * Constructeur de la classe Cellule creant une instance de Cellule ayant comme etat pour la prochaine generation mort.
	 * 
	 * @param state
	 * 			 Etat actuel de la cellule cree.
	 */
	public Cellule(State state) {
		actuel = state;
		tourSuivant = LifeState.DEAD;
	}

	/**
	 * Accesseur permettant d'acceder à l'etat de la cellule pour la generation suivante.
	 * 
	 * @return L'etat de la cellule lors de la prochaine generation.
	 */
	@Override
	public State getStateTourSuivant() {
		return tourSuivant;
	}

	/**
	 * Mutateur mettant a jour l'etat "tourSuivant" de la cellule  courante.
	 * 
	 * @see tourSuivant
	 * 
	 * @param state
	 * 		 	 Le nouvel etat de la cellule courante pour la generation suivante.
	 */
	@Override
	public void setStateTourSuivant(State state) {
		tourSuivant = state;
	}

	/**
	 * Accesseur permettant d'acceder à l'etat de la cellule pour la generation presente.
	 * 
	 * @return 
	 * 		L'etat present de la cellule courante.
	 */
	@Override
	public State getState() {
		return actuel;
	}

	/**
	 * Mutateur mettant a jour l'etat "actuel" de la cellule courante.
	 * 
	 * @see actuel
	 * 
	 * @param state	
	 * 		     Le nouvel etat de la cellule courante pour la generation actuelle.
	 */
	@Override
	public void setState(State state) {
		actuel = state;
	}

	/**
	 * Methode mettant a jour l'etat de la cellule courante par rapport a l'etat de ces voisins.
	 * La methode parcourt la liste des voisins de la cellule courante et definie ainsi le nombre
	 * de voisins dont l'etat est vivant. Ensuite elle applique la ou les regle(s) correspondante(s).
	 * 
	 * @return Le nouvel etat de la cellule courante.
	 */
	@Override
	public State nextState() {
		int voisinVivant = 0;
		int voisinNaissant = 0;
		for (Entry<SquareGridNbh, Cellule> entry : voisins.entrySet()) {
			if (entry.getValue().getState() == LifeState.ALIVE) {
				voisinVivant++;
			}
			if (entry.getValue().getState() == LifeState.DAWNING) {
				voisinNaissant++;
			}
		}
		switch (Regle.getRegle()) {
		case 1:
			return new Conway(this).regle(voisinVivant+voisinNaissant);
		case 2:
			return new DayAndNight(this).regle(voisinVivant+voisinNaissant);
		case 3:
			return new HighLife(this).regle(voisinVivant+voisinNaissant);
		case 4:
			return new Immigration(this).regle(voisinVivant, voisinNaissant);
		default:
			return null;
		}
	}

	/**
	 * Mutateur mettant a jour la liste des voisins de la cellule courante.
	 * Si la liste des voisins ne contient pas de reference vers une cellue associee a une cle representant
	 * la direction passee en parametre, alors on l'ajoute dans l'hashMap.
	 * 
	 * @see voisins
	 * 
	 * @param direction
	 * 				 Direction de la cellule voisine passee en parametre.
	 * 
	 * @param c
	 * 		  reference vers la cellule voisine.
	 */
	@Override
	public void setVoisin(SquareGridNbh direction, Cell<State, SquareGridNbh> c) {
		if (!(voisins.containsKey(direction))) {
			voisins.put(direction, (Cellule) c);
		}
	}

	/**
	 * Accesseur permettant d'acceder à la reference vers la cellule voisine.
	 * La position de cette cellule est definie par la direction de celle_ci
	 * par rapport a la cellule courante.
	 * 
	 * @param direction 
	 * 				 Direction de la cellule voisine par rapport a la cellule courante.
	 * 
	 * @return Une reference vers la cellule voisine.
	 */
	@Override
	public Cell<State, SquareGridNbh> getNeighbor(SquareGridNbh direction) {
		if (voisins.containsKey(direction)) {
			return voisins.get(direction);
		}
		return null;
	}

}