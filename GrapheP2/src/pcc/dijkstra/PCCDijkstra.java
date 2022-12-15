package pcc.dijkstra;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import pcc.IGraph;
import pcc.build.Chaine;
import pcc.build.SommetParent;

public class PCCDijkstra {
	private final String sommetsrc;
    private final HashMap<String, Integer> sommetsGraphe;
    private List<Chaine> chaines;
    
	//M la liste des sommets "marqués" (i.e. traités par l'algorithme).
	private List<String> sommetsMarques;
	
	//P(x) la liste constituée du ou des prédécesseur(s) du sommet x.
	private List<SommetParent> sommetsParents;
	
	public PCCDijkstra(IGraph iG, String src) {
		this.sommetsrc=src;
		
		this.sommetsGraphe=new HashMap<>();
		initSommet(iG);

        this.chaines = new ArrayList<>();
        initChaine(iG);
		
		this.sommetsMarques = new ArrayList<>();
		this.sommetsMarques.add(this.sommetsrc);		//M = {sommetsrc}
		
		initSommetsMarques(iG);
		dijkstra(iG);
	}
	
	private void initChaine(IGraph iG){
        for (int i = 0; i < iG.getNbSommets(); ++i){
            String sommet1 = getSommetFromValue(this.sommetsGraphe, i);
            for (int j = 0; j < iG.getNbSommets(); ++j) {
                if (iG.aArc(getSommetFromValue(this.sommetsGraphe, i),
                        getSommetFromValue(this.sommetsGraphe, j))) {
                    String sommet2 = getSommetFromValue(this.sommetsGraphe, j);
                    this.chaines.add(new Chaine(sommet1, sommet2, iG.getValuation(sommet1, sommet2)));
                }
            }
        }
    }
	
	private void initSommet(IGraph iG) {
        Iterator<String> it = iG.iterator();
        int i = 0;
        while (it.hasNext()){
            this.sommetsGraphe.put(it.next(), i);
            this.sommetsParents.add(new SommetParent());
            ++i;
        }
    }
	
	private void initSommetsMarques(IGraph iG) {
		for(int i=0; i<iG.getNbSommets(); i++) {
			if (iG.aArc(this.sommetsrc,
					getSommetFromValue(this.sommetsGraphe, i))){
				if(this.sommetsrc == getSommetFromValue(this.sommetsGraphe, i))
					//d(sommetsrc)=0; P(sommetsrc) = null
					this.sommetsParents.add(new SommetParent());
				else {
					//d(x) = getValue(sommetsrc, x); P(x) = sommetsrc
					this.sommetsParents.get(i).miseAJourSommet(iG.getValuation(sommetsrc,
							getSommetFromValue(this.sommetsGraphe, i)),this.sommetsrc);
				}
			}
		}
	}
	
	private void dijkstra (IGraph iG) {
		List<String> sommets = new ArrayList<>();
		for (int i = 0; i < sommetsParents.size(); ++i) {
			sommets.add(getSommetFromValue(this.sommetsGraphe,i));
		}
		for (int i = 0; sommetsMarques.size() != sommets.size(); ++i) {
			int coutMin = findMin();
			if (sommetsParents.get(i).getCoutParent() == coutMin)
				sommetsMarques.add(sommets.get(i));
			for (int j = 0; j < sommetsParents.size(); ++j) {
				if(!sommetsMarques.contains(sommetsParents.get(j)) && iG.aArc(this.sommetsMarques.get(i),
							getSommetFromValue(this.sommetsGraphe, j))){
					
				}
			}
		}
	}
	
	public void calculateShortestPaths(IGraph iG) {
        //initialisation du sommet de depart
        this.sommetsParents.get(this.sommetsGraphe.get(this.sommetsrc)).sommetDeDepart();
        for (int i = 0; i < iG.getNbSommets(); ++i) {
            // verifie si un mise a jour a ete faite
            boolean aMaj = false;
            for (Chaine chaine : this.chaines) {
                SommetParent sp = this.sommetsParents.get(this.sommetsGraphe.get(
                        chaine.getSource()));
                if (!sp.estInfinie()) {
                    if (chaine.estInferieur(sp.getCoutParent(),
                            this.sommetsParents.get(this.sommetsGraphe.get(
                            chaine.getDestination())).getCoutParent())){
                        // cout pour aller du sommet a vers un sommet b
                        this.sommetsParents.get(this.sommetsGraphe.get(
                                        chaine.getDestination())).
                                miseAJourSommet(chaine.
                                        calculeCoutDestination(sp), chaine
                                        .getSource());
                    }
            	}
			}
            if (!aMaj)
                break;
        }
    }

	private int findMin() {
		int poidsMin = this.sommetsParents.get(0).getCoutParent();
		for (int i = 1; i < sommetsParents.size(); ++i) {
			if(poidsMin > this.sommetsParents.get(i).getCoutParent())
				poidsMin = this.sommetsParents.get(i).getCoutParent();
		}
		return poidsMin;
	}

	public static String getSommetFromValue(HashMap<String, Integer> map,
											Integer value) {
        for (Map.Entry<String, Integer> entry : map.entrySet())
            if (Objects.equals(value, entry.getValue()))
                return entry.getKey();
        return null;
    }
}