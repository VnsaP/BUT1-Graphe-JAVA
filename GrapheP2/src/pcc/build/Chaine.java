package pcc.build;

public class Chaine {

    private final String source, destination;
    private final int valuation;

    public Chaine(String src, String dest, int val){
        this.source = src;
        this.destination = dest;
        this.valuation = val;
    }

    public boolean estInferieur(int coutParent, int coutDestination){
        return this.valuation + coutParent < coutDestination;
    }
    public int calculeCoutDestination(SommetParent sp){
        return this.valuation + sp.getCoutParent();
    }
    public String getSource() {
        return this.source;
    }

    public String getDestination(){
        return this.destination;
    }

    @Override
    public String toString(){
        return this.source + " - " + this.destination + " :" + this.valuation;
    }
}