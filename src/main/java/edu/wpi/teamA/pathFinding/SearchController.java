package edu.wpi.teamA.pathFinding;

import edu.wpi.teamA.database.l1edges;
import edu.wpi.teamA.database.l1nodes;
import java.util.ArrayList;

public class SearchController {
  public class Wrapping {
    l1nodes currentNode;
    Wrapping previousPath;
    boolean noPrevPath;

    public Wrapping(l1nodes currentNode, Wrapping previousPath) {
      this.currentNode = currentNode;
      this.previousPath = previousPath;
      noPrevPath = false;
    }

    public Wrapping(l1nodes currentNode) {
      this.currentNode = currentNode;
      noPrevPath = true;
    }

    public void addPath(Wrapping path) {
      this.previousPath = path;
      noPrevPath = false;
    }
  }

  ArrayList<l1nodes> nodeList;
  ArrayList<l1edges> edgeList;

  public ArrayList pathOfNodes(l1nodes startNode, l1nodes endNode) {
    ArrayList<l1nodes> queue = new ArrayList<l1nodes>();
    ArrayList<l1nodes> path = new ArrayList<l1nodes>();
    ArrayList<l1nodes> visited = new ArrayList<l1nodes>();
    ArrayList<Wrapping> listOfPaths = new ArrayList<Wrapping>();

    l1nodes currentNode = startNode;
    Wrapping currentPath = new Wrapping(currentNode);
    visited.add(currentNode);

    while (!currentNode.getNodeID().equals(endNode.getNodeID())) {

      for (int i = 0; i < currentNode.edgeCount(); i++) {

        l1edges currentEdge = currentNode.getEdge(i);
        l1nodes otherNode;

        if (currentEdge
            .getStartNode()
            .getNodeID()
            .equals(
                currentNode
                    .getNodeID())) { // check whether node is starting node or ending node in the
          // edge
          otherNode = currentEdge.getEndNode();
        } else {
          otherNode = currentEdge.getStartNode();
        }

        boolean otherVisited = false;
        for (int j = 0; j < visited.size(); j++) {
          if (otherNode
              .getNodeID()
              .equals(visited.get(j).getNodeID())) { // check if node has been visited
            otherVisited = true;
          }
        }

        if (!otherVisited) { // if not visited, add to queue and add to wrapping queue
          queue.add(otherNode);
          Wrapping newPath = new Wrapping(otherNode, currentPath);
          listOfPaths.add(newPath);
        }
      }

      visited.add(currentNode);
      currentNode = queue.remove(0);
      currentPath = listOfPaths.remove(0);
    }

    while (currentPath.noPrevPath == false) { // while the path still has a previous path
      path.add(0, currentPath.currentNode);
      currentPath = currentPath.previousPath;
    }
    path.add(0, startNode);

    return path;
  }
}
