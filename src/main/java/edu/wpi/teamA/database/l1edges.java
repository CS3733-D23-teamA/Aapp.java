package edu.wpi.teamA.database;

public class l1edges {
  String edgeID;
  String startNode;
  String endNode;

  public static void DisplayEdge(l1edges Edge) {
    System.out.println(
        "ID: "
            + Edge.edgeID
            + "\t"
            + "startNode: "
            + Edge.startNode
            + "\t"
            + "endNode: "
            + Edge.endNode
            + "\n"
            + "----------------------------------------------");
  }

  public l1edges(String edgeID, String startNode, String endNode) {
    this.edgeID = edgeID;
    this.startNode = startNode;
    this.endNode = endNode;
  }
}
