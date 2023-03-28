package edu.wpi.teamA.database;

public class l1nodes {
  String nodeID;
  int xcoord;
  int ycoord;
  String floor;
  String building;
  String nodeType;
  String longName;
  String shortName;

  public static void DisplayNode(l1nodes Node) {
    System.out.println(
        "ID: "
            + Node.nodeID
            + "\t"
            + "xcoord: "
            + Node.xcoord
            + "\t"
            + "ycoord: "
            + Node.ycoord
            + "\n "
            + "floor: "
            + Node.floor
            + "\t"
            + "building: "
            + Node.building
            + "\t"
            + "nodeType: "
            + Node.nodeType
            + "\t"
            + "longName: "
            + Node.longName
            + "\t"
            + "shortName: "
            + Node.shortName
            + "\n"
            + "--------------------------------------------------------------");
  }

  public l1nodes(
      String nodeID,
      int xcoord,
      int ycoord,
      String floor,
      String building,
      String nodeType,
      String longName,
      String shortName) {
    this.nodeID = nodeID;
    this.xcoord = xcoord;
    this.ycoord = ycoord;
    this.floor = floor;
    this.building = building;
    this.nodeType = nodeType;
    this.longName = longName;
    this.shortName = shortName;
  }
}
