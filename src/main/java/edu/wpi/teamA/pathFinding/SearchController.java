package edu.wpi.teamA.pathFinding;

import edu.wpi.teamA.database.l1edges;
import edu.wpi.teamA.database.l1nodes;

import java.util.ArrayList;

public class SearchController {
    ArrayList<l1nodes> nodeList;
    ArrayList<l1edges> edgeList;

    public ArrayList pathOfNodes(l1nodes startNode, l1nodes endNode) {
        ArrayList<l1nodes> queue = new ArrayList<l1nodes>();
        ArrayList<l1nodes> path = new ArrayList<l1nodes>();
        ArrayList<l1nodes> visited = new ArrayList<l1nodes>();

        l1nodes currentNode = startNode;
        visited.add(currentNode);

        while (!currentNode.getNodeID().equals(endNode.getNodeID())) {

            for (int i = 0; i < currentNode.edgeCount(); i++) {

                l1edges currentEdge = currentNode.getEdge(i);
                l1nodes otherNode;

                if (currentEdge.getStartNode().getNodeID().equals(currentNode.getNodeID())) {
                    otherNode = endNode;
                } else {
                    otherNode = startNode;
                }

                boolean otherVisited = false;
                for (int j = 0; j < visited.size(); j++) {
                    if (otherNode.getNodeID().equals(visited.get(j).getNodeID())) {
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

        return path;
    }
}
