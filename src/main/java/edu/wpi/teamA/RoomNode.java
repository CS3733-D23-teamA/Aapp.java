package edu.wpi.teamA;

import org.w3c.dom.Node;

import java.util.ArrayList;

public class RoomNode {
    String nodeID;
    private int xcoord;
    private int ycoord;
    private String floor;
    private String building;
    private String nodeType;
    private String longName;
    private String shortName;
    private ArrayList<Edge> edgeList;

    public RoomNode(String nodeID, int xcoord, int ycoord, String floor, String building,
                    String nodeType, String longName, String shortName) {
        this.nodeID = nodeID;
        this.xcoord = xcoord;
        this.ycoord = ycoord;
        this.floor = floor;
        this.building = building;
        this.nodeType = nodeType;
        this.longName = longName;
        this.shortName = shortName;

    }

    public int edgeCount() {
        edgeList.size();
    }

    public Edge getEdge(int index) {
        edgeList.get(index);
    }
}
