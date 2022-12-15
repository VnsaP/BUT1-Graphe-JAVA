package pcc.bellman;

import exeption.NoPathEx;
import pcc.IGraph;
import pcc.build.Chaine;
import pcc.build.SommetParent;

import java.util.*;

public class PCCBellman {
    private final HashMap<String, Integer> nomSommets;
    private final String source;
    private final ArrayList<SommetParent> sommetsParents;
    private final ArrayList<Chaine> chaines;
    private final IGraph graphe;
    private String pcc;

    public PCCBellman(IGraph graphe, String src){
        this.nomSommets = new HashMap<>();
        this.source = src;
        this.sommetsParents = new ArrayList<>();
        this.chaines = new ArrayList<>();
        this.graphe = graphe;
        this.pcc = "";

        initSommet();
        initChaine();
    }

    private void initSommet() {
        Iterator<String> it = this.graphe.iterator();
        int i = 0;
        while (it.hasNext()){
            this.nomSommets.put(it.next(), i);
            this.sommetsParents.add(new SommetParent());
            ++i;
        }
    }

    private void initChaine(){
        for (int i = 0; i < this.graphe.getNbSommets(); ++i){
            String sommet1 = getSommetFromValue(this.nomSommets, i);
            for (int j = 0; j < this.graphe.getNbSommets(); ++j) {
                if (this.graphe.aArc(getSommetFromValue(this.nomSommets, i),
                        getSommetFromValue(this.nomSommets, j))) {
                    String sommet2 = getSommetFromValue(this.nomSommets, j);
                    this.chaines.add(new Chaine(sommet1, sommet2,
                            this.graphe.getValuation(sommet1, sommet2)));
                }
            }
        }
    }

    /**
     * Permet d'optenir la clef de la hashmap en fonction de la valeur de
     * la clef
     * @param map l'hashmap
     * @param value la valeur qui sera utiliser
     *              pour la recherche de clef
     * @return la clef
     */
    private String getSommetFromValue(HashMap<String, Integer> map, Integer value) {
        for (Map.Entry<String, Integer> entry : map.entrySet())
            if (Objects.equals(value, entry.getValue()))
                return entry.getKey();
        return null;
    }

    public void calculateShortestPaths() {
        //initialisation du sommet de depart
        this.sommetsParents.get(this.nomSommets.get(this.source)).sommetDeDepart();
        for (int i = 0; i < graphe.getNbSommets(); ++i) {
            // verifie si un mise a jour a ete faite
            boolean aMaj = false;
            for (Chaine chaine : this.chaines) {
                SommetParent sp = this.sommetsParents.get(this.nomSommets.get(
                        chaine.getSource()));
                if (!sp.estInfinie())
                    if (chaine.estInferieur(sp.getCoutParent(),
                            this.sommetsParents.get(this.nomSommets.get(
                            chaine.getDestination())).getCoutParent())){
                        // cout pour aller du sommet a vers un sommet b
                        this.sommetsParents.get(this.nomSommets.get(
                                        chaine.getDestination())).
                                miseAJourSommet(chaine.
                                        calculeCoutDestination(sp), chaine
                                        .getSource());
                        aMaj = true;
                    }
            }
            if (!aMaj)
                break;
        }
    }

    private void essayPCC(String dest){
        if (!this.graphe.aSommetSource(dest)) {
            throw new NoPathEx();
        }
        else {
            recherchePCC(dest);
            if (this.pcc.equals(""))
                throw new NoPathEx();
            else
                this.pcc += dest;
        }
    }

    private void recherchePCC(String dest){
         String parent = this.sommetsParents.get(this.nomSommets.get(dest)).
                getSParent();
        if (parent == null)
            return;
        recherchePCC(parent);
        this.pcc += parent;
    }

    public String PCC(String dest){
        this.pcc = "";
        try {
            essayPCC(dest);
        } catch (NoPathEx n){
            return "There is no path for this vertex";
        }
        return this.pcc.toString();
    }

    /** a completer **/
    public boolean estOK(IGraph g){
        initSommet();
        return true;
    }
}