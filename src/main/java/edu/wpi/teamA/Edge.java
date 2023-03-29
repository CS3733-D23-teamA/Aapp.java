package edu.wpi.teamA;

public class Edge {
    String edgeID;
    RoomNode startNode;
    RoomNode endNode;
    public Edge(String edgeID, RoomNode startNode, RoomNode endNode) {
        this.edgeID = edgeID;
        this.startNode = startNode;
        this.endNode = endNode;
    }
}
