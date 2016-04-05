package model;

import javax.swing.Icon;
import javax.swing.ImageIcon;

/**
 * Interface representant le patron du type Etat definissant l'etat de la cellule courante.
 * 
 * @see LifeState
 */
public interface State {
	/**
	 * Icone vide par defaut.
	 */
	Icon DEFAULT_ICON = new ImageIcon();

	/**
	 * Methode par defaut representant l'etat mort pour la cellule courante.
	 * 
	 * @return Caractere representant l'etat DEAD.
	 */
	default char toChar() {
		return '.';
	}

	/**
	 * Methode par defaut renvoyant la representation de l'etat par defaut d'une cellule. 
	 * 
	 * @return Icone representant l'etat par defaut.
	 */
	default Icon toIcon() {
		return DEFAULT_ICON;
	}
}