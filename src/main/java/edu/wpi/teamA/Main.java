package edu.wpi.teamA;

import edu.wpi.teamA.database.l1edges;
import edu.wpi.teamA.database.l1nodes;
import edu.wpi.teamA.pathFinding.SearchController;
import java.util.ArrayList;

public class Main {
  public static void main(String[] args) {
    SearchController cont = new SearchController();

    l1nodes nodeA = new l1nodes("nodeA", 0, 0, "", "", "", "", "");
    l1nodes nodeB = new l1nodes("nodeB", 0, 0, "", "", "", "", "");
    l1nodes nodeC = new l1nodes("nodeC", 0, 0, "", "", "", "", "");
    l1nodes nodeD = new l1nodes("nodeD", 0, 0, "", "", "", "", "");
    l1nodes nodeE = new l1nodes("nodeE", 0, 0, "", "", "", "", "");

    l1edges edgeAB = new l1edges("edgeAB", nodeA, nodeB);
    l1edges edgeBC = new l1edges("edgeBC", nodeB, nodeC);
    l1edges edgeCD = new l1edges("edgeCD", nodeC, nodeD);
    l1edges edgeAE = new l1edges("edgeAE", nodeA, nodeE);
    // l1edges edgeAC = new l1edges("edgeAE", nodeA, nodeC);

    nodeA.addEdge(edgeAB);
    nodeB.addEdge(edgeAB);
    nodeB.addEdge(edgeBC);
    nodeC.addEdge(edgeBC);
    nodeA.addEdge(edgeAE);
    nodeE.addEdge(edgeAE);
    nodeC.addEdge(edgeCD);
    nodeD.addEdge(edgeCD);
    // nodeA.addEdge(edgeAC);
    // nodeC.addEdge(edgeAC);

    ArrayList<l1nodes> path = cont.pathOfNodes(nodeC, nodeA);
    System.out.println("Start: ");
    System.out.println(path.size());
    for (int i = 0; i < path.size(); i++) {
      System.out.println(path.get(i).getNodeID());
    }
  }
}
