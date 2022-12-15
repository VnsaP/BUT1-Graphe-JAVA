package pcc.build;


public class SommetParent {
    private String SParent; // sommet parent
    private int coutParent; // cout pour venir jusqu'au parent

    public SommetParent(){
        SParent = null;
        coutParent = Integer.MAX_VALUE;
    }

    private boolean estSommetDeDepart(){
        return this.coutParent == 0;
    }

    public int getCoutParent(){
        return this.coutParent;
    }

    public String getSParent(){
        return this.SParent;
    }

    public boolean aSommetParent(){
        return this.SParent != null;
    }

    public void miseAJourSommet(int cp, String sp){
        this.coutParent = cp;
        this.SParent = sp;
    }
    public boolean estInfinie(){
        return this.coutParent == Integer.MAX_VALUE;
    }

    public void sommetDeDepart(){
        assert(!estSommetDeDepart());
        this.coutParent = 0;
    }
}