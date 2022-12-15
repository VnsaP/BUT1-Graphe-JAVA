package graphes;

	import java.util.Iterator;

	public interface IGraph {
	    /**
	     * Retourne le nombre de sommets d'un graphe
	     * @return le nombre de sommets
	     */
	    public abstract int getNbSommets();

	    /**
	     * Permet l'ajout d'un arc valuer dans un graphe,
	     * en donnant le sommet source, sa destination
	     * et sa valuation
	     * @param src sommet source
	     * @param dest sommet de destination
	     * @param val valeur de la valuation
	     */
	    public abstract void ajouterArc(String src, String dest, int val); // 2,3,5

	    /**
	     * Permet de connaitre le nombre de sommet
	     * sortant d'un graphe
	     * @param sommet le sommet
	     * @return le nombre de sommet sotant
	     */
	    public abstract int dOut(String sommet);

	    /**
	     * Permet de connaitre le nombre de sommet entrant
	     * d'un graphe
	     * @param sommet le sommet
	     * @return le nombre de sommet sortant
	     */
	    public abstract int dIn(String sommet);

	    /**
	     * Permet de connaitre l'existance d'un arc en fonction
	     * du sommet source et destination donne
	     * @param src le sommet source
	     * @param dest le sommet de destination
	     * @return true si l'arc existe, false dans cas contraire
	     */
	    public abstract boolean aArc(String src, String dest);

	    /**
	     * Permet d'obtenire la valuation d'un arc, en fonction
	     * du sommet source et destination fournie
	     * @param src le sommet source
	     * @param dest le sommet de destination
	     * @return la valuation de l'arc
	     */
	    public abstract  int getValuation(String src, String dest);

	    /**
	     * Renvoir une la chaine de caractere representent le graphe
	     * @return une representation d'un graphe
	     */
	    public abstract String toString();

	    /**
	     * Permet de recuperer les sommets d'un graphe
	     * @return les sommets
	     */
	    public abstract Iterator<String> iterator();

		public abstract boolean aSommetSource(String dest);
		
	}
