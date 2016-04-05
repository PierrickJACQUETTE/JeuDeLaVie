package vue;

import controleur.Jeu;
import javafx.application.Application;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.Spinner;
import javafx.scene.control.TitledPane;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.LifeState;

/**
 * Classe Vue etendant la classe Application. Cette classe gere toute la partie
 * graphique du programme.
 */
public class Vue extends Application {

	/**
	 * Reference vers l'instance courante de type Jeu.
	 */
	private Jeu jeu;

	/**
	 * Constante entiere representant la taille de base des labels.
	 */
	private final int DEFAULT_SIZE_LABEL = 14;

	/**
	 * Bordure de la fenetre graphique principale.
	 */
	private BorderPane base;

	/**
	 * La scene dans laquelle sera ajouter le root.
	 */
	private Scene scene;

	/**
	 * Bordure de la zone de dessin de la grille au sein de la fenetre gaphique
	 * principale.
	 */
	private BorderPane partieG;

	/**
	 * Zone de dessin de la partie gauche(zone ou la grille sera affichee).
	 */
	private Canvas gauche;

	/**
	 * Contexte graphique de la zone de dessin de la grille.
	 */
	private GraphicsContext gc;

	/**
	 * Boites contenant tous les elements lies soit a la creation, soit a
	 * l'importation soit les boutons cliquable au cours de la partie.
	 */
	private VBox droite, creerGroup, importerGroup;

	/**
	 * Titres des deux menus deroulants.
	 */
	private TitledPane creer, importer;

	/**
	 * Declaration des
	 */
	private Spinner<Integer> largeur, hauteur;

	/**
	 * Barre du choix du pourcentage de cellules vivantes a placer dans la
	 * grille.
	 */
	private Slider pourcentage;

	/**
	 * Les labels du programme.
	 */
	private Label countLabel, labelRegle, typeDeGrille, largeurText, hauteurText;

	/**
	 * Le texte informant l'utilisateur du nombre de generations passees depuis
	 * le debut de la partie.
	 */
	private Text generationText;

	/**
	 * Les groupes de radio boutons permettant de choisir la grille a importer,
	 * la topologie ou le type de jeu.
	 */
	private ToggleGroup laquelleImporter, regle, grille;

	/**
	 * Les radio boutons du programme.
	 */
	private RadioButton one, two, three, four, five, six, conway, day, high,immigration, hexagonal, rectangulaire;

	/**
	 * Les boutons du programme.
	 */
	private Button runButton, stopButton, randomizeButton, clearButton, unTourButton, recommencer;

	/**
	 * Constante entiere definissant la largeur de la fenetre principale du jeu
	 * de la vie.
	 */
	private final int lFenetre = 600;

	/**
	 * Constante entiere definissant la hauteur de la fenetre principale du jeu
	 * de la vie.
	 */
	private final int hFentre = 600;

	/**
	 * Constante entiere definissant la hauteur du texte du compteur de
	 * generations.
	 */
	private final int hGeneration = 30;

	/**
	 * Constante de type Color definissant la couleur asociee a l'etat vivant.
	 */
	private final Color alive = Color.BLACK;

	/**
	 * Constante de type Color definissant la couleur asociee a l'etat mort.
	 */
	private final Color dead = Color.BLANCHEDALMOND;

	/**
	 * Double definissante la largeur de la fenetre dans laquelle la grille est
	 * affichee.
	 */
	private double largeurGaucheDessiner;

	/**
	 * Double definissante la hauteur de la fenetre dans laquelle la grille est
	 * affichee.
	 */
	private double hauteurGaucheDessiner;

	/**
	 * Constante entiere definissant l'espace entre chaque rectangle
	 * representant les cellules.
	 */
	private final int espace = 3;

	/**
	 * Double representant la taille des cellules.
	 */
	private double tailleCase;

	/**
	 * Reference sur le service courant.
	 */
	private Service<Void> s;

