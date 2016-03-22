JAVAC=javac
CONSOLE = console/src/
GRAPHIQUE = graphique/src/
CONTROLEUR = controleur/
MODELE = model/
VUE = vue/
JAVA = *.java
CLASS = *.class

all :
	$(JAVAC) $(GRAPHIQUE)$(CONTROLEUR)$(JAVA) $(GRAPHIQUE)$(MODELE)$(JAVA) $(GRAPHIQUE)$(VUE)$(JAVA)
	$(JAVAC) $(CONSOLE)$(CONTROLEUR)$(JAVA) $(CONSOLE)$(MODELE)$(JAVA)

clean :
	rm -f $(GRAPHIQUE)$(CONTROLEUR)$(CLASS) $(GRAPHIQUE)$(MODELE)$(CLASS) $(GRAPHIQUE)$(VUE)$(CLASS)
	rm -f $(CONSOLE)$(CONTROLEUR)$(CLASS) $(CONSOLE)$(MODELE)$(CLASS)

%.class : %.java
	$(JAVAC) $<
