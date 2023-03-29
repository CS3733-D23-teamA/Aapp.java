package edu.wpi.teamA;

import java.util.ArrayList;

public class SearchController {
    ArrayList<RoomNode> nodeList;
    ArrayList<Edge> edgeList;

    public ArrayList pathOfNodes(RoomNode startNode, RoomNode endNode) {
        ArrayList<RoomNode> queue = new ArrayList<RoomNode>();
        ArrayList<RoomNode> path = new ArrayList<RoomNode>();
        ArrayList<RoomNode> visited = new ArrayList<RoomNode>();

        RoomNode currentNode = startNode;
        visited.add(currentNode);

        while (!currentNode.nodeID.equals(endNode.nodeID)) {

            for (int i = 0; i < currentNode.edgeCount(); i++) {

                Edge currentEdge = currentNode.getEdge(i);
                RoomNode otherNode;

                if (currentEdge.startNode.nodeID.equals(currentNode.nodeID)) {
                    otherNode = endNode;
                } else {
                    otherNode = startNode;
                }

                boolean otherVisited = false;
                for (int j = 0; j < visited.size(); j++) {
                    if (otherNode.nodeID.equals(visited.get(j).nodeID)) {
                        otherVisited = true;
                    }
                }

                if (!otherVisited) {
                    queue.add(otherNode);
                }

            }

            visited.add(currentNode);
            currentNode = queue.remove(0);

        }

        return
    }
}