	/**
	 * Methode initialisant la fenetre graphique principale du programme. Il y a
	 * initialisation des boutons et menus deroulants laissant un large choix a
	 * l'utilisateur ainsi que de la partie gauche affichant la grille.
	 * 
	 * @param primaryStage
	 *            Scene graphique principale.
	 */
	private void init(Stage primaryStage) {
		droite = new VBox();
		partieG = new BorderPane();
		gauche = new Canvas(lFenetre / 4 * 3, hFentre - hGeneration);

		generationText = new Text("Generation : " + jeu.getGeneration());
		partieG.setBottom(generationText);

		partieG.setCenter(gauche);
		droite.setStyle("-fx-border-color: goldenrod;");
		base = new BorderPane(null, null, droite, generationText, partieG);
		scene = new Scene(base, lFenetre, hFentre);
		droite.setMaxWidth(scene.getWidth() / 4);
		droite.setMinWidth(scene.getWidth() / 4);
		initGauche();
		initDroit();
		primaryStage.setResizable(false);
		primaryStage.setScene(scene);
	}

	/**
	 * Methode initialisant l'affichage de la partie gauche de la fenetre
	 * principale, c'est-a-dire la grille.
	 */
	private void initGauche() {
		generationText.setText("Generation : " + jeu.getGeneration());
		gc = gauche.getGraphicsContext2D();
		final int nbHaut = jeu.getGrilleJeu().getHauteur();
		final int nbLar = jeu.getGrilleJeu().getLargeur();
		double tailleL = gauche.getWidth() - 2 * espace;
		double tailleH = gauche.getHeight() - 2 * espace;
		double sizeUtileLar = tailleL - (espace * (nbLar - 1));
		double sizeCellLar = sizeUtileLar / nbLar;
		double sizeLar = (jeu.getTypeDeGrilleJeu() == 1) ? sizeCellLar : ((sizeUtileLar - (sizeCellLar / 2)) / nbLar);
		double sizeHaut = (tailleH - (espace * (nbHaut - 1))) / nbHaut;
		double sizes = (sizeHaut < sizeLar) ? sizeHaut : sizeLar;
		double posX = 2;
		double decalage = (jeu.getTypeDeGrilleJeu() == 1) ? 0 : (sizes / 2);
		for (int i = 0; i < nbLar; i++) {
			double posY = 2;
			for (int j = 0; j < nbHaut; j++) {
				Color c = (jeu.getGrilleJeu().getGrille()[j][i].getState() == LifeState.ALIVE) ? alive : dead;
				gc.setFill(c);
				if (j % 2 == 1) {
					gc.fillRect(posX + decalage, posY, sizes, sizes);
					posY += espace + sizes;
				} else {
					gc.fillRect(posX, posY, sizes, sizes);
					posY += espace + sizes;
				}
			}
			posX += espace + sizes;
			hauteurGaucheDessiner = posY;
		}
		largeurGaucheDessiner = posX;
		tailleCase = sizes;
	}

	/**
	 * Methode initialisant l'affichage de la partie droite de la fenetre
	 * principale, c'est-a-dire les menus.
	 */
	private void initDroit() {
		creer();
		importer();
		button();
	}

	/**
	 * Methode initialisant le menu de creation d'une grille.
	 */
	private void creer() {
		creerGroup = new VBox();
		creerGroup.setSpacing(5);
		creer = new TitledPane("Creer", creerGroup);
		creer.setExpanded(false);
		droite.getChildren().add(creer);

		taille();
		regle();
		grille();
		cell();
	}

	/**
	 * Methode definissant la taille des composants graphique inclus dans la
	 * fenetre principale.
	 */
	private void taille() {

		largeurText = new Label("Largeur :");
		largeurText.setUnderline(true);
		largeurText.setFont(Font.font(DEFAULT_SIZE_LABEL));
		largeur = new Spinner<Integer>(1, 1000, jeu.getGrilleJeu().getLargeur());
		largeur.setEditable(false);

		hauteurText = new Label("Hauteur :");
		hauteurText.setUnderline(true);
		hauteurText.setFont(Font.font(DEFAULT_SIZE_LABEL));
		hauteur = new Spinner<Integer>(1, 1000, jeu.getGrilleJeu().getHauteur());
		hauteur.setEditable(false);
		creerGroup.getChildren().add(largeurText);
		creerGroup.getChildren().add(largeur);
		creerGroup.getChildren().add(hauteurText);
		creerGroup.getChildren().add(hauteur);
	}

