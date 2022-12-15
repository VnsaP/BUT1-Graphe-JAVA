package application;

import graphes.GrapheLA;
import pcc.dijkstra.PCCDijkstra;


public class Appli {
    public static void main(String[] args) {
        String [] s = {"a", "b", "c", "d"};
        GrapheLA tla = new GrapheLA(s);
        tla.ajouterArc("d", "a", -9);
        tla.ajouterArc("a", "d", 5);
        tla.ajouterArc("b", "c", 10);
        tla.ajouterArc("a", "a", 8);
        tla.ajouterArc("b", "a", -1);
        tla.ajouterArc("c", "a", -8);
        tla.ajouterArc("c", "c", 1);
        tla.ajouterArc("c", "d", 65);
        tla.ajouterArc("c", "l", 65);

    //    System.out.print(tla);
        
        PCCDijkstra d = new PCCDijkstra(tla,"a");
        
        System.out.println(d.toString());
    }
}
