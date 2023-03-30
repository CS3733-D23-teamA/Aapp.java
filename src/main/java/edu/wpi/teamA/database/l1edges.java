package edu.wpi.teamA.database;

public class l1edges {
  String edgeID;
  l1nodes startNode;
  l1nodes endNode;

  public l1nodes getStartNode() {
    return startNode;
  }

  public l1nodes getEndNode() {
    return endNode;
  }

  public static void DisplayEdge(l1edges Edge) {
    System.out.println(
        "ID: "
            + Edge.edgeID
            + "\t"
            + "startNode: "
            + Edge.startNode.longName
            + "\t"
            + "endNode: "
            + Edge.endNode.longName
            + "\n"
            + "----------------------------------------------");
  }

  public l1edges(String edgeID, l1nodes startNode, l1nodes endNode) {
    this.edgeID = edgeID;
    this.startNode = startNode;
    this.endNode = endNode;
  }
}