	/**
	 * Methode initialisant le menu du choix du jeu.
	 */
	private void regle() {
		labelRegle = new Label("Choix des regles :");
		labelRegle.setUnderline(true);
		labelRegle.setFont(Font.font(DEFAULT_SIZE_LABEL));
		regle = new ToggleGroup();
		conway = new RadioButton("Conway");
		conway.setContentDisplay(ContentDisplay.TEXT_ONLY);
		day = new RadioButton("Day And Night");
		high = new RadioButton("High Life");
		conway.setToggleGroup(regle);
		day.setToggleGroup(regle);
		high.setToggleGroup(regle);
		immigration = new RadioButton("Immigration");
		immigration.setToggleGroup(regle);
		conway.setSelected(true);
		jeu.setRegle(1);
		creerGroup.getChildren().add(labelRegle);
		creerGroup.getChildren().addAll(conway, day, high,immigration);
	}

	/**
	 * Methode initialisant le menu du choix de la topologie de la grille.
	 */
	private void grille() {
		grille = new ToggleGroup();
		typeDeGrille = new Label("Type de grille : ");
		typeDeGrille.setUnderline(true);
		typeDeGrille.setFont(Font.font(DEFAULT_SIZE_LABEL));
		rectangulaire = new RadioButton("Rectangulaire");
		rectangulaire.setToggleGroup(grille);
		rectangulaire.setSelected(true);
		jeu.setTypeDeGrilleJeu(1);
		hexagonal = new RadioButton("Hexagonal");
		hexagonal.setToggleGroup(grille);
		creerGroup.getChildren().add(typeDeGrille);
		creerGroup.getChildren().addAll(rectangulaire, hexagonal);
	}

	/**
	 * Methode initialisant le menu du choix du pourcentage de cellules vivantes
	 * lors de la premiere generation.
	 */
	private void cell() {
		countLabel = new Label("Combien de pourcentage de cellules vivantes :");
		countLabel.setUnderline(true);
		countLabel.setFont(Font.font(DEFAULT_SIZE_LABEL));
		countLabel.setWrapText(true);
		pourcentage = new Slider(0, 100, jeu.getPoucentage());
		creerGroup.getChildren().add(countLabel);
		creerGroup.getChildren().add(pourcentage);
	}

	/**
	 * Methode initialisant le menu du choix des grilles predefinies
	 * importables.
	 */
	private void importer() {
		importerGroup = new VBox();
		importer = new TitledPane("Importer", importerGroup);
		importer.setExpanded(false);
		droite.getChildren().add(importer);

		laquelleImporter = new ToggleGroup();
		one = new RadioButton("1");
		one.setToggleGroup(laquelleImporter);
		two = new RadioButton("2");
		two.setToggleGroup(laquelleImporter);
		three = new RadioButton("3");
		three.setToggleGroup(laquelleImporter);
		four = new RadioButton("4");
		four.setToggleGroup(laquelleImporter);
		five = new RadioButton("5");
		five.setToggleGroup(laquelleImporter);
		six = new RadioButton("6");
		six.setToggleGroup(laquelleImporter);
		six.setSelected(true);
		importerGroup.getChildren().addAll(one, two, three, four, five, six);
	}

	/**
	 * Methode initialisant les differents boutons cliquables au cours de la
	 * partie(run, un tour, stop, recommencer, effacer, aleatoire).
	 */
	private void button() {
		runButton = new Button("Run");
		runButton.setDisable(true);
		unTourButton = new Button("Un Tour");
		stopButton = new Button("Stop");
		randomizeButton = new Button("Aleatoire");
		randomizeButton.setDisable(true);
		clearButton = new Button("Effacer");
		droite.getChildren().add(runButton);
		droite.getChildren().add(stopButton);
		stopButton.setDisable(true);
		droite.getChildren().add(unTourButton);
		unTourButton.setDisable(true);
		recommencer = new Button("Recommencer");
		recommencer.setDisable(true);
		droite.getChildren().add(randomizeButton);
		droite.getChildren().add(clearButton);
		droite.getChildren().add(recommencer);
	}

