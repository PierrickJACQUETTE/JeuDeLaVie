package model;

/**
 * Enumeration implementant l'interface State, contenant les deux etats du jeu de Conway (Mort et Vivant).
 * 
 * @see State
 */
public enum LifeState implements State {
	DEAD {
		public char toChar() {
			return '.';
		}
	},
	ALIVE {
		public char toChar() {
			return 'O';
		}
	},
	DAWNING {
		public char toChar() {
			return 'o';
		}
	}
}