	/**
	 * Methode gerant toutes les actions devant avoir lieu en fonction des
	 * evenements lies a la souris de l'utilisateur. Il y a desactivation de
	 * certains composants en fonction des boutons sur lesquels l'utilisateur a
	 * clique.
	 */
	private void action() {

		creer.setOnMouseClicked(new EventHandler<Event>() {
			@Override
			public void handle(Event e) {
				if (creer.isExpanded()) {
					randomizeButton.setDisable(false);
					importer.setExpanded(false);
					runButton.setDisable(false);
				} else {
					importer.setExpanded(true);
					randomizeButton.setDisable(true);
					runButton.setDisable(false);
				}
			}
		});

		importer.setOnMouseClicked(new EventHandler<Event>() {
			@Override
			public void handle(Event e) {
				if (importer.isExpanded()) {
					creer.setExpanded(false);
					randomizeButton.setDisable(true);
					runButton.setDisable(false);
				} else {
					creer.setExpanded(true);
					randomizeButton.setDisable(false);
					runButton.setDisable(false);
				}
			}
		});

		partieG.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				selectionneCell(event);
				runButton.setDisable(false);
			}
		});

		partieG.setOnMouseDragged(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				selectionneCell(event);
				runButton.setDisable(false);
			}
		});

		largeur.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				actionTaille();
			}
		});

		largeur.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				switch (event.getCode()) {
				case UP:
					actionTaille(largeur.getValue() + 1, hauteur.getValue());
					break;
				case DOWN:
					actionTaille(largeur.getValue() - 1, hauteur.getValue());
					break;
				default:
					break;
				}

			}
		});

		hauteur.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				actionTaille();
			}
		});

		hauteur.setOnKeyPressed(event -> {
			switch (event.getCode()) {
			case UP:
				actionTaille(largeur.getValue(), hauteur.getValue() + 1);
				break;
			case DOWN:
				actionTaille(largeur.getValue(), hauteur.getValue() - 1);
				break;
			default:
				break;
			}
		});

		conway.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				jeu.actionRegle("Conway");
				reInitialise();
				runButton.setDisable(false);
			}
		});

		day.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				jeu.actionRegle("Day");
				reInitialise();
				runButton.setDisable(false);
			}
		});

		high.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				jeu.actionRegle("High");
				reInitialise();
				runButton.setDisable(false);
			}
		});
		
		immigration.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				jeu.actionRegle("Immigration");
				reInitialise();
				runButton.setDisable(false);
			}
		});

		rectangulaire.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				actionGrille("Rectangulaire");
				runButton.setDisable(false);
			}
		});

		hexagonal.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				actionGrille("Hexagonal");
				runButton.setDisable(false);
			}
		});

		pourcentage.setOnMouseDragged(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				actionPourcentage();
			}
		});

		pourcentage.setOnKeyPressed(event -> {
			switch (event.getCode()) {
			case RIGHT:
				actionPourcentage(pourcentage.getValue() + 10);
				break;
			case LEFT:
				actionPourcentage(pourcentage.getValue() - 10);
				break;
			default:
				break;
			}
		});

		one.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				actionImporter(1);
				runButton.setDisable(false);
			}
		});

		two.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				actionImporter(2);
				runButton.setDisable(false);
			}
		});

		three.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				actionImporter(3);
				runButton.setDisable(false);
			}
		});

		four.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				actionImporter(4);
				runButton.setDisable(false);
			}
		});

		five.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				actionImporter(5);
				runButton.setDisable(false);
			}
		});

		six.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				actionImporter(6);
				runButton.setDisable(false);
			}
		});

		runButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				buttonNonVisible("run");
				actionRun();
			}

		});

		unTourButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				buttonNonVisible("oneTurn");
				actionUnTour();

			}
		});

		stopButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				buttonNonVisible("stop");
				actionStop();
			}
		});

		randomizeButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				jeu.actionAleatoire();
				reInitialise();
			}
		});

		clearButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				gc.clearRect(0, 0, gauche.getWidth(), gauche.getHeight());
				runButton.setDisable(true);
			}
		});

		recommencer.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				actionRecommencer();
			}
		});
	}

	/**
	 * Methode desactivant les boutons/menus en fonctions de ce qui a deja ete
	 * selectionne par l'utilisateur.
	 * 
	 * @param s
	 *            Chaine de caracteres representant la fonction a laquelle se
	 *            rapporte le bouton.
	 */
	private void buttonNonVisible(String s) {
		if (importer.isExpanded()) {
			creer.setDisable(true);
			importer.setDisable(true);
		}
		if (creer.isExpanded()) {
			importer.setDisable(true);
			creer.setDisable(true);
		}
		clearButton.setDisable(true);
		randomizeButton.setDisable(true);
		if (s.equals("run")) {
			stopButton.setDisable(false);
			runButton.setDisable(true);
			unTourButton.setDisable(true);
			recommencer.setDisable(true);
		}
		if (s.equals("stop")) {
			runButton.setDisable(false);
			stopButton.setDisable(true);
			unTourButton.setDisable(false);
			recommencer.setDisable(false);
		}
		if (s.equals("oneTurn")) {
			unTourButton.setDisable(false);
			stopButton.setDisable(true);
			runButton.setDisable(false);
			recommencer.setDisable(false);
		}
	}

	/**
	 * Methode gerant les evenements ayant lieu apres que l'utilisateur ai
	 * cliquer sur le bouton recommencer.
	 */
	private void actionRecommencer() {
		creer.setDisable(false);
		importer.setDisable(false);
		stopButton.setDisable(true);
		randomizeButton.setDisable(false);
		clearButton.setDisable(false);
		jeu.actionRecommencer();
		reInitialise();
	}

	/**
	 * Methode gerant les evenements ayant lieu apres que l'utilisateur ai
	 * cliquer sur le bouton stop. Le bouton stop permet de mettre en pause la
	 * partie.
	 */
	private void actionStop() {
		s.cancel();
	}

	/**
	 * Methode gerant les evenements ayant lieu apres que l'utilisateur ai
	 * cliquer sur le bouton un tour. Le bouton un tour permet de faire du tour
	 * par tour au cours de la partie.
	 */
	private void actionUnTour() {
		s = new Service<Void>() {
			@Override
			protected Task<Void> createTask() {
				return new Task<Void>() {
					@Override
					protected Void call() throws Exception {
						jeu.actionRun();
						jeu.getGrilleJeu().update();
						reInitialise();
						return null;
					}
				};
			}
		};
		s.start();
	}

	/**
	 * Methode gerant les evenements ayant lieu apres que l'utilisateur ai
	 * cliquer sur le bouton run. Le bouton run permet de lancer la partie.
	 */
	private void actionRun() {
		s = new Service<Void>() {
			@Override
			protected Task<Void> createTask() {
				return new Task<Void>() {
					@Override
					protected Void call() throws Exception {
						int tempsExecution = 0;
						int tempsAttente = 3000;
						while (jeu.getGrilleJeu().OneCellALone()) {
							long debut = System.currentTimeMillis();
							jeu.actionRun();
							jeu.getGrilleJeu().update();
							reInitialise();
							if (tempsAttente - tempsExecution < 0) {
								Thread.sleep(0);
							} else {
								Thread.sleep(tempsAttente - tempsExecution);
							}
							long fin = System.currentTimeMillis();
							tempsExecution = (int) (fin - debut);
						}
						recommencer.setDisable(false);
						stopButton.setDisable(true);
						return null;
					}
				};
			}
		};
		s.start();
	}

	/**
	 * Methode gerant les evenements ayant lieu apres que l'utilisateur ai
	 * cliquer sur le bouton importer.
	 * 
	 * @param laquelle
	 *            Entier identifiant la grille a importer.
	 */
	private void actionImporter(int laquelle) {
		jeu.actionImporter(laquelle);
		reInitialise();
	}

	/**
	 * Methode gerant les evenements ayant lieu apres que l'utilisateur ai
	 * choisi le pourcentage de cellules vivantes a la generation 1.
	 * 
	 * @param a
	 *            Double valeur de pourcentage.
	 */
	private void actionPourcentage(double a) {
		a = (a < 0) ? 0 : a;
		a = (a > 100) ? 100 : a;
		runButton.setDisable(false);
		jeu.actionPourcentage(a);
		reInitialise();
	}

	/**
	 * Methode gerant les evenements ayant lieu apres que l'utilisateur ai
	 * choisi le pourcentage de cellules vivantes a la generation 1.
	 */
	private void actionPourcentage() {
		actionPourcentage(pourcentage.getValue());
	}

	/**
	 * Methode gerant les evenements ayant lieu apres que l'utilisateur ai
	 * choisi la topologie de la grille.
	 * 
	 * @param s
	 *            Chaine de caracteres representant l'action a effectuer sur la
	 *            grille.
	 */
	private void actionGrille(String s) {
		jeu.actionGrille(s);
		reInitialise();
	}

	/**
	 * Methode gerant les evenements ayant lieu apres que l'utilisateur ai
	 * choisi les dimensions de la grille.
	 * 
	 * @param largeur
	 *            Entier representant la largeur de la grille.
	 * 
	 * @param hauteur
	 *            Entier representant la hauteur de la grille.
	 * 
	 */
	private void actionTaille(int largeur, int hauteur) {
		largeur = (largeur == 0) ? 1 : largeur;
		hauteur = (hauteur == 0) ? 1 : hauteur;
		runButton.setDisable(false);
		jeu.actionTaille(largeur, hauteur);
		reInitialise();
	}

	/**
	 * Methode gerant les evenements ayant lieu apres que l'utilisateur ai
	 * choisi les dimensions de la grille et fournissant a la seconde methode
	 * les dimensions.
	 */
	private void actionTaille() {
		actionTaille(largeur.getValue(), hauteur.getValue());
	}

	/**
	 * Methode reinitialisant la partie gauche(la grille) suite au choix de
	 * recommencer par l'utilisateur.
	 */
	public void reInitialise() {
		gc.clearRect(0, 0, gauche.getWidth(), gauche.getHeight());
		initGauche();
	}

	/**
	 * Methode permettant de savoir quelle cellule l'utilisateur vient de
	 * selectionner avec sa souris.
	 * 
	 * @param event
	 *            evenement lie a la souris de l'utilisateur.
	 */
	private void selectionneCell(MouseEvent event) {
		double xMouse = event.getSceneX();
		double yMouse = event.getSceneY();
		if (xMouse <= largeurGaucheDessiner && yMouse <= hauteurGaucheDessiner) {
			int tour = 0;
			for (double i = 2; i < largeurGaucheDessiner; i += tailleCase + espace) {
				int tourJ = 0;
				for (double j = 2; j < hauteurGaucheDessiner; j += tailleCase + espace) {
					if (tourJ % 2 == 1 && jeu.getTypeDeGrilleJeu() == 2) {
						if (i + tailleCase / 2 < xMouse && i + tailleCase + tailleCase / 2 > xMouse && j < yMouse
								&& j + tailleCase > yMouse) {
							testMouseCliqGrilleOk(tour, tourJ, i + tailleCase / 2, j);
						}
					} else if (i < xMouse && i + tailleCase > xMouse && j < yMouse && j + tailleCase > yMouse) {

						testMouseCliqGrilleOk(tour, tourJ, i, j);
					}
					tourJ++;
				}
				tour++;
			}
		}
	}

	/**
	 * Methode gerant l'affichage des cellules en fonction de leur etat.
	 * 
	 * @param i
	 *            Colonne a laquelle se situe la cellule courante.
	 * @param j
	 *            Ligne a lauelle se situe la cellule courante.
	 * @param iRectangle
	 *            Abscisse du bord gauche du rectangle representant la cellule
	 *            courante.
	 * @param jRectangle
	 *            Ordonnee du bord gauche du rectangle representant la cellule
	 *            courante.
	 */
	private void testMouseCliqGrilleOk(int i, int j, double iRectangle, double jRectangle) {
		LifeState c = (jeu.getGrilleJeu().getGrille()[j][i].getState() == LifeState.ALIVE) ? LifeState.DEAD
				: LifeState.ALIVE;
		jeu.setGrilleCaseJeu(c, j, i);
		Color cc = (jeu.getGrilleJeu().getGrille()[j][i].getState() == LifeState.ALIVE) ? alive : dead;
		gc.setFill(cc);
		gc.fillRect(iRectangle, jRectangle, tailleCase, tailleCase);
	}

	/**
	 * Methode initialisant la partie, la fenetre graphique principale et
	 * l'affichant.
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {
		jeu = new Jeu();
		init(primaryStage);
		primaryStage.setTitle("Jeu de la vie");
		primaryStage.show();
		action();
	}

	/**
	 * Methode appellant la methode launch de la classe Application qui lance le
	 * reste du programme.
	 * 
	 * @param args
	 *            Les parametres saisis lors du lancements du programme.
	 */
	public void mainVue(String[] args) {
		launch(args);
	}
}
